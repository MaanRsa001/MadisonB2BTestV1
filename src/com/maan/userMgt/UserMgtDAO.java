package com.maan.userMgt;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.dao.CommonDAO;
import com.maan.common.login.CommonBean;

public class UserMgtDAO extends MyJdbcTemplate {

	final Logger logger=com.maan.common.LogUtil.getLogger(getClass());

	public void insertUserInfo(Object[] args1){
		try{
			String query = "";
			/*query=getQuery("INS_USERREG_PERSONAL_INFO");
			LogManager.info("Query===>" + query);
			removeNull(args1);
			logger.info("Args==>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);*/

			query=getQuery("INS_USERREG_LOGIN_MASTER");
			LogManager.info("Query===>" + query);
			removeNull(args1);
			logger.info("Args==>"+StringUtils.join(args1,","));
			this.mytemplate.update(query,args1);	
			
		}
		catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}
	}

	public List <Object> getCommisionData(String uagencyCode, String agencyCode, String branchCode) {
		List <Object> commisionDetails=null;
		try 
		{
			LogManager.push("Method to getCommisionData");
			String query=getQuery("GET_USERREG_COMMISSION")+" order by lud.product_id";
			LogManager.info("Query===>" + query);
			commisionDetails=this.mytemplate.queryForList(query,new Object[]{uagencyCode, agencyCode, branchCode});
			LogManager.info("getCommisionData() - Exit");
		} 
		catch (Exception e) 
		{
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return commisionDetails;
	}

	public void getUserDetails(final UserMgtBean ba, String uagencyCode){
		try{
			String query=getQuery("GET_BROKER_USERREG_DETAILS");
			LogManager.info("Query===>" + query);
			LogManager.info("AgencyCode===>" + ba.getUagencyCode());
			Object[] obj=new Object[1];
			obj[0]= ba.getUagencyCode();
			List usertDetails=this.mytemplate.queryForList(query,obj);

			if(usertDetails!=null || usertDetails.size()>0){
				Map map=(Map)usertDetails.get(0);
				ba.setCustomerName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
				ba.setCustLastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
				ba.setGender(map.get("GENDER")==null?"":map.get("GENDER").toString());
				ba.setCustdob(map.get("DOB")==null?"":map.get("DOB").toString());
				ba.setMobileNo(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
				ba.setUemail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
				ba.setEmail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
				ba.setAddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
				ba.setAddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
				ba.setOccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
				ba.setPoBox(map.get("POBOX")==null?"":map.get("POBOX").toString());
				ba.setAgencyCode(map.get("agency_code")==null?"":map.get("agency_code").toString());
				ba.setUagencyCode(map.get("agency_code")==null?"":map.get("agency_code").toString());
				ba.setCity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
				ba.setCustomerId(map.get("customer_id")==null?"":map.get("customer_id").toString());
				ba.setUtype(map.get("USERTYPE")==null?"":map.get("USERTYPE").toString());
				ba.setCustPassportNo(map.get("PASSPORT_NUMBER")==null?"":map.get("PASSPORT_NUMBER").toString());
				/*ba.setCustnrc(map.get("NRC")==null?"":map.get("NRC").toString());
				String[] value = ba.getCustnrc().split("/");
				if(value!=null && value.length >= 3){
					ba.setCustnrc1(value[0]);
					ba.setCustnrc2(value[1]);
					ba.setCustnrc3(value[2]);
				}*/
				String nrc = map.get("NRC")==null?"":map.get("NRC").toString();
				if(StringUtils.isNotBlank(nrc)) {
					String[] nrcarr = nrc.split("/");
					if(nrcarr.length > 2){
						ba.setCustnrc1(nrcarr[0]);
						ba.setCustnrc2(nrcarr[1]);
						ba.setCustnrc3(nrcarr[2]);
					}
				}
				ba.setCompanyRegNo(map.get("COMPANY_REG_NO")==null?"":map.get("COMPANY_REG_NO").toString());
				ba.setCustomerType(map.get("CUSTOMER_TYPE")==null?"":map.get("CUSTOMER_TYPE").toString());
				ba.setAgencyCode(map.get("OA_CODE")==null?"":map.get("OA_CODE").toString());
				ba.setBroker(map.get("OA_CODE")==null?"":map.get("OA_CODE").toString());
				ba.setTitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
				ba.setCustAlterMobileNo(map.get("ALTERNATE_MOBILE")==null?"":map.get("ALTERNATE_MOBILE").toString());
				
				
				/*ba.setUnationality(map.get("NATIONALITY")==null?"":map.get("NATIONALITY").toString());
				ba.setUnationalityName(map.get("NATIONALITY_NAME")==null?"":map.get("NATIONALITY_NAME").toString());
				ba.setUfax(map.get("FAX")==null?"":map.get("FAX").toString());
				ba.setUphone(map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString());
				ba.setUlogin_Id(map.get("login_id")==null?"":map.get("login_id").toString());
				ba.setBname(map.get("bname")==null?"":map.get("bname").toString());
				ba.setUstatus(map.get("status")==null?"":map.get("status").toString());
				ba.setUcountryNa(map.get("country_name")==null?"":map.get("country_name").toString());
				ba.setBrokerName(map.get("borganization")==null?"":map.get("borganization").toString());
				ba.setUcountry(map.get("COUNTRY")==null?"":map.get("COUNTRY").toString());
				*/
			}
		}catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	
	public void updateUserQuote(String loginId, String applicationNo) {
		try {
			String query = getQuery("UPD_USER_APPLICATIONS");
			Object[] args = new Object[]{loginId, applicationNo};
			LogManager.info("Query==> " + query);
			LogManager.info("Args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query, args);
		}
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}
	/*public String getOACode(String branchCode) {
		String OACode="";
		try{
			LogManager.info("Enter Into getOACode()");
			String query=getQuery("GET_USER_OA_CODE");
			Object[] args={branchCode};
			Map<Object, String> rs;
			rs=this.mytemplate.queryForMap(query,args);
			OACode = rs.get("RSACODE")==null?"":rs.get("RSACODE").toString();
			LogManager.info("Exit into getOACode()");
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
		return OACode;
	}*/

	public void updateUserInfo(Object[] args1){
    	try{
    		String query = "";
			/*query=getQuery("UPD_B2CUSER_PERSONAL_INFO");
			LogManager.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);*/
			
			query=getQuery("UPD_B2CUSER_LOGIN_MASTER");
			LogManager.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args1,","));
			this.mytemplate.update(query,args1);
		   }
		catch (Exception e) {			
			LogManager.debug("EXCEPTION @ updateUserInfo { " + e + " }");
		}
    }

	public void updateSession(String userId, String sessionId) {
		try{
			LogManager.info("Enter into updateSession()");
			String query =getQuery("UPD_USER_SESSION");
			Object[] obj ={userId,sessionId,"Y"};
			LogManager.info("Query ==>" +query);
			this.mytemplate.update(query,obj);
			LogManager.info("Exit into updateSession()");
		}catch (Exception e) {
			LogManager.debug("EXCEPTION @ updateSession { " + e + " }");
		}
	}
	public String checkUserLoginId(UserMgtBean bean, String appId) {
		String result="";
		try {
			LogManager.info("Enter into checkUserLoginId");
			String query=getQuery("GET_USER_TYPE");
			Object[] args={bean.getUserLoginId(),appId};
			Map<String,Object> res=this.mytemplate.queryForMap(query,args);
			result = res.get("USERTYPE") == null?"":res.get("USERTYPE").toString();
			LogManager.info("Exit into checkUserLoginId");
		} catch (Exception e) {
			LogManager.info("Exception @ checkUserLoginId"+e);
		}
		return result;
	} 
	public void updateCustomerIdMotorDataDetail(String[] args2) {
		try {
			LogManager.info("Enter into updateCustomerIdMotorDataDetail");
			String query=getQuery("UPD_CUSTOMER_ID_MOTOR_DATA_DETAILS");
			this.mytemplate.update(query,args2);
			LogManager.info("Exit into updateCustomerIdMotorDataDetail");
		} catch (Exception e) {
			LogManager.info("Exception @ updateCustomerIdMotorDataDetail"+e);
		}
	}

	public int validateMobile(String mobileNo) {
		int count=0;
		try{
			LogManager.info("Enter Into validateMobile()");
			String query = getQuery("MOTOR_USER_VALIDATE_MOBILE");
			Object args[]={mobileNo};
			LogManager.info("Query => "+query);
			LogManager.info("Args => "+StringUtils.join(args,","));
			count = this.mytemplate.queryForInt(query,args);
			LogManager.info("Exit Into validateMobile()");
		}catch (Exception e) {
			LogManager.debug("Exception @ validateMobile()" + e);
			e.printStackTrace();
		}
		return count;
	}

	public int validateMail(String email, String applicationNo) {
		int count=0;
		try{
			LogManager.info("Enter Into validateMail()");
			String query = getQuery("GET_USER_EMAIL_COUNT");
			Object args[]={applicationNo};
			LogManager.info("Query => "+query);
			LogManager.info("Args => "+StringUtils.join(args,","));
			count = this.mytemplate.queryForInt(query,args);
			LogManager.info("Exit Into validateMail()");
		}catch (Exception e) {
			LogManager.debug("Exception @ validateMail()" + e);
			e.printStackTrace();
		}
		return count;
	}

	public void getCustomerDetails(UserMgtBean bean) {
		try{
			LogManager.info("Enter Into getCustomerDetails ");
			String query = getQuery("MOTOR_NEW_USER_EXIST_CUS_INFO");
			Object args[]=new Object[]{bean.getApplicationNo()};
			List<Map<String,Object>> result = this.mytemplate.queryForList(query,args);
			if(result.size()>0){
				Map<String,Object> res = result.get(0);
				bean.setTitle(res.get("TITLE")==null?"":res.get("TITLE").toString());
				bean.setCustomerName(res.get("FIRST_NAME")==null?"":res.get("FIRST_NAME").toString());
				bean.setCustLastName(res.get("LAST_NAME")==null?"":res.get("LAST_NAME").toString());
				bean.setCustdob(res.get("DOB")==null?"":res.get("DOB").toString());
				bean.setOccupation(res.get("OCCUPATION")==null?"":res.get("OCCUPATION").toString());
				bean.setAddress1(res.get("ADDRESS1")==null?"":res.get("ADDRESS1").toString());
				bean.setAddress2(res.get("ADDRESS2")==null?"":res.get("ADDRESS2").toString());
				bean.setPoBox(res.get("POBOX")==null?"":res.get("POBOX").toString());
				bean.setCity(res.get("CITY")==null?"":res.get("CITY").toString());
				/*bean.setUcountry(res.get("COUNTRY")==null?"":res.get("COUNTRY").toString());*/
				/*bean.setUphone(res.get("TELEPHONE")==null?"":res.get("TELEPHONE").toString());*/
				bean.setMobileNo(res.get("MOBILE")==null?"":res.get("MOBILE").toString());
				bean.setUemail(res.get("EMAIL")==null?"":res.get("EMAIL").toString());
				bean.setEmail(res.get("EMAIL")==null?"":res.get("EMAIL").toString());
				bean.setCustPassportNo(res.get("PASSPORT_NUMBER")==null?"":res.get("PASSPORT_NUMBER").toString());
				/*bean.setCustnrc(res.get("NRC")==null?"":res.get("NRC").toString());
				String[] value = bean.getCustnrc().split("/");
				if(value!=null && value.length > 3){
					bean.setCustnrc1(value[0]);
					bean.setCustnrc2(value[1]);
					bean.setCustnrc3(value[2]);
				}*/
				String nrc = res.get("NRC")==null?"":res.get("NRC").toString();
				if(StringUtils.isNotBlank(nrc)) {
					String[] nrcarr = nrc.split("/");
					if(nrcarr.length > 2){
						bean.setCustnrc1(nrcarr[0]);
						bean.setCustnrc2(nrcarr[1]);
						bean.setCustnrc3(nrcarr[2]);
					}
				}
				bean.setCompanyRegNo(res.get("COMPANY_REG_NO")==null?"":res.get("COMPANY_REG_NO").toString());
				bean.setCustomerType(res.get("CUSTOMER_TYPE")==null?"":res.get("CUSTOMER_TYPE").toString());
			}
			if(StringUtils.isBlank(bean.getCustdob())) {
				query = getQuery("GET_DOB_BYDRIVERDOB");
				args = new Object[]{bean.getApplicationNo()};
				result = this.mytemplate.queryForList(query,args);
				if(result.size()>0) {
					Map<String,Object> res = result.get(0);
					bean.setCustdob(res.get("DRIVER_DOB")==null?"":res.get("DRIVER_DOB").toString());
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public void updateCustomerDetail(UserMgtBean bean) {
		try{
			LogManager.info("Enter Into UpdateCustomerDetails");
			String query=getQuery("MOTOR_NEW_USER_CUS_UPD");
			Object args[]=new Object[4];
			args[0] = bean.getUserId()==null?"":bean.getUserId();
			args[1] = bean.getUagencyCode();
			args[2] = bean.getAgencyCode();
			args[3] = bean.getCustomerId();
			LogManager.info("Query => "+query);
			LogManager.info("Arguments =>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
		}catch(Exception e){
			LogManager.debug("Exception Occured @ updateCustomerDetail"+e);
		}
	}*/

	public void updateCustomerPersonal(String userId, String applicationNo) {
		try{
			String query = getQuery("SELECT_MOTOR_EXIT_USER_PER");
			Object[] args;
			args = new Object[]{userId};
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			Map<String,Object> res =this.mytemplate.queryForMap(query,args) ;
	/*		MOTOR_EXIT_USER_PER_UPD = UPDATE PERSONAL_INFO SET LAST_NAME=?,NATIONALITY=?,DOB=TO_DATE(?,'DD/MM/YYYY')
					,GENDER=?,TELEPHONE=?,MOBILE=?,FAX=?,EMAIL=?,ADDRESS1=?,ADDRESS2=?,OCCUPATION=?,POBOX=?,COUNTRY=?,
							EMIRATE=?,LOGIN_ID=?,AGENCY_CODE=?,OA_CODE=?,COMPANY_NAME=?,CITY=?,CUST_NAME=?,PASSPORT_NUMBER=?
					WHERE CUSTOMER_ID=(SELECT CUSTOMER_ID FROM HOME_POSITION_MASTER HPM  WHERE HPM .APPLICATION_NO =?)
			*/
			//Update Personal Info
			query = getQuery("MOTOR_EXIT_USER_PER_UPD");
			args = new Object[22];
			args[0] = res.get("LAST_NAME")==null?"": res.get("LAST_NAME");
			args[1] = res.get("NATIONALITY")==null?"": res.get("NATIONALITY");
			args[2] = res.get("DOB")==null?"": res.get("DOB");
			args[3] = res.get("GENDER")==null?"":res.get("GENDER");
			args[4] = res.get("TELEPHONE")==null?"":res.get("TELEPHONE");
			args[5] = res.get("MOBILE")==null?"":res.get("MOBILE");
			args[6] = res.get("FAX")==null?"":res.get("FAX");
			args[7] = res.get("EMAIL")==null?"":res.get("EMAIL");
			args[8] = res.get("ADDRESS1")==null?"":res.get("ADDRESS1");
			args[9] = res.get("ADDRESS2")==null?"":res.get("ADDRESS2");
			args[10] = res.get("OCCUPATION")==null?"":res.get("OCCUPATION");
			args[11] = res.get("POBOX")==null?"":res.get("POBOX");
			args[12] = res.get("COUNTRY")==null?"":res.get("COUNTRY");
			args[13] = res.get("EMIRATE")==null?"":res.get("EMIRATE");
			args[14] = res.get("LOGIN_ID")==null?"":res.get("LOGIN_ID");
			args[15] = res.get("AGENCY_CODE")==null?"":res.get("AGENCY_CODE");
			args[16] = res.get("OA_CODE")==null?"":res.get("OA_CODE");
			args[17] = res.get("COMPANY_NAME")==null?"":res.get("COMPANY_NAME");
			args[18] = res.get("CITY")==null?"":res.get("CITY");
			args[19] = res.get("CUST_NAME")==null?"":res.get("CUST_NAME");
			args[20] = res.get("PASSPORT_NUMBER")==null?"":res.get("PASSPORT_NUMBER");
			args[21] = applicationNo;
			
			LogManager.info("Query => "+query);
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			
		}catch(Exception e){
			LogManager.debug("Exception Occured @ "+e);
		}
	}

	public String getEmailCount(UserMgtBean bean) {
		String result="";
		try{
			String query=getQuery("GET_USER_EMAIL_COUNT");
			LogManager.info("Query => "+query);
			Object[] args;
			args=new Object[]{bean.getApplicationNo()};
			LogManager.info("Arguments => "+StringUtils.join(args,","));
			result = (String)this.mytemplate.queryForObject(query,args,String.class);
			/*if(Integer.parseInt(result) >0){
				query = getQuery("GET_CUSTOMER_DETAILS_ON_EMAIL");
				LogManager.info("Query => "+query);
				args=new Object[] {bean.getApplicationNo()};
				LogManager.info("Arguments => "+StringUtils.join(args,","));
				List<Map<String,Object>> res = this.mytemplate.queryForList(query,args);
				if(res.size() > 0){
					bean.setTitle(res.get(0).get("TITLE")==null?"":res.get(0).get("TITLE").toString());
					bean.setCustomerName(res.get(0).get("FIRST_NAME")==null?"":res.get(0).get("FIRST_NAME").toString());
					bean.setCustLastName(res.get(0).get("LAST_NAME")==null?"":res.get(0).get("LAST_NAME").toString());
					bean.setCustdob(res.get(0).get("DOB")==null?"":res.get(0).get("DOB").toString());
					bean.setOccupation(res.get(0).get("OCCUPATION")==null?"":res.get(0).get("OCCUPATION").toString());
					bean.setAddress1(res.get(0).get("ADDres.get(0)S1")==null?"":res.get(0).get("ADDres.get(0)S1").toString());
					bean.setAddress2(res.get(0).get("ADDres.get(0)S2")==null?"":res.get(0).get("ADDres.get(0)S2").toString());
					bean.setPoBox(res.get(0).get("POBOX")==null?"":res.get(0).get("POBOX").toString());
					bean.setCity(res.get(0).get("CITY")==null?"":res.get(0).get("CITY").toString());
					bean.setMobileNo(res.get(0).get("MOBILE")==null?"":res.get(0).get("MOBILE").toString());
					bean.setUemail(res.get(0).get("EMAIL")==null?"":res.get(0).get("EMAIL").toString());
					bean.setEmail(res.get(0).get("EMAIL")==null?"":res.get(0).get("EMAIL").toString());
				}
			}*/
		}catch(Exception e){
			LogManager.info("Exception Occured @ getemailCount"+e);
			e.printStackTrace();
		}
		return result;
	}
	public void addProducts(String loginId, String agencyCode, String b2cBrokerId) {
		try {
			String query = getQuery("INS_B2CLOGIN_USER_DETAILS");
			Object[] args = new Object[]{loginId,agencyCode,b2cBrokerId};
			LogManager.info("Query==> " + query);
			LogManager.info("args==> " + StringUtils.join(args, ", "));
			this.mytemplate.update(query, args);
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}

	public String getCustomerEmail(String userLoginId1, String appId) {
		String email="";
		try{
			String query = getQuery("GET_MAIL_FORGOT_MOBILE");
			Object[] args = new Object[]{userLoginId1,appId};
			LogManager.info("Query==> " + query);
			LogManager.info("args==> " + StringUtils.join(args, ", "));
			email = (String) this.mytemplate.queryForObject(query,args,String.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return email;
	}

	public void updateLoginId(UserMgtBean bean) {		 
		String args2[]={bean.getApplicationNo()};
		String query="update HOME_POSITION_MASTER set LOGIN_ID='"+bean.getLoginId()+"' where APPLICATION_NO=?";
		this.mytemplate.update(query,args2);
	}

	public void insertUserInfo(Object[] args1,String type){
		try{
			String query = "";
			/*query=getQuery("INS_USERREG_PERSONAL_INFO");
			LogManager.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);*/
			if("MOBILE".equalsIgnoreCase(type)){
				query=getQuery("INS_USERREG_LOGIN_MASTER_OTP");
			}else{
				query=getQuery("INS_USERREG_LOGIN_MASTER");
			}
			LogManager.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args1,","));
			this.mytemplate.update(query,args1);	
			
		}
		catch (Exception e) {
			LogManager.debug("EXCEPTION @ { " + e + " }");
		}
	}

	public String insertCustomerMob(UserMgtBean bean, String type,String oldCustomerNo) {
		try{
			boolean isexist=false;
			String newcustomerId="",sql="";
			if(StringUtils.isNotBlank(bean.getCustomerId())){
				LogManager.info("Exist Customer =>"+bean.getCustomerId());
				newcustomerId=bean.getCustomerId();
				isexist=true;
			}else{
				newcustomerId =new CommonDAO().getSequenceNo("CUSTOMER_ID",bean.getBranchCode(),bean.getProductId(),"","");
				LogManager.info("new Customer =>"+newcustomerId);
			}
			Object[] obj=null; 
			
			if(isexist){
				sql="UPDATE PERSONAL_INFO set LOGIN_ID='"+bean.getLoginId()+"' where CUSTOMER_ID=? and APPLICATION_ID='1'";
				LogManager.info("Query=>"+sql);
				obj=new Object[]{
						newcustomerId	
				};
			}else{
				sql="insert into PERSONAL_INFO(CUSTOMER_ID,TITLE,FIRST_NAME,LAST_NAME,DOB,DOB_AR,EMAIL,LOGIN_ID,MOBILE,APPLICATION_ID,AGENCY_CODE,OA_CODE,COMPANY_REG_NO,STATUS,AMEND_ID) " +
				"select "+newcustomerId +" CUSTOMER_ID,TITLE,FIRST_NAME,LAST_NAME,DOB,DOB_AR,EMAIL,'"+bean.getLoginId()+"' LOGIN_ID,MOBILE,'2' APPLICATION_ID,'"+bean.getUagencyCode()+"' AGENCY_CODE,'"+bean.getAgencyCode()+"' OA_CODE,COMPANY_REG_NO,STATUS,AMEND_ID " +
						"from PERSONAL_INFO where CUSTOMER_ID=? and APPLICATION_ID='1'";
				LogManager.info("Query=>"+sql);
				obj=new Object[]{
				oldCustomerNo	
		};
			}
			removeNull(obj);
			LogManager.info("obj[] ==> "+StringUtils.join(obj, ","));
			int res=this.mytemplate.update(sql,obj);
			
			
			String args2[]={newcustomerId,bean.getApplicationNo()};
			if("65".equalsIgnoreCase(bean.getProductId())){
				updateCustomerIdMotorDataDetail(args2);
			}
			String query="update HOME_POSITION_MASTER set CUSTOMER_ID=?,LOGIN_ID='"+bean.getLoginId()+"' where APPLICATION_NO=?";
			this.mytemplate.update(query,args2);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
