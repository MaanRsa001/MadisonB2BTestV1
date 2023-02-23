package com.maan.adminnew.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.adminnew.paymentProcess.PaymentProcessService;
import com.maan.adminnew.portfolio.PortfolioBean;
import com.maan.adminnew.portfolio.PortfolioService;
import com.maan.common.DBConstants;
import com.maan.common.ExcelDownload;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.dao.CommonDAO;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.payment.PaymentBean;
import com.maan.payment.PaymentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MotorPaymentAction extends ActionSupport implements ModelDriven<MotorPaymentBean> {
	private static final long serialVersionUID = 1L;
	private MotorPaymentBean bean=new MotorPaymentBean();
	MotorPaymentService service=new MotorPaymentService();
	CommonService cservice=new CommonService();
	CommonDAO commonDAO=new CommonDAO();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String productId = (String) session.get("product_id");
	String loginId=(String)session.get("user"); 
	String userType = (String) session.get("usertype");
	Validation validation=new Validation();
	String freightStatus=(String)session.get("freightStatus")==null?"":(String)session.get("freightStatus");
	//String freightStatus=request.getParameter("freightStatus")==null?"":request.getParameter("freightStatus");

	private String downloadFileName;
	private InputStream inputStream;
	
	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public List<Object> getBankNamelist(){
		return service.getBankNameList(bean);
	}
	public List<Map<String, Object>> getCreditPaymentList(){
		return  service.getCreditPayment(bean);
	}
	
	public List<Map<String, Object>> getNoInstallmentList(){
		return  service.getNoInstallment(bean.getQuoteNo());
	}
	
	public List<Map<String, Object>> getResDecisionList(){
		return  service.getResCodeDecision(bean);
	}

	public String open()
	{
		bean.setMode("");
		/*bean.setMotorProductList(service.getMotorProduct(bean));*/
		bean.setMotorPaymentTypeList(service.getMotorPaymentType(bean));
		return "motorPayment";
	}
	
	public String getMotor(){
		validateDate();
		if(!hasActionErrors()){
			bean.setMotorPaymentList(service.getMotorPayment(bean));
		}else{
			bean.setMode("");
			
		}
		/*bean.setMotorProductList(service.getMotorProduct(bean));*/
		bean.setMotorPaymentTypeList(service.getMotorPaymentType(bean));
		return "motorPayment";
		
	}
	
	public String getMotorView(){
		bean.setMode("viewlist");
		if("user".equalsIgnoreCase(userType)){
			if(StringUtils.isEmpty(bean.getBranchCode())){
				bean.setBranchCode("01");
				}
		}
		service.viewPayment(bean);
		if("user".equalsIgnoreCase(userType)){
			return "motorPaymentUser";
		}else{
			return "motorPayment";
		}
		
	}
	
	private void validateDate(){
		if("search".equalsIgnoreCase(bean.getReqFrom())){
			if (StringUtils.isEmpty(bean.getSearchType())){
				addActionError(getText("Choose Serach By"));
			}if (StringUtils.isEmpty(bean.getSearchValue())){
				addActionError(getText("Enter Data For Search"));
				}
		}else{
			long date = diffInDays(bean.getEndDate(),bean.getStartDate());
			if (StringUtils.isEmpty(bean.getStartDate())){
				addActionError(getText("error.startDate"));
			}else if (StringUtils.isEmpty(bean.getEndDate())){
				addActionError(getText("error.endDate"));
			}else if (date > 0){
				 addActionError(getText("error.diff.date"));
			 }
			/*if(StringUtils.isEmpty(bean.getProductId())){
				addActionError(getText("error.product"));
			}*/
		}
	}

	public MotorPaymentBean getModel() {
		if(StringUtils.isEmpty(bean.getProductId())){
			bean.setProductId(productId);
			}
			if(StringUtils.isEmpty(bean.getBranchCode())){
			bean.setBranchCode(branchCode);
			}
			if(StringUtils.isEmpty(bean.getLoginId())){
			bean.setLoginId(loginId);
			}
		return bean;
	}
	
	public long diffInDays(String startDate,String endDate){
		long result=0;
		if(!StringUtils.isEmpty(startDate)&& !StringUtils.isEmpty(endDate)){
		try{
			Date date = new Date();
	        Calendar cal1 = Calendar.getInstance();
	        Calendar cal2 = Calendar.getInstance();
	        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
	        cal1.setTime(sfd.parse(startDate));
	        if(StringUtils.isBlank(endDate))
	        	cal2.setTime(sfd.parse(sfd.format(date)));
	        else
	        cal2.setTime(sfd.parse(endDate));
	        long milis1 = cal1.getTimeInMillis();
	        long milis2 = cal2.getTimeInMillis();
	        long diff = milis2 - milis1;
	        result = diff / (24 * 60 * 60 * 1000);
		}catch (Exception e) {
			LogManager.debug("Exception Occured @ "+e);
			e.printStackTrace();
		}
		}
		
		return result;
	}
	/*public String dateFormat(String name){
		String arr[]=name.split("/");
		 return arr[1]+"/"+arr[0]+"/"+arr[2];
	}*/
	
	public String ccpUpdate(){
		validatePayment();
		if(!hasActionErrors()){
			service.updateCCP(bean);
			bean.setMode("list");
			getMotor();
			if("y".equalsIgnoreCase(bean.getGenPolicyYN())){
			if("ACCEPT".equalsIgnoreCase(bean.getResDeci())) {
				CommonDAO commonDAO = new CommonDAO();
				if("Y".equalsIgnoreCase(commonDAO.getInstallmentStatus(bean.getQuoteNo()))){
					commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"IPS","Y");
				}
				commonDAO.homePolicyGeneration(bean.getQuoteNo(),productId,branchCode);
				/*if(DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					commonDAO.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference(),bean.getQuoteNo(),bean.getProductId(),bean.getBranchCode());
				}*/
				List<Map<String, Object>> res = commonDAO.getOnlineSurveyor();
				if(res !=null && res.size()>0){
					for(int i=0;i<res.size();i++){
						String SPLoginId=res.get(i).get("LOGIN_ID")==null?"":res.get(i).get("LOGIN_ID").toString();
						new PaymentProcessService().insPaymentProcessTrac(new CommonDAO().getHomePolicyNo(bean.getQuoteNo()),bean.getQuoteNo(),"ssPending","H","",SPLoginId,SPLoginId,productId,branchCode);
					}
				}
				new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_CUST,bean.getQuoteNo()).send();
				new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_SURVEYOR,bean.getQuoteNo()).send();
				commonDAO.updateHomeQuoteStatus(bean.getAppNo(),bean.getReqTranNo(),bean.getQuoteNo(),productId,branchCode);
			}
			new SmsEmailUtil(SmsEmailUtil.ONLINE_PAYMENT_STATUS_UNDERWRITER,bean.getQuoteNo()).send();
			new SmsEmailUtil(SmsEmailUtil.ONLINE_PAYMENT_STATUS_UNDERWRITER,bean.getQuoteNo()).send();
			addActionMessage(getText("update.success.policy"));
		} else{
			addActionMessage(getText("update.success"));
		}
		}else{
			getMotorView();
		}
		return "motorPayment";
	}
	
	private void validatePayment(){
		if(StringUtils.isEmpty(bean.getResCode()))
			addActionError(getText("error.resCode"));
		if(StringUtils.isEmpty(bean.getResTranNo()))
			addActionError(getText("error.resTranNo"));
		if(StringUtils.isEmpty(bean.getResTime()))
			addActionError(getText("error.resTime"));
		if(StringUtils.isEmpty(bean.getResDeci()))
			addActionError(getText("error.res.deci"));
		if(StringUtils.isEmpty(bean.getPayAmount()))
			addActionError(getText("error.res.payAmount"));
		if(StringUtils.isEmpty(bean.getCardDate()))
			addActionError(getText("error.card.date"));
		if("ACCEPT".equalsIgnoreCase(bean.getResDeci())){
			if(StringUtils.isEmpty(bean.getGenPolicyYN()))
				addActionError(getText("error.genPolicyYN"));
		}
	}
	
	private void validatePaymentInsert(){
		if("6".equalsIgnoreCase(bean.getPaymentMode())){
		if(StringUtils.isEmpty(bean.getResCode()))
			addActionError(getText("error.resCode"));
		if(StringUtils.isEmpty(bean.getResTranNo()))
			addActionError(getText("error.resTranNo"));
		if(StringUtils.isEmpty(bean.getResTime()))
			addActionError(getText("error.resTime"));
		if(StringUtils.isEmpty(bean.getResDeci()))
			addActionError(getText("error.res.deci"));
		if(StringUtils.isNotEmpty(bean.getPayAmount()) && Double.valueOf(bean.getPayAmount()) < 0)
			addActionError(getText("error.res.payAmount"));
		/*else if(Double.valueOf(bean.getPayAmount())>Double.valueOf(bean.getInsAmount()))
			addActionError(getText("error.payment.valid.cash.amount"));*/
		if(StringUtils.isEmpty(bean.getCardDate()))
			addActionError(getText("error.card.date"));
		}
		else if("1".equalsIgnoreCase(bean.getPaymentMode())){
		if(StringUtils.isEmpty(bean.getCashDepositBank()))
			addActionError(getText("error.payment.choose.bank"));
		if(StringUtils.isNotEmpty(bean.getCashAmount()) && Double.valueOf(bean.getCashAmount()) < 0)
			addActionError(getText("error.payment.enter.cashamount"));
		/*else if(Double.valueOf(bean.getCashAmount())>Double.valueOf(bean.getInsAmount()))
			addActionError(getText("error.payment.valid.cash.amount"));*/
		if(StringUtils.isEmpty(bean.getCashChellanNo()))
			addActionError(getText("Enter Challan No"));
		if(StringUtils.isEmpty(bean.getCashInstrumentDate()))
			addActionError(getText("error.payment.enter.deposit.date"));
		}else if("2".equalsIgnoreCase(bean.getPaymentMode())){
			if(StringUtils.isEmpty(bean.getBankName()))
				addActionError(getText("error.payment.choose.bank"));
			if(StringUtils.isNotEmpty(bean.getChequeAmount()) && Double.valueOf(bean.getChequeAmount()) < 0)
				addActionError(getText("error.payment.cheque.amount"));
			/*else if(Double.valueOf(bean.getChequeAmount())>Double.valueOf(bean.getInsAmount()))
				addActionError(getText("error.payment.cheque.amount.invalid"));*/
			if(StringUtils.isEmpty(bean.getChequeDate()))
				addActionError(getText("error.payment.enter.cheque.date"));
			if(StringUtils.isEmpty(bean.getChequeNo()))
				addActionError(getText("error.payment.enter.cheque.no"));
			if(StringUtils.isEmpty(bean.getMicr()))
				addActionError(getText("error.payment.micr.code"));
		}
	}
	public String ajaxGetResCode(){
		bean.setResCodeList(service.getResCodeDescription(bean));
		return "ajax";
		
	}
	
	//Installment Manual Update
	public String openIns()
	{
		bean.setMode("");
		return "installment";
	}
	public String getInstallment(){
		validateDate();
		if(!hasActionErrors()){
		bean.setMode("showlist");
		bean.setPaymentDueList(service.getPaymentDue(bean));
		}else{
			bean.setMode("");
			bean.setReqFrom1("");
		}
		return "installment";
		
	}
	public String getInstallmentView(){
		bean.setMode("viewlist");
		bean.setReqFrom1("");
		service.viewInstallment(bean);
		if("user".equalsIgnoreCase(userType)){
			return "installmentUser";
		}else{
			return "installment";
		}
		
	}
	
	public String getInsPaymentView(){
		bean.setMode("viewlist");
		service.viewInstallment(bean);
		if("viewPayment".equalsIgnoreCase(bean.getReqFrom1())){
			service.viewInstallmentPayment(bean);
		}
		if("user".equalsIgnoreCase(userType)){
			return "installmentUser";
		}else{
			return "installment";
		}
		
	}
	
	public String getInsPaymentInsert(){
		validatePaymentInsert();
		if(!hasActionErrors()){
			if("6".equalsIgnoreCase(bean.getPaymentMode())){
				bean.setPayAmount(Double.toString((Double.parseDouble(StringUtils.isBlank(bean.getInsAmount())?"0":bean.getInsAmount())
						+Double.parseDouble(StringUtils.isBlank(bean.getPayAmount())?"0":bean.getPayAmount()))));
				}
			else if("1".equalsIgnoreCase(bean.getPaymentMode())){
				bean.setCashAmount(Double.toString((Double.parseDouble(StringUtils.isBlank(bean.getInsAmount())?"0":bean.getInsAmount())
						+Double.parseDouble(StringUtils.isBlank(bean.getCashAmount())?"0":bean.getCashAmount()))));
			}else if("2".equalsIgnoreCase(bean.getPaymentMode())){
				bean.setChequeAmount(Double.toString((Double.parseDouble(StringUtils.isBlank(bean.getInsAmount())?"0":bean.getInsAmount())
						+Double.parseDouble(StringUtils.isBlank(bean.getChequeAmount())?"0":bean.getChequeAmount()))));
				}
			String status = service.insPaymentInsert(bean);
			if(StringUtils.isBlank(status)){
				service.updateInsPay(bean);
				addActionMessage("Installment Payment was Update Successfully");
				getInstallmentView();
			}else{
				addActionError(getText("Error In Update Process"));
				getInsPaymentView();
			}
		}else{
			getInsPaymentView();
		}
		return "installment";
		
	}

	
	// Payment Due Report
	public String paymentDue(){
		bean.setMode("");
		return "paymentdue";
	}
	
	public String getDueCount(){
		validateDate();
		if(!hasActionErrors()){
		bean.setMode("list");
		bean.setDueCountList(service.getDueCount(bean));
		}
		else{
			bean.setMode("");
		}
		return "paymentdue";
	}
	
	public String getPaymentDue(){
		validateDate();
		if(!hasActionErrors()){
		bean.setMode("showlist");
		bean.setPaymentDueList(service.getPaymentDue(bean));
		}else{
			bean.setMode("");
		}
		return "paymentdue";
	}
	
	
	public String getEmi(){
		bean.setMode("");
		if("RSAIssuer".equalsIgnoreCase(userType)){
			return "emipaymentOP";
		}else{
			return "emipayment";
		}
	}
	
	public String getEmiCount(){
		validateDate();
		if(!hasActionErrors()){
		bean.setMode("list");
		bean.setDueCountList(service.getDueCount(bean));
		}
		else{
			bean.setMode("");
		}
		if("RSAIssuer".equalsIgnoreCase(userType)){
			return "emipaymentOP";
		}else{
			return "emipayment";
		}
	}
	
	public String getEmiDetail(){
		validateDate();
		if(!hasActionErrors()){
		bean.setMode("showlist");
		bean.setPaymentDueList(service.getPaymentDue(bean));
		}else{
			bean.setMode("");
		}
		if("RSAIssuer".equalsIgnoreCase(userType)){
			return "emipaymentOP";
		}else{
			return "emipayment";
		}
	}
	
	public String getEmiView(){
		bean.setMode("viewlist");
		service.viewInstallment(bean);
		if("RSAIssuer".equalsIgnoreCase(userType)){
			return "emipaymentOP";
		}else{
			return "emipayment";
		}
		
	}
	
	public String reportDownload(){
		String res="motorPayment";
		try {
			List<Map<String, Object>> reportList = service.getMotorPayment(bean);
				downloadFileName="PaymentReport.xls";
			ExcelDownload fileWrite=new ExcelDownload();
			List<String> fileHeader = new ArrayList<String>();
			if(reportList!=null && reportList.size()>0){
				Map<String, Object> headerMap = reportList.get(0);
				int headerIndex = 0;
				for(Map.Entry<String, Object> entry : headerMap.entrySet()){
					fileHeader.add(headerIndex, entry.getKey()==null?"":entry.getKey().toString());
					headerIndex++;
				}
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				fileWrite.writeExcelNew(fileHeader, reportList, bos, "otherReport");
				inputStream=new ByteArrayInputStream(bos.toByteArray());
				res="stream";
			}else{
				addActionError("Something Went Wrong in Download Process");
				bean.setMode("list");
				bean.setMotorPaymentList(service.getMotorPayment(bean));
                bean.setMotorPaymentTypeList(service.getMotorPaymentType(bean));
				
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return res;
	}
	
}
