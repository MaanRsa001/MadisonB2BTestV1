package com.maan.common.otp;

import java.security.Key;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.crypto.KeyGenerator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.common.LogManager;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionSupport;

public class OTPGenerator extends  MyJdbcTemplate {
	final Logger logger = LogUtil.getLogger(OTPGenerator.class);
    
    
    private String otpId;
    private String reqFrom;
    
    /*private String otp;
	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getOtp() {
		return otp;
	}*/

	public void setOtpId(String otpId) {
		this.otpId = otpId;
	}

	public String getOtpId() {
		return otpId;
	}

	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}

	public String getReqFrom() {
		return reqFrom;
	}

	
	public int getOTP() {
    	int otp=0;
    	try{
    		final com.maan.common.otp.TimeBasedOneTimePasswordGenerator totp = new com.maan.common.otp.TimeBasedOneTimePasswordGenerator();
    		final Key secretKey;
    		{
    			final KeyGenerator keyGenerator = KeyGenerator.getInstance(totp.getAlgorithm());
    		    // SHA-1 and SHA-256 prefer 64-byte (512-bit) keys; SHA512 prefers 128-byte keys
    		    keyGenerator.init(512);
    		    secretKey = keyGenerator.generateKey();
    		}
    		Date now = new Date();
			//Date later = new Date(now.getTime() + TimeUnit.SECONDS.toMillis(30));
    		otp=totp.generateOneTimePassword(secretKey, now);
    		LogManager.info("OTP==>" + otp);
		}catch(Exception e){
			e.printStackTrace();
			}	
		return otp;
    }
	
	public LinkedList<String> getValidate(String otp,String otpId, String mailOtp)	{
		LinkedList<String> list=new LinkedList<String>();
			if(checkOtp(otp,otpId)){
				list.add("error.otp");
			}/*if(checkMailOtp(mailOtp,otpId)){
				list.add("error.mailotp");
			}*/
			if(list.size()==0){
			if(checkOtpExpiry(otp,otpId)){
				list.add("error.otp.expire");
			}
			}
		return list;
	}
	
	
	public String insertOTP(String[] args) {
		String result="";
		String query="";
		try{
			//args[0]->OTP_ID,args[1]->OTP_TYPE,args[2]->OTP,args[3]->MOBILE_NO
			//args[4]->EMAIL_ID,args[5]->SESSION_ID,args[6]->STATUS
			if(StringUtils.isBlank(args[0])){
				result=(String) this.mytemplate.queryForObject(getQuery("GET_OTP_ID"),String.class);
				args[0]=result;
				query = getQuery("INSERT_OTP_DETAILS");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(args,", "));
				removeNull(args);
				this.mytemplate.update(query, args);
			}else{
				query = getQuery("UPDATE_OTP_DETAILS");
				LogManager.info("Query==>" + query);
				LogManager.info("Args==>" + StringUtils.join(new Object[]{args[2],args[7],args[0]},", "));
				removeNull(args);
				this.mytemplate.update(query, new Object[]{args[2],args[7],args[0]});
				result=args[0];
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}
	
	public String updateOTP(String otpId,String otp, String mailotp) {
		String result="";
		String query="";
		try{
			query = getQuery("UPDATE_OTP_DETAILS");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(new Object[]{otp,mailotp,otpId},", "));
			this.mytemplate.update(query, new Object[]{otp,mailotp,otpId});
			result=otpId;
		}catch(Exception e){
			e.printStackTrace();
		}	
		return result;
	}

	public String otpRegenAjax(){
		if(StringUtils.isNotBlank(otpId)){
			String otp = Integer.toString(getOTP());
			String mailotp = Integer.toString(getOTP());
			updateOTP(otpId,mailotp,otp);
		}
		return "otpAjax";
	}
	
	/*public List<Map<String, Object>> getlistotp(String otpId) {
		String query="";
		List<Map<String, Object>> result=null;
		try{
			query = getQuery("GET_OTP_DETAILS");
			result = this.mytemplate.queryForList(query,new Object[]{otpId});
		}catch(Exception e){
			e.printStackTrace();
		}
	return result;
	}*/
	
	public boolean checkOtp(String otp,String otpId) {
		boolean result = true;
		String query="";
		try{
			query = getQuery("CHECK_OTP_COUNT");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(new Object[]{otp,otpId},", "));
			int count = this.mytemplate.queryForInt(query,new Object[]{otp,otpId});
			if(count>0){
				result = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	public boolean checkMailOtp(String otp,String otpId) {
		boolean result = true;
		String query="";
		try{
			query = getQuery("CHECK_MAILOTP_COUNT");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(new Object[]{otp,otpId},", "));
			int count = this.mytemplate.queryForInt(query,new Object[]{otp,otpId});
			if(count>0){
				result = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
		
	}
	public boolean checkOtpExpiry(String otp,String otpId) {
		boolean result = true;
		String query="";
		try{
			query = getQuery("CHECK_OTP_EXPIRY");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(new Object[]{otp,otpId},", "));
			int count = this.mytemplate.queryForInt(query,new Object[]{otpId,otp});
			if(count > 0){
				result = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String getOtpExpiry(String otpId) {
		String result ="";
		String query="";
		try{
			query = getQuery("GET_OTP_EXPIRY");
			LogManager.info("Query==>" + query);
			LogManager.info("Args==>" + StringUtils.join(new Object[]{otpId},", "));
			result = (String) this.mytemplate.queryForObject(query,new Object[]{otpId},String.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}

