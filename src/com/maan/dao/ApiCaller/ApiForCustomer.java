package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.maan.Motor.controller.MotorBean;
import com.maan.customer.CustomerBean;

public class ApiForCustomer extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCustomerList(String mode, String type, String userId, String brokerCode) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		JSONArray array = new JSONArray();
		try {
			String link=getValueFromWebservice("maan.client.Customer.getCustomerList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("BrokerCode", brokerCode);
			hp.put("CustomerType", type);
			
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
	public String insertCustomerDetails(MotorBean bean, String detailQuote) {
		
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		String result = "";
		try {
			String link=getValueFromWebservice("maan.client.Customer.insertCustomerDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CustomerId", bean.getCustomerId());
			hp.put("Title", bean.getTitle());
			hp.put("FirstName", bean.getCustomerName());
			hp.put("LastName", bean.getCustLastName());
			hp.put("DateOfBirth", bean.getCustdob());
			hp.put("MobileNo", bean.getMobileNo());
			hp.put("Email", bean.getEmail());
			hp.put("CustomerType", bean.getCustomerType());
			hp.put("BrokerCode", bean.getBrokerCode());
			hp.put("LoginId", bean.getLoginId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProductId", bean.getProductId());
			hp.put("Address1", bean.getAddress1());
			hp.put("Address2", bean.getAddress2());
			hp.put("PoBox", bean.getPoBox());
			hp.put("City", bean.getCity());
			hp.put("CoreAppCode", bean.getCoreAppCode());
			hp.put("ClientCustomerId", bean.getClientCustomerId());
			hp.put("CustArNo", bean.getCustArNo());
			hp.put("CustPassportNo", bean.getCustPassportNo());
			hp.put("Custdob", bean.getCustdob());
			hp.put("CustAlterMobileNo", bean.getCustAlterMobileNo());
			hp.put("CustLandLineNo", bean.getCustLandLineNo());
			hp.put("CompanyRegNo", bean.getCompanyRegNo());
			hp.put("CustNameArabic", bean.getCustNameArabic());
			hp.put("Custdobar", bean.getCustdobar());
			hp.put("Gender", bean.getGender());
			hp.put("Occupation", bean.getOccupation());
			hp.put("DetailQuote", detailQuote);
			hp.put("Custnrc1", bean.getCustnrc1());
			hp.put("Custnrc2", bean.getCustnrc2());
			hp.put("Custnrc3", bean.getCustnrc3());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Response");
				bean.setErrors((JSONArray) json.get("Errors"));
				if("SUCCESS".equals(json.get("Message"))) {
					result = json1.get("CustomerId")==null?"":json1.get("CustomerId").toString();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> customerDetails(CustomerBean bean) {
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		JSONObject hp = new JSONObject();
		try {
			String link=getValueFromWebservice("maan.client.Motor.setCustDetail");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CustomerId", bean.getCustomerId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Response");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}
	

}
