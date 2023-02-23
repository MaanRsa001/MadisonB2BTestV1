package com.maan.Travel.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maan.customer.CustBean;


public class TravelBean extends CustBean {
	
	private String productId;
	private String branchCode;
	private String loginId;
	private String issuer;
	private String display;
	private String actionType;
	private String originCountry;
	private String destCountry;
	private String userType;
	private String option;
	private String schemeCover;
	private String travelCover;
	private String coverPeriod;
	private String inceptionDt;
	private String expiryDt;
	private double childPremium=0.0;
	private double adultPremium=0.0;
	private double basePremium;
	private double familyPerson=0.0;
	private double spousevalue=0.0;
	private double loadOrDiscPremium=0.0;
	private double policyFee=0.0;
	private double totalPremium=0.0;
	private String finalPremium;
	private double endtPremium;
	private double premiumPaid;
	private double discountAmt;
	private int noOfAdults=0;
	private int noOfChilds=0;
	private double relationDiscount;
	private String discountType;
	private double coverLoadPercentage;
	private double coverDiscountPercentage;
	private int serialNo;
	private String travelName;
	private String scheme_Covers;
	private String travel_Covers;
	private String spouseDiscountYN;
	private String familyDiscountYN;
	private String groupDiscountYN;
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
	private String selection;
	private String[] optionalCovers;
	private String optCovers;
	private String mode;
	private String backDt;
	private String optionMode;
	private String emirate;
	private String declarationYN;
	
	private String chequeNo;
	private String chequeDate;
	private String chequeAmount;
	private String bankName;
	private String micrCode;
	private String modeOfPayment;
	private String cashDepositBank;
	private String cashAmount;
	private String cashChellanNo;
	private String cashInstrumentDate;
	private String merchant_reference;
	private String currencyType;
	
	private String mtnMobileNo;
	
	private List<String> travelList;
	private List<Integer> serialNos;
	private List<String> travelNames;
	private List<String> dobs;
	private List<String> genders;
	private List<String> relations;
	private List<String> nationalitys;
	private List<Integer> ages;
	private List<String> coverages;
	private List<Double> travelPremium;
	private List<Double> coveragePremium;
	private List<Object> travelListDetail;
	private List<String> passportNo;
	private List<String> passportExpDate;
	private List<String> travelLastNames;
	private List cover=new ArrayList();
	Map map=new HashMap();
	
	
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getSchemeCover() {
		return schemeCover;
	}
	public void setSchemeCover(String schemeCover) {
		this.schemeCover = schemeCover;
	}
	public String getTravelCover() {
		return travelCover;
	}
	public void setTravelCover(String travelCover) {
		this.travelCover = travelCover;
	}
	public String getCoverPeriod() {
		return coverPeriod;
	}
	public void setCoverPeriod(String coverPeriod) {
		this.coverPeriod = coverPeriod;
	}
	public String getInceptionDt() {
		return inceptionDt;
	}
	public void setInceptionDt(String inceptionDt) {
		this.inceptionDt = inceptionDt;
	}
	public String getExpiryDt() {
		return expiryDt;
	}
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}
	public double getChildPremium() {
		return childPremium;
	}
	public void setChildPremium(double childPremium) {
		this.childPremium = childPremium;
	}
	public double getAdultPremium() {
		return adultPremium;
	}
	public void setAdultPremium(double adultPremium) {
		this.adultPremium = adultPremium;
	}
	public double getBasePremium() {
		return basePremium;
	}
	public void setBasePremium(double basePremium) {
		this.basePremium = basePremium;
	}
	public double getFamilyPerson() {
		return familyPerson;
	}
	public void setFamilyPerson(double familyPerson) {
		this.familyPerson = familyPerson;
	}
	public double getSpousevalue() {
		return spousevalue;
	}
	public void setSpousevalue(double spousevalue) {
		this.spousevalue = spousevalue;
	}
	public double getLoadOrDiscPremium() {
		return loadOrDiscPremium;
	}
	public void setLoadOrDiscPremium(double loadOrDiscPremium) {
		this.loadOrDiscPremium = loadOrDiscPremium;
	}
	public double getPolicyFee() {
		return policyFee;
	}
	public void setPolicyFee(double policyFee) {
		this.policyFee = policyFee;
	}
	public double getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(double totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getFinalPremium() {
		return finalPremium;
	}
	public void setFinalPremium(String finalPremium) {
		this.finalPremium = finalPremium;
	}
	public double getEndtPremium() {
		return endtPremium;
	}
	public void setEndtPremium(double endtPremium) {
		this.endtPremium = endtPremium;
	}
	public double getPremiumPaid() {
		return premiumPaid;
	}
	public void setPremiumPaid(double premiumPaid) {
		this.premiumPaid = premiumPaid;
	}
	public double getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(double discountAmt) {
		this.discountAmt = discountAmt;
	}
	public int getNoOfAdults() {
		return noOfAdults;
	}
	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}
	public int getNoOfChilds() {
		return noOfChilds;
	}
	public void setNoOfChilds(int noOfChilds) {
		this.noOfChilds = noOfChilds;
	}
	public double getRelationDiscount() {
		return relationDiscount;
	}
	public void setRelationDiscount(double relationDiscount) {
		this.relationDiscount = relationDiscount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public double getCoverLoadPercentage() {
		return coverLoadPercentage;
	}
	public void setCoverLoadPercentage(double coverLoadPercentage) {
		this.coverLoadPercentage = coverLoadPercentage;
	}
	public double getCoverDiscountPercentage() {
		return coverDiscountPercentage;
	}
	public void setCoverDiscountPercentage(double coverDiscountPercentage) {
		this.coverDiscountPercentage = coverDiscountPercentage;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getTravelName() {
		return travelName;
	}
	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	public String getScheme_Covers() {
		return scheme_Covers;
	}
	public void setScheme_Covers(String schemeCovers) {
		scheme_Covers = schemeCovers;
	}
	public String getTravel_Covers() {
		return travel_Covers;
	}
	public void setTravel_Covers(String travelCovers) {
		travel_Covers = travelCovers;
	}
	public String getSpouseDiscountYN() {
		return spouseDiscountYN;
	}
	public void setSpouseDiscountYN(String spouseDiscountYN) {
		this.spouseDiscountYN = spouseDiscountYN;
	}
	public String getFamilyDiscountYN() {
		return familyDiscountYN;
	}
	public void setFamilyDiscountYN(String familyDiscountYN) {
		this.familyDiscountYN = familyDiscountYN;
	}
	public String getGroupDiscountYN() {
		return groupDiscountYN;
	}
	public void setGroupDiscountYN(String groupDiscountYN) {
		this.groupDiscountYN = groupDiscountYN;
	}
	public String getModeOfPay() {
		return modeOfPay;
	}
	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}
	public String getGeneratePolicyYN() {
		return generatePolicyYN;
	}
	public void setGeneratePolicyYN(String generatePolicyYN) {
		this.generatePolicyYN = generatePolicyYN;
	}
	public String getQuoteEmailYN() {
		return quoteEmailYN;
	}
	public void setQuoteEmailYN(String quoteEmailYN) {
		this.quoteEmailYN = quoteEmailYN;
	}
	public String getPolicyEmailYN() {
		return policyEmailYN;
	}
	public void setPolicyEmailYN(String policyEmailYN) {
		this.policyEmailYN = policyEmailYN;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}
	public String getDebitNo() {
		return debitNo;
	}
	public void setDebitNo(String debitNo) {
		this.debitNo = debitNo;
	}
	public String getShowReferralYN() {
		return showReferralYN;
	}
	public void setShowReferralYN(String showReferralYN) {
		this.showReferralYN = showReferralYN;
	}
	public String getReferralYN() {
		return referralYN;
	}
	public void setReferralYN(String referralYN) {
		this.referralYN = referralYN;
	}
	public String getReferralComments() {
		return referralComments;
	}
	public void setReferralComments(String referralComments) {
		this.referralComments = referralComments;
	}
	public String getReferralMsg() {
		return referralMsg;
	}
	public void setReferralMsg(String referralMsg) {
		this.referralMsg = referralMsg;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getAdminRefStatus() {
		return adminRefStatus;
	}
	public void setAdminRefStatus(String adminRefStatus) {
		this.adminRefStatus = adminRefStatus;
	}
	public String getAdminRemarks() {
		return adminRemarks;
	}
	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCancelPolicy() {
		return cancelPolicy;
	}
	public void setCancelPolicy(String cancelPolicy) {
		this.cancelPolicy = cancelPolicy;
	}
	public String getReissuePolicy() {
		return reissuePolicy;
	}
	public void setReissuePolicy(String reissuePolicy) {
		this.reissuePolicy = reissuePolicy;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getQuoteStatus() {
		return quoteStatus;
	}
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getBrokerCompany() {
		return brokerCompany;
	}
	public void setBrokerCompany(String brokerCompany) {
		this.brokerCompany = brokerCompany;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public String[] getOptionalCovers() {
		return optionalCovers;
	}
	public void setOptionalCovers(String[] optionalCovers) {
		this.optionalCovers = optionalCovers;
	}
	public String getOptCovers() {
		return optCovers;
	}
	public void setOptCovers(String optCovers) {
		this.optCovers = optCovers;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getBackDt() {
		return backDt;
	}
	public void setBackDt(String backDt) {
		this.backDt = backDt;
	}
	public String getOptionMode() {
		return optionMode;
	}
	public void setOptionMode(String optionMode) {
		this.optionMode = optionMode;
	}
	public String getEmirate() {
		return emirate;
	}
	public void setEmirate(String emirate) {
		this.emirate = emirate;
	}
	public String getDeclarationYN() {
		return declarationYN;
	}
	public void setDeclarationYN(String declarationYN) {
		this.declarationYN = declarationYN;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getChequeDate() {
		return chequeDate;
	}
	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
	}
	public String getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(String chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getCashDepositBank() {
		return cashDepositBank;
	}
	public void setCashDepositBank(String cashDepositBank) {
		this.cashDepositBank = cashDepositBank;
	}
	public String getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(String cashAmount) {
		this.cashAmount = cashAmount;
	}
	public String getCashChellanNo() {
		return cashChellanNo;
	}
	public void setCashChellanNo(String cashChellanNo) {
		this.cashChellanNo = cashChellanNo;
	}
	public String getCashInstrumentDate() {
		return cashInstrumentDate;
	}
	public void setCashInstrumentDate(String cashInstrumentDate) {
		this.cashInstrumentDate = cashInstrumentDate;
	}
	public String getMerchant_reference() {
		return merchant_reference;
	}
	public void setMerchant_reference(String merchantReference) {
		merchant_reference = merchantReference;
	}
	public List<String> getTravelList() {
		return travelList;
	}
	public void setTravelList(List<String> travelList) {
		this.travelList = travelList;
	}
	public List<Integer> getSerialNos() {
		return serialNos;
	}
	public void setSerialNos(List<Integer> serialNos) {
		this.serialNos = serialNos;
	}
	public List<String> getTravelNames() {
		return travelNames;
	}
	public void setTravelNames(List<String> travelNames) {
		this.travelNames = travelNames;
	}
	public List<String> getDobs() {
		return dobs;
	}
	public void setDobs(List<String> dobs) {
		this.dobs = dobs;
	}
	public List<String> getGenders() {
		return genders;
	}
	public void setGenders(List<String> genders) {
		this.genders = genders;
	}
	public List<String> getRelations() {
		return relations;
	}
	public void setRelations(List<String> relations) {
		this.relations = relations;
	}
	public List<String> getNationalitys() {
		return nationalitys;
	}
	public void setNationalitys(List<String> nationalitys) {
		this.nationalitys = nationalitys;
	}
	public List<Integer> getAges() {
		return ages;
	}
	public void setAges(List<Integer> ages) {
		this.ages = ages;
	}
	public List<String> getCoverages() {
		return coverages;
	}
	public void setCoverages(List<String> coverages) {
		this.coverages = coverages;
	}
	public List<Double> getTravelPremium() {
		return travelPremium;
	}
	public void setTravelPremium(List<Double> travelPremium) {
		this.travelPremium = travelPremium;
	}
	public List<Double> getCoveragePremium() {
		return coveragePremium;
	}
	public void setCoveragePremium(List<Double> coveragePremium) {
		this.coveragePremium = coveragePremium;
	}
	public List<Object> getTravelListDetail() {
		return travelListDetail;
	}
	public void setTravelListDetail(List<Object> travelListDetail) {
		this.travelListDetail = travelListDetail;
	}
	public List<String> getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(List<String> passportNo) {
		this.passportNo = passportNo;
	}
	public List<String> getPassportExpDate() {
		return passportExpDate;
	}
	public void setPassportExpDate(List<String> passportExpDate) {
		this.passportExpDate = passportExpDate;
	}
	public List<String> getTravelLastNames() {
		return travelLastNames;
	}
	public void setTravelLastNames(List<String> travelLastNames) {
		this.travelLastNames = travelLastNames;
	}
	public List getCover() {
		return cover;
	}
	public void setCover(List cover) {
		this.cover = cover;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	
//  OTP BLOCK START   //
	private String otpId;
	private String otpStatus;
	private String otp;
	private String mailOtp;
	//private int myOtp;
	private String disMode;

	public String getOtpId() {
		return otpId;
	}
	public void setOtpId(String otpId) {
		this.otpId = otpId;
	}
	public String getOtpStatus() {
		return otpStatus;
	}
	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getMailOtp() {
		return mailOtp;
	}
	public void setMailOtp(String mailOtp) {
		this.mailOtp = mailOtp;
	}
	/*public void setMyOtp(int myOtp) {
		this.myOtp = myOtp;
	}
	public int getMyOtp() {
		return myOtp;
	}*/
	public String getDisMode() {
		return disMode;
	}
	public void setDisMode(String disMode) {
		this.disMode = disMode;
	}
	//    OTP BLOCK END    //
	/**
	 * @return the mtnMobileNo
	 */
	public String getMtnMobileNo() {
		return mtnMobileNo;
	}
	/**
	 * @param mtnMobileNo the mtnMobileNo to set
	 */
	public void setMtnMobileNo(String mtnMobileNo) {
		this.mtnMobileNo = mtnMobileNo;
	}
	


}
