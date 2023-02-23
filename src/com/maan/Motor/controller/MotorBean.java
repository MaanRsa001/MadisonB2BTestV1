package com.maan.Motor.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.maan.customer.CustBean;


public class MotorBean extends CustBean{
	private String loginId;
	private String issuer;
	private String branchCode;
	private String productId;
	private String userType;
	private String destCountry;
	private String originCountry;
	private String user;
	private String display;
	private String option;
	private String applicationNo;
	//private String policyNo;
	private String actionType;
	private String policyStartDate;
	private String policyEndDate;
	private String policyEndDatePeriod;
	private String currencyType;
	private String vehicleId;
	private String tpLiablityYN;
	private String tpLiablityAmount;
	
	private String make;
	private String model;
	private String vehicleUsage;
	private String seatingCapacity;
	private String typeBody;
	private String menuType;
	
	private String docType;
	private String refNo;
	private String detailId;
	private String deviceModel;
	private String deviceManuf;
	private String deviceBrand;
	private String deviceProduct;
	private String encodedImageStr;
	private String webRootPath;
	private String policyName;
	
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	private String assistantType;
	private String desc;
	private String longitude;
	private String latitude;
	private String reqFrom;
	private String loc;
	private String feedBack;
	private List<Map<String,Object>> TypeofAssistantList;
	private List<Map<String,Object>> refNoList;
	private List<HashMap<String,Object>> errors = new ArrayList<HashMap<String,Object>>();
	
	
	/*
	private String sumInsured;
	private String cubicCapacity;
	private String noOfCylinder;
	private String areaCoverage;
	private String agencyRepairYN;
	private String driverDOB;
	private String driverNationality;
	private String uaeLicenceNo;
	private String uaeLicExpDT;
	private String claimYN;
	private String claimAmount;
	private String noClaimBonus;
	*/
	
	/*private String regNo;
	private String chassisNo;
	private String engineNo;
	private String vehicleColour;
	private String vehicleRegLoc;
	private String plateCharacter;
	private String leasedYN;
	private String bankOfFinance;
	private String regYr;*/
	

	public List<HashMap<String, Object>> getErrors() {
		return errors;
	}
	public void setErrors(List<HashMap<String, Object>> errors) {
		this.errors = errors;
	}
	private String fleetNo;
	private String makeName;
	private String modelName;
	private String typeBodyName;
	private String quoteDate;
	private String product;
	private String vehicleUsageName;
	private String agencyRepairName;
	//private String minPremium;
	private String rate;
	private String policyType;
	private String[] policyCover;
	private List<Map<String,Object>> premiumInfo;
	private String premium;
	private String loadOrDiscPremium;
	private String policyFee;
	private String totalPremium;
	private String generatePolicyYN;
	private String quoteEmailYN;
	private String quoteStatus;
	private String insNameArabic;
	private String insAddressArabic;
	private String showReferralYN;
	private String referralYN;
	private String referralComments;
	private String receiptNo;
	private String debitNo;
	private String modeOfPay;
	private String chqInvNo;
	private String amendId;
	private String adminRefStatus;
	private String adminRemarks;
	private String referralMsg;
	private String sign;
	private List<Integer> coverId;
	private List<Double> sI;
	private List<Double> baseRate;
	private String optCovers;
	private String transId;
	private String policyCoverId;
	private String helpType;
	
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
	private String captchText;
	public String getDrivercheckbox() {
		return drivercheckbox;
	}
	public void setDrivercheckbox(String drivercheckbox) {
		this.drivercheckbox = drivercheckbox;
	}
	private String drivercheckbox;
	
	private String userId;
	private String contentTypeId;
	
	private String isVehicleEdit;
	
	private String loginBranch;
	
	private List<Map<String,Object>> policyTypeList;
	/*private List<Map<String,Object>> customerIndividualList;
	private List<Map<String,Object>> customerCorporateList;*/
	private String mode;
	private String referal;
	private String mtnMobileNo;
	private String policyTypeDesc;
	private String referQuoteYN;
	private String brokeruser;
	
	
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
	private String endTypeId;
	private String brokerCompany;
	private String custName;
	private String declarationYN;
	
	private String deleteVehicleId;
	private String rateVehicleId;
	private String totalCoverPremium;
	private String premiumType;
	
	private String merchant_reference;
	
	private List<Map<String,Object>> policyInformationList;
	
	private List<String> makeIdList;
	private List<String> modelIdList;
	private List<String> typeBodyIdList;
	private List<String> mfgYrIdList;
	private List<String> sumInsuredList;
	private List<String> cubicCapacityList;
	/*private List<String> noOfCylinderIdList;*/
	private List<String> seatingCapacityList;
	private List<String> vehicleUsageIdList;
	/*private List<String> areaCoverageIdList;*/
	private List<String> vehicleIdList;
	private List<String> driverIdList;
	private List<String> driverDOBList;
	private List<String> noClaimBonusIdList;
	private List<String> claimYNIdList;
	private List<String> claimAmountList;
	private List<String> agencyRepairYNIdList;
	private List<String> deductibleIdList;
	private List<String> electricalAccList;
	private List<String> nonElectricalAccList;
	private List<String> vehicleUsageNameList;
	private List<String> typeBodyNameList;
	private List<String> ownerDriverYNList;
	
	private List<String> regNoList;
	private List<String> chassisNoList;
	private List<String> engineNoList;
	private List<String> vehicleColourIdList;
	/*private List<String> vehicleRegLocIdList;*/
	private List<String> prevPolicyNoList;
	private List<String> prevInsCompanyIdList;
	private List<String> prevExpiryDateList;
	
	private List<String> leasedYNIdList;
	private List<String> bankOfFinanceIdList;
	private List<String> prevClaimYn;
	private List<String> noOfClaims;
	/*private List<String> insNameArabicList;
	private List<String> insAddressArabicList;
	private List<String> plateCharacterIdList;
	private List<String> plateNo1;
	private List<String> plateNo2;
	private List<String> plateNo3;
	private List<String> plateNo4;*/
	
	private Map<String,String> policyCoverMap;
	private Map<String,Object> comparisionDetailsMap;
	
	private HashMap<String, Map<String,Map>> quote;
	private HashMap<String, Map<String,Map>> quoteId;
	
	private List<Map<String,Object>> helpInfoList;
	private List<Map<String,Object>> premiumInfoList;
	private List<Map<String,String>> premiumGroupIdList;
	private List<Map<String,Object>> vehicleDetailsList;
	private List<Map<String,Object>> transList;
	private Map<String,String> policyTypeRateMap;
	private List<Map<String,Object>> mulVehDtls;
	private List<Map<String,Object>> vehiclinfoList;
	
	public List<Map<String, Object>> getVehiclinfoList() {
		return vehiclinfoList;
	}
	public void setVehiclinfoList(List<Map<String, Object>> vehiclinfoList) {
		this.vehiclinfoList = vehiclinfoList;
	}
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	
	private String filePath;
	private String fileName;
	private List<String> elecSno;	
	private List<String> elecDescrip;
	private List<String> elecAmount;
	private List<String> nonelesno;
	private List<String> noneleDescrip;
	private List<String> nonelecAmont;
	private List<String> documentIdList;
	
	private String otp;
	private String otpId;
	private String otpExpiry;
	private String otpStatus;
	private List<Map<String, Object>> otpList;
	private String ologinId;
	public List<Map<String, Object>> getDocumentinfo() {
		return documentinfo;
	}
	public void setDocumentinfo(List<Map<String, Object>> documentinfo) {
		this.documentinfo = documentinfo;
	}
	private String opwd;
	private String MailOtp;
	private String type;
	private List<Map<String,Object>> documentinfo;
	//private int myOtp;
//	private String title;
//	private String customerName;
//	private String custLastName;
//	private String mobileNo;
//	private String customerType;
	
	public List<String> getNonelesno() {
		return nonelesno;
	}
	public void setNonelesno(List<String> nonelesno) {
		this.nonelesno = nonelesno;
	}
	public List<String> getNoneleDescrip() {
		return noneleDescrip;
	}
	public void setNoneleDescrip(List<String> noneleDescrip) {
		this.noneleDescrip = noneleDescrip;
	}
	public List<String> getNonelecAmont() {
		return nonelecAmont;
	}
	public void setNonelecAmont(List<String> nonelecAmont) {
		this.nonelecAmont = nonelecAmont;
	}
	public List<String> getElecSno() {
		return elecSno;
	}
	public void setElecSno(List<String> elecSno) {
		this.elecSno = elecSno;
	}
	public List<String> getElecDescrip() {
		return elecDescrip;
	}
	public void setElecDescrip(List<String> elecDescrip) {
		this.elecDescrip = elecDescrip;
	}
	public List<String> getElecAmount() {
		return elecAmount;
	}
	public void setElecAmount(List<String> elecAmount) {
		this.elecAmount = elecAmount;
	}
	public String getPolicyCoverId() {
		return policyCoverId;
	}
	public void setPolicyCoverId(String policyCoverId) {
		this.policyCoverId = policyCoverId;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
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
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	/*public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}*/
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public String getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public String getFleetNo() {
		return fleetNo;
	}
	public void setFleetNo(String fleetNo) {
		this.fleetNo = fleetNo;
	}
	public String getMakeName() {
		return makeName;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getTypeBodyName() {
		return typeBodyName;
	}
	public void setTypeBodyName(String typeBodyName) {
		this.typeBodyName = typeBodyName;
	}
	public String getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(String quoteDate) {
		this.quoteDate = quoteDate;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getVehicleUsageName() {
		return vehicleUsageName;
	}
	public void setVehicleUsageName(String vehicleUsageName) {
		this.vehicleUsageName = vehicleUsageName;
	}
	public String getAgencyRepairName() {
		return agencyRepairName;
	}
	public void setAgencyRepairName(String agencyRepairName) {
		this.agencyRepairName = agencyRepairName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String[] getPolicyCover() {
		return policyCover;
	}
	public void setPolicyCover(String[] policyCover) {
		this.policyCover = policyCover;
	}
	public List<Map<String,Object>> getPremiumInfo() {
		return premiumInfo;
	}
	public void setPremiumInfo(List<Map<String,Object>> premiumInfo) {
		this.premiumInfo = premiumInfo;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getLoadOrDiscPremium() {
		return loadOrDiscPremium;
	}
	public void setLoadOrDiscPremium(String loadOrDiscPremium) {
		this.loadOrDiscPremium = loadOrDiscPremium;
	}
	public String getPolicyFee() {
		return policyFee;
	}
	public void setPolicyFee(String policyFee) {
		this.policyFee = policyFee;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
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
	public String getQuoteStatus() {
		return quoteStatus;
	}
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}
	public String getInsNameArabic() {
		return insNameArabic;
	}
	public void setInsNameArabic(String insNameArabic) {
		this.insNameArabic = insNameArabic;
	}
	public String getInsAddressArabic() {
		return insAddressArabic;
	}
	public void setInsAddressArabic(String insAddressArabic) {
		this.insAddressArabic = insAddressArabic;
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
	public String getModeOfPay() {
		return modeOfPay;
	}
	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}
	public String getChqInvNo() {
		return chqInvNo;
	}
	public void setChqInvNo(String chqInvNo) {
		this.chqInvNo = chqInvNo;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
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
	public String getReferralMsg() {
		return referralMsg;
	}
	public void setReferralMsg(String referralMsg) {
		this.referralMsg = referralMsg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public List<Integer> getCoverId() {
		return coverId;
	}
	public void setCoverId(List<Integer> coverId) {
		this.coverId = coverId;
	}
	public List<Double> getsI() {
		return sI;
	}
	public void setsI(List<Double> sI) {
		this.sI = sI;
	}
	public List<Double> getBaseRate() {
		return baseRate;
	}
	public void setBaseRate(List<Double> baseRate) {
		this.baseRate = baseRate;
	}
	public String getOptCovers() {
		return optCovers;
	}
	public void setOptCovers(String optCovers) {
		this.optCovers = optCovers;
	}
	public String getHelpType() {
		return helpType;
	}
	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}
	public List<Map<String, Object>> getHelpInfoList() {
		return helpInfoList;
	}
	public void setHelpInfoList(List<Map<String, Object>> helpInfoList) {
		this.helpInfoList = helpInfoList;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public String getEndTypeId() {
		return endTypeId;
	}
	public void setEndTypeId(String endTypeId) {
		this.endTypeId = endTypeId;
	}
	public String getBrokerCompany() {
		return brokerCompany;
	}
	public void setBrokerCompany(String brokerCompany) {
		this.brokerCompany = brokerCompany;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public void setQuote(HashMap<String, Map<String,Map>> quote) {
		this.quote = quote;
	}
	public HashMap<String, Map<String,Map>> getQuote() {
		return quote;
	}
	public void setQuoteId(HashMap<String, Map<String,Map>> quoteId) {
		this.quoteId = quoteId;
	}
	public HashMap<String, Map<String,Map>> getQuoteId() {
		return quoteId;
	}
	public Map<String, String> getPolicyTypeRateMap() {
		return policyTypeRateMap;
	}
	public void setPolicyTypeRateMap(Map<String, String> policyTypeRateMap) {
		this.policyTypeRateMap = policyTypeRateMap;
	}
	public List<Map<String, Object>> getPremiumInfoList() {
		return premiumInfoList;
	}
	public void setPremiumInfoList(List<Map<String, Object>> premiumInfoList) {
		this.premiumInfoList = premiumInfoList;
	}
	public List<Map<String, String>> getPremiumGroupIdList() {
		return premiumGroupIdList;
	}
	public void setPremiumGroupIdList(List<Map<String, String>> premiumGroupIdList) {
		this.premiumGroupIdList = premiumGroupIdList;
	}
	public String getTotalCoverPremium() {
		return totalCoverPremium;
	}
	public void setTotalCoverPremium(String totalCoverPremium) {
		this.totalCoverPremium = totalCoverPremium;
	}
	public Map<String, String> getPolicyCoverMap() {
		return policyCoverMap;
	}
	public void setPolicyCoverMap(Map<String, String> policyCoverMap) {
		this.policyCoverMap = policyCoverMap;
	}
	public List<String> getMakeIdList() {
		return makeIdList;
	}
	public void setMakeIdList(List<String> makeIdList) {
		this.makeIdList = makeIdList;
	}
	public List<String> getModelIdList() {
		return modelIdList;
	}
	public void setModelIdList(List<String> modelIdList) {
		this.modelIdList = modelIdList;
	}
	public List<String> getTypeBodyIdList() {
		return typeBodyIdList;
	}
	public void setTypeBodyIdList(List<String> typeBodyIdList) {
		this.typeBodyIdList = typeBodyIdList;
	}
	public List<String> getMfgYrIdList() {
		return mfgYrIdList;
	}
	public void setMfgYrIdList(List<String> mfgYrIdList) {
		this.mfgYrIdList = mfgYrIdList;
	}
	public List<String> getSumInsuredList() {
		return sumInsuredList;
	}
	public void setSumInsuredList(List<String> sumInsuredList) {
		this.sumInsuredList = sumInsuredList;
	}
	public List<String> getSeatingCapacityList() {
		return seatingCapacityList;
	}
	public void setSeatingCapacityList(List<String> seatingCapacityList) {
		this.seatingCapacityList = seatingCapacityList;
	}
	public List<String> getVehicleIdList() {
		return vehicleIdList;
	}
	public void setVehicleIdList(List<String> vehicleIdList) {
		this.vehicleIdList = vehicleIdList;
	}
	public List<String> getVehicleUsageIdList() {
		return vehicleUsageIdList;
	}
	public void setVehicleUsageIdList(List<String> vehicleUsageIdList) {
		this.vehicleUsageIdList = vehicleUsageIdList;
	}
	public List<String> getDriverDOBList() {
		return driverDOBList;
	}
	public void setDriverDOBList(List<String> driverDOBList) {
		this.driverDOBList = driverDOBList;
	}
	public List<String> getNoClaimBonusIdList() {
		return noClaimBonusIdList;
	}
	public void setNoClaimBonusIdList(List<String> noClaimBonusIdList) {
		this.noClaimBonusIdList = noClaimBonusIdList;
	}
	public List<String> getClaimYNIdList() {
		return claimYNIdList;
	}
	public void setClaimYNIdList(List<String> claimYNIdList) {
		this.claimYNIdList = claimYNIdList;
	}
	public List<String> getClaimAmountList() {
		return claimAmountList;
	}
	public void setClaimAmountList(List<String> claimAmountList) {
		this.claimAmountList = claimAmountList;
	}
	public String getDeleteVehicleId() {
		return deleteVehicleId;
	}
	public void setDeleteVehicleId(String deleteVehicleId) {
		this.deleteVehicleId = deleteVehicleId;
	}
	public List<String> getAgencyRepairYNIdList() {
		return agencyRepairYNIdList;
	}
	public void setAgencyRepairYNIdList(List<String> agencyRepairYNIdList) {
		this.agencyRepairYNIdList = agencyRepairYNIdList;
	}
	public List<String> getRegNoList() {
		return regNoList;
	}
	public void setRegNoList(List<String> regNoList) {
		this.regNoList = regNoList;
	}
	public List<String> getChassisNoList() {
		return chassisNoList;
	}
	public void setChassisNoList(List<String> chassisNoList) {
		this.chassisNoList = chassisNoList;
	}
	public List<String> getEngineNoList() {
		return engineNoList;
	}
	public void setEngineNoList(List<String> engineNoList) {
		this.engineNoList = engineNoList;
	}
	public List<String> getVehicleColourIdList() {
		return vehicleColourIdList;
	}
	public void setVehicleColourIdList(List<String> vehicleColourIdList) {
		this.vehicleColourIdList = vehicleColourIdList;
	}
	public List<String> getLeasedYNIdList() {
		return leasedYNIdList;
	}
	public void setLeasedYNIdList(List<String> leasedYNIdList) {
		this.leasedYNIdList = leasedYNIdList;
	}
	public List<String> getBankOfFinanceIdList() {
		return bankOfFinanceIdList;
	}
	public void setBankOfFinanceIdList(List<String> bankOfFinanceIdList) {
		this.bankOfFinanceIdList = bankOfFinanceIdList;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public List<Map<String,Object>> getPolicyInformationList() {
		return policyInformationList;
	}
	public void setPolicyInformationList(List<Map<String,Object>> policyInformationList) {
		this.policyInformationList = policyInformationList;
	}
	public List<String> getDeductibleIdList() {
		return deductibleIdList;
	}
	public void setDeductibleIdList(List<String> deductibleIdList) {
		this.deductibleIdList = deductibleIdList;
	}
	public String getMerchant_reference() {
		return merchant_reference;
	}
	public void setMerchant_reference(String merchantReference) {
		merchant_reference = merchantReference;
	}
	public List<String> getDriverIdList() {
		return driverIdList;
	}
	public void setDriverIdList(List<String> driverIdList) {
		this.driverIdList = driverIdList;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPremiumType() {
		return premiumType;
	}
	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	public List<File> getUpload() {
		return upload;
	}
	public void setUpload(List<File> upload) {
		this.upload = upload;
	}
	public List<String> getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public List<String> getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDeclarationYN() {
		return declarationYN;
	}
	public void setDeclarationYN(String declarationYN) {
		this.declarationYN = declarationYN;
	}
	public List<String> getCubicCapacityList() {
		return cubicCapacityList;
	}
	public void setCubicCapacityList(List<String> cubicCapacityList) {
		this.cubicCapacityList = cubicCapacityList;
	}
	public List<String> getElectricalAccList() {
		return electricalAccList;
	}
	public void setElectricalAccList(List<String> electricalAccList) {
		this.electricalAccList = electricalAccList;
	}
	public List<String> getNonElectricalAccList() {
		return nonElectricalAccList;
	}
	public void setNonElectricalAccList(List<String> nonElectricalAccList) {
		this.nonElectricalAccList = nonElectricalAccList;
	}
	public Map<String, Object> getComparisionDetailsMap() {
		return comparisionDetailsMap;
	}
	public void setComparisionDetailsMap(Map<String, Object> comparisionDetailsMap) {
		this.comparisionDetailsMap = comparisionDetailsMap;
	}
	public String getPolicyEndDatePeriod() {
		return policyEndDatePeriod;
	}
	public void setPolicyEndDatePeriod(String policyEndDatePeriod) {
		this.policyEndDatePeriod = policyEndDatePeriod;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getVehicleUsage() {
		return vehicleUsage;
	}
	public void setVehicleUsage(String vehicleUsage) {
		this.vehicleUsage = vehicleUsage;
	}
	public String getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(String seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getTypeBody() {
		return typeBody;
	}
	public void setTypeBody(String typeBody) {
		this.typeBody = typeBody;
	}
	public List<String> getVehicleUsageNameList() {
		return vehicleUsageNameList;
	}
	public void setVehicleUsageNameList(List<String> vehicleUsageNameList) {
		this.vehicleUsageNameList = vehicleUsageNameList;
	}
	public List<String> getTypeBodyNameList() {
		return typeBodyNameList;
	}
	public void setTypeBodyNameList(List<String> typeBodyNameList) {
		this.typeBodyNameList = typeBodyNameList;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public List<String> getPrevPolicyNoList() {
		return prevPolicyNoList;
	}
	public void setPrevPolicyNoList(List<String> prevPolicyNoList) {
		this.prevPolicyNoList = prevPolicyNoList;
	}
	public List<String> getPrevExpiryDateList() {
		return prevExpiryDateList;
	}
	public void setPrevExpiryDateList(List<String> prevExpiryDateList) {
		this.prevExpiryDateList = prevExpiryDateList;
	}
	public List<String> getPrevInsCompanyIdList() {
		return prevInsCompanyIdList;
	}
	public void setPrevInsCompanyIdList(List<String> prevInsCompanyIdList) {
		this.prevInsCompanyIdList = prevInsCompanyIdList;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public void setCaptchText(String captchText) {
		this.captchText = captchText;
	}
	public String getCaptchText() {
		return captchText;
	}
	public List<String> getOwnerDriverYNList() {
		return ownerDriverYNList;
	}
	public void setOwnerDriverYNList(List<String> ownerDriverYNList) {
		this.ownerDriverYNList = ownerDriverYNList;
	}
	public List<String> getDocumentIdList() {
		return documentIdList;
	}
	public void setDocumentIdList(List<String> documentIdList) {
		this.documentIdList = documentIdList;
	}
	public String getTpLiablityYN() {
		return tpLiablityYN;
	}
	public void setTpLiablityYN(String tpLiablityYN) {
		this.tpLiablityYN = tpLiablityYN;
	}
	public String getTpLiablityAmount() {
		return tpLiablityAmount;
	}
	public void setTpLiablityAmount(String tpLiablityAmount) {
		this.tpLiablityAmount = tpLiablityAmount;
	}
	public List<Map<String, Object>> getVehicleDetailsList() {
		return vehicleDetailsList;
	}
	public void setVehicleDetailsList(List<Map<String, Object>> vehicleDetailsList) {
		this.vehicleDetailsList = vehicleDetailsList;
	}
	public String getRateVehicleId() {
		return rateVehicleId;
	}
	public void setRateVehicleId(String rateVehicleId) {
		this.rateVehicleId = rateVehicleId;
	}
	public void setTransList(List<Map<String,Object>> transList) {
		this.transList = transList;
	}
	public List<Map<String,Object>> getTransList() {
		return transList;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceManuf() {
		return deviceManuf;
	}
	public void setDeviceManuf(String deviceManuf) {
		this.deviceManuf = deviceManuf;
	}
	public String getDeviceBrand() {
		return deviceBrand;
	}
	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}
	public String getDeviceProduct() {
		return deviceProduct;
	}
	public void setDeviceProduct(String deviceProduct) {
		this.deviceProduct = deviceProduct;
	}
	/**
	 * @param detailId the detailId to set
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	/**
	 * @return the detailId
	 */
	public String getDetailId() {
		return detailId;
	}
	public String getEncodedImageStr() {
		return encodedImageStr;
	}
	public void setEncodedImageStr(String encodedImageStr) {
		this.encodedImageStr = encodedImageStr;
	}
	public String getWebRootPath() {
		return webRootPath;
	}
	public void setWebRootPath(String webRootPath) {
		this.webRootPath = webRootPath;
	}
	public String getAssistantType() {
		return assistantType;
	}
	public void setAssistantType(String assistantType) {
		this.assistantType = assistantType;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @param typeofAssistantList the typeofAssistantList to set
	 */
	public void setTypeofAssistantList(List<Map<String,Object>> typeofAssistantList) {
		TypeofAssistantList = typeofAssistantList;
	}
	/**
	 * @return the typeofAssistantList
	 */
	public List<Map<String,Object>> getTypeofAssistantList() {
		return TypeofAssistantList;
	}
	/**
	 * @param reqFrom the reqFrom to set
	 */
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	/**
	 * @return the reqFrom
	 */
	public String getReqFrom() {
		return reqFrom;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}
	/**
	 * @param feedBack the feedBack to set
	 */
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	/**
	 * @return the feedBack
	 */
	public String getFeedBack() {
		return feedBack;
	}
	/**
	 * @param refNoList the refNoList to set
	 */
	public void setRefNoList(List<Map<String,Object>> refNoList) {
		this.refNoList = refNoList;
	}
	/**
	 * @return the refNoList
	 */
	public List<Map<String,Object>> getRefNoList() {
		return refNoList;
	}
	public List<Map<String, Object>> getMulVehDtls() {
		return mulVehDtls;
	}
	public void setMulVehDtls(List<Map<String, Object>> mulVehDtls) {
		this.mulVehDtls = mulVehDtls;
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
		return MailOtp;
	}
	public void setMailOtp(String mailOtp) {
		MailOtp = mailOtp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/*public void setMyOtp(int myOtp) {
		this.myOtp = myOtp;
	}
	public int getMyOtp() {
		return myOtp;
	}*/
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getIsVehicleEdit() {
		return isVehicleEdit;
	}
	public void setIsVehicleEdit(String isVehicleEdit) {
		this.isVehicleEdit = isVehicleEdit;
	}
	public String getLoginBranch() {
		return loginBranch;
	}
	public void setLoginBranch(String loginBranch) {
		this.loginBranch = loginBranch;
	}
	public List<Map<String, Object>> getPolicyTypeList() {
		return policyTypeList;
	}
	public void setPolicyTypeList(List<Map<String, Object>> policyTypeList) {
		this.policyTypeList = policyTypeList;
	}
	
	private String custNameLabel;
	private String custEmailLabel;
	private String custLastNameLabel;

	public String getCustNameLabel() {
		return custNameLabel;
	}
	public void setCustNameLabel(String custNameLabel) {
		this.custNameLabel = custNameLabel;
	}
	public String getCustEmailLabel() {
		return custEmailLabel;
	}
	public void setCustEmailLabel(String custEmailLabel) {
		this.custEmailLabel = custEmailLabel;
	}
	public String getCustLastNameLabel() {
		return custLastNameLabel;
	}
	public void setCustLastNameLabel(String custLastNameLabel) {
		this.custLastNameLabel = custLastNameLabel;
	}
	/*public List<Map<String,Object>> getCustomerIndividualList() {
		return customerIndividualList;
	}
	public void setCustomerIndividualList(List<Map<String,Object>> customerIndividualList) {
		this.customerIndividualList = customerIndividualList;
	}
	public List<Map<String,Object>> getCustomerCorporateList() {
		return customerCorporateList;
	}
	public void setCustomerCorporateList(List<Map<String,Object>> customerCorporateList) {
		this.customerCorporateList = customerCorporateList;
	}*/
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	private String vehicleType;
	private String isClaimDtl;
	private List<Map<String,Object>> vehDtls;
	
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getIsClaimDtl() {
		return isClaimDtl;
	}
	public void setIsClaimDtl(String isClaimDtl) {
		this.isClaimDtl = isClaimDtl;
	}
	public List<Map<String, Object>> getVehDtls() {
		return vehDtls;
	}
	public void setVehDtls(List<Map<String, Object>> vehDtls) {
		this.vehDtls = vehDtls;
	}
	
	private List<Object> paymentModes;

	public List<Object> getPaymentModes() {
		return paymentModes;
	}
	public void setPaymentModes(List<Object> paymentModes) {
		this.paymentModes = paymentModes;
	}
	public String getMtnMobileNo() {
		return mtnMobileNo;
	}
	public void setMtnMobileNo(String mtnMobileNo) {
		this.mtnMobileNo = mtnMobileNo;
	}
	public String getPolicyTypeDesc() {
		return policyTypeDesc;
	}
	public void setPolicyTypeDesc(String policyTypeDesc) {
		this.policyTypeDesc = policyTypeDesc;
	}
	
	private List<Double> basePrem;

	public List<Double> getBasePrem() {
		return basePrem;
	}
	public void setBasePrem(List<Double> basePrem) {
		this.basePrem = basePrem;
	}
	private List<Double> baseRateVeh;

	public List<Double> getBaseRateVeh() {
		return baseRateVeh;
	}
	public void setBaseRateVeh(List<Double> baseRateVeh) {
		this.baseRateVeh = baseRateVeh;
	}
	
	

	
	private List<Map<String,Object>> conditionList;
	private List<Map<String,Object>> conditionEditList;
	private List<String> condition;
	private String conditionDesc;
	private List<String> conditionText;
	private String conditionSelected;
	
	private List<Map<String,Object>> deductibleAddList;
	private List<Map<String,Object>> deductibleEditList;
	private List<String> deductible;
	private String deductibleDesc;
	private List<String> deductibleText;
	private String deductibleSelected;
	
	public List<Map<String, Object>> getConditionList() {
		return conditionList;
	}
	public void setConditionList(List<Map<String, Object>> conditionList) {
		this.conditionList = conditionList;
	}
	public List<Map<String, Object>> getConditionEditList() {
		return conditionEditList;
	}
	public void setConditionEditList(List<Map<String, Object>> conditionEditList) {
		this.conditionEditList = conditionEditList;
	}
	public List<String> getCondition() {
		return condition;
	}
	public void setCondition(List<String> condition) {
		this.condition = condition;
	}
	public String getConditionDesc() {
		return conditionDesc;
	}
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}
	public List<String> getConditionText() {
		return conditionText;
	}
	public void setConditionText(List<String> conditionText) {
		this.conditionText = conditionText;
	}
	public String getConditionSelected() {
		return conditionSelected;
	}
	public void setConditionSelected(String conditionSelected) {
		this.conditionSelected = conditionSelected;
	}
	public List<Map<String, Object>> getDeductibleAddList() {
		return deductibleAddList;
	}
	public void setDeductibleAddList(List<Map<String, Object>> deductibleAddList) {
		this.deductibleAddList = deductibleAddList;
	}
	public List<Map<String, Object>> getDeductibleEditList() {
		return deductibleEditList;
	}
	public void setDeductibleEditList(List<Map<String, Object>> deductibleEditList) {
		this.deductibleEditList = deductibleEditList;
	}
	public List<String> getDeductible() {
		return deductible;
	}
	public void setDeductible(List<String> deductible) {
		this.deductible = deductible;
	}
	public String getDeductibleDesc() {
		return deductibleDesc;
	}
	public void setDeductibleDesc(String deductibleDesc) {
		this.deductibleDesc = deductibleDesc;
	}
	public List<String> getDeductibleText() {
		return deductibleText;
	}
	public void setDeductibleText(List<String> deductibleText) {
		this.deductibleText = deductibleText;
	}
	public String getDeductibleSelected() {
		return deductibleSelected;
	}
	public void setDeductibleSelected(String deductibleSelected) {
		this.deductibleSelected = deductibleSelected;
	}
	
	private String airtelMoneyNumber;
	
	public String getAirtelMoneyNumber() {
		return airtelMoneyNumber;
	}
	public void setAirtelMoneyNumber(String airtelMoneyNumber) {
		this.airtelMoneyNumber = airtelMoneyNumber;
	}
	
	private List<Map<String,Object>> premiumInfoNew;

	public List<Map<String, Object>> getPremiumInfoNew() {
		return premiumInfoNew;
	}
	public void setPremiumInfoNew(List<Map<String, Object>> premiumInfoNew) {
		this.premiumInfoNew = premiumInfoNew;
	}
	
	private String e;
	private String quotationStatus;

	public String getE() {
		return e;
	}
	public void setE(String e) {
		this.e = e;
	}
	public String getQuotationStatus() {
		return quotationStatus;
	}
	public void setQuotationStatus(String quotationStatus) {
		this.quotationStatus = quotationStatus;
	}
	public String getReferal() {
		return referal;
	}
	public void setReferal(String referal) {
		this.referal = referal;
	}
	public String getReferQuoteYN() {
		return referQuoteYN;
	}
	public void setReferQuoteYN(String referQuoteYN) {
		this.referQuoteYN = referQuoteYN;
	}
	public String getBrokeruser() {
		return brokeruser;
	}
	public void setBrokeruser(String brokeruser) {
		this.brokeruser = brokeruser;
	}
	public List<String> getPrevClaimYn() {
		return prevClaimYn;
	}
	public void setPrevClaimYn(List<String> prevClaimYn) {
		this.prevClaimYn = prevClaimYn;
	}
	public List<String> getNoOfClaims() {
		return noOfClaims;
	}
	public void setNoOfClaims(List<String> noOfClaims) {
		this.noOfClaims = noOfClaims;
	}
	
}
