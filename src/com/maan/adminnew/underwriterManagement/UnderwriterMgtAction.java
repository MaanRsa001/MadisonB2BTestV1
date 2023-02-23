package com.maan.adminnew.underwriterManagement;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.password.passwordEnc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class UnderwriterMgtAction extends ActionSupport implements ModelDriven<UnderwriterMgtBean>{
	
	private static final long serialVersionUID = 1L;
	private UnderwriterMgtBean bean = new UnderwriterMgtBean();
	UnderwriterMgtService service=new UnderwriterMgtService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String belongingBranch=(String)session.get("BelongingBranch");
	String login_id=(String)session.get("underwriter");
	private List <Object> underwriterList;
	private List <Object> underwriterInfo;
	private List <Object> issuerList;
	private List <Map<String, Object>> includeIssuer;
	private List <Map<String, Object>> excludeIssuer;
	String appId=(String)session.get("AppId");
	CommonService cservice=new CommonService();
	passwordEnc pass = new passwordEnc();
	private List <Object> commisionDetails=new ArrayList<Object>();
	//String mainbranch=((Map)session.get("BrokerDetails")).get("mainbranch").toString();
	
	public List<Map<String, Object>> getExcludeIssuer() {
		return excludeIssuer;
	}
	public void setExcludeIssuer(List<Map<String, Object>> excludeIssuer) {
		this.excludeIssuer = excludeIssuer;
	}

	public List<Object> getIssuerList() {
		return issuerList;
	}
	public void setIssuerList(List<Object> issuerList) {
		this.issuerList = issuerList;
	}
	
	public List<Object> getUnderwriterList() {
		return underwriterList;
	}
	public void setUnderwriterList(List<Object> underwriterList) {
		this.underwriterList = underwriterList;
	}
	public List<Object> getUnderwriterInfo() {
		return underwriterInfo;
	}
	public void setUnderwriterInfo(List<Object> underwriterInfo) {
		this.underwriterInfo = underwriterInfo;
	}
	
	public UnderwriterMgtBean getModel() {
		return bean;
	}
	
	public List<Map<String, String>> getProductList(){ 
		return cservice.getProductsDET(belongingBranch,bean.getAgencyCode());
	}
	
	public String getABList(){
    	LogManager.push("ENTER-->Method to getABList");
		underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		LogManager.info("getABList() - Exit");
		return "underwriterList";
    }
	
	public String view(){
    	LogManager.push("Method to view");
    	bean.setOptionMode("edit");
    	underwriterInfo=service.getUnderwriterDetails(bean, branchCode, bean.getIssurName());
		LogManager.info("view() - Exit");
    	return "underwriterInfo";
    }
	public String changePass(){
		return "changePass";
	}
	public String getNew(){
		return "edit";
	}
	public String edit(){
		
		return "edit";
	}
	public String insertIssuer(){
		String forward="edit";
		validateInsert();
		if(!hasActionErrors()){
		  service.insertUnderwriter(bean);
		  addActionError(getText("Inserted successfully"));
		  forward="underwriterList";
		  underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		}
		return forward;
	}
	public String updateissuer(){
		LogManager.push("Method to update");
		bean.setOptionMode("edit");
		validateInsert();
		if(!hasActionErrors()){
			service.updateUnderwriter(bean);
			addActionError(getText("Issuer details updated successfully"));
			underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		}
		LogManager.info("update() - Exit");
		return "underwriterInfo";
	}
	public String includeIssuer(){
		LogManager.push("Method to include");
		includeIssuer=service.includeissuerDetails(bean, bean.getType1(), belongingBranch);
		//underwriterInfo=service.getUnderwriterDetails(bean, branchCode, bean.getIssurName());
		bean.setProductId(null);
		bean.setStatusChk(null);
		LogManager.info("issuer() - Exit");
		return "includeIssuer";
	}
	public String excludeIssuer(){
		LogManager.push("Method to exclude");
		excludeIssuer=service.excludeissuerDetails(bean, bean.getType1(), belongingBranch);
		bean.setProductId(null);
		bean.setStatusChk(null);
		LogManager.info("exclude() - Exit");
		return "excludeIssuer";
	}
	public String branchSelection(){
		/*LogManager.push("Method to branchSelection()");
		bean.setMainBranchCode(branchCode);
		service.getRSABranches(bean);*/
		return "branchSelection";
	}
	private void validateInsert(){
	 	if(StringUtils.isBlank(bean.getIssurName()))
	 		addActionError(getText("issuer.name.empty"));
	 	if(StringUtils.isBlank(bean.getEmailId()))
	 		addActionError("Please enter Email Id");
	 	else if(StringUtils.contains(bean.getEmailId(), " "))
	 		addActionError("Email Id should not contain white spaces");
	 	else if("invalid".equalsIgnoreCase(new Validation().emailValidate(bean.getEmailId())))
    		addActionError("Please enter valid Email Id");
    	if(StringUtils.isBlank(bean.getLoginId()))
	 		addActionError(getText("issuer.loginId.empty"));
    	else if(StringUtils.contains(bean.getLoginId(), " "))
	 		addActionError("Login Id should not contain white spaces");
	 	else if(cservice.getAdminInfo(bean.getLoginId()).size()>0 && !"edit".equalsIgnoreCase(bean.getOptionMode()))
	 		addActionError(getText("error.loginid.exist"));
	 	if(StringUtils.isBlank(bean.getCoreLoginId()))
	 		addActionError(getText("issuer.coreloginId.empty"));
	 	if(!"edit".equalsIgnoreCase(bean.getOptionMode())){
		 	if(StringUtils.isBlank(bean.getPassword()))
		 		addActionError(getText("issuer.password.empty"));
		 	else if(StringUtils.contains(bean.getPassword(), " "))
		 		addActionError("Password should not contain white spaces");
	 	}
	 	/*if(StringUtils.isBlank(bean.getBranchNames()))
	 		addActionError(getText("issuer.branch.empty"));*/
	 	if(bean.getProducts()==null || bean.getProducts().size()<=0)
	 		addActionError(getText("issuer.product.empty"));
	 	if(StringUtils.isBlank(bean.getBranchId())){
	 		addActionError(getText("issuer.branchId.empty"));
	 	}
	 		
	}
	public String updatePass(){
    	LogManager.push("Method to checkPwd()");
    	validatePassword();
    	try{
	    	if(getActionErrors().isEmpty()){
	    		bean.setPassword(pass.crypt(bean.getPassword()));
				service.changePassword(bean);
				bean.setDisplay("passwordsuccess");
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "changePass";
    }
	public void validatePassword()
    {
    	if(StringUtils.isEmpty(bean.getPassword())){
    		addActionError(getText("error.broker.newpassword"));
    	}else if(StringUtils.contains(bean.getPassword(), " "))
	 		addActionError("Password should not contain white spaces");
    	if(StringUtils.isEmpty(bean.getRpassword())){
    		addActionError(getText("error.broker.repassword"));
    	}if(!bean.getRpassword().equals(bean.getPassword())){
    		addActionError(getText("error.different"));
    	}
    }
	public String updateExclude(){
		try {
			//bean.getStatus().removeAll(Arrays.asList("", null));
			if(!service.isNotBlank(bean.getStatusChk()))
				addActionError("Please Choose Broker to Exclude");
			else {
				for(int i=0;i<bean.getStatusChk().size();i++){
					if(bean.getStatusChk().get(i)) {
						String products=bean.getProductId().get(i);
					   	Object [] productsIds=products.split(",");
				   		if(!service.isNotBlank(productsIds))
				   			addActionError("Please Choose Products to Exclude for Broker Code"
				   					+ ": "+bean.getBrokerCode().get(i));
						}
					}
				}
			if(!hasActionErrors()){
				service.updateExcludedBrokers(bean);
				addActionMessage(getText("Selected Brokers are Excluded successfully"));
			}
			includeIssuer=service.includeissuerDetails(bean, bean.getType1(),belongingBranch);
			if(!hasActionErrors()) {
				bean.setProductId(null);
				bean.setStatusChk(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "includeIssuer";
	}
	public String updateInclude(){
		try {
			//bean.getStatus().removeAll(Arrays.asList("", null));
			if(!service.isNotBlank(bean.getStatusChk()))
				addActionError("Please Choose Broker to Exclude");
			else {
				for(int i=0;i<bean.getStatusChk().size();i++){
					if(bean.getStatusChk().get(i)) {
						String products=bean.getProductId().get(i);
					   	Object [] productsIds=products.split(",");
				   		if(!service.isNotBlank(productsIds))
				   			addActionError("Please Choose Products to Exclude for Broker Code: "+bean.getBrokerCode().get(i));
						}
					}
				}
			if(!hasActionErrors()){
				service.updateIncludeBrokers(bean);
				addActionMessage(getText("Selected Brokers are Included successfully"));
			}
			excludeIssuer=service.excludeissuerDetails(bean, bean.getType1(), belongingBranch);
			if(!hasActionErrors()) {
				bean.setProductId(null);
				bean.setStatusChk(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "excludeIssuer";
	}
	public String getIssuerAjax(){
		underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		return "adminAjax";
	}	 
	public List<Object>getBranchList(){
		return cservice.getAdminBranchList(branchCode, appId);
	}
	public List<Map<String, Object>> getIncludeIssuer() {
		return includeIssuer;
	}
	public void setIncludeIssuer(List<Map<String, Object>> includeIssuer) {
		this.includeIssuer = includeIssuer;
	}	
}