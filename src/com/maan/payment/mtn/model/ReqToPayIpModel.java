package com.maan.payment.mtn.model;

import com.google.gson.annotations.SerializedName;

public class ReqToPayIpModel {
	
	@SerializedName("financialTransactionId")
	private String financialTransactionId;
	@SerializedName("amount")
	private String amount;
	@SerializedName("currency")
	private String currency;
	@SerializedName("externalId")
	private String externalId;
	@SerializedName("payer")
	private Payer payer;
	@SerializedName("payerMessage")
	private String payerMessage;
	@SerializedName("payeeNote")
	private String payeeNote;
	@SerializedName("status")
	private String status;
	@SerializedName("reason")
	private String reason;
	
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return externalId;
	}
	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	/**
	 * @return the payer
	 */
	public Payer getPayer() {
		return payer;
	}
	/**
	 * @param payer the payer to set
	 */
	public void setPayer(Payer payer) {
		this.payer = payer;
	}
	/**
	 * @return the payerMessage
	 */
	public String getPayerMessage() {
		return payerMessage;
	}
	/**
	 * @param payerMessage the payerMessage to set
	 */
	public void setPayerMessage(String payerMessage) {
		this.payerMessage = payerMessage;
	}
	/**
	 * @return the payeeNote
	 */
	public String getPayeeNote() {
		return payeeNote;
	}
	/**
	 * @param payeeNote the payeeNote to set
	 */
	public void setPayeeNote(String payeeNote) {
		this.payeeNote = payeeNote;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	public String getFinancialTransactionId() {
		return financialTransactionId;
	}
	public void setFinancialTransactionId(String financialTransactionId) {
		this.financialTransactionId = financialTransactionId;
	}

}
