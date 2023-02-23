package com.maan.customer;

import java.util.List;
import java.util.Map;

import nl.captcha.Captcha;

public class CustBean {
	private String customerId;
	private String title;
	private String customerName;
	private String coreAppCode;
	private String address1; 
	private String address2;
	private String city;
	private String poBox;
	private String mobileNo;
	private String email;
	private String custCoreCode;
	private String clientCustomerId;
	private String custArNo;
	private String brokerCode;
	private String brokerName;
	private String executive;
	private String searchValue;
	
	//Motor Customer start 
	private String custLastName;
	private String custPassportNo;
	private String custdob;
	private String custAlterMobileNo;
	private String custLandLineNo;
	private String customerType;
	private String companyRegNo;
	private String custNameArabic;
	private String custdobar;
	private String custnrc1;
	private String custnrc2;
	private String custnrc3;
	private String captchavalue;
	
	private Captcha captcha;

	private String quoteNo;
	
	//Motor Customer end
	
	//Payment
	private String installmentYN;
	private String insIntialAmount;
	private String noOfIns;
	
	//common variables
	private String deviceType; //ANDROID OR HYBRID
	private String loginType; // B2C in session
	private String actualBranch; //adminBranch in session
	private String gender;
	private String occupation;
	private List<String> actionErrorsBean; //ANDROID OR HYBRID
	private List<String> actionMessagesBean; //ANDROID OR HYBRID
	
	private String policyNo;
	
	private String mobileNum;
	
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCoreAppCode() {
		return coreAppCode;
	}
	public void setCoreAppCode(String coreAppCode) {
		this.coreAppCode = coreAppCode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPoBox() {
		return poBox;
	}
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCustCoreCode() {
		return custCoreCode;
	}
	public void setCustCoreCode(String custCoreCode) {
		this.custCoreCode = custCoreCode;
	}
	public String getClientCustomerId() {
		return clientCustomerId;
	}
	public void setClientCustomerId(String clientCustomerId) {
		this.clientCustomerId = clientCustomerId;
	}
	public String getCustArNo() {
		return custArNo;
	}
	public void setCustArNo(String custArNo) {
		this.custArNo = custArNo;
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getExecutive() {
		return executive;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getCustLastName() {
		return custLastName;
	}
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	public String getCustPassportNo() {
		return custPassportNo;
	}
	public void setCustPassportNo(String custPassportNo) {
		this.custPassportNo = custPassportNo;
	}
	public String getCustAlterMobileNo() {
		return custAlterMobileNo;
	}
	public void setCustAlterMobileNo(String custAlterMobileNo) {
		this.custAlterMobileNo = custAlterMobileNo;
	}
	public String getCustLandLineNo() {
		return custLandLineNo;
	}
	public void setCustLandLineNo(String custLandLineNo) {
		this.custLandLineNo = custLandLineNo;
	}
	public String getCustdob() {
		return custdob;
	}
	public void setCustdob(String custdob) {
		this.custdob = custdob;
	}
	public String getCompanyRegNo() {
		return companyRegNo;
	}
	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustNameArabic() {
		return custNameArabic;
	}
	public void setCustNameArabic(String custNameArabic) {
		this.custNameArabic = custNameArabic;
	}
	public String getCustdobar() {
		return custdobar;
	}
	public void setCustdobar(String custdobar) {
		this.custdobar = custdobar;
	}
	public String getCustnrc1() {
		return custnrc1;
	}
	public void setCustnrc1(String custnrc1) {
		this.custnrc1 = custnrc1;
	}
	public String getCustnrc2() {
		return custnrc2;
	}
	public void setCustnrc2(String custnrc2) {
		this.custnrc2 = custnrc2;
	}
	public String getCustnrc3() {
		return custnrc3;
	}
	public void setCustnrc3(String custnrc3) {
		this.custnrc3 = custnrc3;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getActualBranch() {
		return actualBranch;
	}
	public void setActualBranch(String actualBranch) {
		this.actualBranch = actualBranch;
	}
	public String getCaptchavalue() {
		return captchavalue;
	}
	public void setCaptchavalue(String captchavalue) {
		this.captchavalue = captchavalue;
	}
	public Captcha getCaptcha() {
		return captcha;
	}
	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getOccupation() {
		return occupation;
	}
	public final String getQuoteNo() {
		return quoteNo;
	}
	public final void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public final String getInstallmentYN() {
		return installmentYN;
	}
	public final void setInstallmentYN(String installmentYN) {
		this.installmentYN = installmentYN;
	}
	public final List<String> getActionErrorsBean() {
		return actionErrorsBean;
	}
	public final void setActionErrorsBean(List<String> actionErrorsBean) {
		this.actionErrorsBean = actionErrorsBean;
	}
	public final List<String> getActionMessagesBean() {
		return actionMessagesBean;
	}
	public final void setActionMessagesBean(List<String> actionMessagesBean) {
		this.actionMessagesBean = actionMessagesBean;
	}
	/**
	 * @param insIntialAmount the insIntialAmount to set
	 */
	public void setInsIntialAmount(String insIntialAmount) {
		this.insIntialAmount = insIntialAmount;
	}
	/**
	 * @return the insIntialAmount
	 */
	public String getInsIntialAmount() {
		return insIntialAmount;
	}
	/**
	 * @param noOfIns the noOfIns to set
	 */
	public void setNoOfIns(String noOfIns) {
		this.noOfIns = noOfIns;
	}
	/**
	 * @return the noOfIns
	 */
	public String getNoOfIns() {
		return noOfIns;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	private String agencyCode;
	private String brokerBranch;
	private List<Map<String,Object>> brokerBranchList;
	private List<Map<String,Object>> brokerUserList;

	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getBrokerBranch() {
		return brokerBranch;
	}
	public void setBrokerBranch(String brokerBranch) {
		this.brokerBranch = brokerBranch;
	}
	public List<Map<String, Object>> getBrokerBranchList() {
		return brokerBranchList;
	}
	public void setBrokerBranchList(List<Map<String, Object>> brokerBranchList) {
		this.brokerBranchList = brokerBranchList;
	}
	
	private String forwardURL;

	public String getForwardURL() {
		return forwardURL;
	}
	public void setForwardURL(String forwardURL) {
		this.forwardURL = forwardURL;
	}
	public List<Map<String, Object>> getBrokerUserList() {
		return brokerUserList;
	}
	public void setBrokerUserList(List<Map<String, Object>> brokerUserList) {
		this.brokerUserList = brokerUserList;
	}
	
}
