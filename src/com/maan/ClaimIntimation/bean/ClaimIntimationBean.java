package com.maan.ClaimIntimation.bean;

import java.io.File;
import java.util.List;
import java.util.Map;

import nl.captcha.Captcha;

public class ClaimIntimationBean {
	private String name;
	private String passport;
	private String dateOfAccident;
	private String vehicleRegNo;
	private String phone;
	public String policyNo;
	private String mode;
	private String status;
	private List<Map<String, Object>> claimList;
	private String claimId;
	private String remarks;
	private String policyNo1;
	private String policyNo2;
	private String policyNo3;
	private String policyNo4;
	private String policyNo5;
	private String captchavalue;
	private Captcha captcha;
	private String productId;
	private String claimRefNo;
	private String cStatus; // claim status
	private String sStatus; // search status
	private String updateDate;
	private String claimNo;
	private List<Map<String,Object>> claimDetails;
	
	
	private String filePath;
	private String fileName;
	private List<String> docDescription;
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> documentIdList;
	private List<Map<String,Object>> claimAttachedDocs;
	
	
	public List<String> getDocumentIdList() {
		return documentIdList;
	}
	public void setDocumentIdList(List<String> documentIdList) {
		this.documentIdList = documentIdList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getDateOfAccident() {
		return dateOfAccident;
	}
	public void setDateOfAccident(String dateOfAccident) {
		this.dateOfAccident = dateOfAccident;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public void setClaimList(List<Map<String, Object>> claimList) {
		this.claimList = claimList;
	}
	public List<Map<String, Object>> getClaimList() {
		return claimList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}
	public String getClaimId() {
		return claimId;
	}
	
	public String getPolicyNo1() {
		return policyNo1;
	}
	public void setPolicyNo1(String policyNo1) {
		this.policyNo1 = policyNo1;
	}
	public String getPolicyNo2() {
		return policyNo2;
	}
	public void setPolicyNo2(String policyNo2) {
		this.policyNo2 = policyNo2;
	}
	public String getPolicyNo3() {
		return policyNo3;
	}
	public void setPolicyNo3(String policyNo3) {
		this.policyNo3 = policyNo3;
	}
	public String getPolicyNo4() {
		return policyNo4;
	}
	public void setPolicyNo4(String policyNo4) {
		this.policyNo4 = policyNo4;
	}
	public String getPolicyNo5() {
		return policyNo5;
	}
	public void setPolicyNo5(String policyNo5) {
		this.policyNo5 = policyNo5;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public void setCaptchavalue(String captchavalue) {
		this.captchavalue = captchavalue;
	}
	public String getCaptchavalue() {
		return captchavalue;
	}
	public void setCaptcha(Captcha captcha) {
		this.captcha = captcha;
	}
	public Captcha getCaptcha() {
		return captcha;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductId() {
		return productId;
	}
	public void setClaimRefNo(String claimRefNo) {
		this.claimRefNo = claimRefNo;
	}
	public String getClaimRefNo() {
		return claimRefNo;
	}
	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}
	public String getcStatus() {
		return cStatus;
	}
	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	public String getsStatus() {
		return sStatus;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<String> getDocDescription() {
		return docDescription;
	}
	public void setDocDescription(List<String> docDescription) {
		this.docDescription = docDescription;
	}
	public List<File> getUpload() {
		return upload;
	}
	public void setUpload(List<File> upload) {
		this.upload = upload;
	}
	public List<String> getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public List<Map<String, Object>> getClaimAttachedDocs() {
		return claimAttachedDocs;
	}
	public void setClaimAttachedDocs(List<Map<String, Object>> claimAttachedDocs) {
		this.claimAttachedDocs = claimAttachedDocs;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public List<Map<String, Object>> getClaimDetails() {
		return claimDetails;
	}
	public void setClaimDetails(List<Map<String, Object>> claimDetails) {
		this.claimDetails = claimDetails;
	}
}
