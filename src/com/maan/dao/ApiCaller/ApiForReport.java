package com.maan.dao.ApiCaller;

import java.util.concurrent.Callable;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.report.ReportBean;

public class ApiForReport extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getCertificate(String quoteNo,String vehicleId) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		String result="";
		try {
			String link=getValueFromWebservice("maan.client.Report.Certificate");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("QuoteNo", quoteNo);
			hp.put("VehicleId", vehicleId);
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				result = (String) json.get("Response");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

}
