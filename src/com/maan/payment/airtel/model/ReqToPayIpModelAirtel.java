package com.maan.payment.airtel.model;

import com.google.gson.annotations.SerializedName;

public class ReqToPayIpModelAirtel {
	
	@SerializedName("reference")
	private String reference;
	@SerializedName("subscriber")
	private Subscriber subscriber;
	@SerializedName("transaction")
	private Transaction transaction;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	
}
