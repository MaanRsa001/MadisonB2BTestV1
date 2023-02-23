package com.maan.common.login;

import java.util.List;
import java.util.Map;

public class CommonBean{
	private String loginId;
	private String pwd;
	private String utype;
	private String userId;
	private String userPwd;
	private String status;
	private String newpwd;
	private String repwd;
	private String mailId;
	private String pwdMsg;
	private int swidth;
	private String branch;
	private String bloginId;
	private String loginType;
	private String bpwd;
	private String aloginId;
	private String apwd;
	private String product;
	
	//OTP Bean
	private String otp;
	private String otpId;
	private String otpExpiry;
	private String otpStatus;
	private List<Map<String, Object>> otpList;
	private String ologinId;
	private String opwd;
	private String mailOtp;
	private String showValue;
	private String email;
	private String mode;
	private String fori18nLink;
	private Map<Integer, String> sumInsured;
	private List<String> sumInsuredList;
	private String contentType;
	private String currencyType;
	private Map<Integer, String> BrokerDetails;
	private List<Map<String, Object>> ProductDetails;
	private String scheme_id;
	//private int myOtp;
	
	private String branchName;
	
	public String getDefaultSearch(){
		return "Policy No";
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPwdMsg() {
		return pwdMsg;
	}
	public void setPwdMsg(String pwdMsg) {
		this.pwdMsg = pwdMsg;
	}
	public int getSwidth() {
		return swidth;
	}
	public void setSwidth(int swidth) {
		this.swidth = swidth;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBranch() {
		return branch;
	}
	public String getBloginId() {
		return bloginId;
	}
	public void setBloginId(String bloginId) {
		this.bloginId = bloginId;
	}
	public String getBpwd() {
		return bpwd;
	}
	public void setBpwd(String bpwd) {
		this.bpwd = bpwd;
	}
	public String getAloginId() {
		return aloginId;
	}
	public void setAloginId(String aloginId) {
		this.aloginId = aloginId;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProduct() {
		return product;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getOtpId() {
		return otpId;
	}
	public void setOtpId(String otpId) {
		this.otpId = otpId;
	}
	public String getOtpExpiry() {
		return otpExpiry;
	}
	public void setOtpExpiry(String otpExpiry) {
		this.otpExpiry = otpExpiry;
	}
	public String getOtpStatus() {
		return otpStatus;
	}
	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}
	public List<Map<String, Object>> getOtpList() {
		return otpList;
	}
	public void setOtpList(List<Map<String, Object>> otpList) {
		this.otpList = otpList;
	}
	public String getOloginId() {
		return ologinId;
	}
	public void setOloginId(String ologinId) {
		this.ologinId = ologinId;
	}
	public String getOpwd() {
		return opwd;
	}
	public void setOpwd(String opwd) {
		this.opwd = opwd;
	}
	public String getMailOtp() {
		return mailOtp;
	}
	public void setMailOtp(String mailOtp) {
		this.mailOtp = mailOtp;
	}
	public String getShowValue() {
		return showValue;
	}
	public void setShowValue(String showValue) {
		this.showValue = showValue;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFori18nLink() {
		return fori18nLink;
	}
	public void setFori18nLink(String fori18nLink) {
		this.fori18nLink = fori18nLink;
	}
	/*public void setMyOtp(int myOtp) {
		this.myOtp = myOtp;
	}
	public int getMyOtp() {
		return myOtp;
	}*/
	public Map<Integer, String> getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(Map<Integer, String> sumInsured) {
		this.sumInsured = sumInsured;
	}
	public List<String> getSumInsuredList() {
		return sumInsuredList;
	}
	public void setSumInsuredList(List<String> sumInsuredList) {
		this.sumInsuredList = sumInsuredList;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public Map<Integer, String> getBrokerDetails() {
		return BrokerDetails;
	}
	public void setBrokerDetails(Map<Integer, String> brokerDetails) {
		BrokerDetails = brokerDetails;
	}
	public List<Map<String, Object>> getProductDetails() {
		return ProductDetails;
	}
	public void setProductDetails(List<Map<String, Object>> productDetails) {
		ProductDetails = productDetails;
	}
	public String getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(String schemeId) {
		scheme_id = schemeId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	private String e;
	private String forward;
	private String b2cCustYN;

	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public void setForward(String forward) {
		this.forward = forward;
	}
	public String getForward() {
		return forward;
	}
	public String getB2cCustYN() {
		return b2cCustYN;
	}
	public void setB2cCustYN(String b2cCustYN) {
		this.b2cCustYN = b2cCustYN;
	}
}
