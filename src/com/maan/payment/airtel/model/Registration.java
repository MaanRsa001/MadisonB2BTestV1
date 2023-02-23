package com.maan.payment.airtel.model;

import com.google.gson.annotations.SerializedName;

public class Registration {
	@SerializedName("id")
	private String id;
	@SerializedName("status")
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
