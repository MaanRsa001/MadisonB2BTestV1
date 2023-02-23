package com.maan.adminnew.paymentProcess;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.status.MotorPaymentDao;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.dao.CommonDAO;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.payment.PaymentDAO;
import com.maan.upload.service.UploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentProcessAction extends ActionSupport implements ModelDriven<PaymentProcessBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PaymentProcessService service=new PaymentProcessService();
	PaymentProcessBean bean = new PaymentProcessBean();

	public PaymentProcessBean getModel() {
		Map<String, Object> session=ActionContext.getContext().getSession();
		String branchCode=(String)session.get("LoginBranchCode");
		String productId=(String) session.get("product_id");
		String userType=(String) session.get("usertype");
		String brokerCode=(String) session.get("brokerCode");
		String loginId=(String)session.get("user");
		bean.setBranchCode(branchCode);
		bean.setProductId(productId);
		bean.setBrokerCode(brokerCode);
		bean.setLoginId(loginId);
		bean.setUserType(userType);
		return bean;
	}
	public List<Map<String,Object>> getCustomerInfo(){
		return new CustomerDAO().getCustomerInfo(bean.getQuoteNo());
	}
	public List<Object> getBankNamelist(){
		return  service.getBankNameList(bean);
	}
	public List<Object> getModeOfPaymentList() {
		return service.getModeOfPaymentList(bean);
	}
	public List<Map<String,Object>> getTracList() {
		return service.getTransactionTrackingList(bean);
	}
	public List<Map<String,Object>> getTransList() {
		return service.getTransactionList(bean);
	}
	public List<Map<String,Object>> getTravellerDetailsList() {
		return service.travellerDetailsList(bean);
	}
	public List<Map<String,Object>> getHomeInfo(){
		return service.homeInfo(bean);
	}
	public List<Map<String,Object>> getSubhome(){
		return service.subhomeInfo(bean);
	}
	public List<Map<String,Object>> getApproverLoginList(){
		return service.getApproverLoginList(bean.getReqForm(),bean.getProductId());
	}
	public List<Map<String,Object>> getNoInstallmentList(){
		MotorPaymentDao Dao=new MotorPaymentDao();
		return Dao.getNoInstallment(bean.getQuoteNo());
	}
	public String creditControl(){
		bean.setMode("");
		if("uwPending".equalsIgnoreCase(bean.getReqForm()) || "uwYStatus".equalsIgnoreCase(bean.getReqForm()) || "uwNStatus".equalsIgnoreCase(bean.getReqForm())){
			return "UWPaymentProcess";
		}	
		else{
			return "paymentprocess";
		}
	}
	public String view(){
		bean.setMode("viewPayment");
		/*service.getViewCC(bean);*/
		service.getPaymentDetails(bean);
		service.setPaymentProcessDetails(bean);
		if("ccPending".equalsIgnoreCase(bean.getReqForm())){
			if("7".equalsIgnoreCase(bean.getModeOfPayment()) && StringUtils.isEmpty(bean.getResDecision())){
				bean.setCheck("notshow");
			}
		}
		if("65".equalsIgnoreCase(bean.getProductId()))
			service.getPremiumInfo(bean);
			bean.setVehicleDetailList(service.getvehicleDetails(bean));
		if("uwPending".equalsIgnoreCase(bean.getReqForm()) || "uwYStatus".equalsIgnoreCase(bean.getReqForm()) || "uwNStatus".equalsIgnoreCase(bean.getReqForm())){
			return "UWPaymentProcess";
		}	
		else{
			return "paymentprocess";
		}
	}
	public String insertCC(){
		try{
			getValidate();
			if(!hasActionErrors()){
			service.insPaymentProcessTrac(bean.getPolicyNo(),bean.getQuoteNo(),bean.getReqForm(),bean.getStatus(),bean.getRemarks(),bean.getLoginId(),bean.getApplicapleLoginId(),bean.getProductId(),bean.getBranchCode());
			if("editPayment".equalsIgnoreCase(bean.getMode())){
				PaymentDAO dao=new PaymentDAO();
				dao.updatePaymentDetailsForCC(
					bean.getModeOfPayment(),bean.getChequeNo(), bean.getChequeDate(), bean.getChequeAmount(), 
					bean.getPremium(), bean.getBankName(),bean.getMicrCode(),bean.getCashDepositBank(),
					bean.getCashAmount(),bean.getCashChellanNo(),bean.getCashInstrumentDate(),
					bean.getApplicationNo(),bean.getQuoteNo(),bean.getProductId(),
					bean.getMerchant_reference(),bean.getEmail(),bean.getCustomerName(),bean.getBranchCode()
				);
				addActionMessage(getText("payment.success"));
			}
			addActionMessage(getText("insert.success"));
			bean.setMode("");
			//Sms sending
			CommonDAO commonDAO = new CommonDAO();
			if("ccPending".equalsIgnoreCase(bean.getReqForm())){
				if("Y".equalsIgnoreCase(bean.getStatus())) {
					if("Y".equalsIgnoreCase(commonDAO.getInstallmentStatus(bean.getQuoteNo()))){
						commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"IPS","Y");
					}
					new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_CUST,bean.getQuoteNo()).send();
					new SmsEmailUtil(SmsEmailUtil.PAYMENT_SUCESS_SURVEYOR,bean.getQuoteNo()).send();
				}
			}
			else if("ssPending".equalsIgnoreCase(bean.getReqForm())){
				if("Y".equalsIgnoreCase(bean.getStatus())){
					new SmsEmailUtil(SmsEmailUtil.SURVEYOR_ACCEPT,bean.getQuoteNo()).send();
					new SmsEmailUtil(SmsEmailUtil.SURVEYOR_ACCEPT_SURVEYOR,bean.getQuoteNo()).send();
					new SmsEmailUtil(SmsEmailUtil.SURVEYOR_ACCEPT_OPUSER,bean.getQuoteNo()).send();
				} else if("N".equalsIgnoreCase(bean.getStatus())){
					new SmsEmailUtil(SmsEmailUtil.SURVEYOR_REJECT,bean.getQuoteNo()).send();
					new SmsEmailUtil(SmsEmailUtil.SURVEYOR_REJECT_SURVEYOR,bean.getQuoteNo()).send();
					new SmsEmailUtil(SmsEmailUtil.SURVEYOR_REJECT_OPUSER,bean.getQuoteNo()).send();
				}
			}
			else if("uwPending".equalsIgnoreCase(bean.getReqForm())){
				if("Y".equalsIgnoreCase(bean.getStatus())){
					String pvOut="";
					try{
						Map <String ,Object> map=commonDAO.commonIntgProcess(bean.getQuoteNo(), bean.getBranchCode());
						pvOut=(String)map.get("PVOUT")==null?"":(String)map.get("PVOUT");
					}catch (Exception e) {
						e.printStackTrace();
						pvOut="Error";
					}
					
					if(StringUtils.isNotBlank(pvOut)){
						addActionError("Integration Process Error");
					}
					if("Y".equalsIgnoreCase(commonDAO.getInstallmentStatus(bean.getQuoteNo()))){
						commonDAO.updateInstallmentDetail(bean.getQuoteNo(),"ISS","Y");
					}
					
				}else if("H".equalsIgnoreCase(bean.getStatus())){
				}
				else{
				}
			}
			
			
			}else{
				if(!"editPayment".equalsIgnoreCase(bean.getMode())){
					service.getPaymentDetails(bean);
				}
				if("65".equalsIgnoreCase(bean.getProductId()))
					bean.setVehicleDetailList(service.getvehicleDetails(bean));
				bean.setMode("viewPayment");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		if("uwPending".equalsIgnoreCase(bean.getReqForm()) || "uwYStatus".equalsIgnoreCase(bean.getReqForm()) || "uwNStatus".equalsIgnoreCase(bean.getReqForm())){
			return "UWPaymentProcess";
		}	
		else{
			return "paymentprocess";
		}
	}
	public void getValidate()
	{
		if("editPayment".equalsIgnoreCase(bean.getMode())){
		LinkedList<String> list=service.getValidate(bean);
		for (String st : list) {
			if(st.indexOf("#")!=-1) {
				String args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
				this.addActionError(getText(st.substring(0,st.indexOf('#')),args));
			} else {
				this.addActionError(getText(st));	
			}
		}
		}
		if(StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.status"));
	}
	public List<Map<String,Object>> getCustAttachedDocs() {
		return service.getCustAttachedDocs(bean.getProductId(),bean.getQuoteNo(),bean.getReqForm());
	}
	public List<Map<String,Object>> getSurveyorAttachedDocs() {
		return new UploadService().getUploadDocList(bean.getProductId(),bean.getQuoteNo(),bean.getVehicleId(),"surveyor");
	}
	
	public String policyConvertion(){
		return "policyConvertion";
	}
	public String quoteDetail(){
		LogManager.info("Enter Into quoteDetail()");
		try {
			if(StringUtils.isNotBlank(bean.getQuoteNo())){
				if(StringUtils.isNumeric(bean.getQuoteNo())){
					bean.setMode("QuoteDtl");
					bean.setQuoteDetails(service.getQuoteDetails(bean));
				}else
					addActionError("Please Enter Valid Quote No");
			}else
				addActionError("Please Enter Quote No to Search");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "policyConvertion";
	}
	public String convert(){
		LogManager.info("Enter Into convert()");
		try {
			bean.setMode("QuoteDtl");
			bean.setQuoteDetails(service.getQuoteDetails(bean));
			if("6".equalsIgnoreCase(bean.getPaymentType())){
				if("ACCEPT".equalsIgnoreCase(bean.getResDecision())){
					addActionError("Policy No is Already Generated for this Quote");
				}else{
					int result=service.updatePaymentdetail(bean);
					if(result>0){
						CommonDAO daoNew=new CommonDAO();
						
						daoNew.homePolicyGeneration(bean.getQuoteNo(),bean.getProductId(),"01");
						
						if("Y".equalsIgnoreCase(bean.getInstallmentYN())){
							daoNew.updateInstallmentDetail(bean.getQuoteNo(),"IPS","Y");
						}
						
						if(DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							daoNew.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference(),bean.getQuoteNo(),bean.getProductId(),"01");
						}
						List<Map<String, Object>> res = daoNew.getOnlineSurveyor();
						if(res !=null && res.size()>0){
							for(int i=0;i<res.size();i++){
								String SPLoginId=res.get(i).get("LOGIN_ID")==null?"":res.get(i).get("LOGIN_ID").toString();
								service.insPaymentProcessTrac(new CommonDAO().getHomePolicyNo(bean.getQuoteNo()),bean.getQuoteNo(),"ssPending","H","",SPLoginId,SPLoginId,bean.getProductId(),bean.getBranchCode());
							}
							
						}
						
						String result1=daoNew.updateHomeQuoteStatus(bean.getApplicationNo(),bean.getMerchant_reference(),bean.getQuoteNo(),bean.getProductId(),bean.getBranchCode());
						
						if("SUCCESS".equalsIgnoreCase(result1)) {
							
							String paymentType=daoNew.getPaymentType(bean.getQuoteNo());
							if("6".equalsIgnoreCase(paymentType)){
								LogManager.info("CONV Enter into paymentMode 6");
								new PaymentProcessService().insPaymentProcessTrac(new CommonDAO().getHomePolicyNo(bean.getQuoteNo()),bean.getQuoteNo(),"uwPending","Y","","onlineUW","onlineUW",bean.getProductId(),bean.getBranchCode());
								LogManager.info("CONV policy Status Upadte Done as P");
								String pvOut="";
								try{
									LogManager.info("CONV Integration Calling starts..");
									Map <String ,Object> map=daoNew.commonIntgProcess(bean.getQuoteNo(), bean.getBranchCode());
									pvOut=(String)map.get("PVOUT")==null?"":(String)map.get("PVOUT");
									LogManager.info("CONV Integration Calling end..");
								}catch (Exception e) {
									e.printStackTrace();
									pvOut="Error in Staging Table Movement..";
								}
								
								if(StringUtils.isNotBlank(pvOut) && "Success".equalsIgnoreCase(pvOut)){
									//addActionMessage(pvOut);
									LogManager.info(pvOut);
								}
								else if(StringUtils.isNotBlank(pvOut) && !"Success".equalsIgnoreCase(pvOut)){
									addActionError("Staging Table "+pvOut);
									LogManager.info(pvOut);
								}
								if("Y".equalsIgnoreCase(daoNew.getInstallmentStatus(bean.getQuoteNo()))){
									daoNew.updateInstallmentDetail(bean.getQuoteNo(),"ISS","Y");
								}
								daoNew.getUpdPaymentProcessStaus(bean.getQuoteNo());
								daoNew.getUpdPolicyStaus("SP",bean.getQuoteNo());
								LogManager.info("CONV policy Status Upadte Done as SP");
								addActionMessage("Quote No "+bean.getQuoteNo()+" is Successfully Converted and listed Under Surveyor Pending, Policy No is "+new CommonDAO().getHomePolicyNo(bean.getQuoteNo()));
							}
							
						}
						
					}else{
						addActionError("Error At Payment Detail Update");
					}
				}
				
			}else{
				addActionError("This Quote is not a Card Payment Policy");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LogManager.info("Exit from convert()");
		return "policyConvertion";
	}
}
