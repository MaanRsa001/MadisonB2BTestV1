package com.maan.Health.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthBean {
	
	private String productId;
	private String branchCode;
	private String loginId;
	private String issuer;
	private String quoteNo;
	private String customerId;
	private String display;
	private String actionType;
	private String originCountry;
	private String destCountry;
	private String userType;
	private String option;
	private String title;
	private String fullName;
	private String address;
	private String cusCivilId;
	private String poBox;
	private String country;
	private String nationality;
	private String telephoneNo;
	private String mobileNo;
	private String fax;
	private String emailId;
	private String city;
	private String dob;
	private String occupation;
	private String gender;
	private String relation;
	private String schemeCover;
	private String travelCover;
	private String coverPeriod;
	private String inceptionDt;
	private String expiryDt;
	private double basePremium;
	private double loadOrDiscPremium=0.0;
	private double policyFee=0.0;
	private double totalPremium=0.0;
	private double finalPremium;
	private double endtPremium;
	private double premiumPaid;
	private double discountAmt;
	private String discountType;
	private String insurerName;
	private String scheme_Covers;
	private String modeOfPay;
	private String generatePolicyYN;
	private String quoteEmailYN;
	private String policyEmailYN;
	private String policyNo;
	private String receiptNo;
	private String debitNo;
	private String showReferralYN;
	private String referralYN;
	private String referralComments;
	private String referralMsg;
	private String applicationNo;
	private String adminRefStatus;
	private String adminRemarks;
	private String sign;
	private String user;
	private String cancelPolicy;
	private String reissuePolicy;
	private String reason;
	private String quoteStatus;
	private String entryDate;
	private String brokerCompany;
	private String amendId;
	private String maritalStatus;
	private String sponsorId;
	private String sponsorName;
	private String sponsorCity;
	private String sponsorMobileNo;
	private List<UploadBean> documentList = null;
	
	
	
	

	Map map=new HashMap();
	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	List cover=new ArrayList();
	
	/**
	 * @return the cancelPolicy
	 */
	public String getCancelPolicy() {
		return cancelPolicy;
	}

	public void setCancelPolicy(String cancelPolicy) {
		this.cancelPolicy = cancelPolicy;
	}
	/**
	 * @return the reissuePolicy
	 */
	public String getReissuePolicy() {
		return reissuePolicy;
	}
	/**
	 * @param reissuePolicy the reissuePolicy to set
	 */
	public void setReissuePolicy(String reissuePolicy) {
		this.reissuePolicy = reissuePolicy;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param issuer the issuer to set
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * @return the quoteNo
	 */
	public String getQuoteNo() {
		return quoteNo;
	}
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}
	/**
	 * @param display the display to set
	 */
	public void setDisplay(String display) {
		this.display = display;
	}
	
	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	/**
	 * @return the originCountry
	 */
	public String getOriginCountry() {
		return originCountry;
	}
	/**
	 * @param originCountry the originCountry to set
	 */
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	/**
	 * @return the destCountry
	 */
	public String getDestCountry() {
		return destCountry;
	}
	/**
	 * @param destCountry the destCountry to set
	 */
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	
	
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}
	/**
	 * @param option the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the cusCivilId
	 */
	public String getCusCivilId() {
		return cusCivilId;
	}
	/**
	 * @param cusCivilId the cusCivilId to set
	 */
	public void setCusCivilId(String cusCivilId) {
		this.cusCivilId = cusCivilId;
	}
	/**
	 * @return the poBox
	 */
	public String getPoBox() {
		return poBox;
	}
	/**
	 * @param poBox the poBox to set
	 */
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the telephoneNo
	 */
	public String getTelephoneNo() {
		return telephoneNo;
	}
	/**
	 * @param telephoneNo the telephoneNo to set
	 */
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param eMailId the eMailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}
	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}
	/**
	 * @return the schemeCover
	 */
	public String getSchemeCover() {
		return schemeCover;
	}
	/**
	 * @param schemeCover the schemeCover to set
	 */
	public void setSchemeCover(String schemeCover) {
		this.schemeCover = schemeCover;
	}
	/**
	 * @return the travelCover
	 */
	public String getTravelCover() {
		return travelCover;
	}
	/**
	 * @param travelCover the travelCover to set
	 */
	public void setTravelCover(String travelCover) {
		this.travelCover = travelCover;
	}
	/**
	 * @return the coverPeriod
	 */
	public String getCoverPeriod() {
		return coverPeriod;
	}
	/**
	 * @param coverPeriod the coverPeriod to set
	 */
	public void setCoverPeriod(String coverPeriod) {
		this.coverPeriod = coverPeriod;
	}
	/**
	 * @return the inceptionDt
	 */
	public String getInceptionDt() {
		return inceptionDt;
	}

	/**
	 * @param inceptionDt the inceptionDt to set
	 */
	public void setInceptionDt(String inceptionDt) {
		this.inceptionDt = inceptionDt;
	}
	/**
	 * @return the expiryDt
	 */
	public String getExpiryDt() {
		return expiryDt;
	}
	/**
	 * @param expiryDt the expiryDt to set
	 */
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}
	
	
	/**
	 * @return the basePremium
	 */
	public double getBasePremium() {
		return basePremium;
	}
	/**
	 * @param basePremium the basePremium to set
	 */
	public void setBasePremium(double basePremium) {
		this.basePremium = basePremium;
	}
	/**
	 * @return the discountType
	 */
	public String getDiscountType() {
		return discountType;
	}
	/**
	 * @param discountType the discountType to set
	 */
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	/**
	 * @return the insurerName
	 */
	public String getInsurerName() {
		return insurerName;
	}
	/**
	 * @param insurerName the insurerName to set
	 */
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	/**
	 * @return the scheme_Covers
	 */
	public String getScheme_Covers() {
		return scheme_Covers;
	}
	/**
	 * @param schemeCovers the scheme_Covers to set
	 */
	public void setScheme_Covers(String schemeCovers) {
		scheme_Covers = schemeCovers;
	}
	/**
	 * @return the personal_baggage
	 */
	/**
	 * @return the loadOrDiscPremium
	 */
	public double getLoadOrDiscPremium() {
		return loadOrDiscPremium;
	}
	/**
	 * @param loadOrDiscPremium the loadOrDiscPremium to set
	 */
	public void setLoadOrDiscPremium(double loadOrDiscPremium) {
		this.loadOrDiscPremium = loadOrDiscPremium;
	}
	
	/**
	 * @return the policyFee
	 */
	public double getPolicyFee() {
		return policyFee;
	}

	/**
	 * @param policyFee the policyFee to set
	 */
	public void setPolicyFee(double policyFee) {
		this.policyFee = policyFee;
	}

	/**
	 * @return the totalPremium
	 */
	public double getTotalPremium() {
		return totalPremium;
	}
	/**
	 * @param totalPremium the totalPremium to set
	 */
	public void setTotalPremium(double totalPremium) {
		this.totalPremium = totalPremium;
	}
	
	/**
	 * @return the finalPremium
	 */
	public double getFinalPremium() {
		return finalPremium;
	}
	/**
	 * @param finalPremium the finalPremium to set
	 */
	public void setFinalPremium(double finalPremium) {
		this.finalPremium = finalPremium;
	}
	
	
	/**
	 * @return the endtPremium
	 */
	public double getEndtPremium() {
		return endtPremium;
	}

	/**
	 * @param endtPremium the endtPremium to set
	 */
	public void setEndtPremium(double endtPremium) {
		this.endtPremium = endtPremium;
	}

	/**
	 * @return the premiumPaid
	 */
	public double getPremiumPaid() {
		return premiumPaid;
	}

	/**
	 * @param premiumPaid the premiumPaid to set
	 */
	public void setPremiumPaid(double premiumPaid) {
		this.premiumPaid = premiumPaid;
	}

	/**
	 * @return the discountAmt
	 */
	public double getDiscountAmt() {
		return discountAmt;
	}
	/**
	 * @param discountAmt the discountAmt to set
	 */
	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}
	/**
	 * @return the modeOfPay
	 */
	public String getModeOfPay() {
		return modeOfPay;
	}
	/**
	 * @param modeOfPay the modeOfPay to set
	 */
	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}
	/**
	 * @return the generatePolicyYN
	 */
	public String getGeneratePolicyYN() {
		return generatePolicyYN;
	}
	/**
	 * @param generatePolicyYN the generatePolicyYN to set
	 */
	public void setGeneratePolicyYN(String generatePolicyYN) {
		this.generatePolicyYN = generatePolicyYN;
	}
	
	/**
	 * @return the quoteEmailYN
	 */
	public String getQuoteEmailYN() {
		return quoteEmailYN;
	}

	/**
	 * @param quoteEmailYN the quoteEmailYN to set
	 */
	public void setQuoteEmailYN(String quoteEmailYN) {
		this.quoteEmailYN = quoteEmailYN;
	}

	/**
	 * @return the policyEmailYN
	 */
	public String getPolicyEmailYN() {
		return policyEmailYN;
	}

	/**
	 * @param policyEmailYN the policyEmailYN to set
	 */
	public void setPolicyEmailYN(String policyEmailYN) {
		this.policyEmailYN = policyEmailYN;
	}

	/**
	 * @return the policyNo
	 */
	public String getPolicyNo() {
		return policyNo;
	}
	/**
	 * @param policyNo the policyNo to set
	 */
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
	
	/**
	 * @return the receiptNo
	 */
	public String getReceiptNo() {
		return receiptNo;
	}

	/**
	 * @param receiptNo the receiptNo to set
	 */
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	
	/**
	 * @return the debitNo
	 */
	public String getDebitNo() {
		return debitNo;
	}

	/**
	 * @param debitNo the debitNo to set
	 */
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}


	/**
	 * @return the showReferralYN
	 */
	public String getShowReferralYN() {
		return showReferralYN;
	}

	/**
	 * @param showReferralYN the showReferralYN to set
	 */
	public void setShowReferralYN(String showReferralYN) {
		this.showReferralYN = showReferralYN;
	}

	/**
	 * @return the referralYN
	 */
	public String getReferralYN() {
		return referralYN;
	}
	/**
	 * @param referralYN the referralYN to set
	 */
	public void setReferralYN(String referralYN) {
		this.referralYN = referralYN;
	}
	/**
	 * @return the referralComments
	 */
	public String getReferralComments() {
		return referralComments;
	}
	/**
	 * @param referralComments the referralComments to set
	 */
	public void setReferralComments(String referralComments) {
		this.referralComments = referralComments;
	}
	/**
	 * @return the referralMsg
	 */
	public String getReferralMsg() {
		return referralMsg;
	}
	/**
	 * @param referralMsg the referralMsg to set
	 */
	public void setReferralMsg(String referralMsg) {
		this.referralMsg = referralMsg;
	}
	/**
	 * @return the applicationNo
	 */
	public String getApplicationNo() {
		return applicationNo;
	}
	/**
	 * @param applicationNo the applicationNo to set
	 */
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	
	/**
	 * @return the adminRefStatus
	 */
	public String getAdminRefStatus() {
		return adminRefStatus;
	}
	/**
	 * @param adminRefStatus the adminRefStatus to set
	 */
	public void setAdminRefStatus(String adminRefStatus) {
		this.adminRefStatus = adminRefStatus;
	}
	/**
	 * @return the adminRemarks
	 */
	public String getAdminRemarks() {
		return adminRemarks;
	}
	/**
	 * @param adminRemarks the adminRemarks to set
	 */
	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the quoteStatus
	 */
	public String getQuoteStatus() {
		return quoteStatus;
	}
	/**
	 * @param quoteStatus the quoteStatus to set
	 */
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}
	public List getCover() {
		return cover;
	}
	public void setCover(List cover) {
		this.cover = cover;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	
	
	/**
	 * @return the brokerCompany
	 */
	public String getBrokerCompany() {
		return brokerCompany;
	}

	/**
	 * @param brokerCompany the brokerCompany to set
	 */
	public void setBrokerCompany(String brokerCompany) {
		this.brokerCompany = brokerCompany;
	}

	/**
	 * @return the amendId
	 */
	public String getAmendId() {
		return amendId;
	}

	/**
	 * @param amendId the amendId to set
	 */
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the sponsorId
	 */
	public String getSponsorId() {
		return sponsorId;
	}

	/**
	 * @param sponsorId the sponsorId to set
	 */
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}

	/**
	 * @return the sponsorName
	 */
	public String getSponsorName() {
		return sponsorName;
	}

	/**
	 * @param sponsorName the sponsorName to set
	 */
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	/**
	 * @return the sponsorCity
	 */
	public String getSponsorCity() {
		return sponsorCity;
	}

	/**
	 * @param sponsorCity the sponsorCity to set
	 */
	public void setSponsorCity(String sponsorCity) {
		this.sponsorCity = sponsorCity;
	}

	/**
	 * @return the sponsorMobileNo
	 */
	public String getSponsorMobileNo() {
		return sponsorMobileNo;
	}

	/**
	 * @param sponsorMobileNo the sponsorMobileNo to set
	 */
	public void setSponsorMobileNo(String sponsorMobileNo) {
		this.sponsorMobileNo = sponsorMobileNo;
	}
	
	 public List<UploadBean> getDocumentList() {
			return documentList;
		}

	/**
	 * @param documentList the documentList to set
	 */
	public void setDocumentList(List<UploadBean> documentList) {
		this.documentList = documentList;
	}
	 
}
