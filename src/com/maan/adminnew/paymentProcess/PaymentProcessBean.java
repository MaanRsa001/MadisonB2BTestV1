package com.maan.adminnew.paymentProcess;

import java.util.List;
import java.util.Map;

import com.maan.customer.CustBean;


public class PaymentProcessBean extends CustBean{
	private String reqForm;
	private String startDate;
	private String endDate;
	private String policyType;
	private String mode;
	private String productId;
	private String paymentType;
	private String customerName;
	private String add1;
	private String add2;
	private String poBox;
	private String mobileNo;
	private String fromDate;
	private String toDate;
	private String policyNo;
	private String premium;
	private String vat;
	private String totPremium;
	private String chequeNo;
	private String chequeDate;
	private String chequeAmount;
	private String bankName;
	private String micrCode;
	private String reqTime;
	private String resTime;
	private String resTranNo;
	private String reqTranNo;
	private String resStatus;
	private String resMsge;
	private String resCode;
	private String maskedCard;
	private String customerId;
	private String CCStatus;
	private String modeOfPayment;
	private String applicationNo;
	private String merchant_reference;
	private String email;
	private String branchCode;
	private String brokerCode;
	private String loginId;
	private String option;
	private String userType;
	private String remarks;
	private String status;
	private String cashDepositBank;
	private String cashAmount;
	private String cashChellanNo;
	private String cashInstrumentDate;
	private String currencyType;
	private String resDecision;
	private String check;
	private String applicapleLoginId;
	private String vehicleId;
	
	private String mtnMobileNo;
	
	private List<Map<String,Object>> premiumInfo;
	private List<Map<String,Object>> premiumInfoList;
	private String policyFee;
	private List<Object> vehilceIdList;
	private List<Object> vehilceIdAcceptedList;
	private List<Object> vehilceIdRejectedList;
	
	private List<Map<String,Object>> creditControlList;
	private List<Map<String,Object>> vehicleDetailList;
	
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
	
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBrokerCode() {
		return brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getTotPremium() {
		return totPremium;
	}

	public void setTotPremium(String totPremium) {
		this.totPremium = totPremium;
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

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public String getResTime() {
		return resTime;
	}

	public void setResTime(String resTime) {
		this.resTime = resTime;
	}

	public String getResTranNo() {
		return resTranNo;
	}

	public void setResTranNo(String resTranNo) {
		this.resTranNo = resTranNo;
	}

	public String getReqTranNo() {
		return reqTranNo;
	}

	public void setReqTranNo(String reqTranNo) {
		this.reqTranNo = reqTranNo;
	}

	public String getResStatus() {
		return resStatus;
	}

	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}

	public String getResMsge() {
		return resMsge;
	}

	public void setResMsge(String resMsge) {
		this.resMsge = resMsge;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getMaskedCard() {
		return maskedCard;
	}

	public void setMaskedCard(String maskedCard) {
		this.maskedCard = maskedCard;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setReqForm(String reqForm) {
		this.reqForm = reqForm;
	}

	public String getReqForm() {
		return reqForm;
	}

	public void setCreditControlList(List<Map<String,Object>> creditControlList) {
		this.creditControlList = creditControlList;
	}

	public List<Map<String,Object>> getCreditControlList() {
		return creditControlList;
	}

	public void setVehicleDetailList(List<Map<String,Object>> vehicleDetailList) {
		this.vehicleDetailList = vehicleDetailList;
	}

	public List<Map<String,Object>> getVehicleDetailList() {
		return vehicleDetailList;
	}

	public void setCCStatus(String CCStatus) {
		this.CCStatus = CCStatus;
	}

	public String getCCStatus() {
		return CCStatus;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setMerchant_reference(String merchant_reference) {
		this.merchant_reference = merchant_reference;
	}

	public String getMerchant_reference() {
		return merchant_reference;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setVehilceIdList(List<Object> vehilceIdList) {
		this.vehilceIdList = vehilceIdList;
	}

	public List<Object> getVehilceIdList() {
		return vehilceIdList;
	}

	public void setVehilceIdAcceptedList(List<Object> vehilceIdAcceptedList) {
		this.vehilceIdAcceptedList = vehilceIdAcceptedList;
	}

	public List<Object> getVehilceIdAcceptedList() {
		return vehilceIdAcceptedList;
	}

	public void setVehilceIdRejectedList(List<Object> vehilceIdRejectedList) {
		this.vehilceIdRejectedList = vehilceIdRejectedList;
	}

	public List<Object> getVehilceIdRejectedList() {
		return vehilceIdRejectedList;
	}

	public void setResDecision(String resDecision) {
		this.resDecision = resDecision;
	}

	public String getResDecision() {
		return resDecision;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCheck() {
		return check;
	}

	public void setApplicapleLoginId(String applicapleLoginId) {
		this.applicapleLoginId = applicapleLoginId;
	}

	public String getApplicapleLoginId() {
		return applicapleLoginId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public List<Map<String, Object>> getPremiumInfo() {
		return premiumInfo;
	}

	public void setPremiumInfo(List<Map<String, Object>> premiumInfo) {
		this.premiumInfo = premiumInfo;
	}

	public List<Map<String, Object>> getPremiumInfoList() {
		return premiumInfoList;
	}

	public void setPremiumInfoList(List<Map<String, Object>> premiumInfoList) {
		this.premiumInfoList = premiumInfoList;
	}

	public String getPolicyFee() {
		return policyFee;
	}

	public void setPolicyFee(String policyFee) {
		this.policyFee = policyFee;
	}
	
	
	
	private List<Map<String,Object>> QuoteDetails;
	private String billRefNum;

	public List<Map<String, Object>> getQuoteDetails() {
		return QuoteDetails;
	}

	public void setQuoteDetails(List<Map<String, Object>> quoteDetails) {
		QuoteDetails = quoteDetails;
	}

	public String getBillRefNum() {
		return billRefNum;
	}

	public void setBillRefNum(String billRefNum) {
		this.billRefNum = billRefNum;
	}

	public String getMtnMobileNo() {
		return mtnMobileNo;
	}

	public void setMtnMobileNo(String mtnMobileNo) {
		this.mtnMobileNo = mtnMobileNo;
	}

	
}
