package com.maan.ClaimIntimation.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.maan.ClaimIntimation.bean.ClaimIntimationBean;
import com.maan.ClaimIntimation.dao.ClaimIntimationDAO;
import com.maan.adminnew.common.CommonService;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ClaimIntimationService {
	private static final String DOCUMENT_FILE_PATH = CommonService.getApplicationPath() + "documents/";
	ClaimIntimationDAO dao =new ClaimIntimationDAO();

	public List<Map<String, Object>> listClaim(ClaimIntimationBean bean) {
		return dao.listClaim(bean);
	}

	public int insertClaim(ClaimIntimationBean bean) {
		return dao.insertClaim(bean);
	}

	public void getEditList(ClaimIntimationBean bean) {
		dao.getEditList(bean);
	}

	public String getClaimRef(ClaimIntimationBean bean) {
		return dao.getClaimRef(bean);
	}

	public List<Map<String, Object>> getClaimStatus(ClaimIntimationBean bean) {
		return dao.getClaimStatus(bean);
	}

	public List<Map<String, Object>> getClaimIntimUploadList(ClaimIntimationBean bean) {
		return dao.getClaimIntimUploadList(bean);
	}
	
	public List<String> insertDocumentDetails(List<String> documentIdList,List<String> docDescription, List<String> uploadFileName, List<File> uploadFile, String claimRefNo, String productId, String uploadFileStr, String deviceType) throws IOException {
		List<String> errorList = new ArrayList<String>();
		int j=0;
		if(documentIdList!=null){
			for(int i=0 ; i< documentIdList.size() ; i++) {
				if(documentIdList.get(i) != null && uploadFileName.get(i) != null){
					if(!DBConstants.DEVICETYPE_HYBRID.equals(deviceType) && (uploadFile.get(j).length()/1024)>2048) {
						errorList.add(" File Size Must Be Less Then 2MB at row "+ (i+1));
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy_h-mm-ss-SSSSSS_a");
						Calendar cal = Calendar.getInstance();
						String date = sdf.format(cal.getTime());
						String documentPath = DOCUMENT_FILE_PATH + claimRefNo +"_" + j + "_" + documentIdList.get(i) + date + "." +FilenameUtils.getExtension(uploadFileName.get(j));
						//System.out.println(bean.getUpload().get(j).length());
						if(DBConstants.DEVICETYPE_HYBRID.equals(deviceType)) {
							decodeFile(uploadFileStr, documentPath);
						} else {
							FileUtils.copyFile(uploadFile.get(j), new File(documentPath));
						}
						dao.insertDocumentDetails(claimRefNo,documentIdList.get(i),documentPath, docDescription.get(i),uploadFileName.get(j),productId);
						j++;
					}
				}
			}
		}
		return errorList;
	}
	
	private void decodeFile(String fileStr, String fileLoc) {
		FileOutputStream fos = null;
		try {
			byte[] bytes1 = Base64.decode(fileStr);
			fos=new FileOutputStream(fileLoc);
			fos.write(bytes1);
			fos.flush();
		} catch(Exception exception) {
			LogManager.debug(exception);
		} finally {
			try {
				if(fos!=null) {
					fos.close();
				}
			} catch(Exception exception) {
				LogManager.debug(exception);
			}
		}
	}

	public void deleteDocument(String filePath) throws Exception {
		FileUtils.deleteQuietly(new File(filePath));
		dao.deleteDocument( filePath);
	}

	public String getClaimExistRef(ClaimIntimationBean bean) {
		return dao.getClaimExistRef(bean);
	}

	public List<Map<String, Object>> getDocDownloadList(ClaimIntimationBean bean) {
		return dao.getDocDownloadList(bean);
	}

	public List<Map<String, Object>> setClaimDetails(ClaimIntimationBean bean) {
		return dao.setClaimDetails(bean);
	}
	
}
