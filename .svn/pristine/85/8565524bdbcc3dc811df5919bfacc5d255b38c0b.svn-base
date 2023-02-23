package com.maan.Health.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.maan.Health.Services.DocUploadService;
import com.maan.common.LogManager;
import com.opensymphony.xwork2.ActionSupport;

public class DocUploadAction extends ActionSupport{
	HttpServletRequest request=ServletActionContext.getRequest();
	private static final long serialVersionUID = 1L;
	private static final String DROPDOWN = "dropdown";
	private static final String FIELD = "ELEMENT_NAME";
	private DocUploadService service = new DocUploadService();
	private List<String> documentIds = new ArrayList<String>();
	private List<File> document = new ArrayList<File>();
	private List<String> documentFileName = new ArrayList<String>();
	private List<String> documentContentType = new ArrayList<String>();
	private List<String> documentDescription = new ArrayList<String>();
	private List<UploadBean> updDocumentList = new ArrayList<UploadBean>();
	private String applicationNo;
	private String docId;
	private String docSNo;
	private String reqFrom;
	private int fileCount;
	private String docOurName;
	private String docName;
	private InputStream inputStream=null;
	
	public List<String> getDocumentIds() {
	    	return documentIds;
	}

	public void setDocumentIds(List<String> documentIds) {
			this.documentIds = documentIds;
	}
	
	public List<File> getDocument() {
		return document;
	}

	public void setDocument(List<File> document) {
		this.document = document;
	}

	public List<String> getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(List<String> documentFileName) {
		this.documentFileName = documentFileName;
	}

	public List<String> getDocumentContentType() {
		return documentContentType;
	}

	public void setDocumentContentType(List<String> documentContentType) {
		this.documentContentType = documentContentType;
	}

	public List<String> getDocumentDescription() {
		return documentDescription;
	}

	public void setDocumentDescription(List<String> documentDescription) {
		this.documentDescription = documentDescription;
	}

	public List<UploadBean> getUpdDocumentList() {
		return updDocumentList;
	}
	
	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
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

	public String getReqFrom() {
		return reqFrom;
	}

	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	
	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocOurName() {
		return docOurName;
	}

	public void setDocOurName(String docOurName) {
		this.docOurName = docOurName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getUploadPage(){
    	List<String> idList = new ArrayList<String>();
    	idList.add(""+(++fileCount));
    	idList.add(""+(++fileCount));
    	documentIds = idList;
    	return INPUT;
    }
	public String uploadDocuments(){
    	LogManager.info("uploadDocuments - Enter");
    	String status = null;
    	if(document.size()<=0){
    		status = getText("error.upload.file.required");
    	}
    	if(status == null){
	    	ServletContext servletContext = ServletActionContext.getServletContext();
	    	HttpSession session = ServletActionContext.getRequest().getSession();
	    	String filePath = servletContext.getRealPath("/HealthDocuments");
	    	status = service.insertUploadDetails(filePath, applicationNo, docId, documentIds, document, 
	    			documentFileName, documentDescription, (String)session.getAttribute("user"));
    	}
    	if(status == null){
    		reqFrom = "DocumentsUploaded";
    	}else{
    		addActionError(status);
    	}
    	LogManager.info("uploadDocuments - Exit");
    	return INPUT; 
	}
	public String getUploadFileDetails(){
		updDocumentList = service.getUploadedDocumentList(applicationNo, docId);
		request.setAttribute(FIELD, "showDoc");
		return DROPDOWN;
	}
	public String downloadFile(){
		LogManager.info("downloadFile() - Enter || fileName: "+getDocOurName());
		try {
			inputStream=new FileInputStream(new File(this.request.getSession().getServletContext().getRealPath("/HealthDocuments/")+"/"+getApplicationNo()+"/"+getDocOurName()));
		} catch (Exception e) {
			LogManager.debug(e);
			inputStream=new ByteArrayInputStream("File Not Found".getBytes());
		}
		LogManager.info("downloadFile() - Exit ");
		return "download";
	}
	public String deleteUploadFileDetails(){
		ServletContext servletContext = ServletActionContext.getServletContext();
    	String filePath = servletContext.getRealPath("/HealthDocuments");
    	String status = service.deleteUploadFileDetails(applicationNo, docId, docSNo, filePath);
		updDocumentList = service.getUploadedDocumentList(applicationNo, docId);
		request.setAttribute(FIELD, "showDoc");
		return DROPDOWN;
	}

}
