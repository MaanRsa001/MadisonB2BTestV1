package com.maan.Health.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.maan.Motor.controller.MotorBean;


public class UploadBean  {
	private String docId;
	private String docSNo;
	private String docName;
	private String docOurName;
	private String docPath;
	private String quoteNo;
	private String docDesc;
	private String docUploadBy;
	private String docUploadDate;
	private String docUpdateBy;
	private String docUpdateDate;
	private String contentId;
	private String status;
	private String documenttypeId;
	public String getDocumenttypeId() {
		return documenttypeId;
	}
	public void setDocumenttypeId(String documenttypeId) {
		this.documenttypeId = documenttypeId;
	}
	//private String productId;
	private String deleteVehicleId;
	private String filePath;
	private String fileName;
	private List<UploadBean> updDocumentList = new ArrayList<UploadBean>();
	private List<String> documentIdList;
	private List<String> docDescription;
	private List<String> remarks;
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	private String applicationNo;
		
	public List<String> getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getDocOurName() {
		return docOurName;
	}
	public void setDocOurName(String docOurName) {
		this.docOurName = docOurName;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getDocUploadBy() {
		return docUploadBy;
	}
	public void setDocUploadBy(String docUploadBy) {
		this.docUploadBy = docUploadBy;
	}
	public String getDocUpdateBy() {
		return docUpdateBy;
	}
	public void setDocUpdateBy(String docUpdateBy) {
		this.docUpdateBy = docUpdateBy;
	}
	public String getDocUpdateDate() {
		return docUpdateDate;
	}
	public void setDocUpdateDate(String docUpdateDate) {
		this.docUpdateDate = docUpdateDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	public String getDocSNo() {
		return docSNo;
	}
	public void setDocSNo(String docSNo) {
		this.docSNo = docSNo;
	}
	public List<UploadBean> getUpdDocumentList() {
		return updDocumentList;
	}
	public void setUpdDocumentList(List<UploadBean> updDocumentList) {
		this.updDocumentList = updDocumentList;
	}
	public String getDocUploadDate() {
		return docUploadDate;
	}
	public void setDocUploadDate(String docUploadDate) {
		this.docUploadDate = docUploadDate;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocDesc() {
		return docDesc;
	}
	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}
	public String getDeleteVehicleId() {
		return deleteVehicleId;
	}
	public void setDeleteVehicleId(String deleteVehicleId) {
		this.deleteVehicleId = deleteVehicleId;
	}
	public List<String> getDocumentIdList() {
		return documentIdList;
	}
	public void setDocumentIdList(List<String> documentIdList) {
		this.documentIdList = documentIdList;
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
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
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
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public List<String> getRemarks() {
		return remarks;
	}
	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}


}
