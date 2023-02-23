package com.maan.dao.ApiCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.CollectionUtils;

import com.maan.Motor.controller.MotorBean;

public class ApiForCommon extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getOptionsList(String option, String productId, String type,MotorBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array= new JSONArray();
		
		try {
			
			String link=getValueFromWebservice("maan.client.Common.getOptionsList.title");
			authorization= getValueFromWebservice("marine.insurance.auth");
	
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
	public List<Object> getModelList(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		List<Object> EmptyList = new ArrayList<Object>();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getOptionsList.model");
		authorization= getValueFromWebservice("marine.insurance.auth");
		
		hp.put("MakeId", bean.getMake());
		link = getActualLink(link, hp);
		
		if(StringUtils.isNotBlank(bean.getMake())) {
		String responseStr=callAPIGet(link, authorization);
		if(responseStr!=null && responseStr.length()>0) {
			json = (JSONObject) parser.parse(responseStr);
			array = (JSONArray) json.get("Response");
		}
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	return CollectionUtils.isEmpty(array)? EmptyList : array;
}

	@SuppressWarnings("unchecked")
	public List<Object> getMakeList(MotorBean bean) {
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getOptionsList.make");
		authorization= getValueFromWebservice("marine.insurance.auth");
		
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
	public List<Object> getDeductibleList(String option, MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getDeductibleList");
		authorization= getValueFromWebservice("marine.insurance.auth");
		
		hp.put("ProductId", bean.getProductId());
		hp.put("BranchCode", bean.getBranchCode());
		hp.put("SeatCapacity", bean.getSeatingCapacity());
		hp.put("VehicleUsage", bean.getVehicleUsage());
		hp.put("BodyId", bean.getTypeBody());
		
		if(StringUtils.isNotBlank(bean.getSeatingCapacity())) {
		String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
		if(responseStr!=null && responseStr.length()>0) {
			json = (JSONObject) parser.parse(responseStr);
			array = (JSONArray) json.get("Response");
		}
		}else {
			array = new JSONArray();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	return array;
}

	@SuppressWarnings("unchecked")
	public List<Object> getVehicleColourList(MotorBean bean) {
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getVehicleColourList");
		authorization= getValueFromWebservice("marine.insurance.auth");
		
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
	public List<Object> getBankOfFinanceList(MotorBean bean) {
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getBankOfFinanceList");
		authorization= getValueFromWebservice("marine.insurance.auth");
		
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

	public List<Object> getNoOfClaims(MotorBean bean) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getNoOfClaims");
		authorization = getValueFromWebservice("marine.insurance.auth");
		
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
	public List<Object> getNoClaimBonusList(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getNoClaimBonusList");
		authorization = getValueFromWebservice("marine.insurance.auth");
		
		hp.put("ProductId", bean.getProductId());
		hp.put("BranchCode", bean.getBranchCode());
		hp.put("SeatCapacity", bean.getSeatingCapacity());
		hp.put("VehicleUsage", bean.getVehicleUsage());
		hp.put("BodyId", bean.getTypeBody());
		
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

	public List<Object> insCompanyList(MotorBean bean) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.insCompanyList");
		authorization = getValueFromWebservice("marine.insurance.auth");
		
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
	public List<Object> getCityList(MotorBean bean) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getCityList");
		authorization = getValueFromWebservice("marine.insurance.auth");
		
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
	public List<Object> getPolicyEndList(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getPolicyEndList");
		authorization = getValueFromWebservice("marine.insurance.auth");
		
		hp.put("PolicyStartDate", bean.getPolicyStartDate());
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
	public List<Map<String, Object>> getSubBranchList(String loginId, String mgenBranch) {
		JSONObject hp = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getSubBranchList");
		authorization = getValueFromWebservice("marine.insurance.auth");
		
		hp.put("LoginId", loginId);
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
	public List<Object> getBankNamelist(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		try {
		String link = getValueFromWebservice("maan.client.Common.getBankNamelist");
		authorization = getValueFromWebservice("marine.insurance.auth");
		
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


}
