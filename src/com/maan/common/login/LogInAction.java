package com.maan.common.login;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogManager;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.common.otp.OTPGenerator;
import com.maan.common.sms.CryptoService;
import com.maan.common.sms.SmsEmailUtil;
import com.maan.opencover.bean.newCoverBean;
import com.maan.product.ProductSelection;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class LogInAction extends ActionSupport  implements SessionAware, ServletRequestAware, ModelDriven<CommonBean>{
	final Logger logger=LogUtil.getLogger(LogInAction.class);
	private static final long serialVersionUID = 10001L;
	private HttpServletRequest request = ServletActionContext.getRequest();
	ServletContext context=request.getSession().getServletContext();
	private LogInService service=new LogInService();
	private CommonBean bean=new CommonBean(); 
	CommonService cservice=new CommonService();
	private Map<String, Object> session = null;	
	private String appId="16";
	private String b2cType = "";
	Validation validation = new Validation();
    
	public void setSession(Map<String, Object> map) {
	    this.session = map;
	}
	public void setServletRequest(HttpServletRequest hsr) {
	    this.request = hsr;
	}

	public String page(){
		return "page";
    }
	public String AdminLogin(){
		return "page";
	}
	public String submit() {
        String result="page";
        logger.info("Enter==>submit()");
        assignLogin();
        validlogin();
        Map <String, String>rs1=service.getMailDetails(appId);
        String pwdlen[]=rs1.get("PWD_LEN").toString().split("-");
        session.put("expiTime", rs1.get("EXP_TIME"));
        session.put("expiDate", rs1.get("EXP_DATE"));
        session.put("pwdCount", rs1.get("PWD_CNT"));
        session.put("pwdLenMin", pwdlen[0]);
        session.put("pwdLenMax", pwdlen[1]);
        session.put("appID", appId);
        System.out.println("Login::::"+ bean.getBranch());
	    if (getActionErrors().size() <= 0) {
    	String[] statuses=service.validateUser(bean.getLoginId(), bean.getPwd(),"",(String)session.get("appID"),"5");//===>check method through UserId, Password
	    	String status1=statuses[0];
	        if(status1==null){
	        	String userStatus=statuses[1];
	        	boolean pass=false;
	        	if("Y".equalsIgnoreCase(bean.getB2cCustYN()))
	        		pass=true;
	        	else
	        		pass=service.checkPasswordChange(bean.getLoginId(), userStatus,(String) session.get("expiTime"),(String) session.get("expiDate"),(String) session.get("appID"));
	        	
	        	if(pass){
	        		List userDetails=service.getUserInfo(bean.getLoginId(), (String)session.get("appID"));
					 if (userDetails == null || userDetails.size()<=0) 
			        	addActionError(getText("E110"));
			         else{
			        	if("changepwd".equalsIgnoreCase(bean.getStatus())){
			        		bean.setStatus("changepwd");
							result = "changePWD";
			        	}else{
			        		service.insertSessionInfo(bean.getLoginId(), request.getSession(false).getId(), service.getClientIpAddr(request));
			        		if (userDetails != null && userDetails.size()>0){
			        			Map rs=(Map)userDetails.get(0);
			                    if("admin".equalsIgnoreCase(rs.get("USERTYPE").toString()) || "creditcontroller".equalsIgnoreCase(rs.get("USERTYPE").toString()) || "surveyor".equalsIgnoreCase(rs.get("USERTYPE").toString()) || "underwriter".equalsIgnoreCase(rs.get("USERTYPE").toString()) ){
		                    		//session.put("MenuList", cservice.getMenuList(rs.get("MENU_ID")==null?"":rs.get("MENU_ID").toString(), rs.get("BELONGING_BRANCH").toString(),bean.getProduct()));
			                    	session.put("MENU_ID", rs.get("MENU_ID")==null?"":rs.get("MENU_ID").toString());
			                    	session.put("user", rs.get("LOGIN_ID"));
		                    		session.put("user1", rs.get("USERTYPE"));
		                    		session.put("usertype", "admin");
		                    		if(!("Broker".equalsIgnoreCase(rs.get("USERTYPE").toString()) || "User".equalsIgnoreCase(rs.get("USERTYPE").toString()))){
			                    		session.put("BranchCode",bean.getBranch());
			                    		session.put("AdminBranchCode",bean.getBranch());
			                    		session.put("LoginBranchCode", bean.getBranch());
			                    		session.put("adminBranch", bean.getBranch());
			                    		//result = "adminHome";
			                    		result = "home";
		                    		}else{
		                    			addActionError(getText("E128"));
		                    			result = INPUT;
		                    		}
		                    		session.put("branchName", rs.get("BRANCH_NAME"));
		                    		session.put("AdminCountryId", rs.get("COUNTRY_ID").toString());
		                    		session.put("AppId", "2");
		                    		session.put("ses", request.getSession(false).getId());
		                    		session.put("userLoginMode", context.getRealPath("").indexOf("Test")!=-1?"Test":"Live");
		                    		session.put("swidth", bean.getSwidth());
		                    		session.put("BrokerDetails", cservice.getBrokerUserDetails(rs.get("BRANCH_CODE").toString()));
		                    		
		                    		String[][] currenctDetials = new newCoverBean().getCurrencyName(rs.get("BRANCH_CODE").toString());
		                    		session.put("currencyType",currenctDetials[0][0]!=null?currenctDetials[0][0]:"SAR");
		                    		session.put("decimalPlace",(currenctDetials[0][1]!=null?currenctDetials[0][1]:"2"));
		                    		
		                    		session.put("BelongingBranch",rs.get("BELONGING_BRANCH"));
		                    		session.put("Attached_Branch", (rs.get("ATTACHED_BRANCH")==null || "".equals(rs.get("ATTACHED_BRANCH").toString()))?rs.get("BRANCH_CODE"):rs.get("ATTACHED_BRANCH"));
		                    		session.put("accesstype", rs.get("ACCESSTYPE"));
		                    		//session.put("product_id",bean.getProduct());
		                    	}
			                    else{
			                    	String userType=rs.get("USERTYPE").toString();
			                    	String loginType=bean.getLoginType();
			                    	boolean issuerCondition=("RSAIssuer".equalsIgnoreCase(userType) && ("admin".equalsIgnoreCase(loginType)||"user".equalsIgnoreCase(loginType))) ;
			                    	boolean brokerCondition=(!"RSAIssuer".equalsIgnoreCase(userType) &&  ("Broker".equalsIgnoreCase(loginType) || "".equalsIgnoreCase(loginType)  || "user".equalsIgnoreCase(loginType) ));
			                    	
			                    	if( issuerCondition || brokerCondition ){			                    	
			                    		if(issuerCondition ){
			                    			String attachedBranch=(rs.get("ATTACHED_BRANCH")==null || "".equals(rs.get("ATTACHED_BRANCH").toString()))?"":rs.get("ATTACHED_BRANCH").toString();
			                    			if("".equals(attachedBranch)){
			                    				addActionError(getText("E129")); // For Issuer Attached branch is Empty				                    			
				                    		}else if(StringUtils.isNotBlank(bean.getBranch()) && !attachedBranch.contains(bean.getBranch())){
				                    			addActionError(getText("E130")); // For Issuer Attached branch is Empty				                    			
				                    		}			                    				
			                    		}else if(brokerCondition){
			                    			String attachedBranch=(rs.get("ATTACHED_BRANCH")==null || "".equals(rs.get("ATTACHED_BRANCH").toString()))?"":rs.get("ATTACHED_BRANCH").toString();
			                    			if("".equals(attachedBranch)){
			                    				addActionError("This User is not Attached for any Branch"); // For Broker/User Attached branch is Empty				                    			
				                    		}else if(StringUtils.isNotBlank(bean.getBranch()) && !attachedBranch.contains(bean.getBranch())){
				                    			addActionError("This User are not Attached for this Branch to Login"); // For Broker/User Attached branch is Wrong				                    			
				                    		}	
			                    		}
			                    		if(!hasActionErrors()){
			                    			session.put("ses", request.getSession(false).getId());
				                    		session.put("user1", "brokers");
				                    		session.put("rsa_type","s");
				                    		session.put("usertype", rs.get("USERTYPE"));
				                    		session.put("user", rs.get("LOGIN_ID"));
				                    		session.put("userLoginMode", context.getRealPath("").indexOf("Test")!=-1?"Test":"Live");
				                    		session.put("swidth", bean.getSwidth());				                    		
				                    		String[][] currenctDetials = new newCoverBean().getCurrencyName(rs.get("BRANCH_CODE").toString());
				                    		session.put("currencyType",currenctDetials[0][0]!=null?currenctDetials[0][0]:"SAR");
				                    		session.put("decimalPlace",(currenctDetials[0][1]!=null?currenctDetials[0][1]:"2"));				                    		
				                    		session.put("BelongingBranch",rs.get("BELONGING_BRANCH"));	
				                    		session.put("LoginType", b2cType);
				                    		session.put("LoginBranchCode", rs.get("BRANCH_CODE").toString());
				                    		session.put("BranchCode", rs.get("BRANCH_CODE").toString());
				                    		session.put("Attached_Branch", (rs.get("ATTACHED_BRANCH")==null || "".equals(rs.get("ATTACHED_BRANCH").toString()))?rs.get("BRANCH_CODE"):rs.get("ATTACHED_BRANCH"));
				                    		if("RSAIssuer".equalsIgnoreCase(userType)) {
				                    			session.put("ReferalYN", rs.get("REFERAL"));
				                    		}
				                    		result="home";
			                    		}else{
			                    			result=INPUT;
			                    		}			                    	 
			                    	}else{
			                    		addActionError(getText("E128"));
		                    			result = INPUT;
			                    	}
			                    }
			                    session.put("LoginIdType", bean.getLoginType());
			                    session.put("selectedBranch",bean.getBranch());
			                    //session.put("branchName",bean.getBranchName());
			                    session.put("branchName", StringUtils.isBlank(bean.getBranchName())?rs.get("BRANCH_NAME"):bean.getBranchName());
			                    //if("30".equalsIgnoreCase(bean.getProduct())){
				                    session.put("homeSumIns", bean.getSumInsured());
				                    session.put("homeSumInsList", bean.getSumInsuredList());
				                    session.put("ContentType", bean.getContentType());
				                    session.put("currencyType", bean.getCurrencyType());
				                    session.put("BrokerDetails", bean.getBrokerDetails());
				                    session.put("ProductDetails", bean.getProductDetails());
				                    session.put("scheme_id", bean.getScheme_id());
			                    //}
							}
			        	}
			        }
				}else{
					bean.setStatus("changepwd");
						result = "changePWD";
					}
	        }
	        else if("changepwd".equalsIgnoreCase(status1)){
	        	bean.setStatus("changepwd");
				result = "changePWD";
			}
	        else if("changePwd".equalsIgnoreCase(bean.getStatus())){
	        	addActionError(status1);
				result = "changePWD";
			}
	        else{
	        	addActionError(status1);
	        	result = INPUT;
			}
	    }
	    else if("changePwd".equalsIgnoreCase(bean.getStatus())){
	    	result = "changePWD";
	    }
	    else{
	    	result = INPUT;
	    }
	    if("changePWD".equals(result)){
	    	 bean.setPwdMsg(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH, new String[]{pwdlen[0], pwdlen[1]}));
	    }
        logger.info("Login Submit Method - Exit,result=> "+result);
        if("input".equalsIgnoreCase(result) || "page".equalsIgnoreCase(result)){
        	if(!"Broker".equalsIgnoreCase(bean.getLoginType())&&!"user".equalsIgnoreCase(bean.getLoginType())){
        		LogManager.info("Login Type => "+bean.getLoginType());
        		result="adminLogin";
        	}else if("user".equalsIgnoreCase(bean.getLoginType())){
        		result="page";
        	}
        }
        return result;
    }
	
	private void assignLogin() {
		logger.info("assignLogin Method - Enter");
		 String userName;
		 String pwd;
		 if(StringUtils.isBlank(bean.getLoginId()) && StringUtils.isBlank(bean.getPwd())){ 
			 if("Broker".equalsIgnoreCase(bean.getLoginType())){
				 userName=bean.getBloginId();
				 pwd=bean.getBpwd();
			 }else{
				 userName=bean.getAloginId()==null?bean.getOloginId():bean.getAloginId();
				 pwd=bean.getApwd()==null?bean.getOpwd():bean.getApwd();
			 }
			 logger.info("assignLogin Method - Exit, userName => "+userName +"pwd =>"+pwd);
			 bean.setLoginId(userName);
			 bean.setPwd(pwd);
		 }
		  
	}
	public String pwdChange() {
		logger.info("Enter==>pwdChange()");
		String status1;
		bean.setStatus("changepwd");
    	String returnResult = "changePWD";
    	if (bean.getNewpwd() == null || "".equals(bean.getNewpwd().trim())) {
	        addActionError(getText("E101"));
		}if (bean.getRepwd() == null || "".equals(bean.getRepwd().trim())) {
	        addActionError(getText("E102"));	//Please Enter Confirm Password
		}else if (!bean.getRepwd().equals(bean.getRepwd())) {
	        addActionError(getText("E103"));	//Both Passwords are Different
 		}else if(!validPassword((bean.getRepwd()))){
			addActionError(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH, new String[]{(String)session.get("pwdLenMin"), (String)session.get("pwdLenMax")}));	//New Password Should contain one Uppercase, one lowercase, one number & one special character(@#$%)
		}
    	if (getActionErrors().size() <= 0) {
    		status1=service.changePassword(bean.getLoginId(),bean.getRepwd(), appId);
    		if(status1==null){
    			bean.setStatus("success");
    		}
	    	else{
	    		addActionError(status1);
	    	}
    	}
    	logger.info("Exit==>pwdChange()");
        return returnResult;
    	
    }
	public String pwdForgot(){
		logger.info("Enter==>pwdForgot()");
		if(StringUtils.isNumeric(bean.getLoginId())&&"09".equalsIgnoreCase(bean.getLoginId().substring(0, 2)))
			 addActionError("Invalid Login For this Process");
		else{
			if("user".equals(bean.getUtype())){
				if (bean.getLoginId() == null || "".equals(bean.getLoginId().trim())) {
		            addActionError("Please Enter Login Mail Id");	//Please Enter UserName
		        }
			}
			else{
				if (bean.getLoginId() == null || "".equals(bean.getLoginId().trim())) {
		            addActionError(getText("E105"));	//Please Enter UserName
		        }
			}
			if (bean.getMailId() == null || "".equals(bean.getMailId().trim())) {
	            addActionError(getText("E106"));	//Please Enter Email Id
	        }else if (!validEmail((bean.getMailId()))) {
	            addActionError(getText("E108"));	//Please Enter valid Email Id
	        }if (getActionErrors().size() <= 0) {
	        	Object obj[]={bean.getLoginId(),bean.getMailId(), appId};
	        	List list=service.validateMailForgot(obj);
	        	if(list!=null && list.size()>0){
					String temp="tempPwd";
			    	String status1=service.sendUserPwd(bean.getLoginId(),temp, appId);
			    	if (status1 == null) {
			    		bean.setStatus("success");
			    	}
			    	if(status1!=null){
			    		addActionError(status1);
			    	}
	        	}
	        	else if(list==null || list.size()==0){
	        		addActionError(getText("E109"));	//UserName and Email Id not match for this application
	        	}
		    	logger.info("Exit==>pwdForgot()");
			}
		}
        return "forgotPWD";
    }
	
	public String option(){
		String returnResult=INPUT;
		if("changePwd".equalsIgnoreCase(bean.getStatus())) {
			returnResult="changePWD";
		}else if("forgotPwd".equalsIgnoreCase(bean.getStatus())) {
			returnResult="forgotPWD";
		}
		 Map rs1=service.getMailDetails(appId);
		 String pwdlen[]=rs1.get("PWD_LEN").toString().split("-");
		 bean.setPwdMsg(LocalizedTextUtil.findDefaultText("E104", Locale.ENGLISH, new String[]{pwdlen[0], pwdlen[1]}));
    	return returnResult;
    }
	
	public String out(){
		logger.info("Enter==> Logout()");
        bean.setLoginId((String) session.get("user"));
      // if("30".equalsIgnoreCase(session.get("product_id").toString())){
	        bean.setSumInsured((Map<Integer, String>) session.get("sum_ins"));
	        bean.setSumInsuredList((List<String>) session.get("sumInsList"));
	        bean.setContentType(session.get("ContentType")==null?"":session.get("ContentType").toString());
	        bean.setCurrencyType(session.get("currencyType")==null?"":session.get("currencyType").toString());
	        bean.setBrokerDetails((Map<Integer, String>) session.get("BrokerDetails"));
	        bean.setProductDetails((List<Map<String, Object>>) session.get("ProductDetails"));
	        bean.setScheme_id(session.get("scheme_id")==null?"":session.get("scheme_id").toString());
      // }
        String user=session.get("usertype")==null?"":session.get("usertype").toString();
        service.updateSessionInfo(bean.getLoginId(), request.getSession().getId());
        if(session.get("product_id")==null || !"33".equalsIgnoreCase(session.get("product_id").toString()))
        	((SessionMap<String, Object>) this.session).invalidate();
        logger.info("Exit==> Logout()");
        /*if("admin".equalsIgnoreCase(user)||"creditcontroller".equalsIgnoreCase(user)||"surveyor".equalsIgnoreCase(user)){
        	return "adminLogin";
        }else{
        	return page();
        }*/
        return page();
    }
	
	public CommonBean getModel() {
		return bean;
	}
	
	public boolean validPassword(String newpassword){
    	Pattern pattern=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*!]).{7,20})");
    	Matcher matcher = pattern.matcher(newpassword);
    	return matcher.matches();
	}
	public boolean validEmail(String email){
    	Pattern pattern = Pattern.compile("^[A-Za-z0-9_\\+-]+(\\.[A-Za-z0-9_\\+-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.([A-Za-z]{2,4})$");
    	boolean stat = true;
        if(email.contains(",")){
        	String[] emails = email.split(",");        	
        	for(String ids:emails){
        		Matcher matcher = pattern.matcher(ids);
        		if(!matcher.matches()){
        			stat = false;
        			break;
        		}
        	}
    	}else{
            Matcher matcher = pattern.matcher(email);
            stat = matcher.matches();
    	}
        return stat;
    }
	public void validlogin(){
		if(StringUtils.isEmpty(bean.getLoginId())){
			addActionError(getText("loginid.empty"));
		}if(StringUtils.isEmpty(bean.getPwd())){
			addActionError(getText("pwd.empty"));
		}
		if(!"changepwd".equalsIgnoreCase(bean.getStatus())){
			if(StringUtils.isBlank(bean.getBranch())){
				addActionError("Please Select Branch");
			}
		}
		/*if(StringUtils.isEmpty(bean.getLoginId())){
			addActionError(getText("loginid.empty.mob.num"));
		}else if((!"09".equals(bean.getLoginId().substring(0, 2)))){
			addActionError(getText("loginid.valid.mob.num"));
		}*/
	}
	
	public String b2clogin() {
		out();
		bean.setLoginId(getText("B2C_LOGIN_ID"));
		bean.setLoginType("Broker");
		bean.setPwd("Admin@01");
		b2cType = "B2C";
		return submit();
	}
	
	public String landing(){
		out();
		return "page"; 	
	}
	public String NewRegistration(){
		b2clogin();
		return "newUser";
	}
	private void brokerDetails() {
		try{
			LogManager.info("Enter into brokerDetails()");
			ProductSelection product = new ProductSelection();
			HashMap brokerDetails = new HashMap();
			String brokerBra = "";
			String actualBranch = "";
			String loginCode = session.get("user").toString();
			Map branchDt = product.getBrokerUserBranch(loginCode,"B2C",brokerBra);
			if(branchDt.size()>0){
				brokerBra = (String)branchDt.get("defaultBranch");
				actualBranch = (String)branchDt.get("actualBranch");
			}
			brokerDetails = product.getBrokerUserDetails(brokerBra);
			session.put("LoginBranchCode",brokerBra);//Default Branch Code
			session.put("adminBranch",actualBranch);//Actual Branch Code
			session.put("BrokerDetails",brokerDetails);
			session.put("LANG",brokerDetails.get("LANG"));
			session.put("product_id","65");
			LogManager.info("Exit into brokerDetails()");
		}catch (Exception e) {
			LogManager.debug("Exception @ add() {"+e+"}");
		}
	}
	public List<Map<String,Object>> getBranchList(){
		return service.getBranchList();
	}
	public List<Map<String,Object>> getProductList(){
		return service.getProductList();
	}
	public String validOTP(){
		LinkedList<String> list = new OTPGenerator().getValidate(bean.getOtp(),bean.getOtpId(),bean.getMailOtp());
		 if(list.size()>0){
			 for(int i=0;i<list.size();i++)
				 addActionError(getText(list.get(i)));
			 	bean.setOtp("");
			 	bean.setMailOtp("");
				 bean.setShowValue("otpVerify");
				 return "page";
		 }else{
			 boolean ispass=service.setPassword(bean);
			 bean.setLoginId("");
			 if(ispass){
				 return submit();
			 }else{
				 addActionError(getText("error.mobile.invalid"));
				 return "input";
			 }
		 }		
	}
	public String validOTPNew(){
		LinkedList<String> list = new OTPGenerator().getValidate(bean.getOtp(),bean.getOtpId(),bean.getMailOtp());
		 if(list.size()>0){
			 for(int i=0;i<list.size();i++)
				 addActionError(getText(list.get(i)));
			 	bean.setOtp("");
			 	bean.setMailOtp("");
				 bean.setShowValue("otpVerifyNew");
				 return "page";
		 }else{
			 boolean ispass=service.setPasswordNew(bean);
			  //ispass=true;
			  //bean.setOpwd("Admin@01");
			 //bean.setLoginId("");
			 bean.setPwd(bean.getOpwd()==null?"":bean.getOpwd().toString());
			 if(ispass){
				 //return submitNew();
				 return submit();
			 }else{
				 addActionError(getText("error.mobile.invalid"));
				 return "input";
			 }
		 }		
	}
	public String mobLogin(String userName,String pwd){
		LogManager.info("Enter into mobLogin() ,userName=> "+userName+" pwd=> "+pwd);
		out();
		 bean.setLoginId(userName);
		 bean.setPwd(pwd);
		 bean.setLoginType("Broker");
		 return "mobLogin";
	}
	public String otpLogin(){
		//Sent OtP
		String forward="page";
		//session.put("isArabic",StringUtils.isBlank(bean.getIsArabic())?"":bean.getIsArabic());
		if(StringUtils.isNotBlank(bean.getOloginId())){
			
			if(Validation.INVALID.equals(validation.validateMobile(bean.getOloginId()))) {
				addActionError("Please Enter Valid Mobile No");
			}
			else {
				int coutn=service.getPasswordCount(bean);
				if(coutn>0){
					int otp=new OTPGenerator().getOTP();
					//bean.setMyOtp(otp);
					int mailOtp=new OTPGenerator().getOTP();
					//(String) getSession().get("Id")
					bean.setOtpId(new OTPGenerator().insertOTP(new String[]{bean.getOtpId(),"B2C_LOGIN",
							Integer.toString(otp),bean.getOloginId(),bean.getEmail(),"","N",Integer.toString(mailOtp)}));
					//new SmsEmailUtil("GET_OTP",bean.getOtpId()).send();
					new SmsEmailUtil("GET_OTP",Integer.toString(otp),bean.getOloginId(),new OTPGenerator().getOtpExpiry(bean.getOtpId()),Integer.toString(mailOtp),bean.getEmail()).send();
					bean.setOtpStatus("N");
					bean.setOtp("");
					bean.setShowValue("otpVerify");
				}else{
					//addActionError(getText("error.mobile.invalid"));
					addActionError(getText("error.mobile.notregister"));
				} 
			}
		}else{
			addActionError(getText("error.mobile.empty"));
		}
		bean.setFori18nLink("Y");
		/*if("b2c".equalsIgnoreCase(bean.getMode())){
			forward = "B2Cpage";
		}else{
			forward = "page";
		}*/
		
		return forward;
	}
	public String UserLogin(){
		out();
		bean.setShowValue("userLogin");
		bean.setLoginType("user");
		return "page"; 	
	}
	public String otpLoginUsr(){
		//Sent OtP
		String forward="page";
		//session.put("isArabic",StringUtils.isBlank(bean.getIsArabic())?"":bean.getIsArabic());
		if(StringUtils.isNotBlank(bean.getLoginId())){
			int coutn=service.getPasswordCountNew(bean);
			if(coutn>0){
				String mobNum=service.getMobileNumber(bean);
				if(StringUtils.isNotBlank(mobNum)){
					bean.setOloginId(mobNum);
					int otp=new OTPGenerator().getOTP();
					//bean.setMyOtp(otp);
					int mailOtp=new OTPGenerator().getOTP();
					//(String) getSession().get("Id")
					bean.setOtpId(new OTPGenerator().insertOTP(new String[]{bean.getOtpId(),"B2C_LOGIN",
							Integer.toString(otp),bean.getOloginId(),bean.getEmail(),"","N",Integer.toString(mailOtp)}));
					//new SmsEmailUtil("GET_OTP",bean.getOtpId()).send();
					new SmsEmailUtil("GET_OTP",Integer.toString(otp),bean.getOloginId(),new OTPGenerator().getOtpExpiry(bean.getOtpId()),Integer.toString(mailOtp),bean.getEmail()).send();
					bean.setOtpStatus("N");
					bean.setOtp("");
					bean.setShowValue("otpVerifyNew");
				}else{
					//addActionError(getText("error.mobile.invalid"));
					addActionError(getText("error.login.notregister.mob"));
				} 
			}else {
				addActionError(getText("error.login.notregister"));
			}
		}else{
			addActionError(getText("error.login.empty"));
		}
		bean.setFori18nLink("Y");
		/*if("b2c".equalsIgnoreCase(bean.getMode())){
			forward = "B2Cpage";
		}else{
			forward = "page";
		}
		*/
		return forward;
	}
	
	public String madisonPay(){
		try {
			if(session== null || (session!=null && session.get("user") == null)) {
				b2clogin();			
			}
			String productId=setPayEnctData(bean);
			if("65".equalsIgnoreCase(productId))
				bean.setForward("payResponseMotor.do");
			else if("30".equalsIgnoreCase(productId))
				bean.setForward("payResponseHome.do");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirectHome";
	}
	
	public String setPayEnctData(CommonBean bean) throws Exception {
		String productId="";
		try {
			String decrypt = CryptoService.decrypt(bean.getE());
			System.out.println("Login decrypt value setPayEnctData() => "+decrypt);
			if(decrypt.indexOf("~~")!=1){
				String[] split = decrypt.split("~~");
				if(split.length>0){
					String[] split4 = split[2].split("=");	
					productId=split4[1];
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productId;

	}
    
	public String  doauth(){
        try{
        	String forward="";
        	Map loginMap=(Map) service.getLoginDetails(bean.getLoginId(),"login");
        	String pwd=loginMap.get("PASSWORD")==null?"":loginMap.get("PASSWORD").toString();
        	String branch=loginMap.get("BRANCH_CODE")==null?"":loginMap.get("BRANCH_CODE").toString();
        	String usertype=loginMap.get("USERTYPE")==null?"":loginMap.get("USERTYPE").toString();
            //String unencryptedString="loginId="+bean.getLoginId()+"~password="+pwd+"~usertype="+usertype+"~branchcode="+branch+"~productId="+bean.getProduct();
        	String unencryptedString="loginId="+bean.getLoginId()+"~productId="+bean.getProduct();
        	//String unencryptedString="";
            List<Map<String, Object>> productListWithId = service.getProductListWithId(bean.getLoginId(),"01",bean.getProduct(),"");
            if(productListWithId!=null && productListWithId.size()>0) {
             	forward=productListWithId.get(0).get("APP_LOGIN_URL").toString();
            }
            bean.setE(CryptoService.encrypt(unencryptedString));
            forward=forward+""+bean.getE();
            bean.setForward(forward);
               
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward";
    }
	
	public String entry(){
		String forward="";
		try {
			String agencyCode="";
			String decrypt = CryptoService.decrypt(bean.getE());
			System.out.println("Login decrypt value entry()=> "+decrypt);
			if(decrypt.indexOf("~~")!=1){
				String[] split = decrypt.split("~~");
				if(split.length>0){
					String[] split1 = split[0].split("=");	
					agencyCode=split1[1];
				} 
			}
			Map loginMap=(Map) service.getLoginDetails(agencyCode,"agency");
			/*bean.setLoginId("marshuser02");
			bean.setPwd("Admin@01");*/
			String isb2c=loginMap.get("IS_B2C")==null?"":loginMap.get("IS_B2C").toString();
			if("N".equalsIgnoreCase(isb2c))
				addActionError("Not a Valid B2C User, Please Login from Here");
			//else{
				bean.setLoginId(loginMap.get("LOGIN_ID")==null?"":loginMap.get("LOGIN_ID").toString());
				bean.setPwd(loginMap.get("PASSWORD")==null?"":loginMap.get("PASSWORD").toString());
				bean.setBranch("01");
				bean.setLoginType("user");
				bean.setB2cCustYN("Y");
				session.put("B2cCustYN", "Y");
				b2cType = "B2CB";
			//}
			 
			forward= submit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
}
