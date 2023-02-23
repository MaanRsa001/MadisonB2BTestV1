package com.maan.adminnew.report.motor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.report.JasperReports;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MotorReportAction extends ActionSupport implements ModelDriven<MotorReportBean> {
	private static final long serialVersionUID = 1L;
	private MotorReportBean bean=new MotorReportBean();
	final HttpServletRequest request=ServletActionContext.getRequest();
	MotorReportService service=new MotorReportService();
	CommonService cservice=new CommonService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String loginId=(String)session.get("user"); 
	String userType=(String)session.get("usertype");
	private String productId=(String) session.get("product_id");
	Validation validation=new Validation();
	String freightStatus=(String)session.get("freightStatus")==null?"":(String)session.get("freightStatus");
	private InputStream inputStream;
	private String fileName;

	//String freightStatus=request.getParameter("freightStatus")==null?"":request.getParameter("freightStatus");

	public String open()
	{
		return "motorReport";
	}
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMotor(){
		validateDate();
		if(!hasActionErrors())
		bean.setMotorReportList(service.getMotorReport(bean));
		/*if(StringUtils.isNotBlank(bean.getFromDate()))
			bean.setFromDate(dateFormat(bean.getFromDate()));
		if(StringUtils.isNotBlank(bean.getToDate()))
			bean.setToDate(dateFormat(bean.getToDate()));*/
		return "motorReport";
		
	}

	
	private void validateDate(){
		long date = diffInDays(bean.getToDate(),bean.getFromDate());
			if (StringUtils.isEmpty(bean.getFromDate())){
				addActionError(getText("error.fromdate"));
				bean.setMode("");
			}else if (StringUtils.isEmpty(bean.getToDate())){
				addActionError(getText("error.todate"));
				bean.setMode("");
			}else if (date > 0){
				 addActionError(getText("error.diff.date"));
				 bean.setMode("");
			 }
		
		
	}

	public MotorReportBean getModel() {
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
	
	
	public String report()throws IOException
	{
		
		fileName = "MotorReport";		 
		JasperReports jr=new JasperReports(); 
		ByteArrayOutputStream report = (ByteArrayOutputStream) jr.motorAdminReport(bean.getFromDate(),bean.getToDate(),bean.getPolicyType()); 
		inputStream = new ByteArrayInputStream(report.toByteArray());
		return "excel";
	}

	public String endorsementDetails()
	{
		if("update".equalsIgnoreCase(bean.getMode())){
			validateUpdate();
			if(!hasActionErrors())
			{
			service.getEndorsementUpdate(bean);	
			bean.setMode("showlist");
			addActionMessage(getText("endorsement.req.succses"));
			
			}
			else{
				bean.setMode("view");	
			}
		}
		if("showlist".equalsIgnoreCase(bean.getMode()))
		{
			validateDate();
			if(!hasActionErrors())
			bean.setEndorsementList(service.geteEndorsementList(bean));	
		}
		if("view".equalsIgnoreCase(bean.getMode()))
		{
			bean.setEndorsementListView(service.getEndorsementListView(bean));	
		}
		
		
		return "endorsementDetails";
	}
	private void validateUpdate(){
		if (StringUtils.isEmpty(bean.getStatus()))
			addActionError(getText("error.admin.status"));	
	}
	
	public String openClaimIntimationReport(){
		return "claimIntimationReport";
	}
	
	public String getIntimationMotor(){
		validateDate();
		if( "65".equalsIgnoreCase(productId)){
			if(!hasActionErrors()){
				bean.setClaimIntimateReportList(service.claimIntimateReportList(bean));
				bean.setClaimIntimateReportTpaList(service.claimIntimateReportTpaList(bean));
			}
		}
		if( "30".equalsIgnoreCase(productId)){
			if(!hasActionErrors()){
				bean.setClaimIntimateReportList(service.claimIntimateReportList(bean));
			}
		}
		return "claimIntimationReport";
	}
	
	public String getIntimationViewMotor(){
		if( "65".equalsIgnoreCase(productId)){
		int selecta=2;
		int selectb=3;
		bean.setIntimatePolicyList(service.getIntimatePolicy(bean)); 
		bean.setIntimateVehicleList(service.getIntimateVehicle(bean));
		if(service.getCheckIntimatePolicy(bean) <= selecta){
			bean.setIntimateList(service.getIntimateView(bean));
			service.getIntimateEdit(bean);
		}
		if(service.getCheckIntimatePolicy(bean) >= selectb){
			bean.setIntimateThirdPartyList(service.getIntimateThirdPartyView(bean));
			service.getIntimateThirdPartyEdit(bean);
		}
		}
		if( "30".equalsIgnoreCase(productId)){
			bean.setIntimatePolicyList(service.getIntimatePolicy(bean));
			service.getAssitHomeInfo(bean);
			bean.setHomeList(service.getHomeInfo(bean));
			bean.setIntimateList(service.getIntimateView(bean));
			service.getIntimateEdit(bean);
		}
		return "claimIntimationReportView";
	}
	
	public String getIntimationInsertMotor(){
		validateIntimationUpdate();
		if( "65".equalsIgnoreCase(productId)){
		if(!hasActionErrors()){
		if(!(getText("MOTOR_TP_ID")).equalsIgnoreCase(bean.getPolicyType())){
			service.getIntimationInsert(bean);
			addActionMessage(getText("message.sucessfully"));
			}
		if((getText("MOTOR_TP_ID")).equalsIgnoreCase(bean.getPolicyType())){
			service.getIntimationTpaInsert(bean);
			addActionMessage(getText("message.sucessfully"));
			}
		    bean.setClaimIntimateReportList(service.claimIntimateReportList(bean));
		    bean.setClaimIntimateReportTpaList(service.claimIntimateReportTpaList(bean));
			return "claimIntimationReport";
		}
	    	bean.setIntimatePolicyList(service.getIntimatePolicy(bean)); 
			bean.setIntimateVehicleList(service.getIntimateVehicle(bean));
	    	if(!(getText("MOTOR_TP_ID")).equalsIgnoreCase(bean.getPolicyType())){
				bean.setIntimateList(service.getIntimateView(bean));
			}
			if((getText("MOTOR_TP_ID")).equalsIgnoreCase(bean.getPolicyType())){
				bean.setIntimateThirdPartyList(service.getIntimateThirdPartyView(bean));
			}
		}
		if( "30".equalsIgnoreCase(productId)){
			if(!hasActionErrors()){
				service.getIntimationInsert(bean);
				addActionMessage(getText("message.sucessfully"));
				bean.setClaimIntimateReportList(service.claimIntimateReportList(bean));
				return "claimIntimationReport";
			}
			bean.setIntimatePolicyList(service.getIntimatePolicy(bean));
			service.getAssitHomeInfo(bean);
			bean.setHomeList(service.getHomeInfo(bean));
			bean.setIntimateList(service.getIntimateView(bean));
		}
			return "claimIntimationReportView";
	    }
	
	public String getIntimationBackMotor(){
		if( "65".equalsIgnoreCase(productId)){
			bean.setClaimIntimateReportList(service.claimIntimateReportList(bean));
			bean.setClaimIntimateReportTpaList(service.claimIntimateReportTpaList(bean));
		}
		if( "30".equalsIgnoreCase(productId)){
			bean.setClaimIntimateReportList(service.claimIntimateReportList(bean));
		}

		return "claimIntimationReport";
	}
	
	private void validateIntimationUpdate(){
		if (StringUtils.isEmpty(bean.getApproverStatus()))
			addActionError(getText("error.admin.status"));	
	}
	public String roadAssist(){
	String result="";
	if("RSAIssuer".equalsIgnoreCase(userType)){
			result ="roadAssistReportOP";
		}
	else{
			result ="roadAssistReportAdmin";
		}
	return result;
	}
	
	public String getRoadAssist(){
		String result="";
		validateDate();
		if(!hasActionErrors()){
		bean.setRoadAssistList(service.getRosdAssistReport(bean,"list"));
		bean.setMode("list");
		}
		if("RSAIssuer".equalsIgnoreCase(userType)){
			result ="roadAssistReportOP";
		}else{
			result ="roadAssistReportAdmin";
		}
		return result;
	}
	public String getRoadAssistView(){
		bean.setRoadAssistList1(service.getRosdAssistReport(bean,"view"));
		bean.setMode("view");
		return "roadAssistReportOP";
	}
	public String updRoadAssist(){
		validateUpdate();
		if(!hasActionErrors()){
		service.updRoadAssist(bean);
		getRoadAssist();
		}else{
			getRoadAssistView();
		}
		return "roadAssistReportOP";
		
	}
}

