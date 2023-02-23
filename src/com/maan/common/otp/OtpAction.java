package com.maan.common.otp;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.sms.SmsEmailUtil;
import com.opensymphony.xwork2.ActionSupport;

public class OtpAction extends  ActionSupport {
	
	private static final long serialVersionUID = 1L;
	final Logger logger = LogUtil.getLogger(OTPGenerator.class);
    
	OTPGenerator otpGen=new OTPGenerator();
    
    private String otpId;
    private String reqFrom;
    private String otp;
    private String mobile;
    private String email;
    
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtp() {
		return otp;
	}

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
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile() {
		return mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String regenAjax(){
		if(StringUtils.isNotBlank(otpId)){
			String otp = Integer.toString(otpGen.getOTP());
			String mailotp = Integer.toString(otpGen.getOTP());
			otpGen.updateOTP(otpId,otp,mailotp);
			//new SmsEmailUtil("GET_OTP",otpId).send();
			new SmsEmailUtil("GET_OTP",otp,mobile,new OTPGenerator().getOtpExpiry(otpId),mailotp,email).send();
			//addActionMessage("OTP Regenerate Successfully");
			addActionError(getText("error.otp.success"));
		}
		return "otpAjax";
	}
	
	
}

