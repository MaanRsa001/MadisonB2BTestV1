package com.maan.Home.displayBean;

public class QuoteRegisterBean {

	private int sno;

	private String quoteNo;

	private String customerName;

	private String quoteDate;

	private String validityPeriod;

	private String status;

	private String premiumAmount;

	private String customerID;
	
	private String applicationNo;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(final String customerName) {
		this.customerName = customerName;
	}

	public String getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(final String quoteDate) {
		this.quoteDate = quoteDate;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(final String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(final int sno) {
		this.sno = sno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(final String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(final String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(final String customerID) {
		this.customerID = customerID;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(final String applicationNo) {
		this.applicationNo = applicationNo;
	}

}
