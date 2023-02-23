package com.maan.payment.airtel.model;

import com.google.gson.annotations.SerializedName;

public class UserEnqData {
	@SerializedName("first_name")
	private String firstname;
	@SerializedName("last_name")
	private String lastname;
	@SerializedName("msisdn")
	private String msisdn;
	@SerializedName("dob")
	private String dob;
	@SerializedName("grade")
	private String grade;
	@SerializedName("is_pin_set")
	private boolean ispinset;
	@SerializedName("is_barred")
	private boolean isbarred;
	@SerializedName("registration")
	private Registration registration;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public boolean isIspinset() {
		return ispinset;
	}
	public void setIspinset(boolean ispinset) {
		this.ispinset = ispinset;
	}
	public boolean isIsbarred() {
		return isbarred;
	}
	public void setIsbarred(boolean isbarred) {
		this.isbarred = isbarred;
	}
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
}
