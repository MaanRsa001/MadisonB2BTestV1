package com.maan.dao.ApiCaller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.Health.controller.UploadBean;

public class ApiForUpload extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDocumentList(String productId, String userType) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONArray array = new JSONArray();
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Upload.getDocumentList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ProductId", productId);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Response");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public void insertDocumentDetails(UploadBean bean,String pid){
		
		JSONObject hp = new JSONObject();
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.Upload.insertDocumentDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			if(bean.getDocumentIdList()!=null) {
				for(int i=0;i<bean.getDocumentIdList().size();i++) {
					JSONObject abj = new JSONObject();
					abj.put("DocumentTypeId", bean.getDocumentIdList().get(i));
					abj.put("Description", bean.getDocDescription().get(i));
					abj.put("Base64File", encodeFile(bean.getUpload().get(i)));
					abj.put("FileName", bean.getUploadFileName().get(i));
					abj.put("QuoteNo", bean.getQuoteNo());
					abj.put("ProductId", pid);
					abj.put("VtypeId", bean.getDeleteVehicleId());
					array.add(abj);
				}
			}
				
				String responseStr=callAPIPost(link, authorization, array.toString().replaceAll("\"\"", "null"));
				if(responseStr!=null && responseStr.length()>0) {
					json = (JSONObject) parser.parse(responseStr);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String encodeFile(File file) {
	    try {
	        byte[] fileContent = Files.readAllBytes(file.toPath());
	        return Base64.getEncoder().encodeToString(fileContent);
	    } catch (IOException e) {
	        throw new IllegalStateException("could not read file " + file, e);
	    }
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUploadDocList(String productId, String quoteNo, String vehicleId,
			String userType) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONArray array = new JSONArray();
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Upload.getUploadDocList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("QuoteNo", quoteNo);
			hp.put("ProductId", productId);
			hp.put("VtypeId", vehicleId);
		
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Response");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public void deleteDocument(UploadBean bean, String productId) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Upload.deleteDocument");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("QuoteNo", bean.getQuoteNo());
			hp.put("ProductId", productId);
			hp.put("VtypeId", bean.getDeleteVehicleId());
			hp.put("DocumentTypeId", bean.getDocumenttypeId());
		
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	


}
