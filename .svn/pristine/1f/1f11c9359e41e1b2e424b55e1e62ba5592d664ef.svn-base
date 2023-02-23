package com.maan.common.sms;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.common.LogManager;
import com.maan.common.MyJdbcTemplate;

/**
 * Created By ibrahim  on 25/03/2016
 * */

public class SmsEmailDAO extends MyJdbcTemplate {

	public Map<Object, Object> getCustInfo(String quoteNo)
	{
		Map<Object, Object> rec=null;
		try{
			Object []args = {quoteNo};
			String query=getQuery("GET_SMS_CUST_INFO");
			rec=this.mytemplate.queryForMap(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return rec;
	}
	
	public Map<Object, Object> getRoadAssistant(String refNo)
	{
		Map<Object, Object> rec=null;
		try{
			Object []args = {refNo};
			String query=getQuery("GET_ROAD_ASSISTANT_INFO");
			rec=this.mytemplate.queryForMap(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return rec;
	}
	public Map<Object, Object> getClaimInfo(String claimNo)
	{
		Map<Object, Object> rec=null;
		try{
			Object []args = {claimNo,claimNo};
			String query=getQuery("GET_SMS_CLAIM_INFO");
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
			rec=this.mytemplate.queryForMap(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return rec;
	}
	public Map<Object, Object> getUserInfo(String loginId)
	{
		Map<Object, Object> rec=null;
		try{
			Object []args = {loginId};
			String query=getQuery("GET_SMS_USER_INFO");
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
			rec=this.mytemplate.queryForMap(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return rec;
	}
	public List<Map<String,Object>> getAdminDetails(String userType) {
		List<Map<String,Object>> result=null;
		try{
			String query = getQuery("GET_SMS_ADMIN_INFO");
			Object []args = {userType};
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
			result=this.mytemplate.queryForList(query,args);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String smsRequired(String quoteNo,String type){
		String result="";
		try{
			String query= "";
			Object args[] = null;
			if("quote".equalsIgnoreCase(type)){
				query= getQuery("GET_QUOTE_SMS_STATUS");
				args=new Object[]{quoteNo};
			}
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
			result = (String) this.mytemplate.queryForObject(query,args,String.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void updateSmsStatus(String quoteNo, String type) {
		try{
			String query= "";
			Object args[] = null;
			if("quote".equalsIgnoreCase(type)){
				query= getQuery("UPD_QUOTE_SMS_STATUS");
				args=new Object[]{"N",quoteNo};
			}
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Map<String,Object> getApproverDetails(String reqFrom, String quoteNo) {
		Map<String,Object> result=null;
		try{
			String query = getQuery("GET_SMS_POLICYAPPROVER_DETAILS");
			Object[] args = new Object[3];
			args[0] = quoteNo;
			if( SmsEmailUtil.SURVEYOR_ACCEPT_SURVEYOR.equals(reqFrom) || SmsEmailUtil.SURVEYOR_REJECT_SURVEYOR.equals(reqFrom)
					|| SmsEmailUtil.SURVEYOR_ACCEPT_OPUSER.equals(reqFrom) || SmsEmailUtil.SURVEYOR_REJECT_OPUSER.equals(reqFrom) ) {
				args[1] = "SS";
			}
			if( SmsEmailUtil.SURVEYOR_ACCEPT_OPUSER.equals(reqFrom) || SmsEmailUtil.SURVEYOR_ACCEPT_SURVEYOR.equals(reqFrom) ) {
				args[2] = "Y";
			} else if( SmsEmailUtil.SURVEYOR_REJECT_OPUSER.equals(reqFrom) || SmsEmailUtil.SURVEYOR_REJECT_SURVEYOR.equals(reqFrom) ) {
				args[2] = "N";
			}
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
			result = this.mytemplate.queryForMap(query,args);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public Map<String,Object> getSmsEmailContent(String reqFrom) {
		Map<String,Object> result=null;
		try{
			String query=getQuery("GET_SMS_EMAIL_CONTENT");
			Object args[] = {reqFrom};
			result=this.mytemplate.queryForMap(query,args);
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public Map<Object, Object> getMailInfo(String mobileNo) {
		Map<Object, Object> result=null;
		try{
			String query=getQuery("GET_EMAIL_INFO");
			Object args[] = {mobileNo};
			LogManager.info("Query => "+query+"\n Arguments=>"+StringUtils.join(args,","));
			result=this.mytemplate.queryForMap(query,args);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<Object, Object>> getRenewalCustInfo(String quoteNo) {
		List<Map<Object, Object>> rec=null;
		try{
			Object []args = {quoteNo};
			String query=getQuery("GET_RENEW_SMS_CUST_INFO");
			rec=this.mytemplate.queryForList(query,args);
		} catch(Exception e){
			e.printStackTrace();
		}
		return rec;
	}	
}
