package com.maan.payment.airtel.model;

import com.google.gson.annotations.SerializedName;

public class Status {
	@SerializedName("message")
	private String message;
	@SerializedName("code")
	private String code;
	@SerializedName("result_code")
	private String resultcode;
	@SerializedName("success")
	private boolean success;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getResultcode() {
		return resultcode;
	}
	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
