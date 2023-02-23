package com.maan.payment.airtel.model;

import com.google.gson.annotations.SerializedName;

public class Subscriber {
	
	@SerializedName("country")
	private String country;
	@SerializedName("currency")
	private String currency;
	@SerializedName("msisdn")
	private int msisdn;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(int msisdn) {
		this.msisdn = msisdn;
	}
	
	
}
