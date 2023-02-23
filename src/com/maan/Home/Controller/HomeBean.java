package com.maan.Home.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.maan.customer.CustBean;

public class HomeBean extends CustBean {
	private String loginId;
	private String from;
	private String rowNum;
	private String colSize;
	private String display;
	private String productId;
	private String branchCode;
	private String applicationNo;
	private String actionType;
	private String userType;
	private String user;
	private String schemeId;
	private String contentTypeId;
	private String insuredLocation;
	private String totalPremium;
	private String effectiveDate;
	private String expiryDate;
	private String coverId;
	private String selectTab;
	private String buildCon="N";
	private String liveAccom="N";
	private String reclaimLand="N";
	private String consDays="N";
	private String decRej="N";
	private String pastThr="N";
	private String noClaim;
	private String typeClaim;
	private String claimAmt;
	private String typeProperty;
	private String showReferralYN;
	private String referralYN="N";
	private String referralComments;
	private String modeOfPay;
	private String generatePolicyYN;
	private String policyNo;
	private String receiptNo;
	private String debitNo;
	private String quoteEmailYN;
	private String policyEmailYN;
	private String policyFee;
	private String adminRemarks;
	private String finalPremium;
	private String loadOrDiscPremium;
	private String adminRefStatus;
	private String sign;
	private String amendId="0";
	private String referralMsg;
	private String validCoverage;
	private String helpInfo;
	private String commission;
	private String menuType;
	private String reqFrom;
	private String subCoverId;
	private String inceptionDt;
	private String expiryDt;
	private String coverSumInsured;
	private String addRowYN;
	private String generalInfo;
	private String destCountry;
	private String originCountry;
	private String option;
	private String issuer;
	private String tabInfo;
	private String tabStatus;
	private String paCoverYN;
	private String tractorYN;
	private String generalPremiumStatus;
	private String premiumInfoType;
	private String reqFromq;
	private String premiumEditStatus;
	private String contentName;
	private String actualPremium;
	private String actualOptionalPremium;
	private String discountPercent;
	private String discountPremium;
	private String minPremiumYN;
	private String coverTS;
	
	private String policyGenerateYn;
	
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
	private String currencyType;
	private String merchant_reference;
	
	private String mtnMobileNo;
	
	private String schemeName;
	

	private List<String> excessIdAr;
	private List<String> excessPercentAr;
	private List<String> excessAmountAr;
	private List<String> excessDescAr;
	private List<Boolean> excessDeleteAr;
	
	private List<String> warrantiesIdAr;
	private List<String> warrantiesDescAr;
	private List<Boolean> warrantiesDeleteAr;
	
	private List<String> exclusionsIdAr;
	private List<String> exclusionsDescAr;
	private List<Boolean> exclusionsDeleteAr;
	
	private List<Object> helpInfoList;
	private List<Map<String,Object>> Home=new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> Subhome=new ArrayList<Map<String,Object>>();
	/*private List<Object> errorList;*/
	private List<Map<String,Object>> subPremium;
	
	private List<String> colDyn0;
	private List<String> colDyn1;
	private List<String> colDyn2;
	private List<String> colDyn3;
	private List<String> colDyn4;
	private List<String> colDyn5;
	private List<String> colDyn6;
	private List<String> colDyn7;
	private List<String> colDyn8;
	private List<String> colDyn9;
	private List<String> deleteRow=new ArrayList<String>();
	private List<String> deleteRowList;
	private List<String> sumInsuredList;
	private List<String> subSumInsuredList;
	
	private List<Map<String,Object>> premiumList;
	private List<Map<String,Object>> personalInfo;
	private List<Map<String,Object>> subList;
	private List<Map<String,Object>> coverageList;
	private List<Map<String,Object>> premiumTabList;
	private List<Map<String,Object>> subPremiumTab;
	private List<Map<String,Object>> contentTypeList;
	private List<Map<String,Object>> excessList;
	private List<Map<String,Object>> extraBenefitsList;
	
	
	private Map<Integer, String> COVERAGES_BASE_RATE;
	private Map<Integer, String> COVER_ID;
	private Map<Integer, String> COVERAGES_SUB_BASE_RATE;
	private Map<Integer, String> SUM_INSURED;
	private Map<Integer, String> COVERAGES_COVERED_EMPLOYEES;
	private Map<String, Object> detailsToCoverMap;
	
	/*
	private Map<Integer, String> SUB_SUM_INSURED_LIMIT;
	private Map<Integer, String> SUB_COVERAGES_NAME;
	private Map<Integer, String> COVERAGES_Y_N_OPTION;
	
	private Map<Integer, String> COVERAGES_NAME;
	private Map<Integer, String> SUB_COVERAGES;
	private Map<Integer, String> SUM_INSURED_LIMIT;
	private Map<Integer, String> MIN_SUM_INSURED;
	private Map<Integer, String> COVERAGES_SUB_ID;
	private Map<Integer, String> COVER_ID;
	private Map<Integer, String> COVERAGES_DISPLAY_NAME;
	private Map<Integer, String> CALC_TYPE;
	private Map<Integer, String> COVERAGES_LIMIT;
	private Map<Integer, String> CALC_STATUS;
	*/
	
	/********* Farmer Insurance | PA Cover - Start **********/
	private String paPolicyType;
	private String paCostOfTravel;
	private String paSupportingItem;
	private String paExitingDisability;
	private String paDisabilityDesc;
	private String paIsRollPolicyYN;
	private String paPrevPolicyNo;
	private String paPrevExpiryDate;
	private String paPrevInsCompany;
	private String paPrevClaimAmount;
	private String paMedicalExtn;
	private String paOptionType;
	
	private String paName;
	private String paDob;
	private String paIndurer;
	private String paRelationship;
	private String paOccupation;
	private String paTableOfBenifits;
	private String paSumInsured;
	private String paAnnualIncome;
	private String paFatherFirstName;
	private String paFatherMiddleName;
	private String paFatherLastName;
	private String paInsuredId;
	
	private List<String> paInsuredIdList;
	private List<String> paInsuredRateList;
	private List<String> paMedExtnRateList;
	private List<String> paTravelCostRateList;
	private List<String> paSupportRateList;
	/********* Farmer Insurance | PA Cover - End **********/
	/********* Farmer Insurance | Motor - Start **********/
	
	private String make;
	private String typeBody;
	private String model;
	private String mfgYr;
	private String sumInsured;
	private String cubicCapacity;
	private String seatingCapacity;
	private String noOfCylinder;
	private String vehicleUsage;
	private String areaCoverage;
	private String agencyRepairYN;
	private String driverDOB;
	private String driverNationality;
	private String uaeLicenceNo;
	private String uaeLicExpDT;
	private String claimYN;
	private String claimAmount;
	private String noClaimBonus;
	
	private String manufactureDate;
	private String motorLampsYN;
	private String trailerIdv1;
	private String trailer1MfrDate;
	private String motorPolicyType;
	private String trailerIdv2;
	private String trailer2MfrDate;
	private String voluntaryDeductible;
	private String electricalAcc;
	private String nonElectricalAcc;
	private String bifuelKit;
	private String noOfTrailer;
	private String restrictedTPPDYN;
	
	private String regNo;
	private String chassisNo;
	private String engineNo;
	private String vehicleColour;
	private String vehicleRegLoc;
	private String plateCharacter;
	private String leasedYN;
	private String bankOfFinance;
	private String regYr;
	private String insNameArabic;
	private String insAddressArabic;
	private String regNo1;
	private String regNo2;
	private String regNo3;
	private String regNo4;
	private String ltFinanceYN;
	private String trailer1RegNo;
	private String trailer1RegNo1;
	private String trailer1RegNo2;
	private String trailer1RegNo3;
	private String trailer1RegNo4;
	private String trailer2RegNo;
	private String trailer2RegNo1;
	private String trailer2RegNo2;
	private String trailer2RegNo3;
	private String trailer2RegNo4;
	
	private String ownerDriver;
	private String unNamedPassengersNos;
	private String unNamedPassengersSi;
	private String paidDriversNos;
	private String paidDriversSi;
	
	private String legalLibCover;
	private String driverCondCleaner;
	private String nonFarePassenger;
	private String employeesInOperation;
	
	private String fleetNo;
	private String quoteStatus;
	
	/*private String policyStartDate;
	private String policyEndDate;*/
	
	private String noClaimBonusVal;
	private String loadingOd;
	private String loadingTp;
	private String processType;
	
	private List<String> coverageIdList;
	private List<String> coverageNameList;
	private List<String> coverageValueList;
	private List<String> tractorSubCoverList;
	private List<String> tractorRateList;
	/********* Farmer Insurance | Motor - End **********/
	
	private String endTypeId;
	
	private List<Map<String,String>> contentCover;
	
	private List<Map<String, Object>> locationDtlsList;
	private boolean locationDtlsStatus;
	
	private List<List<String>> schemeLocation;
	private List<Boolean> schemeSelect;
	private String customerFullName;
	
	private String excessSign;
	private String excessPremium;
	private String policyFeePercent;
	private String premiumCalcValidation;
	
	private String editFrom;
	
	public Map<Integer, String> getCOVERAGES_COVERED_EMPLOYEES() {
		return COVERAGES_COVERED_EMPLOYEES;
	}
	public void setCOVERAGES_COVERED_EMPLOYEES(
			Map<Integer, String> cOVERAGESCOVEREDEMPLOYEES) {
		COVERAGES_COVERED_EMPLOYEES = cOVERAGESCOVEREDEMPLOYEES;
	}
	public Map<Integer, String> getCOVERAGES_SUB_BASE_RATE() {
		return COVERAGES_SUB_BASE_RATE;
	}
	public void setCOVERAGES_SUB_BASE_RATE(Map<Integer, String> cOVERAGESSUBBASERATE) {
		COVERAGES_SUB_BASE_RATE = cOVERAGESSUBBASERATE;
	}
	public List<Map<String,Object>> getSubhome() {
		return Subhome;
	}
	public void setSubhome(List<Map<String,Object>> subhome) {
		Subhome = subhome;
	}
	public String getHelpInfo() {
		return helpInfo;
	}
	public void setHelpInfo(String helpInfo) {
		this.helpInfo = helpInfo;
	}
	public List<Map<String, Object>> getPremiumList() {
		return premiumList;
	}
	public void setPremiumList(List<Map<String, Object>> premiumList) {
		this.premiumList = premiumList;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public List<Map<String, Object>> getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(List<Map<String, Object>> personalInfo) {
		this.personalInfo = personalInfo;
	}
	public List<Map<String, Object>> getSubList() {
		return subList;
	}
	public void setSubList(List<Map<String, Object>> subList) {
		this.subList = subList;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getColSize() {
		return colSize;
	}
	public void setColSize(String colSize) {
		this.colSize = colSize;
	}
	public List<Map<String, Object>> getCoverageList() {
		return coverageList;
	}
	public void setCoverageList(List<Map<String, Object>> coverageList) {
		this.coverageList = coverageList;
	}
	public List<String> getColDyn0() {
		return colDyn0;
	}
	public void setColDyn0(List<String> colDyn0) {
		this.colDyn0 = colDyn0;
	}
	public List<String> getColDyn1() {
		return colDyn1;
	}
	public void setColDyn1(List<String> colDyn1) {
		this.colDyn1 = colDyn1;
	}
	public List<String> getColDyn2() {
		return colDyn2;
	}
	public void setColDyn2(List<String> colDyn2) {
		this.colDyn2 = colDyn2;
	}
	public List<String> getColDyn3() {
		return colDyn3;
	}
	public void setColDyn3(List<String> colDyn3) {
		this.colDyn3 = colDyn3;
	}
	public List<String> getColDyn4() {
		return colDyn4;
	}
	public void setColDyn4(List<String> colDyn4) {
		this.colDyn4 = colDyn4;
	}
	public List<String> getColDyn5() {
		return colDyn5;
	}
	public void setColDyn5(List<String> colDyn5) {
		this.colDyn5 = colDyn5;
	}
	public List<String> getColDyn6() {
		return colDyn6;
	}
	public void setColDyn6(List<String> colDyn6) {
		this.colDyn6 = colDyn6;
	}
	public List<String> getColDyn7() {
		return colDyn7;
	}
	public void setColDyn7(List<String> colDyn7) {
		this.colDyn7 = colDyn7;
	}
	public List<String> getColDyn8() {
		return colDyn8;
	}
	public void setColDyn8(List<String> colDyn8) {
		this.colDyn8 = colDyn8;
	}
	public List<String> getColDyn9() {
		return colDyn9;
	}
	public void setColDyn9(List<String> colDyn9) {
		this.colDyn9 = colDyn9;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
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
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = StringUtils.defaultIfEmpty(contentTypeId, "0");
	}
	public String getInsuredLocation() {
		return insuredLocation;
	}
	public void setInsuredLocation(String insuredLocation) {
		this.insuredLocation = insuredLocation;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Map<Integer, String> getSUM_INSURED() {
		return SUM_INSURED;
	}
	public void setSUM_INSURED(Map<Integer, String> sUMINSURED) {
		SUM_INSURED = sUMINSURED;
	}
	public List<Map<String,Object>> getHome() {
		return Home;
	}
	public void setHome(List<Map<String,Object>> home) {
		Home = home;
	}
	public String getCoverId() {
		return coverId;
	}
	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}
	public String getSelectTab() {
		return selectTab;
	}
	public void setSelectTab(String selectTab) {
		this.selectTab = selectTab;
	}
	public String getBuildCon() {
		return buildCon;
	}
	public void setBuildCon(String buildCon) {
		this.buildCon = buildCon;
	}
	public String getLiveAccom() {
		return liveAccom;
	}
	public void setLiveAccom(String liveAccom) {
		this.liveAccom = liveAccom;
	}
	public String getReclaimLand() {
		return reclaimLand;
	}
	public void setReclaimLand(String reclaimLand) {
		this.reclaimLand = reclaimLand;
	}
	public String getConsDays() {
		return consDays;
	}
	public void setConsDays(String consDays) {
		this.consDays = consDays;
	}
	public String getDecRej() {
		return decRej;
	}
	public void setDecRej(String decRej) {
		this.decRej = decRej;
	}
	public String getPastThr() {
		return pastThr;
	}
	public void setPastThr(String pastThr) {
		this.pastThr = pastThr;
	}
	public String getNoClaim() {
		return noClaim;
	}
	public void setNoClaim(String noClaim) {
		this.noClaim = noClaim;
	}
	public String getTypeProperty() {
		return typeProperty;
	}
	public void setTypeProperty(String typeProperty) {
		this.typeProperty = typeProperty;
	}
	public String getTypeClaim() {
		return typeClaim;
	}
	public void setTypeClaim(String typeClaim) {
		this.typeClaim = typeClaim;
	}
	public String getClaimAmt() {
		return claimAmt;
	}
	public void setClaimAmt(String claimAmt) {
		this.claimAmt = claimAmt;
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
	public String getPolicyFee() {
		return policyFee;
	}
	public void setPolicyFee(String policyFee) {
		this.policyFee = policyFee;
	}
	public String getAdminRemarks() {
		return adminRemarks;
	}
	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}
	public String getFinalPremium() {
		return finalPremium;
	}
	public void setFinalPremium(String finalPremium) {
		this.finalPremium = finalPremium;
	}
	public String getLoadOrDiscPremium() {
		return loadOrDiscPremium;
	}
	public void setLoadOrDiscPremium(String loadOrDiscPremium) {
		this.loadOrDiscPremium = loadOrDiscPremium;
	}
	public String getAdminRefStatus() {
		return adminRefStatus;
	}
	public void setAdminRefStatus(String adminRefStatus) {
		this.adminRefStatus = adminRefStatus;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public String getReferralMsg() {
		return referralMsg;
	}
	public void setReferralMsg(String referralMsg) {
		this.referralMsg = referralMsg;
	}
	public String getValidCoverage() {
		return validCoverage;
	}
	public void setValidCoverage(String validCoverage) {
		this.validCoverage = validCoverage;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public List<Map<String,Object>> getSubPremium() {
		return subPremium;
	}
	public void setSubPremium(List<Map<String,Object>> subPremium) {
		this.subPremium = subPremium;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getSubCoverId() {
		return subCoverId;
	}
	public void setSubCoverId(String subCoverId) {
		this.subCoverId = subCoverId;
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
	/*public Map<Integer, String> getSUB_SUM_INSURED_LIMIT() {
		return SUB_SUM_INSURED_LIMIT;
	}
	public void setSUB_SUM_INSURED_LIMIT(Map<Integer, String> sUBSUMINSUREDLIMIT) {
		SUB_SUM_INSURED_LIMIT = sUBSUMINSUREDLIMIT;
	}*/
	public String getCoverSumInsured() {
		return coverSumInsured;
	}
	public void setCoverSumInsured(String coverSumInsured) {
		this.coverSumInsured = coverSumInsured;
	}
	public List<String> getDeleteRow() {
		return deleteRow;
	}
	public void setDeleteRow(List<String> deleteRow) {
		this.deleteRow = deleteRow;
	}
	public String getAddRowYN() {
		return addRowYN;
	}
	public void setAddRowYN(String addRowYN) {
		this.addRowYN = addRowYN;
	}
	public String getGeneralInfo() {
		return generalInfo;
	}
	public void setGeneralInfo(String generalInfo) {
		this.generalInfo = generalInfo;
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
	public String getOption() {
		return option;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public List<Object> getHelpInfoList() {
		return helpInfoList;
	}
	public void setHelpInfoList(List<Object> helpInfoList) {
		this.helpInfoList = helpInfoList;
	}
	public List<String> getSumInsuredList() {
		return sumInsuredList;
	}
	public void setSumInsuredList(List<String> sumInsuredList) {
		this.sumInsuredList = sumInsuredList;
	}
	public List<String> getSubSumInsuredList() {
		return subSumInsuredList;
	}
	public void setSubSumInsuredList(List<String> subSumInsuredList) {
		this.subSumInsuredList = subSumInsuredList;
	}
	public String getTabInfo() {
		return tabInfo;
	}
	public void setTabInfo(String tabInfo) {
		this.tabInfo = tabInfo;
	}
	public List<Map<String, Object>> getPremiumTabList() {
		return premiumTabList;
	}
	public void setPremiumTabList(List<Map<String, Object>> premiumTabList) {
		this.premiumTabList = premiumTabList;
	}
	public List<Map<String, Object>> getSubPremiumTab() {
		return subPremiumTab;
	}
	public void setSubPremiumTab(List<Map<String, Object>> subPremiumTab) {
		this.subPremiumTab = subPremiumTab;
	}
	public List<Map<String, Object>> getContentTypeList() {
		return contentTypeList;
	}
	public void setContentTypeList(List<Map<String, Object>> contentTypeList) {
		this.contentTypeList = contentTypeList;
	}
	public String getTabStatus() {
		return tabStatus;
	}
	public void setTabStatus(String tabStatus) {
		this.tabStatus = tabStatus;
	}
	public List<Map<String, String>> getContentCover() {
		return contentCover;
	}
	public void setContentCover(List<Map<String, String>> contentCover) {
		this.contentCover = contentCover;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMfgYr() {
		return mfgYr;
	}
	public void setMfgYr(String mfgYr) {
		this.mfgYr = mfgYr;
	}
	public String getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}
	public String getCubicCapacity() {
		return cubicCapacity;
	}
	public void setCubicCapacity(String cubicCapacity) {
		this.cubicCapacity = cubicCapacity;
	}
	public String getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(String seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getNoOfCylinder() {
		return noOfCylinder;
	}
	public void setNoOfCylinder(String noOfCylinder) {
		this.noOfCylinder = noOfCylinder;
	}
	public String getVehicleUsage() {
		return vehicleUsage;
	}
	public void setVehicleUsage(String vehicleUsage) {
		this.vehicleUsage = vehicleUsage;
	}
	public String getAreaCoverage() {
		return areaCoverage;
	}
	public void setAreaCoverage(String areaCoverage) {
		this.areaCoverage = areaCoverage;
	}
	public String getAgencyRepairYN() {
		return agencyRepairYN;
	}
	public void setAgencyRepairYN(String agencyRepairYN) {
		this.agencyRepairYN = agencyRepairYN;
	}
	public String getDriverDOB() {
		return driverDOB;
	}
	public void setDriverDOB(String driverDOB) {
		this.driverDOB = driverDOB;
	}
	public String getDriverNationality() {
		return driverNationality;
	}
	public void setDriverNationality(String driverNationality) {
		this.driverNationality = driverNationality;
	}
	public String getUaeLicenceNo() {
		return uaeLicenceNo;
	}
	public void setUaeLicenceNo(String uaeLicenceNo) {
		this.uaeLicenceNo = uaeLicenceNo;
	}
	public String getUaeLicExpDT() {
		return uaeLicExpDT;
	}
	public void setUaeLicExpDT(String uaeLicExpDT) {
		this.uaeLicExpDT = uaeLicExpDT;
	}
	public String getClaimYN() {
		return claimYN;
	}
	public void setClaimYN(String claimYN) {
		this.claimYN = claimYN;
	}
	public String getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getNoClaimBonus() {
		return noClaimBonus;
	}
	public void setNoClaimBonus(String noClaimBonus) {
		this.noClaimBonus = noClaimBonus;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getChassisNo() {
		return chassisNo;
	}
	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getVehicleColour() {
		return vehicleColour;
	}
	public void setVehicleColour(String vehicleColour) {
		this.vehicleColour = vehicleColour;
	}
	public String getVehicleRegLoc() {
		return vehicleRegLoc;
	}
	public void setVehicleRegLoc(String vehicleRegLoc) {
		this.vehicleRegLoc = vehicleRegLoc;
	}
	public String getPlateCharacter() {
		return plateCharacter;
	}
	public void setPlateCharacter(String plateCharacter) {
		this.plateCharacter = plateCharacter;
	}
	public String getLeasedYN() {
		return leasedYN;
	}
	public void setLeasedYN(String leasedYN) {
		this.leasedYN = leasedYN;
	}
	public String getBankOfFinance() {
		return bankOfFinance;
	}
	public void setBankOfFinance(String bankOfFinance) {
		this.bankOfFinance = bankOfFinance;
	}
	public String getRegYr() {
		return regYr;
	}
	public void setRegYr(String regYr) {
		this.regYr = regYr;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getTypeBody() {
		return typeBody;
	}
	public void setTypeBody(String typeBody) {
		this.typeBody = typeBody;
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
	public String getFleetNo() {
		return fleetNo;
	}
	public void setFleetNo(String fleetNo) {
		this.fleetNo = fleetNo;
	}
	public String getEndTypeId() {
		return endTypeId;
	}
	public void setEndTypeId(String endTypeId) {
		this.endTypeId = endTypeId;
	}
	public String getManufactureDate() {
		return manufactureDate;
	}
	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	public String getTrailerIdv1() {
		return trailerIdv1;
	}
	public void setTrailerIdv1(String trailerIdv1) {
		this.trailerIdv1 = trailerIdv1;
	}
	public String getTrailer1MfrDate() {
		return trailer1MfrDate;
	}
	public void setTrailer1MfrDate(String trailer1MfrDate) {
		this.trailer1MfrDate = trailer1MfrDate;
	}
	public String getMotorPolicyType() {
		return motorPolicyType;
	}
	public void setMotorPolicyType(String motorPolicyType) {
		this.motorPolicyType = motorPolicyType;
	}
	public String getTrailerIdv2() {
		return trailerIdv2;
	}
	public void setTrailerIdv2(String trailerIdv2) {
		this.trailerIdv2 = trailerIdv2;
	}
	public String getTrailer2MfrDate() {
		return trailer2MfrDate;
	}
	public void setTrailer2MfrDate(String trailer2MfrDate) {
		this.trailer2MfrDate = trailer2MfrDate;
	}
	public String getOwnerDriver() {
		return ownerDriver;
	}
	public void setOwnerDriver(String ownerDriver) {
		this.ownerDriver = ownerDriver;
	}
	public String getUnNamedPassengersNos() {
		return unNamedPassengersNos;
	}
	public void setUnNamedPassengersNos(String unNamedPassengersNos) {
		this.unNamedPassengersNos = unNamedPassengersNos;
	}
	public String getUnNamedPassengersSi() {
		return unNamedPassengersSi;
	}
	public void setUnNamedPassengersSi(String unNamedPassengersSi) {
		this.unNamedPassengersSi = unNamedPassengersSi;
	}
	public String getPaidDriversNos() {
		return paidDriversNos;
	}
	public void setPaidDriversNos(String paidDriversNos) {
		this.paidDriversNos = paidDriversNos;
	}
	public String getPaidDriversSi() {
		return paidDriversSi;
	}
	public void setPaidDriversSi(String paidDriversSi) {
		this.paidDriversSi = paidDriversSi;
	}
	public String getLegalLibCover() {
		return legalLibCover;
	}
	public void setLegalLibCover(String legalLibCover) {
		this.legalLibCover = legalLibCover;
	}
	public String getDriverCondCleaner() {
		return driverCondCleaner;
	}
	public void setDriverCondCleaner(String driverCondCleaner) {
		this.driverCondCleaner = driverCondCleaner;
	}
	public String getNonFarePassenger() {
		return nonFarePassenger;
	}
	public void setNonFarePassenger(String nonFarePassenger) {
		this.nonFarePassenger = nonFarePassenger;
	}
	public String getEmployeesInOperation() {
		return employeesInOperation;
	}
	public void setEmployeesInOperation(String employeesInOperation) {
		this.employeesInOperation = employeesInOperation;
	}
	public String getVoluntaryDeductible() {
		return voluntaryDeductible;
	}
	public void setVoluntaryDeductible(String voluntaryDeductible) {
		this.voluntaryDeductible = voluntaryDeductible;
	}
	public String getElectricalAcc() {
		return electricalAcc;
	}
	public void setElectricalAcc(String electricalAcc) {
		this.electricalAcc = electricalAcc;
	}
	public String getNonElectricalAcc() {
		return nonElectricalAcc;
	}
	public void setNonElectricalAcc(String nonElectricalAcc) {
		this.nonElectricalAcc = nonElectricalAcc;
	}
	public String getBifuelKit() {
		return bifuelKit;
	}
	public void setBifuelKit(String bifuelKit) {
		this.bifuelKit = bifuelKit;
	}
	public List<String> getCoverageIdList() {
		return coverageIdList;
	}
	public void setCoverageIdList(List<String> coverageIdList) {
		this.coverageIdList = coverageIdList;
	}
	public List<String> getCoverageNameList() {
		return coverageNameList;
	}
	public void setCoverageNameList(List<String> coverageNameList) {
		this.coverageNameList = coverageNameList;
	}
	public List<String> getCoverageValueList() {
		return coverageValueList;
	}
	public void setCoverageValueList(List<String> coverageValueList) {
		this.coverageValueList = coverageValueList;
	}
	public String getNoClaimBonusVal() {
		return noClaimBonusVal;
	}
	public void setNoClaimBonusVal(String noClaimBonusVal) {
		this.noClaimBonusVal = noClaimBonusVal;
	}
	public String getLoadingOd() {
		return loadingOd;
	}
	public void setLoadingOd(String loadingOd) {
		this.loadingOd = loadingOd;
	}
	public String getLoadingTp() {
		return loadingTp;
	}
	public void setLoadingTp(String loadingTp) {
		this.loadingTp = loadingTp;
	}
	public String getMotorLampsYN() {
		return motorLampsYN;
	}
	public void setMotorLampsYN(String motorLampsYN) {
		this.motorLampsYN = motorLampsYN;
	}
	public String getRegNo1() {
		return regNo1;
	}
	public void setRegNo1(String regNo1) {
		this.regNo1 = regNo1;
	}
	public String getRegNo2() {
		return regNo2;
	}
	public void setRegNo2(String regNo2) {
		this.regNo2 = regNo2;
	}
	public String getRegNo3() {
		return regNo3;
	}
	public void setRegNo3(String regNo3) {
		this.regNo3 = regNo3;
	}
	public String getRegNo4() {
		return regNo4;
	}
	public void setRegNo4(String regNo4) {
		this.regNo4 = regNo4;
	}
	public String getNoOfTrailer() {
		return noOfTrailer;
	}
	public void setNoOfTrailer(String noOfTrailer) {
		this.noOfTrailer = noOfTrailer;
	}
	public String getLtFinanceYN() {
		return ltFinanceYN;
	}
	public void setLtFinanceYN(String ltFinanceYN) {
		this.ltFinanceYN = ltFinanceYN;
	}
	public String getRestrictedTPPDYN() {
		return restrictedTPPDYN;
	}
	public void setRestrictedTPPDYN(String restrictedTPPDYN) {
		this.restrictedTPPDYN = restrictedTPPDYN;
	}
	public String getTrailer1RegNo() {
		return trailer1RegNo;
	}
	public void setTrailer1RegNo(String trailer1RegNo) {
		this.trailer1RegNo = trailer1RegNo;
	}
	public String getTrailer1RegNo1() {
		return trailer1RegNo1;
	}
	public void setTrailer1RegNo1(String trailer1RegNo1) {
		this.trailer1RegNo1 = trailer1RegNo1;
	}
	public String getTrailer1RegNo2() {
		return trailer1RegNo2;
	}
	public void setTrailer1RegNo2(String trailer1RegNo2) {
		this.trailer1RegNo2 = trailer1RegNo2;
	}
	public String getTrailer1RegNo3() {
		return trailer1RegNo3;
	}
	public void setTrailer1RegNo3(String trailer1RegNo3) {
		this.trailer1RegNo3 = trailer1RegNo3;
	}
	public String getTrailer1RegNo4() {
		return trailer1RegNo4;
	}
	public void setTrailer1RegNo4(String trailer1RegNo4) {
		this.trailer1RegNo4 = trailer1RegNo4;
	}
	public String getTrailer2RegNo() {
		return trailer2RegNo;
	}
	public void setTrailer2RegNo(String trailer2RegNo) {
		this.trailer2RegNo = trailer2RegNo;
	}
	public String getTrailer2RegNo1() {
		return trailer2RegNo1;
	}
	public void setTrailer2RegNo1(String trailer2RegNo1) {
		this.trailer2RegNo1 = trailer2RegNo1;
	}
	public String getTrailer2RegNo2() {
		return trailer2RegNo2;
	}
	public void setTrailer2RegNo2(String trailer2RegNo2) {
		this.trailer2RegNo2 = trailer2RegNo2;
	}
	public String getTrailer2RegNo3() {
		return trailer2RegNo3;
	}
	public void setTrailer2RegNo3(String trailer2RegNo3) {
		this.trailer2RegNo3 = trailer2RegNo3;
	}
	public String getTrailer2RegNo4() {
		return trailer2RegNo4;
	}
	public void setTrailer2RegNo4(String trailer2RegNo4) {
		this.trailer2RegNo4 = trailer2RegNo4;
	}
	public String getPaCoverYN() {
		return paCoverYN;
	}
	public void setPaCoverYN(String paCoverYN) {
		this.paCoverYN = paCoverYN;
	}
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getGeneralPremiumStatus() {
		return generalPremiumStatus;
	}
	public void setGeneralPremiumStatus(String generalPremiumStatus) {
		this.generalPremiumStatus = generalPremiumStatus;
	}
	public String getPaName() {
		return paName;
	}
	public void setPaName(String paName) {
		this.paName = paName;
	}
	public String getPaDob() {
		return paDob;
	}
	public void setPaDob(String paDob) {
		this.paDob = paDob;
	}
	public String getPaIndurer() {
		return paIndurer;
	}
	public void setPaIndurer(String paIndurer) {
		this.paIndurer = paIndurer;
	}
	public String getPaRelationship() {
		return paRelationship;
	}
	public void setPaRelationship(String paRelationship) {
		this.paRelationship = paRelationship;
	}
	public String getPaOccupation() {
		return paOccupation;
	}
	public void setPaOccupation(String paOccupation) {
		this.paOccupation = paOccupation;
	}
	public String getPaTableOfBenifits() {
		return paTableOfBenifits;
	}
	public void setPaTableOfBenifits(String paTableOfBenifits) {
		this.paTableOfBenifits = paTableOfBenifits;
	}
	public String getPaSumInsured() {
		return paSumInsured;
	}
	public void setPaSumInsured(String paSumInsured) {
		this.paSumInsured = paSumInsured;
	}
	public String getPaAnnualIncome() {
		return paAnnualIncome;
	}
	public void setPaAnnualIncome(String paAnnualIncome) {
		this.paAnnualIncome = paAnnualIncome;
	}
	public String getPaFatherFirstName() {
		return paFatherFirstName;
	}
	public void setPaFatherFirstName(String paFatherFirstName) {
		this.paFatherFirstName = paFatherFirstName;
	}
	public String getPaFatherMiddleName() {
		return paFatherMiddleName;
	}
	public void setPaFatherMiddleName(String paFatherMiddleName) {
		this.paFatherMiddleName = paFatherMiddleName;
	}
	public String getPaFatherLastName() {
		return paFatherLastName;
	}
	public void setPaFatherLastName(String paFatherLastName) {
		this.paFatherLastName = paFatherLastName;
	}
	public String getPaInsuredId() {
		return paInsuredId;
	}
	public void setPaInsuredId(String paInsuredId) {
		this.paInsuredId = paInsuredId;
	}
	public String getPaPolicyType() {
		return paPolicyType;
	}
	public void setPaPolicyType(String paPolicyType) {
		this.paPolicyType = paPolicyType;
	}
	public String getPaIsRollPolicyYN() {
		return paIsRollPolicyYN;
	}
	public void setPaIsRollPolicyYN(String paIsRollPolicyYN) {
		this.paIsRollPolicyYN = paIsRollPolicyYN;
	}
	public String getPaPrevPolicyNo() {
		return paPrevPolicyNo;
	}
	public void setPaPrevPolicyNo(String paPrevPolicyNo) {
		this.paPrevPolicyNo = paPrevPolicyNo;
	}
	public String getPaPrevExpiryDate() {
		return paPrevExpiryDate;
	}
	public void setPaPrevExpiryDate(String paPrevExpiryDate) {
		this.paPrevExpiryDate = paPrevExpiryDate;
	}
	public String getPaPrevInsCompany() {
		return paPrevInsCompany;
	}
	public void setPaPrevInsCompany(String paPrevInsCompany) {
		this.paPrevInsCompany = paPrevInsCompany;
	}
	public String getPaPrevClaimAmount() {
		return paPrevClaimAmount;
	}
	public void setPaPrevClaimAmount(String paPrevClaimAmount) {
		this.paPrevClaimAmount = paPrevClaimAmount;
	}
	public String getPaCostOfTravel() {
		return paCostOfTravel;
	}
	public void setPaCostOfTravel(String paCostOfTravel) {
		this.paCostOfTravel = paCostOfTravel;
	}
	public String getPaSupportingItem() {
		return paSupportingItem;
	}
	public void setPaSupportingItem(String paSupportingItem) {
		this.paSupportingItem = paSupportingItem;
	}
	public String getPaExitingDisability() {
		return paExitingDisability;
	}
	public void setPaExitingDisability(String paExitingDisability) {
		this.paExitingDisability = paExitingDisability;
	}
	public String getPaDisabilityDesc() {
		return paDisabilityDesc;
	}
	public void setPaDisabilityDesc(String paDisabilityDesc) {
		this.paDisabilityDesc = paDisabilityDesc;
	}
	public String getPremiumInfoType() {
		return premiumInfoType;
	}
	public void setPremiumInfoType(String premiumInfoType) {
		this.premiumInfoType = premiumInfoType;
	}
	public String getPaMedicalExtn() {
		return paMedicalExtn;
	}
	public void setPaMedicalExtn(String paMedicalExtn) {
		this.paMedicalExtn = paMedicalExtn;
	}
	public String getPaOptionType() {
		return paOptionType;
	}
	public void setPaOptionType(String paOptionType) {
		this.paOptionType = paOptionType;
	}
	public List<String> getPaInsuredRateList() {
		return paInsuredRateList;
	}
	public void setPaInsuredRateList(List<String> paInsuredRateList) {
		this.paInsuredRateList = paInsuredRateList;
	}
	public List<String> getPaInsuredIdList() {
		return paInsuredIdList;
	}
	public void setPaInsuredIdList(List<String> paInsuredIdList) {
		this.paInsuredIdList = paInsuredIdList;
	}
	public String getTractorYN() {
		return tractorYN;
	}
	public void setTractorYN(String tractorYN) {
		this.tractorYN = tractorYN;
	}
	public List<String> getTractorSubCoverList() {
		return tractorSubCoverList;
	}
	public void setTractorSubCoverList(List<String> tractorSubCoverList) {
		this.tractorSubCoverList = tractorSubCoverList;
	}
	public List<String> getTractorRateList() {
		return tractorRateList;
	}
	public void setTractorRateList(List<String> tractorRateList) {
		this.tractorRateList = tractorRateList;
	}
	public List<String> getPaMedExtnRateList() {
		return paMedExtnRateList;
	}
	public void setPaMedExtnRateList(List<String> paMedExtnRateList) {
		this.paMedExtnRateList = paMedExtnRateList;
	}
	public List<String> getPaTravelCostRateList() {
		return paTravelCostRateList;
	}
	public void setPaTravelCostRateList(List<String> paTravelCostRateList) {
		this.paTravelCostRateList = paTravelCostRateList;
	}
	public List<String> getPaSupportRateList() {
		return paSupportRateList;
	}
	public void setPaSupportRateList(List<String> paSupportRateList) {
		this.paSupportRateList = paSupportRateList;
	}
	public String getReqFromq() {
		return reqFromq;
	}
	public void setReqFromq(String reqFromq) {
		this.reqFromq = reqFromq;
	}
	public String getPremiumEditStatus() {
		return premiumEditStatus;
	}
	public void setPremiumEditStatus(String premiumEditStatus) {
		this.premiumEditStatus = premiumEditStatus;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public void setMerchant_reference(String merchant_reference) {
		this.merchant_reference = merchant_reference;
	}
	public String getMerchant_reference() {
		return merchant_reference;
	}
	public String getActualPremium() {
		return actualPremium;
	}
	public void setActualPremium(String actualPremium) {
		this.actualPremium = actualPremium;
	}
	public String getDiscountPremium() {
		return discountPremium;
	}
	public void setDiscountPremium(String discountPremium) {
		this.discountPremium = discountPremium;
	}
	public String getActualOptionalPremium() {
		return actualOptionalPremium;
	}
	public void setActualOptionalPremium(String actualOptionalPremium) {
		this.actualOptionalPremium = actualOptionalPremium;
	}
	public String getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(String discountPercent) {
		this.discountPercent = discountPercent;
	}
	public void setMinPremiumYN(String minPremiumYN) {
		this.minPremiumYN = minPremiumYN;
	}
	public String getMinPremiumYN() {
		return minPremiumYN;
	}
	public List<String> getDeleteRowList() {
		return deleteRowList;
	}
	public void setDeleteRowList(List<String> deleteRowList) {
		this.deleteRowList = deleteRowList;
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
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public Map<Integer, String> getCOVERAGES_BASE_RATE() {
		return COVERAGES_BASE_RATE;
	}
	public void setCOVERAGES_BASE_RATE(Map<Integer, String> cOVERAGESBASERATE) {
		COVERAGES_BASE_RATE = cOVERAGESBASERATE;
	}
	public void setCOVER_ID(Map<Integer, String> cOVER_ID) {
		COVER_ID = cOVER_ID;
	}
	public Map<Integer, String> getCOVER_ID() {
		return COVER_ID;
	}
	public String getCoverTS() {
		return coverTS;
	}
	public void setCoverTS(String coverTS) {
		this.coverTS = coverTS;
	}
	public List<Map<String, Object>> getExcessList() {
		return excessList;
	}
	public void setExcessList(List<Map<String, Object>> excessList) {
		this.excessList = excessList;
	}
	public List<Map<String, Object>> getExtraBenefitsList() {
		return extraBenefitsList;
	}
	public void setExtraBenefitsList(List<Map<String, Object>> extraBenefitsList) {
		this.extraBenefitsList = extraBenefitsList;
	}
	public Map<String, Object> getDetailsToCoverMap() {
		return detailsToCoverMap;
	}
	public void setDetailsToCoverMap(Map<String, Object> detailsToCoverMap) {
		this.detailsToCoverMap = detailsToCoverMap;
	}
	
	//      OTP BLOCK START   //
	private String otpId;
	private String otpStatus;
	private String otp;
	private String mailOtp;
	//private int myOtp;

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
	//    OTP BLOCK END    //
	
	private String nextScheme;
	private String btnYN;
	private String scheme;
	private String schemeSelected;
	private List<String> schemeList;
	private String prevScheme;
	private List<Map<String,Object>>[] premiumListNew;
	private List<Map<String,Object>> schemeListNew;
	private List<Map<String,Object>> extendedCoverList;
	private String extendedCover;
	private List<Map<String,Object>> exclusionsList;
	private List<Map<String,Object>> warrantiesList;
	private String ajScheme;
	private String coverSelected;
	private String existSchemeSelected;
	private String excessPercent;
	private String excessAmount;
	private String excessDesc;
	private String lossOfLimbs;
	private List<Map<String,Object>> LossLimbsList;
	private String add;
	
	private String ajMinimumPremiumYn;
	private String ajRemarks;
	
	private double volDiscountAmount;
	private double corpDiscountAmount;
	private double splDiscountAmount;
	
	private double ajVolDiscountPercent;
	private double ajVolDiscountAmount;
	private double ajCorpDiscountPercent;
	private double ajCorpDiscountAmount;
	private double ajSplDiscountPercent;
	private double ajSplDiscountAmount;
	
	private String calcFrom;
	private String premCalcErrorSchLoc;
	
	

	public String getNextScheme() {
		return nextScheme;
	}
	public void setNextScheme(String nextScheme) {
		this.nextScheme = nextScheme;
	}
	public String getBtnYN() {
		return btnYN;
	}
	public void setBtnYN(String btnYN) {
		this.btnYN = btnYN;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getSchemeSelected() {
		return schemeSelected;
	}
	public void setSchemeSelected(String schemeSelected) {
		this.schemeSelected = schemeSelected;
	}
	public List<String> getSchemeList() {
		return schemeList;
	}
	public void setSchemeList(List<String> schemeList) {
		this.schemeList = schemeList;
	}
	public String getPrevScheme() {
		return prevScheme;
	}
	public void setPrevScheme(String prevScheme) {
		this.prevScheme = prevScheme;
	}
	public List<Map<String, Object>>[] getPremiumListNew() {
		return premiumListNew;
	}
	public void setPremiumListNew(List<Map<String, Object>>[] premiumListNew) {
		this.premiumListNew = premiumListNew;
	}
	public List<Map<String, Object>> getSchemeListNew() {
		return schemeListNew;
	}
	public void setSchemeListNew(List<Map<String, Object>> schemeListNew) {
		this.schemeListNew = schemeListNew;
	}
	public List<Map<String, Object>> getExtendedCoverList() {
		return extendedCoverList;
	}
	public void setExtendedCoverList(List<Map<String, Object>> extendedCoverList) {
		this.extendedCoverList = extendedCoverList;
	}
	public String getExtendedCover() {
		return extendedCover;
	}
	public void setExtendedCover(String extendedCover) {
		this.extendedCover = extendedCover;
	}
	public List<Map<String, Object>> getExclusionsList() {
		return exclusionsList;
	}
	public void setExclusionsList(List<Map<String, Object>> exclusionsList) {
		this.exclusionsList = exclusionsList;
	}
	public List<Map<String, Object>> getWarrantiesList() {
		return warrantiesList;
	}
	public void setWarrantiesList(List<Map<String, Object>> warrantiesList) {
		this.warrantiesList = warrantiesList;
	}
	public String getAjScheme() {
		return ajScheme;
	}
	public void setAjScheme(String ajScheme) {
		this.ajScheme = ajScheme;
	}
	public String getCoverSelected() {
		return coverSelected;
	}
	public void setCoverSelected(String coverSelected) {
		this.coverSelected = coverSelected;
	}
	public String getExistSchemeSelected() {
		return existSchemeSelected;
	}
	public void setExistSchemeSelected(String existSchemeSelected) {
		this.existSchemeSelected = existSchemeSelected;
	}
	public String getExcessPercent() {
		return excessPercent;
	}
	public void setExcessPercent(String excessPercent) {
		this.excessPercent = excessPercent;
	}
	public String getExcessAmount() {
		return excessAmount;
	}
	public void setExcessAmount(String excessAmount) {
		this.excessAmount = excessAmount;
	}
	public String getExcessDesc() {
		return excessDesc;
	}
	public void setExcessDesc(String excessDesc) {
		this.excessDesc = excessDesc;
	}
	public String getLossOfLimbs() {
		return lossOfLimbs;
	}
	public void setLossOfLimbs(String lossOfLimbs) {
		this.lossOfLimbs = lossOfLimbs;
	}
	public List<Map<String, Object>> getLossLimbsList() {
		return LossLimbsList;
	}
	public void setLossLimbsList(List<Map<String, Object>> lossLimbsList) {
		LossLimbsList = lossLimbsList;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	
 

	private String[][] sumInsuredNM=new  String[30][30];
	public String[][] getSumInsuredNM() {
		return sumInsuredNM;
	}
	public void setSumInsuredNM(String[][] sumInsuredNM) {
		this.sumInsuredNM = sumInsuredNM;
	}
	private String[][] premiumNM=new  String[30][30];
	public String[][] getPremiumNM() {
		return premiumNM;
	}
	public void setPremiumNM(String[][] premiumNM) {
		this.premiumNM = premiumNM;
	}
	/*
	private ArrayList<String[]> sumInsuredNM=new ArrayList<new String[30]>();
*/
	/*private List<String> location;
	private List<String> locationStatus;*/
	private List<Map<String,Object>> productList;
	private String locations;
	private String locationSelected;
	private String locationIds;
	private String locationSize;
	private String product;
	private String locationId;
	private List<Map<String,Object>> HomeDesc=new ArrayList<Map<String,Object>>();
	private String callFrom;
	private List<String> locationName;
	private String mode;
	private List<Map<String,Object>> locationDetails;
	private String locationDesc;
	private String locStatus;
	private List<String> locationList;
	private String editLocId;
	//private List<Map<String,Object>> dropDownSchemeList;
	//private List<Map<String,Object>> dropDownLocationList;
	private String dropDownScheme;
	private String dropDownSchemeName;
	private String dropDownLocation;
	private String premiumDisplayVal;
	private String modifyYN;
	private String ajLocationId;
	private String ajActualPremium;
	private String ajActualOptionalPremium;
	private String ajTtotalPremium;
	
	private String singleLocName;

	/*public List<String> getLocation() {
		return location;
	}
	public void setLocation(List<String> location) {
		this.location = location;
	}
	public List<String> getLocationStatus() {
		return locationStatus;
	}
	public void setLocationStatus(List<String> locationStatus) {
		this.locationStatus = locationStatus;
	}*/
	public List<Map<String, Object>> getProductList() {
		return productList;
	}
	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}
	public String getLocations() {
		return locations;
	}
	public void setLocations(String locations) {
		this.locations = locations;
	}
	public String getLocationSelected() {
		return locationSelected;
	}
	public void setLocationSelected(String locationSelected) {
		this.locationSelected = locationSelected;
	}
	public String getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}
	public String getLocationSize() {
		return locationSize;
	}
	public void setLocationSize(String locationSize) {
		this.locationSize = locationSize;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public List<Map<String, Object>> getHomeDesc() {
		return HomeDesc;
	}
	public void setHomeDesc(List<Map<String, Object>> homeDesc) {
		HomeDesc = homeDesc;
	}
	public String getCallFrom() {
		return callFrom;
	}
	public void setCallFrom(String callFrom) {
		this.callFrom = callFrom;
	}
	public List<String> getLocationName() {
		return locationName;
	}
	public void setLocationName(List<String> locationName) {
		this.locationName = locationName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public List<Map<String, Object>> getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(List<Map<String, Object>> locationDetails) {
		this.locationDetails = locationDetails;
	}
	public String getLocationDesc() {
		return locationDesc;
	}
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	public String getLocStatus() {
		return locStatus;
	}
	public void setLocStatus(String locStatus) {
		this.locStatus = locStatus;
	}
	public List<String> getLocationList() {
		return locationList;
	}
	public void setLocationList(List<String> locationList) {
		this.locationList = locationList;
	}
	public String getEditLocId() {
		return editLocId;
	}
	public void setEditLocId(String editLocId) {
		this.editLocId = editLocId;
	}
	/*public List<Map<String, Object>> getDropDownSchemeList() {
		return dropDownSchemeList;
	}
	public void setDropDownSchemeList(List<Map<String, Object>> dropDownSchemeList) {
		this.dropDownSchemeList = dropDownSchemeList;
	}*/
	/*public List<Map<String, Object>> getDropDownLocationList() {
		return dropDownLocationList;
	}
	public void setDropDownLocationList(
			List<Map<String, Object>> dropDownLocationList) {
		this.dropDownLocationList = dropDownLocationList;
	}*/
	public String getDropDownScheme() {
		return dropDownScheme;
	}
	public void setDropDownScheme(String dropDownScheme) {
		this.dropDownScheme = dropDownScheme;
	}
	public String getDropDownLocation() {
		return dropDownLocation;
	}
	public void setDropDownLocation(String dropDownLocation) {
		this.dropDownLocation = dropDownLocation;
	}
	public String getPremiumDisplayVal() {
		return premiumDisplayVal;
	}
	public void setPremiumDisplayVal(String premiumDisplayVal) {
		this.premiumDisplayVal = premiumDisplayVal;
	}
	public String getModifyYN() {
		return modifyYN;
	}
	public void setModifyYN(String modifyYN) {
		this.modifyYN = modifyYN;
	}
	public String getAjLocationId() {
		return ajLocationId;
	}
	public void setAjLocationId(String ajLocationId) {
		this.ajLocationId = ajLocationId;
	}
	public String getAjActualPremium() {
		return ajActualPremium;
	}
	public void setAjActualPremium(String ajActualPremium) {
		this.ajActualPremium = ajActualPremium;
	}
	public String getAjActualOptionalPremium() {
		return ajActualOptionalPremium;
	}
	public void setAjActualOptionalPremium(String ajActualOptionalPremium) {
		this.ajActualOptionalPremium = ajActualOptionalPremium;
	}
	public String getAjTtotalPremium() {
		return ajTtotalPremium;
	}
	public void setAjTtotalPremium(String ajTtotalPremium) {
		this.ajTtotalPremium = ajTtotalPremium;
	}
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
	/**
	 * @return the locationDtlsList
	 */
	public List<Map<String, Object>> getLocationDtlsList() {
		return locationDtlsList;
	}
	/**
	 * @param locationDtlsList the locationDtlsList to set
	 */
	public void setLocationDtlsList(List<Map<String, Object>> locationDtlsList) {
		this.locationDtlsList = locationDtlsList;
	}
	/**
	 * @return the locationDtlsStatus
	 */
	public boolean isLocationDtlsStatus() {
		return locationDtlsStatus;
	}
	/**
	 * @param locationDtlsStatus the locationDtlsStatus to set
	 */
	public void setLocationDtlsStatus(boolean locationDtlsStatus) {
		this.locationDtlsStatus = locationDtlsStatus;
	}
	/**
	 * @return the schemeLocation
	 */
	public List<List<String>> getSchemeLocation() {
		return schemeLocation;
	}
	/**
	 * @param schemeLocation the schemeLocation to set
	 */
	public void setSchemeLocation(List<List<String>> schemeLocation) {
		this.schemeLocation = schemeLocation;
	}
	/**
	 * @return the schemeSelect
	 */
	public List<Boolean> getSchemeSelect() {
		return schemeSelect;
	}
	/**
	 * @param schemeSelect the schemeSelect to set
	 */
	public void setSchemeSelect(List<Boolean> schemeSelect) {
		this.schemeSelect = schemeSelect;
	}
	/**
	 * @return the customerFullName
	 */
	public String getCustomerFullName() {
		return customerFullName;
	}
	/**
	 * @param customerFullName the customerFullName to set
	 */
	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
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
	public String getExcessSign() {
		return excessSign;
	}
	public void setExcessSign(String excessSign) {
		this.excessSign = excessSign;
	}
	public String getExcessPremium() {
		return excessPremium;
	}
	public void setExcessPremium(String excessPremium) {
		this.excessPremium = excessPremium;
	}
	/**
	 * @return the policyFeePercent
	 */
	public String getPolicyFeePercent() {
		return policyFeePercent;
	}
	/**
	 * @param policyFeePercent the policyFeePercent to set
	 */
	public void setPolicyFeePercent(String policyFeePercent) {
		this.policyFeePercent = policyFeePercent;
	}
	/**
	 * @return the premiumCalcValidation
	 */
	public String getPremiumCalcValidation() {
		return premiumCalcValidation;
	}
	/**
	 * @param premiumCalcValidation the premiumCalcValidation to set
	 */
	public void setPremiumCalcValidation(String premiumCalcValidation) {
		this.premiumCalcValidation = premiumCalcValidation;
	}
	/**
	 * @return the schemeName
	 */
	public String getSchemeName() {
		return schemeName;
	}
	/**
	 * @param schemeName the schemeName to set
	 */
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public List<String> getExcessIdAr() {
		return excessIdAr;
	}
	public void setExcessIdAr(List<String> excessIdAr) {
		this.excessIdAr = excessIdAr;
	}
	public List<String> getExcessPercentAr() {
		return excessPercentAr;
	}
	public void setExcessPercentAr(List<String> excessPercentAr) {
		this.excessPercentAr = excessPercentAr;
	}
	public List<String> getExcessAmountAr() {
		return excessAmountAr;
	}
	public void setExcessAmountAr(List<String> excessAmountAr) {
		this.excessAmountAr = excessAmountAr;
	}
	public List<String> getExcessDescAr() {
		return excessDescAr;
	}
	public void setExcessDescAr(List<String> excessDescAr) {
		this.excessDescAr = excessDescAr;
	}
	public List<Boolean> getExcessDeleteAr() {
		return excessDeleteAr;
	}
	public void setExcessDeleteAr(List<Boolean> excessDeleteAr) {
		this.excessDeleteAr = excessDeleteAr;
	}
	public List<String> getWarrantiesIdAr() {
		return warrantiesIdAr;
	}
	public void setWarrantiesIdAr(List<String> warrantiesIdAr) {
		this.warrantiesIdAr = warrantiesIdAr;
	}
	public List<String> getWarrantiesDescAr() {
		return warrantiesDescAr;
	}
	public void setWarrantiesDescAr(List<String> warrantiesDescAr) {
		this.warrantiesDescAr = warrantiesDescAr;
	}
	public List<Boolean> getWarrantiesDeleteAr() {
		return warrantiesDeleteAr;
	}
	public void setWarrantiesDeleteAr(List<Boolean> warrantiesDeleteAr) {
		this.warrantiesDeleteAr = warrantiesDeleteAr;
	}
	public List<String> getExclusionsIdAr() {
		return exclusionsIdAr;
	}
	public void setExclusionsIdAr(List<String> exclusionsIdAr) {
		this.exclusionsIdAr = exclusionsIdAr;
	}
	public List<String> getExclusionsDescAr() {
		return exclusionsDescAr;
	}
	public void setExclusionsDescAr(List<String> exclusionsDescAr) {
		this.exclusionsDescAr = exclusionsDescAr;
	}
	public List<Boolean> getExclusionsDeleteAr() {
		return exclusionsDeleteAr;
	}
	public void setExclusionsDeleteAr(List<Boolean> exclusionsDeleteAr) {
		this.exclusionsDeleteAr = exclusionsDeleteAr;
	}
	/**
	 * @return the dropDownSchemeName
	 */
	public String getDropDownSchemeName() {
		return dropDownSchemeName;
	}
	/**
	 * @param dropDownSchemeName the dropDownSchemeName to set
	 */
	public void setDropDownSchemeName(String dropDownSchemeName) {
		this.dropDownSchemeName = dropDownSchemeName;
	}
	/**
	 * @return the editFrom
	 */
	public String getEditFrom() {
		return editFrom;
	}
	/**
	 * @param editFrom the editFrom to set
	 */
	public void setEditFrom(String editFrom) {
		this.editFrom = editFrom;
	}
	/**
	 * @return the singleLocName
	 */
	public String getSingleLocName() {
		return singleLocName;
	}
	/**
	 * @param singleLocName the singleLocName to set
	 */
	public void setSingleLocName(String singleLocName) {
		this.singleLocName = singleLocName;
	}
	/**
	 * @return the policyGenerateYn
	 */
	public String getPolicyGenerateYn() {
		return policyGenerateYn;
	}
	/**
	 * @param policyGenerateYn the policyGenerateYn to set
	 */
	public void setPolicyGenerateYn(String policyGenerateYn) {
		this.policyGenerateYn = policyGenerateYn;
	}
	public String getAjMinimumPremiumYn() {
		return ajMinimumPremiumYn;
	}
	public void setAjMinimumPremiumYn(String ajMinimumPremiumYn) {
		this.ajMinimumPremiumYn = ajMinimumPremiumYn;
	}
	public String getAjRemarks() {
		return ajRemarks;
	}
	public void setAjRemarks(String ajRemarks) {
		this.ajRemarks = ajRemarks;
	}
	public double getVolDiscountAmount() {
		return volDiscountAmount;
	}
	public void setVolDiscountAmount(double volDiscountAmount) {
		this.volDiscountAmount = volDiscountAmount;
	}
	public double getCorpDiscountAmount() {
		return corpDiscountAmount;
	}
	public void setCorpDiscountAmount(double corpDiscountAmount) {
		this.corpDiscountAmount = corpDiscountAmount;
	}
	public double getSplDiscountAmount() {
		return splDiscountAmount;
	}
	public void setSplDiscountAmount(double splDiscountAmount) {
		this.splDiscountAmount = splDiscountAmount;
	}
	public double getAjVolDiscountPercent() {
		return ajVolDiscountPercent;
	}
	public void setAjVolDiscountPercent(double ajVolDiscountPercent) {
		this.ajVolDiscountPercent = ajVolDiscountPercent;
	}
	public double getAjVolDiscountAmount() {
		return ajVolDiscountAmount;
	}
	public void setAjVolDiscountAmount(double ajVolDiscountAmount) {
		this.ajVolDiscountAmount = ajVolDiscountAmount;
	}
	public double getAjCorpDiscountPercent() {
		return ajCorpDiscountPercent;
	}
	public void setAjCorpDiscountPercent(double ajCorpDiscountPercent) {
		this.ajCorpDiscountPercent = ajCorpDiscountPercent;
	}
	public double getAjCorpDiscountAmount() {
		return ajCorpDiscountAmount;
	}
	public void setAjCorpDiscountAmount(double ajCorpDiscountAmount) {
		this.ajCorpDiscountAmount = ajCorpDiscountAmount;
	}
	public double getAjSplDiscountPercent() {
		return ajSplDiscountPercent;
	}
	public void setAjSplDiscountPercent(double ajSplDiscountPercent) {
		this.ajSplDiscountPercent = ajSplDiscountPercent;
	}
	public double getAjSplDiscountAmount() {
		return ajSplDiscountAmount;
	}
	public void setAjSplDiscountAmount(double ajSplDiscountAmount) {
		this.ajSplDiscountAmount = ajSplDiscountAmount;
	}
	/**
	 * @return the calcFrom
	 */
	public String getCalcFrom() {
		return calcFrom;
	}
	/**
	 * @param calcFrom the calcFrom to set
	 */
	public void setCalcFrom(String calcFrom) {
		this.calcFrom = calcFrom;
	}
	/**
	 * @return the premCalcErrorSchLoc
	 */
	public String getPremCalcErrorSchLoc() {
		return premCalcErrorSchLoc;
	}
	/**
	 * @param premCalcErrorSchLoc the premCalcErrorSchLoc to set
	 */
	public void setPremCalcErrorSchLoc(String premCalcErrorSchLoc) {
		this.premCalcErrorSchLoc = premCalcErrorSchLoc;
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
}