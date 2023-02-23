package com.maan.webservice.rest.resource;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maan.adminnew.common.CommonService;
import com.maan.common.DBConstants;
import com.maan.common.LogManager;
import com.maan.upload.service.UploadService;

@Path("document")
public class DocumentResouce {
	@POST
	@Path("/getDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map<String,Object>> getDetails(@FormParam("paramDocumentBean") String paramDocumentBean) {
		LogManager.info("/document/getDetails - Enter --> "+paramDocumentBean);
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		try {
			UploadService service=new UploadService();
			Type type = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> inputParams = new Gson().fromJson(paramDocumentBean, type);
			
			String reqFrom = inputParams.get("type")==null?"":inputParams.get("type").toString();
			String productId = inputParams.get("productId")==null?"":inputParams.get("productId").toString();
			if("DOCUMENT_TYPE_LIST".equals(reqFrom)) {
				resultList = service.getDocumentList(productId,"");
			} else if("DOCUMENT_DETAILS_LIST".equals(reqFrom)) {
				String quoteNo = inputParams.get("quoteNo")==null?"":inputParams.get("quoteNo").toString();
				String vehicleId = inputParams.get("vehicleId")==null?"":inputParams.get("vehicleId").toString();
				resultList = service.getUploadDocList(productId, quoteNo, vehicleId,"");
			}
		} catch (Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.info("/document/getDetails - Exit");
		return resultList;
	}
	
	@POST
	@Path("/deleteDocument")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteDocument(@FormParam("paramDocumentBean") String paramDocumentBean) {
		LogManager.info("/document/deleteDocument - Enter --> "+paramDocumentBean);
		String result = "";
		try {
			UploadService service=new UploadService();
			Type type = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> inputParams = new Gson().fromJson(paramDocumentBean, type);
			
			String filePath = inputParams.get("filePath")==null?"":inputParams.get("filePath").toString();
			service.deleteDocument(filePath);
		} catch (Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.info("/document/deleteDocument - Exit");
		return result;
	}
	
	@POST
	@Path("/downloadDocument")
	@Produces(MediaType.APPLICATION_JSON)
	public String downloadDocument(@FormParam("paramDocumentBean") String paramDocumentBean) {
		LogManager.info("/document/downloadDocument - Enter --> "+paramDocumentBean);
		String result = "";
		try {
			Type type = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> inputParams = new Gson().fromJson(paramDocumentBean, type);
			
			String filePath = inputParams.get("filePath")==null?"":inputParams.get("filePath").toString();
			result = filePath.replaceAll(CommonService.getApplicationPath(), "");
		} catch (Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.info("/document/downloadDocument - Exit");
		return result;
	}
	
	@POST
	@Path("/addDocument")
	@Produces(MediaType.APPLICATION_JSON)
	public String addDocument(@FormParam("paramDocumentBean") String paramDocumentBean) {
		LogManager.info("/document/addDocument - Enter --> "+paramDocumentBean);
		String result = "";
		try {
			UploadService service=new UploadService();
			Type type = new TypeToken<Map<String,Object>>(){}.getType();
			Map<String,Object> inputParams = new Gson().fromJson(paramDocumentBean, type);
			
			List<String> documentIdList = new ArrayList<String>();
			List<String> docDescList = new ArrayList<String>();
			List<String> uploadFileNameList = new ArrayList<String>();
			
			String quoteNo = inputParams.get("quoteNo")==null?"":inputParams.get("quoteNo").toString();
			String vehicleId = inputParams.get("vehicleId")==null?"":inputParams.get("vehicleId").toString();
			String productId = inputParams.get("productId")==null?"":inputParams.get("productId").toString();
			String uploadFileStr =  inputParams.get("uploadFileStr")==null?"":inputParams.get("uploadFileStr").toString().replace(" ", "+");
			
			documentIdList.add(inputParams.get("documentId")==null?"":inputParams.get("documentId").toString());
			docDescList.add(inputParams.get("documentDesc")==null?"":inputParams.get("documentDesc").toString());
			uploadFileNameList.add(inputParams.get("uploadFileName")==null?"":inputParams.get("uploadFileName").toString());
			
			service.insertDocumentDetails(documentIdList, docDescList, uploadFileNameList, null, quoteNo, vehicleId, productId, uploadFileStr, DBConstants.DEVICETYPE_HYBRID);
		} catch (Exception exception) {
			LogManager.debug(exception);
		}
		LogManager.info("/document/addDocument - Exit");
		return result;
	}
	
	
}
