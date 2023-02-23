package com.maan.payment.airtel.model;

import com.google.gson.annotations.SerializedName;

public class UserEnqOpModel {
	@SerializedName("data")
	private UserEnqData data;
	@SerializedName("status")
	private Status status;
	
	public UserEnqData getData() {
		return data;
	}
	public void setData(UserEnqData data) {
		this.data = data;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}