package com.maan.adminnew.BrokerManagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.dao.CommonDAO;
import com.maan.common.password.passwordEnc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class BrokerManagementAction extends ActionSupport implements ModelDriven<BrokerMgmBean>
{
	private static final long serialVersionUID = 1L;
	private BrokerMgmBean bean = new BrokerMgmBean();
	private CommonService cservice=new CommonService();
	Validation validation=new Validation();
	private List <Object> brokerList;
	private List <String> brokerInfo;
	private List <Object> productData;
	private List <Object> commisionDetails;
	private List <Object> branchData;
	private List <Object> branchsInfo;
	private List <Object> coreCustomerInfo;
	private List <String> customerTaxInfo;
	private List <Object> commission_Det;
	private List <Map<String,String>> productList;
	private List <String> comp_Det;
	private List <Object> productInfo=new ArrayList<Object>();
	private List <Object> userInfo;
	private List <Object> userInfo1;
	final Validation validate=new Validation();
	
	BrokerManagementService service=new BrokerManagementService();
	final HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String appId=(String)session.get("AppId");
	String login_id=(String)session.get("user");
	passwordEnc pass = new passwordEnc();	
	
	public BrokerMgmBean getModel() {
		return bean;
	}
	public List<Object> getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(List<Object> productInfo) {
		this.productInfo = productInfo;
	}
	public List <Object> getBrokerList() {
		return brokerList;
	}
	public List <String> getBrokerInfo() {
		return brokerInfo;
	}
	public List <Object> getProductData() {
		return productData;
	}
	public List <Object> getCommisionDetails() {
		return commisionDetails;
	}
	public List <Object> getCommission_Det() {
		return commission_Det;
	}
	public List <Object> getBranchData() {
		return branchData;
	}
	public List <Object> getBranchsInfo() {
		return branchsInfo;
	}
	public List <Object> getBrokerCode() {
		return service.getBrokerCode();
	}
	public List <Object> getCountriesInfo() {
		return cservice.getCountries(branchCode);
	}
	public List <Object> getEmiratesInfo() {
		return cservice.getEmirates(branchCode);
	}
	public List <Object> getNationalitiesInfo() {
		return cservice.getNationalities();
	}
	public List <Object> getTitlesInfo() {
		return cservice.getTitles(branchCode);
	}
	public List <Map<String,String>> getProductDet() {
		return cservice.getProductsDET(branchCode,"");
	}
	public List <Object> getExecutivesInfo() {
		return service.getExecutives();
	}
	public List <Object> getCoreCustomerInfo() {
		return coreCustomerInfo;
	}
	public List <String> getCustomerTaxInfo() {
		return customerTaxInfo;
	}
	public List <String> getComp_Det() {
		return comp_Det;
	}
	public List <Object> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(List <Object> userInfo) {
		this.userInfo = userInfo;
	}
	public List <Object> getUserInfo1() {
		return userInfo1;
	}
	public List <Map<String,String>> getProductList() {
		return productList;
	}
	
	public List<Map<String,Object>> getPolicyTypeList()
	{
		return service.getPolicyTypeList(bean);
	}
	
	public List<Map<String,Object>> getSchemeList()
	{
		return service.getSchemeList(bean,branchCode);
	}
	
	public String getABList(){
    	LogManager.push("ENTER-->Method to getABList");
    	brokerList=cservice.getAdminBrokerList(branchCode,appId);
		LogManager.info("getABList() - Exit"+login_id);
    	return "brokerList";
    }
    public String view(){
    	LogManager.push("Method to view");
    	brokerInfo=service.getBrokerDetails(bean, bean.getAgencyCode(),branchCode);
    	commisionDetails=service.getCommisionData(bean.getAgencyCode());
    	branchData=service.getBranchDetails(branchCode);
		LogManager.info("view() - Exit");
    	return "view";
    }
    public String edit(){
    	bean.setBrcode((String)session.get("BranchCode"));
    	LogManager.push("Method to edit");
    	LogManager.push("branchCode-->"+branchCode);
    	LogManager.push("agencyCode-->"+bean.getAgencyCode());
    	try{
    		if(!"new".equals(bean.getMode())){
    			if("delete".equals(bean.getMode1())){
		    		service.deleteBroLogo(bean.getAgencyCode());
		    		//String filePath = getText("GET_BROKER_LOGO_PATH")+bean.getAgencyCode()+"."+FilenameUtils.getExtension(bean.getBroImgName());
					File deleteFile = new File(request.getSession().getServletContext().getRealPath(bean.getBroImgName()));
		    		FileUtils.deleteQuietly(deleteFile);
		    	}
		    	String[] args={branchCode,bean.getAgencyCode(),branchCode,bean.getAgencyCode()};
		    	brokerInfo=service.getBrokerDetails(bean, bean.getAgencyCode(),branchCode);
		    	customerTaxInfo=service.getBrokerTaxInfo(bean, args);
		    	LogManager.info("getBrokerTaxInfo() - Exit   Result--->"+customerTaxInfo.size());
		    	commisionDetails=service.getCommisionData(bean.getAgencyCode());
    		}
			LogManager.info("edit() - Exit");
    	}catch(Exception e){
    		LogManager.info(e);
    		e.printStackTrace();
    	}
    	return "edit";
    }
    public String checkPwd(){
    	LogManager.push("Method to checkPwd()");
    	validatePassword();
    	try{
	    	if(getActionErrors().isEmpty()){
	    		bean.setPassword(pass.crypt(bean.getPassword()));
				String args[]={bean.getPassword(),bean.getAgencyCode()};
				cservice.checkPassword(args);
				bean.setDisplay("passwordsuccess");
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "assignNew";
    }
    public String getccInfo(){
    	LogManager.push("Method to getccInfo");
    	if(bean.getMode()==null){
	    	LogManager.info("customerName-->"+bean.getCustomerName());
	    	LogManager.info("bcode-->"+branchCode);
	    	coreCustomerInfo=service.getcoreCustomererInfo(bean.getCustomerName(),branchCode, (String)session.get("userLoginMode"));
	    	bean.setMode("display");
    	}else bean.setMode(null);
    	return "coreCust";
    }
    public boolean validPassword(String newpassword)
	{
    	Pattern pattern=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[@*#$%]).{6,20})");//(?=.*[A-Z])
    	Matcher matcher = pattern.matcher(newpassword);
    	return matcher.matches();
	}
    /*public String statusChange(){
    	bean.setStatus(service.getBrokerStatus(bean.getAgencyCode()));
    	return "status";
    }
    public String statusUpdate(){
    	String[] obj={bean.getStatus(),bean.getAgencyCode()};
    	service.updateBrokerStatus(obj);
    	bean.setDisplay("success");
    	return "status";
    }*/
    
    public String newBroker()
    {
    	bean.setBrcode((String)session.get("BranchCode"));
    	LogManager.push("Enter==>newBroker()");
    	
    	String bid="";
		if(bean.getBranchId()!=null && bean.getBranchId().length>0){
			for(String str: bean.getBranchId())
				bid=bid+","+str;
			bid=bid.substring(1);
		}

		bean.setAttachedBranch(bid);
    	
    	if("trueV".equalsIgnoreCase(bean.getValidNcheck()))
    	{
    		comp_Det=service.getCompDet(bean, bean.getAgencyCode());
    		commission_Det=service.getCommissionDET(bean.getAgencyCode(), branchCode);
    	}else{
	    	validatenewUser();
	    	if(getActionErrors().isEmpty()){
	    		String filePath ="";
	    		if("new".equalsIgnoreCase(bean.getMode())){
	    			bean.setCustomer_id(Integer.valueOf(new CommonDAO().getSequenceNo("CUSTOMER_ID",(String) session.get("product_id"),branchCode,"","")));
	    			bean.setBroker_Code(service.getBroke_Code());
					Object[] arg={bean.getCustomer_id(), bean.getCustomer_id(), branchCode};
					
					service.getmax_Broke_Code(arg);
					if(bean.getUploadFileName()!=null){
						filePath = getText("GET_BROKER_LOGO_PATH")+bean.getBroker_Code()+"_"+bean.getUploadFileName();
						File fileToCreate = new File(request.getSession().getServletContext().getRealPath(filePath));
						try {
							FileUtils.copyFile(bean.getUpload(), fileToCreate);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		    		Object[] args={bean.getCustomer_id(),"2", bean.getTitle(), bean.getFirstname(), bean.getLastname(), "1", bean.getNationality(), bean.getDob(), bean.getGender()==null?"":bean.getGender(), bean.getTelephone(), bean.getMobile(), bean.getFax(),
		    				bean.getBemail(), bean.getAddress1(), bean.getAddress2(), bean.getOccupation(), bean.getPobox(), bean.getCountry(), bean.getEmirate(), "Y", bean.getBroker_Code(), bean.getBroker_Code(), bean.getCustomerName(), bean.getARACC(), bean.getLoginid()};
		    		LogManager.info("args===>" + StringUtils.join(args, ", "));
					Object[] info={bean.getBroker_Code(), bean.getBorganization(), bean.getFirstname(), bean.getAddress1(), bean.getAddress2(), "", bean.getOthercity()==null?"": bean.getOthercity(), bean.getCountry(), bean.getTelephone(), bean.getPobox(), bean.getFax(), bean.getEmirate(),
								"", "Y", bean.getCustomer_id(), branchCode, bean.getCIMSNO(), bean.getApprovedby(), bean.getBcode(), bean.getExecutive(), StringUtils.isBlank(bean.getOneOffCommission())?"":bean.getOneOffCommission(), StringUtils.isBlank(bean.getOpenCoverCommission())?"":bean.getOpenCoverCommission(),filePath,bean.getBrokerUsertype()};

					LogManager.info("info===>" + StringUtils.join(info,", "));
					service.newBrokerCreation(args,info);
					
					String args1[]={bean.getLoginid(), pass.crypt(bean.getPassword()), "Broker", bean.getFirstname(),"1", String.valueOf(bean.getBroker_Code()),
        					String.valueOf(bean.getBroker_Code()), "2", "Admin", "Y", "Y", "Y", "BOTH", branchCode, bean.getCountry(),  bean.getLogin_Id(), bean.getBemail(), bean.getAttachedBranch(),bean.getSubBranchId()};

        			LogManager.info("args1===>" + StringUtils.join(args1,", "));
	    			service.insertLogInDet(args1);
					
					//int amendId=service.getMax_amend_Id(branchCode, bean.getBroker_Code())==0?1:service.getMax_amend_Id(branchCode,bean.getBroker_Code());
					//Object[] val={bean.getBroker_Code(), branchCode, bean.getPolicy_fee()==null?"":bean.getPolicy_fee(), bean.getGov_fee()==null?"":bean.getGov_fee(), bean.getEmer_fee()==null?"":bean.getEmer_fee(), bean.getPolicFee()==null?"":bean.getPolicFee(), bean.getGovtTax()==null?"":bean.getGovtTax(), bean.getEmergencyfund()==null?"":bean.getEmergencyfund(),"", amendId, bean.getApp_for()==null?"":bean.getApp_for(), bean.getEffecdate()};

					//LogManager.info("val===>" + StringUtils.join(val,", "));
					
					//service.insTaxInfo(val);
					bean.setDisplay("successNew");
					bean.setAgencyCode(String.valueOf(bean.getBroker_Code()));
					bean.setIndex("0");
	    		}else{
	    			if(bean.getUploadFileName()!=null){
	    				//String dateTime= new Timestamp(new Date().getTime());
		    			filePath = getText("GET_BROKER_LOGO_PATH")+bean.getAgencyCode()+"_"+bean.getUploadFileName();
		    			File fileToCreate = new File(request.getSession().getServletContext().getRealPath(filePath));
						try {
							FileUtils.copyFile(bean.getUpload(), fileToCreate);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			}
	    			bean.setCustomer_id(service.getCustomer_Id(bean.getAgencyCode(), branchCode));
	    			Object[] args = {bean.getBorganization(), bean.getFirstname(), bean.getAddress1(), bean.getAddress2(), "", bean.getOthercity()==null?"":bean.getOthercity(), bean.getCountry(), bean.getTelephone(), bean.getPobox(), bean.getFax(), bean.getEmirate(),
	    					bean.getCustomer_id(), branchCode, bean.getCIMSNO(), bean.getApprovedby(), bean.getBcode(), bean.getExecutive(),StringUtils.isBlank(bean.getOneOffCommission())?"":bean.getOneOffCommission(), StringUtils.isBlank(bean.getOpenCoverCommission())?"":bean.getOpenCoverCommission(),filePath,bean.getBrokerUsertype(), bean.getAgencyCode(), branchCode};
	    			LogManager.info("args===>" + StringUtils.join(args,", "));
					service.update_Broker(args);
					
					Object[] info ={bean.getTitle(), bean.getFirstname(), bean.getLastname(), bean.getNationality(), bean.getDob(), bean.getGender()==null?"":bean.getGender(), bean.getTelephone()==null?"":bean.getTelephone(), bean.getMobile(), bean.getFax(), bean.getBemail(), bean.getAddress1(), bean.getAddress2(),
							bean.getOccupation(), bean.getPobox(), bean.getCountry(), bean.getEmirate(), "Y", bean.getCustomerName(), bean.getARACC(), appId, bean.getAgencyCode()};
					LogManager.info("info===>" + StringUtils.join(info,", "));
					service.update_PersonalInfo(info);
					
					String[] obj={bean.getFirstname(), bean.getStatus(), bean.getBemail(), bean.getAttachedBranch(),bean.getSubBranchId(), bean.getAgencyCode()};
			    	service.updateBrokerStatus(obj);
					
					/*Object[] val={bean.getPolicy_fee()==null?"":bean.getPolicy_fee(), bean.getGov_fee()==null?"":bean.getGov_fee(), 
									bean.getEmer_fee()==null?"":bean.getEmer_fee(), bean.getPolicFee()==null?"":bean.getPolicFee(), 
									bean.getGovtTax()==null?"":bean.getGovtTax(), bean.getEmergencyfund()==null?"":bean.getEmergencyfund(), 
									bean.getApp_for()==null?"":bean.getApp_for(), bean.getEffecdate(),
									bean.getAgencyCode(), branchCode};*/
					
					//LogManager.info("val===>" + StringUtils.join(val,", "));
					
					//service.updTaxInfo(val);
					commisionDetails=service.getCommisionData(bean.getAgencyCode());
					bean.setDisplay("successUpdate");
					bean.setIndex("0");
	    		}
	    	}
    	}
    	return "edit";
    }
    
	public String addProduct(){
		LogManager.push("ENTER===> addProduct");
		String returnResult="edit";
		try {
	    	LogManager.push("ENTER-->Method to validateProducts");
			validateProducts();
			if(!hasActionErrors()){
				if("newAjax".equals(bean.getMode1())){
					Object[] info=null;
					if("30".equalsIgnoreCase(bean.getProductID())){
						info =new Object[]{cservice.getMaxUserId(), bean.getFirstname(), bean.getAgencyCode(), bean.getAgencyCode(), bean.getAgencyCode(), bean.getProductID(), "2", bean.getCommission(), "1000", bean.getInsurance_End_Limit(), bean.getDiscountPremium()==null?"":bean.getDiscountPremium(),
										"0", "1", "", "", "", "Y", bean.getCustomer_id(), bean.getMin_Premium_Amount(), bean.getBack_Date_Allowed(), "", "", "", bean.getLoadingPremium(), bean.getUser_Id_Creation()==null?"":bean.getUser_Id_Creation(), bean.getFreight(), bean.getPayReceipt(), bean.getProvision(), 
										"", ""};
					}else {
						info =new Object[]{cservice.getMaxUserId(), bean.getFirstname(), bean.getAgencyCode(), bean.getAgencyCode(), bean.getAgencyCode(), bean.getProductID(), "2", bean.getCommission(), "1000", bean.getInsurance_End_Limit(), bean.getDiscountPremium()==null?"":bean.getDiscountPremium(),
								"0", "1", "", "", "", "Y", bean.getCustomer_id(), bean.getMin_Premium_Amount(), bean.getBack_Date_Allowed(), "", "", "", bean.getLoadingPremium(),  bean.getUser_Id_Creation()==null?"":bean.getUser_Id_Creation(), bean.getFreight(), bean.getPayReceipt(), bean.getProvision(), "", "","30".equals(bean.getProductID())?"3":"",
								"88888","N"};
					}
					Object obj1[]=new Object[]{bean.getRemark(), bean.getAgencyCode()};
					
					LogManager.info("info===>" + StringUtils.join(info, ", "));
					LogManager.info("obj1===>" + StringUtils.join(obj1, ", "));
					if("30".equalsIgnoreCase(bean.getProductID())){
						service.insertLoginUserDtl(info, obj1,getSchemeList(),bean);
					}else{
						cservice.insertCommission(info, obj1);
					}
					
					if("65".equalsIgnoreCase(bean.getProductID())){
						service.insertBrokerCommission(getPolicyTypeList(),bean);
					}
					bean.setDisplay("newsuccess");
				}
				else{
					Object[] info=null;
					if("30".equalsIgnoreCase(bean.getProductID())){
						info = new Object[]{bean.getCommission(), bean.getInsurance_End_Limit(), "Y", bean.getDiscountPremium()==null?"":bean.getDiscountPremium(), bean.getMin_Premium_Amount(), bean.getBack_Date_Allowed(),
								bean.getLoadingPremium(),bean.getPayReceipt(), bean.getFreight(),  bean.getUser_Id_Creation()==null?"":bean.getUser_Id_Creation(),
								bean.getProvision(),"",""};
					}else {
						info = new Object[]{bean.getCommission(), bean.getInsurance_End_Limit(), "Y", bean.getDiscountPremium()==null?"":bean.getDiscountPremium(), bean.getMin_Premium_Amount(), bean.getBack_Date_Allowed(),
								bean.getLoadingPremium(),bean.getPayReceipt(), bean.getFreight(),  bean.getUser_Id_Creation()==null?"":bean.getUser_Id_Creation(),bean.getProvision(),"","",
								bean.getAgencyCode(), bean.getProductID()};
					}
					Object obj1[]=new Object[]{bean.getRemark(), bean.getAgencyCode()};
					
					LogManager.info("info===>" + StringUtils.join(info, ", "));
					LogManager.info("obj1===>" + StringUtils.join(obj1, ", "));
					if("30".equalsIgnoreCase(bean.getProductID())){
						service.updateLoginUserDtl(info, obj1,getSchemeList(),bean);
					}else{
						cservice.updateCommission(info, obj1);
					}
					
					if("65".equalsIgnoreCase(bean.getProductID())){
						//service.updateBrokerCommission(getPolicyTypeList(),bean);
						service.insertBrokerCommission(getPolicyTypeList(),bean);
					}
					bean.setDisplay("editsuccess");
				}
				bean.setMode1("");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		edit();
		bean.setIndex("1");
		return returnResult;
	}
	
	/*public String newlogin()
    {
    	LogManager.push("newlogin");
    	String returnResult="logcreate";
    	try{
	    	validatePassword();
	    	if(getActionErrors().isEmpty())
	    	{
		    	if(bean.getPassword().equals(bean.getRepassword()))
		        	{
		        		if(validPassword(bean.getPassword())){
		        			String args[]={bean.getLoginid(), pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()), "RSAIssuer", bean.getFirstname(),"8", bean.getAgencyCode(),
		        					bean.getAgencyCode(), "1", "Admin", "Y", "Y", "Y", "BOTH", branchCode, bean.getCountry(),  bean.getLogin_Id()};
		        			for(String k: args){
								LogManager.info("args===>" + k);
							}
			    			service.insertLogInDet(args);
			    			
			    			Object params[]={bean.getLoginid(), bean.getAgencyCode()};
			    			for(Object k: params){
								LogManager.info("args===>" + k);
							}
			    			service.updatePersonalLogin(params);
			    			bean.setDisplay("success");
		        		}
		        		else bean.setDisplay("invalid");
		        	}
		        	else bean.setDisplay("different");
		    	LogManager.info("display-->"+bean.getDisplay());
		    	returnResult="success";
	    	}
	    	else{
	    		returnResult="logcreate";
	    	}
    	}catch(Exception e){
			e.printStackTrace();
		}
    	return returnResult;
    }
	
	public String getBrokerUserInfo(){
		try{
			String args[] = {bean.getAgencyCode()};
			userInfo = service.getProductStatus(args);
			
			String[] args1 = {bean.getAgencyCode(), bean.getProductID()};
			for(Object k: args1){
				LogManager.info("args===>" + k);
			}
			userInfo1 = service.getProductStatuss(args1);
			bean.setBroker_Code(userInfo.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "status";
	}*/
    public void validatenewUser()
    {
    	if("edit".equals(bean.getStatus())){
	    	if(StringUtils.isEmpty(bean.getStatus())){
	    		addActionError(getText("error.quotation.status"));
	    	}
    	}
    	/*if(StringUtils.isEmpty(bean.getNameAndCode())){
    		addActionError(getText("error.quotation.nameAndCode"));
    	}*/
    	final String BROKER_CODE_CHECK = LocalizedTextUtil.findDefaultText("BROKER_CODE_CHECK", Locale.ENGLISH);
    	if(StringUtils.isEmpty(bean.getApprovedby())){
    		addActionError(getText("error.quotation.approvedby"));
    	}
    	if(StringUtils.isEmpty(bean.getBrokerUsertype())) {
    		addActionError("Please Select User Type");
    	}
    	if(StringUtils.isEmpty(bean.getBcode())){
    		addActionError(getText("error.quotation.bcode"));
    	}/*else if(!StringUtils.isNumeric(bean.getBcode())){
    		addActionError(getText("error.quotation.bcode.invalid"));
    	}*/else if(StringUtils.isNotBlank(bean.getBrokerUsertype())) {
    		if(!bean.getBcode().startsWith(bean.getBrokerUsertype())) {
    			addActionError("Broker Code Should Starts with "+bean.getBrokerUsertype());
    		}
    	}
    	else if("YES".equalsIgnoreCase(BROKER_CODE_CHECK) && service.validateBcode(bean.getBcode())<=0) {
    		addActionError(getText("error.quotation.bcode.invalid"));
    	}else if("new".equalsIgnoreCase(bean.getMode()) && service.valBraWiseBcode(bean.getBcode(),branchCode)>0) {
    			addActionError(getText("error.branchwise.bcode.invalid"));
    	}
    	if(StringUtils.isEmpty(bean.getEmirate())){
    		addActionError(getText("error.quotation.emirate"));
    	}if("VARIOUS".equalsIgnoreCase(bean.getEmirate())){
    		if(StringUtils.isBlank(bean.getOthercity()))
    			addActionError(getText("error.broker.othercity.required"));
    		else if(!StringUtils.isAlpha(bean.getOthercity())){
        		addActionError(getText("error.broker.othercity"));
        	}
    	}
    	if(StringUtils.isBlank(bean.getOthercity()))
    		bean.setOthercity("");
    	if(StringUtils.isEmpty(bean.getCountry())){
    		addActionError("Please Select Country");
    	}/*if(StringUtils.isEmpty(bean.getPobox())){
    		addActionError(getText("error.quotation.pobox"));
    	}else */
    	if(StringUtils.isNotBlank(bean.getPobox()) && !StringUtils.isNumeric(bean.getPobox())){
    		addActionError(getText("error.quotation.pobox.valid"));
    	}if(!StringUtils.isNumeric(bean.getTelephone())){
    		addActionError(getText("Please Enter Valid Phone No"));
    	}if(StringUtils.isEmpty(bean.getFirstname())){
    		addActionError(getText("error.quotation.firstname"));
    	}else if(StringUtils.isNumeric(bean.getFirstname())){
    		addActionError(getText("error.quotation.firstname.valid"));
    	}if(StringUtils.isEmpty(bean.getNationality())){
    		addActionError(getText("error.quotation.nationality"));
    	}if(!StringUtils.isNumeric(bean.getMobile())){
    		addActionError(getText("error.broker.mobile"));
    	}if(StringUtils.isEmpty(bean.getBemail())){
    		addActionError(getText("error.quotation.email"));
    	}else if(StringUtils.contains(bean.getBemail(), " "))
	 		addActionError("Email Id should not contain white spaces");
    	else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getBemail()))){
    		addActionError(getText("error.quotation.valid.bemail"));
    	}if(StringUtils.isEmpty(bean.getExecutive())){
    		addActionError(getText("error.quotation.executive"));
    	}if("new".equals(bean.getMode())){
    		if(StringUtils.isEmpty(bean.getLoginid())){
        		addActionError(getText("error.broker.loginid"));
        	}else if(StringUtils.contains(bean.getLoginid(), " "))
    	 		addActionError("Login Id should not contain white spaces");
        	else if(cservice.getAdminInfo(bean.getLoginid()).size()>0 && !"edit".equalsIgnoreCase(bean.getMode()))
    	 		addActionError(getText("error.loginid.exist"));
    		if(StringUtils.isEmpty(bean.getPassword())){
        		addActionError(getText("error.broker.newpassword"));
        	}else if(StringUtils.contains(bean.getPassword(), " "))
    	 		addActionError("Password should not contain white spaces");
    		if(StringUtils.isEmpty(bean.getRepassword())){
        		addActionError(getText("error.broker.repassword"));
        	}else if(!bean.getPassword().equals(bean.getRepassword())){
        		addActionError(getText("error.different"));
        	}else if(!validPassword(bean.getPassword())){
        		addActionError(getText("error.invalid"));
        	}/*else if(cservice.getAdminInfo(bean.getLoginid()).size()>0){
        		addActionError(getText("error.loginid.notavailable"));
        	}*/
    	}
    	if("Y".equalsIgnoreCase(bean.getPolicy_fee())){
    		if(StringUtils.isEmpty(bean.getPolicFee())){
        		addActionError(getText("error.tax.policFee"));
        	}else  {
        		try {
	    			Double.parseDouble(bean.getPolicFee());
	    			if(Double.parseDouble(bean.getPolicFee())>99.999){	
	            		addActionError(getText("error.tax.valid.policFee"));	
	    			}
	    		}
	    		catch(Exception exception) {
	    			addActionError(getText("error.tax.valid.policFee"));
	    		}
        	}
		}if("Y".equalsIgnoreCase(bean.getGov_fee())){
    		if(StringUtils.isEmpty(bean.getGovtTax())){
        		addActionError(getText("error.tax.govtTax"));
        	}else {
        		try {
	    			Double.parseDouble(bean.getGovtTax());
	    			if(Double.parseDouble(bean.getGovtTax())>99.999){	
	            		addActionError(getText("error.tax.valid.govtTax"));	
	    			}
	    		}
	    		catch(Exception exception) {
	    			addActionError(getText("error.tax.valid.govtTax"));
	    		}
        	}
		}if("Y".equalsIgnoreCase(bean.getEmer_fee())){
    		if(StringUtils.isEmpty(bean.getEmergencyfund())){
        		addActionError(getText("error.tax.emergencyfund"));
        	}else if(!StringUtils.isNumeric(bean.getEmergencyfund())){
        		addActionError(getText("error.tax.valid.emergencyfund"));
        	}else if(Integer.parseInt(bean.getEmergencyfund())>99.999){
        		addActionError(getText("error.tax.valid.emergencyfund"));
        	}
		}
		/*if(StringUtils.isEmpty(bean.getOneOffCommission()))
    		addActionError("Please enter Commission for Issuer Quotes : For One Off Policy");
    	else */
    	if(StringUtils.isNotBlank(bean.getOneOffCommission()) && !Validation.decimalValidate(bean.getOneOffCommission()))
    		addActionError("Please enter valid Commission for Issuer Quotes : For One Off Policy");
		/*if(StringUtils.isEmpty(bean.getOpenCoverCommission()))
			addActionError("Please enter Commission for Issuer Quotes : For Open Cover Policy");
		else*/ 
		if(StringUtils.isNotBlank(bean.getOpenCoverCommission()) && !Validation.decimalValidate(bean.getOpenCoverCommission()))
    		addActionError("Please enter valid Commission for Issuer Quotes : For Open Cover Policy");
		/*if(StringUtils.isEmpty(bean.getEffecdate())){
    		addActionError(getText("error.tax.effecdate"));
    	}*/
		if(StringUtils.isBlank(bean.getBorganization()))
			addActionError("Please Enter the Broker Organization");
		
		String extensions[] = {"png","jpg","jpeg","gif"};

		if(bean.getUpload()!=null && (bean.getUpload().length())>0)
		{
			if(!FilenameUtils.isExtension(bean.getUploadFileName().toLowerCase(),extensions))
			{
				addActionError(getText("error.broker.image"));
			}
			//String filePath = request.getContextPath()+"/BrokerImages/"+bean.getUploadFileName()+"."+FilenameUtils.getExtension(bean.getUploadFileName());
			//System.out.println("filePath--->"+filePath);
			/*if(bean.getBroImgUpload().length()>10485760)
			{
				addActionError(getText("error.image.size"));
			}*/

		}if(StringUtils.isBlank(bean.getAttachedBranch()))
    		addActionError(getText("error.attached.branch"));
    }
    public void validatePassword()
    {
    	if(StringUtils.isEmpty(bean.getPassword())){
    		addActionError(getText("error.broker.newpassword"));
    	}if(StringUtils.isEmpty(bean.getRpassword())){
    		addActionError(getText("error.broker.repassword"));
    	}if(!bean.getRpassword().equals(bean.getPassword())){
    		addActionError(getText("error.different"));
    	}
    }
    public boolean validString(String value,int format)
    { 
    	boolean bo=false;
    	int count=0,c=0;
    	
    	try
    	{
    		value=value.trim();
    		String validChar,validno,validextra=null,validCode,validnumonly;
    		String string=new String("");
    					
    			validChar="abcdefghijklmnopqrstuvwxyz";
    			validno="1234567890-";
    			validextra="1234567890.";
    			validCode="abcdefghijklmnopqrstuvwxyz1234567890 ";
    			validnumonly="1234567890";
    			value=value.toLowerCase();
    			if(format==1)
    			    string=new String(validChar);
    			else if(format==2)
    				string=new String(validno);
    			else if(format==3)
    				string=validChar+validno;
    			else if(format==4)
    				string=new String(validextra);
    			else if(format==5)
    				string=new String(validCode);
    			else if(format==6)
    				string=new String(validnumonly);
    			for(int i=0;i<value.length();i++) {
    				if(string.indexOf(value.charAt(i))== -1)
    				bo=true;
    				if(value.charAt(i)=='.')
    					count++;	
    				if(value.charAt(i)=='-')
    				c++;	
    			}
    			if(count>=2 || c>=2)
    				bo=true;
    			
    	}catch(Exception e){
    		return bo;
    	}
    	return bo;
    }   
    
    public void validateProducts(){
    	if("newAjax".equals(bean.getMode1()) && StringUtils.isEmpty(bean.getProductID())){
    		addActionError(getText("error.product.productID"));
    	}
	    else{
	    		//if("3".equals(bean.getProductID())){
		    	if(StringUtils.isBlank(bean.getCommission())){
		    		addActionError(getText("error.product.COMMISSION"));
		    	}else {	
		    		try {
		    			Double.parseDouble(bean.getCommission());
		    		}
		    		catch(Exception exception) {
		    			addActionError(getText("error.product.valid.COMMISSION"));
		    		}		
		    	}if(StringUtils.isBlank(bean.getInsurance_End_Limit())){
		    		addActionError(getText("error.product.INSURANCE_END_LIMIT"));
		    	}else if(!StringUtils.isNumeric(bean.getInsurance_End_Limit())){	
		    		addActionError(getText("error.product.valid.INSURANCE_END_LIMIT"));		
		    	}if(StringUtils.isBlank(bean.getMin_Premium_Amount())){
		    		addActionError(getText("error.product.MIN_PREMIUM_AMOUNT"));
		    	}else if(!StringUtils.isNumeric(bean.getMin_Premium_Amount())){	
		    		addActionError(getText("error.product.valid.MIN_PREMIUM_AMOUNT"));		
		    	}
		    	if("Y".equalsIgnoreCase(bean.getProvision())){
		    		if(StringUtils.isBlank(bean.getLoadingPremium())){
		    			addActionError(getText("error.product.loadingPremium"));
		    		}else if(!StringUtils.isNumeric(bean.getLoadingPremium())){	
		    			addActionError(getText("error.product.valid.loadingPremium"));		
		    		}if(StringUtils.isBlank(bean.getDiscountPremium())){
		    			addActionError(getText("error.product.discountPremium"));
		    		}else if(!StringUtils.isNumeric(bean.getDiscountPremium())){	
		    			addActionError(getText("error.product.valid.discountPremium"));		
		    		}
		    	}else{
					bean.setLoadingPremium(null);
					bean.setDiscountPremium(null);
				}
		    	if(StringUtils.isBlank(bean.getBack_Date_Allowed())){
		    		addActionError(getText("error.product.BACK_DATE_ALLOWED"));
		    	}else if(!StringUtils.isNumeric(bean.getBack_Date_Allowed())){
		    		addActionError(getText("error.product.valid.BACK_DATE_ALLOWED"));
		    	}
		   // }
	    		if(StringUtils.isBlank(bean.getUser_Id_Creation())){
	    		addActionError(getText("error.product.USER_ID_CREATION"));
	    	}if(StringUtils.isBlank(bean.getPayReceipt())){
	    		addActionError(getText("error.product.payReceipt"));
	    	}if(StringUtils.isBlank(bean.getFreight())){
	    		addActionError(getText("error.product.freight"));
	    	}
	    	if(StringUtils.isBlank(bean.getRemark())){
	    		addActionError("Please Select Remarks Required");
	    	}
	    	/*if(StringUtils.isBlank(bean.getCommission())){
	    		addActionError("Please Enter Commission");
	    	}*/
	    	else if ("65".equals(bean.getProductID())){
	    		if(StringUtils.isBlank(bean.getEffectiveDate()))
	    			addActionError("Please Choose Effective Date");
	    		if(StringUtils.isBlank(bean.getEndDate()))
	    			addActionError("Please Choose End Date");
	    		for(int i=0;i<getPolicyTypeList().size();i++){
	    			if(!"3".equalsIgnoreCase(getPolicyTypeList().get(i).get("POLICYTYPE_ID").toString())){
		    			if(StringUtils.isBlank(bean.getSumInsStart().get(i))){
		    				addActionError("Please Enter Sum Insured Start for "+getPolicyTypeList().get(i).get("POLICYTYPE_DESC_ENGLISH"));
		    			}else {	
		    				try {
								Double.parseDouble(bean.getSumInsStart().get(i));
							} catch (Exception e) {
								addActionError("Please Enter Valid Sum Insured Start for "+getPolicyTypeList().get(i).get("POLICYTYPE_DESC_ENGLISH"));
							}
		    				
		    			}
		    			if(StringUtils.isBlank(bean.getSumInsEnd().get(i))){
		    				addActionError("Please Enter Sum Insured End for "+getPolicyTypeList().get(i).get("POLICYTYPE_DESC_ENGLISH"));
		    			}else {
		    				try {
								Double.parseDouble(bean.getSumInsEnd().get(i));
							} catch (Exception e) {
								addActionError("Please Enter Valid Sum Insured End for "+getPolicyTypeList().get(i).get("POLICYTYPE_DESC_ENGLISH"));
							}
		    				
		    			}/*if(StringUtils.isNotBlank(bean.getPolicyFee().get(i))){
		    				try {
								Double.parseDouble(bean.getPolicyFee().get(i));
							} catch (Exception e) {
								addActionError("Please Enter Valid Policy Fee for "+getPolicyTypeList().get(i).get("POLICYTYPE_DESC_ENGLISH"));
							}
		    				
		    			}if(StringUtils.isNotBlank(bean.getOtherFee().get(i)) ){
		    				try {
								Double.parseDouble(bean.getOtherFee().get(i));
							} catch (Exception e) {
								addActionError("Please Enter Valid Other Fee for "+getPolicyTypeList().get(i).get("POLICYTYPE_DESC_ENGLISH"));
							}
		    				
		    			}*/
	    			}
	    		}
	    		/*int count=service.brokerCommissionCount(bean);
	    		int polTypeCount=getPolicyTypeList().size();
	    		if(count<=0){
	    			addActionError("Please Add Policy Wise Commission Detail");
	    		}else if(count!=polTypeCount){
	    			addActionError("Please Add Policy Wise Commission Detail for All policy Types");
	    		}*/
	    	}
	    	else if ("30".equals(bean.getProductID())){
	    		for(int i=0;i<getSchemeList().size();i++){
	    			if(StringUtils.isBlank(bean.getLoadingStart().get(i))){
	    				addActionError("Please Enter Minimum Loading % for "+getSchemeList().get(i).get("SCHEME_NAME"));
	    			}else{
	    				try {
							Double.parseDouble(bean.getLoadingStart().get(i));
						} catch (Exception e) {
							addActionError("Please Enter Valid Minimum Loading % for "+getSchemeList().get(i).get("SCHEME_NAME"));
						}
	    			}
	    			if(StringUtils.isBlank(bean.getLoadingEnd().get(i))){
	    				addActionError("Please Enter Maximum Loading % for "+getSchemeList().get(i).get("SCHEME_NAME"));
	    			}else{
	    				try {
							Double.parseDouble(bean.getLoadingEnd().get(i));
						} catch (Exception e) {
							addActionError("Please Enter Valid Maximum Loading % for "+getSchemeList().get(i).get("SCHEME_NAME"));
						}
	    			}
	    			if(StringUtils.isBlank(bean.getDiscountStart().get(i))){
	    				addActionError("Please Enter Minimum Discount % for "+getSchemeList().get(i).get("SCHEME_NAME"));
	    			}else{
	    				try {
							Double.parseDouble(bean.getDiscountStart().get(i));
						} catch (Exception e) {
							addActionError("Please Enter Valid Minimum Discount % for "+getSchemeList().get(i).get("SCHEME_NAME"));
						}
	    			}
	    			if(StringUtils.isBlank(bean.getDiscountEnd().get(i))){
	    				addActionError("Please Enter Maximum Discount % for "+getSchemeList().get(i).get("SCHEME_NAME"));
	    			}else{
	    				try {
							Double.parseDouble(bean.getDiscountEnd().get(i));
						} catch (Exception e) {
							addActionError("Please Enter Valid Maximum Discount % for "+getSchemeList().get(i).get("SCHEME_NAME"));
						}
	    			}
	    			if(StringUtils.isBlank(bean.getVolumeDiscount().get(i))){
	    				addActionError("Please Enter Volume Discount Limt for "+getSchemeList().get(i).get("SCHEME_NAME"));
	    			}else{
	    				try {
							Double.parseDouble(bean.getVolumeDiscount().get(i));
						} catch (Exception e) {
							addActionError("Please Enter Valid Volume Discount Limt for "+getSchemeList().get(i).get("SCHEME_NAME"));
						}
	    			}
	    			if(StringUtils.isBlank(bean.getCorporateDiscount().get(i))){
	    				addActionError("Please Enter Corporate Discount Limit for "+getSchemeList().get(i).get("SCHEME_NAME"));
	    			}else{
	    				try {
							Double.parseDouble(bean.getCorporateDiscount().get(i));
						} catch (Exception e) {
							addActionError("Please Enter Valid Corporate Discount Limit for "+getSchemeList().get(i).get("SCHEME_NAME"));
						}
	    			}
	    			if(StringUtils.isBlank(bean.getSpecialDiscount().get(i))){
	    				addActionError("Please Enter Special Discount Limit for "+getSchemeList().get(i).get("SCHEME_NAME"));
	    			}else{
	    				try {
							Double.parseDouble(bean.getSpecialDiscount().get(i));
						} catch (Exception e) {
							addActionError("Please Enter Special Discount Limit for "+getSchemeList().get(i).get("SCHEME_NAME"));
						}
	    			}
	    			
	    		}
	    	}
	    }
    }
    
    public String changePwd(){
    	brokerInfo=service.getBrokerDetails(bean, bean.getAgencyCode(),branchCode);
    	bean.setDisplay("");
    	return "assignNew";
    }
    public String getBrokerAjax(){
    	if("brokerLists".equals(bean.getReqFrom())){
    		brokerList=service.getBrokerListAjax(branchCode, bean.getSearchBy(), bean.getSearchValue(), appId);
    	}else if("productselections".equals(bean.getReqFrom())){
    		if("editAjax".equals(bean.getMode1()) && !"ajax".equals(bean.getMode())){
    			productData=service.getProducts(bean);
    			if ("65".equals(bean.getProductID())){
    				service.brokerCommDtl(bean);
    			}else if("30".equals(bean.getProductID())){
    				service.loginUserDtl(bean);
    			}
    		}
    		else if ("65".equals(bean.getProductID()) && bean.getSumInsStart()!=null && bean.getSumInsEnd()!=null 
    				&& bean.getPolicyFee()!=null && bean.getOtherFee()!=null){
			      String sunInsSt=bean.getSumInsStart().get(0).replace("[", "").replace("]", "");
			      String sunInsEnd=bean.getSumInsEnd().get(0).replace("[", "").replace("]", "");
			      String polFee=bean.getPolicyFee().get(0).replace("[", "").replace("]", "");
			      String othFee=bean.getOtherFee().get(0).replace("[", "").replace("]", "");
			      sunInsSt=sunInsSt.replaceAll("\\s", "");
			      sunInsEnd=sunInsEnd.replaceAll("\\s", "");
	    		  polFee=polFee.replaceAll("\\s", "");
			      othFee=othFee.replaceAll("\\s", "");
			      List<String> sunInsStList = new ArrayList<String>(Arrays.asList(sunInsSt.split(",")));
			      List<String> sunInsEndList = new ArrayList<String>(Arrays.asList(sunInsEnd.split(",")));
			      List<String> polFeeList = new ArrayList<String>(Arrays.asList(polFee.split(",")));
			      List<String> othFeeList = new ArrayList<String>(Arrays.asList(othFee.split(",")));
		    	  bean.setSumInsStart(sunInsStList);
			      bean.setSumInsEnd(sunInsEndList);
			      bean.setPolicyFee(polFeeList);
			      bean.setOtherFee(othFeeList);
    		}
    		else if ("30".equals(bean.getProductID()) 
    				&& bean.getLoadingStart()!=null && bean.getLoadingEnd()!=null 
    				&& bean.getDiscountStart()!=null && bean.getDiscountEnd()!=null 
    				&& bean.getVolumeDiscount()!=null&& bean.getCorporateDiscount()!=null && bean.getSpecialDiscount()!=null){
			      
			      String loadingStart=bean.getLoadingStart().get(0).replace("[", "").replace("]", "");
			      String loadingEnd= bean.getLoadingEnd().get(0).replace("[", "").replace("]", "");
			      String discountStart=bean.getDiscountStart().get(0).replace("[", "").replace("]", "");
			      String discountEnd=bean.getDiscountEnd().get(0).replace("[", "").replace("]", "");
			      String volumeDiscount=bean.getVolumeDiscount().get(0).replace("[", "").replace("]", "");
			      String corporateDiscount=bean.getCorporateDiscount().get(0).replace("[", "").replace("]", "");
			      String specialDiscount=bean.getSpecialDiscount().get(0).replace("[", "").replace("]", "");
			      
			      loadingStart=loadingStart.replaceAll("\\s", "");
			      loadingEnd=loadingEnd.replaceAll("\\s", "");
			      discountStart=discountStart.replaceAll("\\s", "");
			      discountEnd=discountEnd.replaceAll("\\s", "");
			      volumeDiscount=volumeDiscount.replaceAll("\\s", "");
			      corporateDiscount=corporateDiscount.replaceAll("\\s", "");
			      specialDiscount=specialDiscount.replaceAll("\\s", "");
			      
			      List<String> loadingStartList=new ArrayList<String>(Arrays.asList(loadingStart.split(",")));
			      List<String> loadingEndList=new ArrayList<String>(Arrays.asList(loadingEnd.split(",")));
			      List<String> discountStartList=new ArrayList<String>(Arrays.asList(discountStart.split(",")));
			      List<String> discountEndList=new ArrayList<String>(Arrays.asList(discountEnd.split(",")));
			      List<String> volumeDiscountList=new ArrayList<String>(Arrays.asList(volumeDiscount.split(",")));
			      List<String> corporateDiscountList=new ArrayList<String>(Arrays.asList(corporateDiscount.split(",")));
			      List<String> specialDiscountList=new ArrayList<String>(Arrays.asList(specialDiscount.split(",")));
			      
		    	  bean.setLoadingStart(loadingStartList);
			      bean.setLoadingEnd(loadingEndList);
			      bean.setDiscountStart(discountStartList);
			      bean.setDiscountEnd(discountEndList);
			      bean.setVolumeDiscount(volumeDiscountList);
			      bean.setCorporateDiscount(corporateDiscountList);
			      bean.setSpecialDiscount(specialDiscountList);
    		}
    		productList=cservice.getProductsDET(branchCode,bean.getAgencyCode());
    	}
    	return "adminAjax";
    }
    public String deleteProduct(){
    	service.deleteProduct(bean);
    	edit();
    	bean.setMode1("");
    	bean.setProductID("");
    	bean.setIndex("1");
    	return "edit";
    }
    
    public List<Map<String,Object>> getBranchList(){
		return cservice.getBranchList();
	}
    public List<Map<String,Object>> getSubBranchList(){
		return cservice.getSubBranchList(bean.getBranchId());
	}
    
    public String subBranch(){
    	return "subBranchList";
    }
	
}