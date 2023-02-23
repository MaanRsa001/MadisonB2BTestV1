package com.maan.upload;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.maan.common.LogManager;
import com.maan.upload.service.UploadService;

public class FileUploadThread implements Runnable{
	UploadService service = new UploadService();
	
	private String transactionId;
	private String uploadFileName;
	private String filePath;
	private String csvPath;
	private File upload;
	private String typeId;
	private String policyType; 
	private String factorID;
	private String broker;
	private String effectivedate;
	private boolean copyStatus;
	
	private String rawTable;
	private String mainTable;
	private String errorTable;
	private String uniqueRefColumn;
	private String uniqueRefDataType;
	private String vehicleUsage;
	private String branchCode;
	
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCsvPath() {
		return csvPath;
	}

	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getRawTable() {
		return rawTable;
	}

	public void setRawTable(String rawTable) {
		this.rawTable = rawTable;
	}

	public String getMainTable() {
		return mainTable;
	}

	public void setMainTable(String mainTable) {
		this.mainTable = mainTable;
	}

	public String getErrorTable() {
		return errorTable;
	}

	public void setErrorTable(String errorTable) {
		this.errorTable = errorTable;
	}

	public String getUniqueRefColumn() {
		return uniqueRefColumn;
	}

	public void setUniqueRefColumn(String uniqueRefColumn) {
		this.uniqueRefColumn = uniqueRefColumn;
	}

	public String getUniqueRefDataType() {
		return uniqueRefDataType;
	}

	public void setUniqueRefDataType(String uniqueRefDataType) {
		this.uniqueRefDataType = uniqueRefDataType;
	}


	public String getFactorID() {
		return factorID;
	}

	public void setFactorID(String factorID) {
		this.factorID = factorID;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(String effectivedate) {
		this.effectivedate = effectivedate;
	}

	public boolean isCopyStatus() {
		return copyStatus;
	}

	public void setCopyStatus(boolean copyStatus) {
		this.copyStatus = copyStatus;
	}

	public String getVehicleUsage() {
		return vehicleUsage;
	}

	public void setVehicleUsage(String vehicleUsage) {
		this.vehicleUsage = vehicleUsage;
	}
	
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public FileUploadThread(String tranId, String uploadFileName,
			String filePath, String csvPath, File upload, String typeId,
			String factorID, String broker, String effectivedate,
			String policyType,boolean copyStatus,String vehicleUsage,String branchCode) {
		setTransactionId(tranId);
		setUploadFileName(uploadFileName);
		setFilePath(filePath);
		setCsvPath(csvPath);
		setUpload(upload);
		setTypeId(typeId);
		setFactorID(factorID);
		setBroker(broker);
		setEffectivedate(effectivedate);
		setPolicyType(policyType);
		setCopyStatus(copyStatus);
		setVehicleUsage(vehicleUsage);
		setBranchCode(branchCode);
	}

	public void run() {
		Map resultMap = null;
		String error="";
		try {
			if(copyStatus){
				UploadAction.excelToCSVRating(filePath+"/"+uploadFileName, csvPath+"/"+uploadFileName);
			    System.out.println("csvFIle Path===>"+csvPath+"/"+uploadFileName);
				File csvFile=new File(csvPath+"/"+uploadFileName);
				if(csvFile.exists() && csvFile.canRead()){
					resultMap=service.insertRawRecords(csvFile, transactionId,uploadFileName,filePath,"",typeId,policyType,factorID,broker,effectivedate);
					error=(String)resultMap.get("ERROR");
					if(StringUtils.isBlank(error)){
						service.updateTransactionDtl("P","Moving Records to main table",transactionId);
						service.mainTableInsert(transactionId, policyType, factorID,broker,vehicleUsage,branchCode);
					}else{
						service.updateTransactionDtl("E",error,transactionId);
						LogManager.info(error);
					}
					//error=(String)resultMap.get("ERROR");
					 //LogManager.info(error);
				}else{
					error="CSV File Not Exist";
					service.updateTransactionDtl("E",error,transactionId);
					LogManager.info(error);
				}
			}else{
				error="Error in File Copy";
				service.updateTransactionDtl("E",error,transactionId);
				LogManager.info(error);
			}
			
		} catch (Exception e) {
			service.updateTransactionDtl("E","Error in Moving Records to main table",transactionId);
			e.printStackTrace();
		}
	}

}
