package com.maan.userMgt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.adminnew.common.CommonService;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.common.Validation;
import com.maan.common.dao.CommonDAO;
import com.maan.common.login.CommonBean;
import com.maan.common.login.LogInAction;
import com.maan.common.login.LogInService;
import com.maan.common.password.passwordEnc;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.common.util.ResourceBundleUtil;
import com.maan.common.util.StringUtil;
import com.maan.customer.dao.CustomerDAO;
import com.maan.customer.service.CustomerService;
import com.maan.services.util.ValidationFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class UserMgtAction extends ActionSupport implements ModelDriven<UserMgtBean> {

	private static final long serialVersionUID = 1L;

	private UserMgtBean bean = new UserMgtBean();
	private CommonService cservice=new CommonService();
	private UserMgtService service = new UserMgtService();
	private CommonDAO commonDAO = new CommonDAO();
	
	Map<String, Object> session=null;
	private String branchCode=null;
	/*****
	 * Redirect Action
	 */
	private static final String MOTOR_SUCCESS = "motorsuccess";
	private static final String HOME_SUCCESS = "homesuccess";
	private static final String TRAVEL_SUCCESS = "travelsuccess";

	public UserMgtBean getModel() {
		bean.setProductId((String) getSession().get("product_id"));
		bean.setBranchCode((String) getSession().get("BelongingBranch"));
		bean.setAppId((String) getSession().get("appID"));
		bean.setPwdCount((String) getSession().get("pwdCount"));
		bean.setUser(getSession().get("user")==null?"":getSession().get("user").toString());
		bean.setLoginId(getSession().get("user")==null?"":getSession().get("user").toString());
		return bean;
	}
	public Object[] getParams(){
		Object[] objects=new String[]{"",bean.getProductId(),bean.getBranchCode(),"","","","","","","","",bean.getUser(),""};
		return objects;
	}
	public List<Object> getTitleList(){
		return commonDAO.getOptionsList("title", getParams());
	}
	public List<Object> getCityList(){
		return commonDAO.getOptionsList("city", getParams());
	}
	public List<Object> getOccupationList() {
		return commonDAO.getOptionsList("Occupation", getParams());
	}
	public void mapRestBean(UserMgtBean bean) {
		bean.setBranchCode("01");
		bean.setAppId("16");
		bean.setPwdCount("3");
		bean.setActionErrorsBean(new ArrayList<String>());
		bean.setActionMessagesBean(new ArrayList<String>());
		this.bean = bean;
	}
	
	public String userLogin(){
		String returnvalue="";
		String status1 = "";
		try{
			bean.setMode("login");
			LogManager.info("Enter==>userSubmit()");
			if(StringUtils.isEmpty(bean.getUserLoginId())) {
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.USERMGT_PROPERTYID,"loginid.empty"));
				} else {
					addActionError(getText("loginid.empty"));
				}
			}
			if(StringUtils.isEmpty(bean.getUserPwd())){
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.USERMGT_PROPERTYID,"pwd.empty"));
				} else {
					addActionError(getText("pwd.empty"));
				}
			}
			if(!hasErrorValues()) {
				String type = service.checkUserLoginId(bean,bean.getAppId());
				if(type.equalsIgnoreCase("User")){
					LogInService lservice=new LogInService();
					String[] statuses=lservice.validateUser(bean.getUserLoginId(), bean.getUserPwd(),"",bean.getAppId(),bean.getPwdCount());
					status1=statuses[0];
					if(status1 == null || status1.equalsIgnoreCase("changepwd")){
						bean.setUserId(bean.getUserLoginId());
						if(StringUtils.isNotBlank(bean.getApplicationNo())){
							//Update loginId Home_position_master 
							service.updateUserQuote(bean.getUserId(), bean.getApplicationNo());
							CommonBean cbean = new CommonBean();
							cbean.setLoginId(bean.getUserLoginId());
							bean.setLoginId(bean.getUserLoginId());
							bean.setUtype(type);
							//Update customer Personal Details based on user id
							new CustomerDAO().updateCustomerPersonal(bean.getUserId(),bean.getApplicationNo());
						}
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.setUser(bean.getUserId());
							bean.setUserType(bean.getUtype());
						} else {
							service.updateSession(bean.getUserId(), getSession().get("ses").toString());
							getSession().put("BROKER_LOGIN_ID", bean.getUserId());
							getSession().put("user", bean.getUserId());
							getSession().put("usertype", bean.getUtype());
							getSession().put("LoginType", "");
						}
					}
					else {
						if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.USERMGT_PROPERTYID,status1));
						} else {
							addActionError(getText(status1));
						}
					}
			    } else {
			    	if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.USERMGT_PROPERTYID,"error.login.not.user"));
					} else {
						addActionError(getText("error.login.not.user"));
					}
				}
			}
			if(hasErrorValues()) {
				
				returnvalue = edit();
			}
			else if("changepwd".equalsIgnoreCase(status1)){
				bean.setReqFrom("userChangePwd");
				returnvalue ="changePwd";
			} else {
				new CustomerDAO().setCustomerDetails(bean);
				if("65".equals(bean.getProductId())) {
					returnvalue = MOTOR_SUCCESS;
				} else if("30".equals(bean.getProductId())) {
					returnvalue = HOME_SUCCESS;
				}else if("33".equalsIgnoreCase(bean.getProductId())){
					returnvalue = TRAVEL_SUCCESS;
				}
			}
			LogManager.info("Exit==>userSubmit()");
		}catch (Exception e) {
			LogManager.info("Exception @ userSubmit(){"+e+"}");
		}
		return returnvalue;
	}
	public String forgotPwd(){
		try{
			LogManager.info("Enter into user forgotPwd");
			bean.setReqFrom("forgetPwd");
			if(StringUtils.isBlank(bean.getUserLoginId1())){
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"E105"));
				} else {
					addActionError(getText("E105"));	//Please Enter UserName
				}
			}else if(cservice.getAdminInfo(bean.getUserLoginId1()).size() <= 0){
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add("Invalid UserId");
				} else {
					addActionError(getText("Invalid UserId"));	//Please Enter valid UserName
				}
				
			}
			if(!DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) && !DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				if(StringUtils.isBlank(bean.getUserEmail())){
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"E106"));
					} else {
						addActionError(getText("E106"));	//Please Enter Email Id
					}
				}
				else if(!new LogInAction().validEmail((bean.getUserEmail()))){
					if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"E108"));
					} else {
						addActionError(getText("E108"));	//Please Enter valid Email Id
					}
				}
			}
			if(!hasErrorValues()){
				
				if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.setUserEmail(service.getCustomerEmail(bean.getUserLoginId1(),bean.getAppId()));
				}
				Object obj[]={bean.getUserLoginId1().trim(),bean.getUserEmail().trim(), bean.getAppId()};
	        	LogInService lservice = new LogInService();
	        	List list=lservice.validateMailForgot(obj);
	        	if(list!=null && list.size()>0){
					String temp="tempPwd";
			    	String status1=lservice.sendUserPwd(bean.getUserLoginId1().trim(),temp,  bean.getAppId());
			    	if (status1 == null) {
			    		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionMessagesBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"forgot.pass.success"));
						} else {
							addActionMessage(getText("forgot.pass.success"));
						}
			    	}
			    	if(status1!=null){
			    		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
							bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,status1));
						} else {
							addActionError(status1);
						}
			    	}
	        	}
	        	else if(list==null || list.size()==0){
	        		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
						bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"E109"));
					} else {
						addActionError(getText("E109"));	//UserName and Email Id not match for this application
					}
	        	}
			}
			LogManager.info("Exit into user forgotPwd");
		}catch (Exception e) {
			LogManager.debug("Exception @ forgotPwd {"+e+"}");
		}
		return "userAjax";
	}
	
	public String edit() {
		if(!hasActionErrors())
			new CustomerDAO().setCustomerDetails(bean);
		//service.getCustomerDetails(bean);
		String count = service.getEmailCount(bean);
		if(Integer.parseInt(count) > 0){
			bean.setDisplay("exist");
		} else {
			bean.setCity(StringUtils.isBlank(bean.getCity())?ResourceBundleUtil.findDefaultText("MOTOR_DEFAULT_CITY"):bean.getCity());
			bean.setDisplay("new");
		}
		return "editMotorUser";
	}
	public String add(){
		bean.setDisplay("new");
		if(StringUtils.isBlank(bean.getMobileNo()))
			bean.setMobileNo("09");
		return "editMotorUser";
	}
	/*
	 * Adding New User with out generate quick quote
	 
	public String addNewuser(){
		String forward="";
		try{
			LogManager.info("Enter Into addNewUser()");
			addUser();
			bean.setMode("new");
			validatenewUser();
			if(!hasActionErrors()){
				passwordEnc pass = new passwordEnc();
				String customerId = new CommonDAO().getSequenceNo("CUSTOMER_ID",(String) session.get("product_id"),branchCode,"","");
				//String OACode = service.getOACode(branchCode);
				bean.setAgencyCode(getText("B2C_AGENCY_CODE"));
				//bean.setUagencyCode(cservice.getUserCode(branchCode));
				Object[] args={customerId, appId, bean.getUtitle(), bean.getUfirstname(), bean.getUlastname(), "1", bean.getUnationality()==null?"":bean.getUnationality(), bean.getUdob(), bean.getUgender()==null?"":bean.getUgender(), bean.getUphone(), bean.getUmobile(),
						bean.getUfax()==null?"":bean.getUfax(), bean.getUemail(), bean.getUaddress1(), bean.getUaddress2(), bean.getUoccupation(), bean.getUpobox(), bean.getUcountry()==null?"":bean.getUcountry(),bean.getUcity(),"Y", bean.getUserId(), bean.getAgencyCode(),bean.getAgencyCode(), bean.getUcity(),""};
				
				String args1[]={bean.getUserId(), pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()), bean.getUtype(), bean.getUfirstname(),"1", bean.getAgencyCode(),bean.getUagencyCode(), "BOTH",
						"","","","","1",bean.getAgencyCode(), "Y", "N", "N", "Y", bean.getUemail(), branchCode, bean.getUcountry()==null?"":bean.getUcountry()};
				service.insertUserInfo(args,args1);
				//addProduct();
				service.addProducts(bean.getUserId(),bean.getUagencyCode(), (String)session.get("user"));
				session.put("BROKER_LOGIN_ID", bean.getUserId());
				session.put("user", bean.getUserId());
				session.put("usertype", bean.getUtype());
				session.put("LoginType", "");
				//update session
				service.updateSession(bean.getUserId(),session.get("ses").toString());
				bean.setDisplay("successNew");
			}
			
			LogManager.info("Exit Into addNewUser()");
		}catch(Exception e){
			LogManager.debug("Exception @ addNewUser() {"+e+"}");
		}
		if(hasActionErrors()) {
			forward = "addMotorUser";
		}
		else {
			forward = "homeUser";
		}
		return forward;
	}*/
	public String newUser() {
		String forward = "";
		try {
			addUser();
			if(!hasErrorValues()) {
				service.addProducts(bean.getUserId(),bean.getUagencyCode(), ResourceBundleUtil.findDefaultText("B2C_LOGIN_ID"));
			}
			if(!hasErrorValues()) {
				if(StringUtils.isNotBlank(bean.getApplicationNo()))
					service.updateUserQuote(bean.getUserId(), bean.getApplicationNo());
				if(!DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) && !DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					service.updateSession(bean.getUserId(),getSession().get("ses").toString());
					getSession().put("BROKER_LOGIN_ID", bean.getUserId());
					getSession().put("user", bean.getUserId());
					getSession().put("usertype", bean.getUtype());
					getSession().put("LoginType", "");
				}
				bean.setDisplay("successNew");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		} finally {
			if(!hasErrorValues()){
				if("65".equals(bean.getProductId())) {
					forward = MOTOR_SUCCESS;
				} else if("30".equals(bean.getProductId())) {
					forward = HOME_SUCCESS;
				}else if("33".equalsIgnoreCase(bean.getProductId())){
					forward = TRAVEL_SUCCESS;
				}else{
					forward = "homeUser";
				}
			}
			else{
				forward = "editMotorUser";
			}
		}
		return forward;
	}
	
	public String modify() {
		try {
			bean.setMode("update");
			String loginId = bean.getUser();
			bean.setUagencyCode(cservice.getAgencyCode(loginId));
			service.getUserDetails(bean, bean.getUagencyCode());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return "editUserProfile";
	}
	
	public String update() {
		try {
			addUser();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "editUserProfile";
	}

	private void addUser() throws Exception {
		LogManager.push("Enter==>newUser()");
		if(StringUtils.isEmpty(bean.getUserId()))
			bean.setUserId(bean.getEmail());//because of change login_id to email id
			bean.setUemail(bean.getEmail());
			validateUser();
		if(!hasErrorValues()){
			if("new".equalsIgnoreCase(bean.getDisplay())){
				passwordEnc pass = new passwordEnc();
				bean.setUagencyCode(cservice.getUserCode(bean.getBranchCode()));
				bean.setAgencyCode(ResourceBundleUtil.findDefaultText("B2C_AGENCY_CODE"));
				//Login Details insertion
				String args1[]={bean.getUserId(), pass.crypt(bean.getPassword()), bean.getUtype(), bean.getCustomerName(),"1", bean.getUagencyCode(), bean.getAgencyCode(), "BOTH",
						"","","","","1", bean.getAgencyCode(), "Y", "N", "N", "Y", bean.getEmail(), bean.getBranchCode(),""};
				service.insertUserInfo(args1);
				//Customer Details Insertion
				/*if(StringUtils.isNotBlank(bean.getCustnrc1()) && StringUtils.isNotBlank(bean.getCustnrc3()) && StringUtils.isNotBlank(bean.getCustnrc3()))
					bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
				else 
					bean.setCustnrc("");*/
				//New Customer Creation
				new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getUemail(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"newUser",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3());
				
				if(StringUtils.isNotBlank(bean.getApplicationNo())){
					bean.setCustomerId(new CustomerDAO().getCustomerId(bean.getApplicationNo()));
					//	Update Already Created One Customer
					new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getUemail(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"newUserCreation",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3());
					String args2[]={bean.getCustomerId(),bean.getApplicationNo()};
					service.updateCustomerIdMotorDataDetail(args2);
				}
				bean.setDisplay("successNew");
				//Sending sms
				new SmsEmailUtil(SmsEmailUtil.REG_SUCCES,bean.getPassword(),"",bean.getUserId(),bean.getCustomerName()).send();
				new SmsEmailUtil(SmsEmailUtil.REG_SUCCESS_OPUSER,bean.getPassword(),"",bean.getUserId(),bean.getCustomerName()).send();
			}
			else{
				//Update Profile Info
				/*if(StringUtils.isNotBlank(bean.getCustnrc1()) && StringUtils.isNotBlank(bean.getCustnrc3()) && StringUtils.isNotBlank(bean.getCustnrc3()))
					bean.setCustnrc(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3());
				else 
					bean.setCustnrc("");*/
				List<Map<String,Object>> custId = new CustomerDAO().getCustomerIdBasedOnLoginId(bean.getUser());
				if(custId!=null && custId.size()>0){
					for(int i=0;i<custId.size();i++){
						bean.setCustomerId(custId.get(i).get("CUSTOMER_ID")==null?"":custId.get(i).get("CUSTOMER_ID").toString());
						if(StringUtils.isNotBlank(bean.getCustomerId()))
							new CustomerService().insertCustomerDetails(bean.getCustomerId(),bean.getUser(),bean.getBranchCode(),bean.getProductId(),bean.getTitle(),bean.getCustomerName(),bean.getMobileNo(),bean.getEmail(),bean.getAddress1(),bean.getAddress2(),bean.getPoBox(),bean.getCity(),bean.getCoreAppCode(),bean.getClientCustomerId(),bean.getCustArNo(),bean.getCustLastName(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getCustNameArabic(),bean.getCustdobar(),bean.getGender(),bean.getOccupation(),"profile",bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3());
					}
				}
				String args1[]={bean.getCustomerName(),"Y", bean.getEmail(),bean.getUagencyCode()};
    			service.updateUserInfo(args1);
    			bean.setDisplay("successUpdate");
    		}
		}
		//commisionDetails=service.getCommisionData(bean.getUagencyCode(), bean.getAgencyCode(), branchCode);
	}
	
	/*public void addProduct() throws Exception  {
		LogManager.push("ENTER===> addProduct");
		String returnResult="edit";
		try {
			int count=cservice.checkExistProduct(productId, bean.getUagencyCode());
			String ocover="";
			if(count==0){
				Object[] info ={cservice.getMaxUserId(), bean.getUfirstname(),bean.getUagencyCode(), bean.getUagencyCode(), bean.getAgencyCode(), productId, "1", "", "", "", "",
						"0", "1", "", "", "", "Y", "","","","","","","","","", "","", ocover, "","30".equals(productId)?"7":""};

				for(Object k: info){
					LogManager.info("info===>" + k);
				}
				Object obj[]=null;
				cservice.insertCommission(info, obj);
				bean.setDisplay("newsuccess");
			}else{
				Object[] args = {"",  "", "Y", "", "", "", "", "", "", "", "", ocover, "", bean.getUagencyCode(),productId};
				for(Object k: args){
					LogManager.info("args===>" + k);
				}
				Object obj[]=null;
				cservice.updateCommission(args, obj);
				bean.setDisplay("updatesuccess");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public String pwdChange() {
		LogManager.info("Enter==>pwdChange()");
		String status1;
		String returnResult = "changePWD";
		Validation validation=new Validation();
    	if (bean.getUserPwd() == null || "".equals(bean.getUserPwd().trim())) {
    		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"E101"));
			} else {
				addActionError(getText("E101"));	//UserName and Email Id not match for this application
			}
		}if (bean.getPassword() == null || "".equals(bean.getPassword().trim())) {
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"E102"));
			} else {
				addActionError(getText("E102"));	//UserName and Email Id not match for this application
			}
		}else if (!bean.getPassword().equals(bean.getPassword())) {
			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.APP_FIELD_NAMES_ID,"E103"));
			} else {
				addActionError(getText("E103"));	//UserName and Email Id not match for this application
			}
 		}else if(!validation.validPassword(bean.getPassword())){
 			if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
				bean.getActionErrorsBean().add(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH, new String[]{"6", "20"}));
			} else {
				addActionError(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH,  new String[]{"6", "20"}));
			}
		}
    	if (!hasErrorValues()) {
    		status1=new LogInService().changePassword(bean.getLoginId(),bean.getPassword(),bean.getAppId());
    		if(status1==null){
    			bean.setReqFrom("success");
    		}
	    	else{
	    		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
					bean.getActionErrorsBean().add(status1);
				} else {
					addActionError(status1);	//UserName and Email Id not match for this application
				}
	    		
	    	}
    	}
    	LogManager.info("Exit==>pwdChange()");
        return returnResult;
    	
    }
	private void validatenewUser(){
		List<String> list=new CustomerService().getMotorCustomerValidate("",bean.getBrokerCode(),bean.getExecutive(),bean.getTitle(),bean.getCustomerName(),bean.getCity(),bean.getPoBox(),bean.getMobileNo(),bean.getEmail(),bean.getCustLastName(),bean.getCustnrc1(),bean.getCustnrc2(),bean.getCustnrc3(),bean.getCustPassportNo(),bean.getCustdob(),bean.getCustAlterMobileNo(),bean.getCustLandLineNo(),bean.getCustomerType(),bean.getCompanyRegNo(),bean.getAddress1(),bean.getGender(),bean.getOccupation());
		Validation validation=new Validation();
		if("new".equals(bean.getDisplay())){
			if(StringUtils.isEmpty(bean.getPassword()))
				list.add("error.quotation.newpassword");
			else if(StringUtils.contains(bean.getPassword(), " "))
				list.add("error.quotation.valid.white.newpassword");
			if(StringUtils.isEmpty(bean.getRepassword())){
				list.add("error.quotation.repassword");
			}else if(StringUtils.contains(bean.getRepassword(), " "))
				list.add("error.quotation.valid.white.repassword");
			else if(!bean.getPassword().equals(bean.getRepassword())){
				list.add("error.quotation.password.different");
			}else if(!validation.validPassword(bean.getPassword())){
				list.add("error.quotation.password.invalid");
			}else if(cservice.getAdminInfo(bean.getUserId()).size()>0){
				list.add("error.loginid.notavailable");
			}
		}
		if(DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) {
			for (String st : list) {
				if(st.indexOf("#")!=-1) {
					Object args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.USERMGT_PROPERTYID,st.substring(0,st.indexOf('#')),args));
				} else {
					bean.getActionErrorsBean().add(ResourceBundleUtil.findText(DBConstants.USERMGT_PROPERTYID,st));
				}
			}
		} else {
			for (String st : list) {
				if(st.indexOf("#")!=-1) {
					String args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
					this.addActionError(getText(st.substring(0,st.indexOf('#')),args));
				} else {
					this.addActionError(getText(st));	
				}
			}
		}
	}
	
	private void validateUser(){
		LinkedList<String> list=new CustomerService().validateCustomerInfo(bean,"profile");
		Validation validation=new Validation();
		if("new".equals(bean.getDisplay())){
			if(StringUtils.isEmpty(bean.getPassword()))
				list.add("error.quotation.newpassword");
			else if(StringUtils.contains(bean.getPassword(), " "))
				list.add("error.quotation.valid.white.newpassword");
			if(StringUtils.isEmpty(bean.getRepassword())){
				list.add("error.quotation.repassword");
			}else if(StringUtils.contains(bean.getRepassword(), " "))
				list.add("error.quotation.valid.white.repassword");
			else if(!bean.getPassword().equals(bean.getRepassword())){
				list.add("error.quotation.password.different");
			}else if(!validation.validPassword(bean.getPassword())){
				list.add("error.quotation.password.invalid");
			}else if(bean.getPassword().length() < 7){
				list.add("error.quotation.password.invalid");
			}else if(cservice.getAdminInfo(bean.getUserId()).size()>0){
				list.add("error.loginid.notavailable");
			}
		}
		for (String st : list) {
			if(st.indexOf("#")!=-1) {
				String args[]=(st.indexOf("~")!=-1?(st.substring(st.indexOf('#')+1, st.lastIndexOf('#'))).split("~"):new String[]{st.substring(st.indexOf('#')+1)});
				this.addActionError(getText(st.substring(0,st.indexOf('#')),args));
			} else {
				this.addActionError(LocalizedTextUtil.findDefaultText(st,Locale.ENGLISH));
			}
		}
		
	}
	
	public List <Object> getTitlesInfo() {
		return cservice.getTitles(bean.getBranchCode());
	}
	public List <Object> getNationalitiesInfo() {
		return cservice.getNationalities();
	}
	public List <Object> getEmiratesInfo() {
		return cservice.getEmirates(bean.getBranchCode());
	}
	public List <Object> getCountriesInfo() {
		return cservice.getCountries(bean.getBranchCode());
	}
	
	
	private Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}
	private boolean hasErrorValues() {
		boolean result = false;
		if((DBConstants.DEVICETYPE_ANDROID.equals(bean.getDeviceType()) || DBConstants.DEVICETYPE_HYBRID.equals(bean.getDeviceType())) && bean.getActionErrorsBean().size()>0) {
			result = true;
		} else if(hasActionErrors()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	public void addMobUser() throws Exception {
		newUser();
	}
	public void setBeanForMobile(String mobileNo,String email, String customerName, String custNameArabic, String custdob,
			String companyRegNo,String customerId,String applicationNo,String quoteNo,String productId){
		LogManager.info("Enter into setBeanForMobile");
		bean.setUserId(mobileNo);//because of change login_id to email id
		bean.setUemail(email);
		bean.setDisplay("new");
		bean.setPassword("Admin@01");
		bean.setRepassword("Admin@01");
		bean.setCustomerId(customerId);
		bean.setTitle("Mr/Mrs");
		bean.setCustomerName(customerName);
		bean.setCustdob(custdob);
		bean.setCompanyRegNo(companyRegNo);
		//bean.setNationality("2");
		bean.setOccupation("1");
		bean.setUtype("User");
		bean.setApplicationNo(applicationNo);
		bean.setQuoteNo(quoteNo);
		bean.setLoginId(mobileNo);
		if(cservice.getAdminInfo(bean.getUserId()).size()>0){
			//Login
			LogManager.info("Enter into login Update condition");
			service.updateLoginId(bean);
		}else{
			//Create Login
			LogManager.info("Enter into new login condition");
			if("65".equalsIgnoreCase(productId)){
				if(StringUtil.isBlankOrNull(bean.getTitle())){
					bean.setTitle("Mr");
				}
				if(StringUtil.isBlankOrNull(bean.getCustomerName())){
					bean.setCustomerName("Guest");
				}
			}
			createLogin();
			new SmsEmailUtil("USER_REGISTER",bean.getQuoteNo()).send();	
			//Login
		}
		LogManager.info("Exit from setBeanForMobile");
	}
	public void createLogin(){
		LogManager.info("Enter into createLogin");
		validateUser();
		if(getActionErrors().isEmpty()){
			if("new".equalsIgnoreCase(bean.getDisplay())){
				branchCode=StringUtils.isBlank(branchCode)?"01":branchCode;
				String user="";
				try{
					user=(String)session.get("user");
				}catch (Exception e) {
					user="madisondirect"; // LOCAL & LIVE
					//user="guestmotor";    // UAT
				}
				passwordEnc pass = new passwordEnc();
				bean.setUagencyCode(cservice.getUserCode(branchCode));
				bean.setAgencyCode(getText("B2C_AGENCY_CODE"));
				String args1[]={bean.getUserId(), pass.crypt(bean.getPassword()), bean.getUtype(), StringUtils.isBlank(bean.getUfirstname())?bean.getCustomerName():bean.getUfirstname(),"1", bean.getUagencyCode(), bean.getAgencyCode(), "BOTH",
						"","","","","1", bean.getAgencyCode(), "Y", "N", "N", "Y", bean.getUemail(), branchCode, bean.getUcountry()==null?"":bean.getUcountry()};
				service.insertUserInfo(args1,"MOBILE");
				String customerId =service.insertCustomerMob(bean,"newUser",bean.getCustomerId());
				service.addProducts(bean.getUserId(),bean.getUagencyCode(),user );
				LogManager.info("Exit from createLogin");
			}
		}
	}
	
}
