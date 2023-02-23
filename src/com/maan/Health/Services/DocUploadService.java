package com.maan.Health.Services;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.maan.Health.DAO.DocUploadDAO;
import com.maan.Health.controller.UploadBean;
import com.maan.common.LogManager;

public class DocUploadService {
	DocUploadDAO dao=new DocUploadDAO();
	public String insertUploadDetails(String filePath, final String applicationNo, final String docId, 
    		final List<String> documentIds, final List<File> document, final List<String> documentFileName, 
    		final List<String> documentDescription, final String userId){
    	LogManager.info("Path => "+filePath);
    	LogManager.info("Total Files => "+document.size());
    	List<Object[]> list = new ArrayList<Object[]>();
    	String result = null;
    	try{
	    	int count = 0;
	    	filePath = filePath + "/" + applicationNo;
	    	File tmpFile = new File(filePath);
	    	if(!tmpFile.exists()){
	    		tmpFile.mkdir();
	    	}
	    	for (File file : document) {
	    		LogManager.info("File => "+documentFileName.get(count));
	    		Calendar cal = Calendar.getInstance();
		    	String time = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"
	    						+cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.HOUR)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
	    		String orgFileName = documentFileName.get(count);
	    		String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
	    		String fileName = documentFileName.get(count);
	    		fileName = applicationNo+"_"+(documentIds.get(count))+"_"+fileName.substring(0, fileName.lastIndexOf("."))+"_"+time;
	    		fileName = fileName + ext;
	    		LogManager.info("Our File => "+fileName);
	    		LogManager.info("Desc => "+documentDescription.get(count));
	    		File savedFile = new File(filePath, fileName); 
	    		file.renameTo(savedFile);
	    		
	    		final Object[] args = new Object[9];
	    		args[0] = applicationNo;
	    		args[1] = docId;
	    		args[3] = (count+1);
	    		args[4] = documentFileName.get(count);
	    		args[5] = fileName;
	    		args[6] = filePath;
	    		args[7] = documentDescription.get(count);
	    		args[8] = userId;
	    		list.add(args);
	    		count++;    		
			}
	    	if(list.size()>0){
	    		result = dao.insertUploadDetails(list, applicationNo, docId);
	    	}
    	}catch(Exception e){
    		LogManager.info(e);
    		result = "Error while saving the Files => " + e.getMessage();
    	}
    	return result;
    }
	 public List<UploadBean> getUploadedDocumentList(final String applicationNo, final String docId){
	    	return dao.getUploadedDocumentList(applicationNo, docId);
	    }
	 public String deleteUploadFileDetails(final String applicationNo, final String docId, 
	    		final String docSNo, final String filePath){
	    	String result = null;
	    	try{
	    		UploadBean bean = dao.getUploadedDocumentDetails(applicationNo, docId, docSNo);
	    		File file = new File(bean.getDocPath()+"/"+bean.getDocOurName());
	    		if(file.exists()){
	    			file.delete();
	    		}
	    		LogManager.info("file exits => "+file.exists());
	    		result = dao.deleteUpdateFileDetails(applicationNo, docId,docSNo);
	    	}catch(Exception e){
	    		LogManager.debug(e);
	    		result = "Error While Deleting File => "+e.getMessage();
	    	}
	    	return result;
	    }
}
