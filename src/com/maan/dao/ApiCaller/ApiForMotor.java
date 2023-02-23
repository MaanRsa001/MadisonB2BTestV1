package com.maan.dao.ApiCaller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.CollectionUtils;

import com.maan.Motor.controller.MotorBean;

public class ApiForMotor extends ApiConfig implements Callable<Object>{
	String authorization="";
	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getQuoteInfo(MotorBean bean, boolean errorStatus) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		String result="";
		
		try {
			String link=getValueFromWebservice("maan.client.nonprop.getQuoteInfo");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("Title", bean.getTitle());
			hp.put("FirstName", bean.getCustomerName());
			hp.put("Last_Name", bean.getCustLastName());
			hp.put("Email", bean.getEmail());
			hp.put("Mobile", bean.getMobileNo());
			hp.put("Type", bean.getCustomerType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
				result = (String) json.get("Result");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPolicyTypeList(MotorBean bean) {
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONArray array= new JSONArray();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getPolicyTypeList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
				array = (JSONArray) json.get("Response");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> setCustDetail(MotorBean bean, String type) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.setCustDetail");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("CustomerId", bean.getCustomerId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
				json1 = (JSONObject) json.get("Response");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}


	@SuppressWarnings("unchecked")
	public String updateVehicleDetailsNew(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		String result="Failed";
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.updateVehicleDetailsNew");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
		for(int i=0 ; i<bean.getVehicleIdList().size() ; i++) {
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("CustomerId", bean.getCustomerId());
			hp.put("QuoteNo", bean.getQuoteNo());
			if("update".equalsIgnoreCase(bean.getReqFrom())) {
			hp.put("VehicleId", bean.getVehicleId());
			}
			hp.put("MakeId", bean.getMakeIdList().get(i));
			hp.put("ModelId", bean.getModelIdList().get(i));
			hp.put("BodyTypeId", bean.getTypeBodyIdList().get(i));
			hp.put("SumInsured", bean.getSumInsuredList().get(i));
			hp.put("ManufacureYear", bean.getMfgYrIdList().get(i));
			hp.put("SeatingCapacity", bean.getSeatingCapacityList().get(i));
			hp.put("ChassisNo", bean.getChassisNoList().get(i));
			hp.put("EngineNo", bean.getEngineNoList().get(i));
			hp.put("PreviousClaimYN", bean.getPrevClaimYn().get(i));
			hp.put("NoOfClaims", bean.getNoOfClaims().get(i));
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("BankOfFinance", bean.getBankOfFinanceIdList().get(i));
			hp.put("VehicleColor", bean.getVehicleColourIdList().get(i));
			hp.put("RegistrationNo", bean.getRegNoList().get(i));
			hp.put("EngineCapacity", bean.getCubicCapacityList().get(i));
			hp.put("ExcessLimit", CollectionUtils.isEmpty(bean.getDeductibleIdList())?null:bean.getDeductibleIdList().get(i));
			hp.put("VehicleUsage", bean.getVehicleUsageIdList().get(i));
			hp.put("LeasedYn", bean.getLeasedYNIdList().get(i));
			hp.put("ElectricalAccesAmt", bean.getElectricalAccList().get(i));
			hp.put("NonElectricalAccesAmt", bean.getNonElectricalAccList().get(i));
			hp.put("DriverDateOfBirth", CollectionUtils.isEmpty(bean.getDriverDOBList())?"":bean.getDriverDOBList().get(i));
			hp.put("PolicyType", bean.getPolicyType());
			hp.put("CurrencyType", bean.getCurrencyType());
			hp.put("PolicyStartDate", bean.getPolicyStartDate());
			hp.put("PolicyEndDate", bean.getPolicyEndDate());
		}
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				if("ERROR".equals(json.get("Message"))) {
					bean.setErrors((JSONArray) json.get("Response"));
				}else {
				json1 = (JSONObject) json.get("Response");
				}
				if("SUCCESS".equals(json.get("Message"))) {
				bean.setApplicationNo(json1.get("ApplicationNo")==null?"":json1.get("ApplicationNo").toString());
				bean.setDeleteVehicleId(json1.get("VehicleId")==null?"":json1.get("VehicleId").toString());
				result = "Success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMultiVehicleDetails(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONArray array = new JSONArray();
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getMultiVehicleDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ApplicationNo", bean.getApplicationNo());
			link = getActualLink(link, hp);
			
			if(StringUtils.isNotBlank(bean.getApplicationNo())) {
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
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
	public Map<String, Object> editVehicleIdDetails(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.editVehicleIdDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("VehicleId", bean.getDeleteVehicleId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
				json1 = (JSONObject) json.get("Response");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public int updateDriverDetails(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		int result = 0;
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.updateDriverDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("VehicleId", bean.getVehicleId());
			hp.put("IsClaimDtl", bean.getIsClaimDtl());
			hp.put("DriverId", bean.getDriverIdList().get(0));
			hp.put("DriverDob", bean.getDriverDOBList().get(0));
			hp.put("Claimyn", bean.getClaimYNIdList().get(0));
			hp.put("PolicyNo", bean.getPrevPolicyNoList().get(0));
			hp.put("OwnnerdriverYn", bean.getOwnerDriverYNList().get(0));
			hp.put("PrePolicyExpDate", bean.getPrevExpiryDateList().get(0));
			hp.put("NoOfClaimBonus", bean.getNoClaimBonusIdList().get(0));
			hp.put("ClaimAmt", bean.getClaimAmountList().get(0));
			hp.put("InsCompany", bean.getPrevInsCompanyIdList().get(0));
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			json = (JSONObject) parser.parse(responseStr);
			if(responseStr!=null && responseStr.length()>0) {
				if("SUCCESS".equals(json.get("Message"))) {
					result = 1;
				}else {
					bean.setErrors((JSONArray) json.get("Response"));
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDriverDetails(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getDriverDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("VehicleId", bean.getDeleteVehicleId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Response");
				bean.setErrors((JSONArray) json.get("Errors"));
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getVehicleDetailsByIdNew(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getVehicleDetailsByIdNew");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("VehicleId", bean.getDeleteVehicleId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
				json1 = (JSONObject) json.get("Response");
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public void deleteVehicleIdDetails(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.deleteVehicleIdDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("VehicleId", bean.getDeleteVehicleId());
			link = getActualLink(link, hp);
			
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> updateQuoteDetailsNew(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject hp1 = new JSONObject();
		JSONObject json,json1 = null;
		JSONObject abj = new JSONObject();
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.updateQuoteDetailsNew");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			if(bean.getPolicyType()!=null) {
				abj.put("PolicyType", bean.getPolicyType());
				abj.put("PolicyStartState", bean.getPolicyStartDate());
				abj.put("PolicyEndDate", bean.getPolicyEndDate());
				abj.put("CurrencyType", bean.getCurrencyType());
			}
			
			hp.put("PolicyReq", abj);
			
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("QuoteNo", bean.getQuoteNo());
			hp.put("BrokerCode", bean.getBrokerCode());
			hp.put("LoginId", bean.getLoginId());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("UserType", bean.getUserType());
			hp.put("CustomerId", bean.getCustomerId());
			hp.put("ProductId", bean.getProductId());
			
			hp1.put("QuoteRequest", hp);
			
			String responseStr=callAPIPost(link, authorization, hp1.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Response");
				bean.setErrors((JSONArray) json.get("Errors"));
				if("Y".equals(json1.get("ReferalStatus"))) {
					bean.setReferralMsg(json1.get("ReferalRemarks")==null?"":json1.get("ReferalRemarks").toString());
				}
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public String getBuypolicy(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		JSONObject abj = new JSONObject();
		String result = null;
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getBuypolicy");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("QuoteNo", bean.getQuoteNo());
			hp.put("ApplicationNo", bean.getApplicationNo());
			hp.put("PolicyStartDate", bean.getPolicyStartDate());
			hp.put("PolicyEndDate", bean.getPolicyEndDate());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ReferalQuoteYn", StringUtils.isBlank(bean.getReferralYN())?"N":bean.getReferralYN());
			hp.put("ReferalRemarks", bean.getReferralComments());
			hp.put("EmailQuoteYn", StringUtils.isBlank(bean.getQuoteEmailYN())?"N":bean.getQuoteEmailYN());
			hp.put("InstallmentYn", bean.getInstallmentYN());
			hp.put("GeneratePolicyYn", bean.getGeneratePolicyYN());
			
			if(bean.getCustomerId()!=null) {
				abj.put("CustomerId", bean.getCustomerId());
				abj.put("Title", bean.getTitle());
				abj.put("FirstName", bean.getCustomerName());
				abj.put("LastName", bean.getCustLastName());
				abj.put("DateOfBirth", bean.getCustdob());
				abj.put("Gender", bean.getGender());
				abj.put("Occupation", bean.getOccupation());
				abj.put("Address1", bean.getAddress1());
				abj.put("Address2", bean.getAddress2());
				abj.put("City", bean.getCity());
				abj.put("PoBox", bean.getPoBox());
				abj.put("MobileNo", bean.getMobileNo());
				abj.put("AlternatMobileNo", bean.getCustAlterMobileNo());
				abj.put("Email", bean.getEmail());
				abj.put("Nrc", String.valueOf(bean.getCustnrc1()+"/"+bean.getCustnrc2()+"/"+bean.getCustnrc3()));
				abj.put("CustomerType", bean.getCustomerType());
				abj.put("CompanyRegNo", bean.getCompanyRegNo());
				abj.put("PassportNo", bean.getCustPassportNo());
			}
			
			hp.put("CustomerInfo", abj);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
				if("SUCCESS".equals(json.get("Message"))) {
					JSONObject json1 = new JSONObject();
					json1 = (JSONObject) json.get("Response");
					bean.setProduct(json1.get("Product")==null?"":json1.get("Product").toString());
					result = "Success";
				}
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void validatePolicy(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.validatePolicy");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("PolicyType", bean.getPolicyType());
			hp.put("PolicyStartState", bean.getPolicyStartDate());
			hp.put("PolicyEndDate", bean.getPolicyEndDate());
			hp.put("CurrencyType", bean.getCurrencyType());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				bean.setErrors((JSONArray) json.get("Errors"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPaymentList(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONArray array = new JSONArray();
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getModeOfPaymentList");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			link = getActualLink(link, hp);
			String responseStr=callAPIGet(link, authorization);
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				array = (JSONArray) json.get("Response");
				bean.setErrors((JSONArray) json.get("Errors"));
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> makepay(MotorBean bean) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		String link=null;
		
		try {
			if("1".equalsIgnoreCase(bean.getModeOfPayment())) {
				link=getValueFromWebservice("maan.client.Motor.makepay");
			}else if("2".equalsIgnoreCase(bean.getModeOfPayment())){
				link=getValueFromWebservice("maan.client.Motor.cheque");
			}else if("102".equalsIgnoreCase(bean.getModeOfPayment())) {
				link=getValueFromWebservice("maan.client.Motor.airtel");
			}else if("101".equalsIgnoreCase(bean.getModeOfPayment())) {
				link=getValueFromWebservice("maan.client.Motor.mtn");
			}
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("QuoteNo", bean.getQuoteNo());
			hp.put("BranchCode", bean.getBranchCode());
			hp.put("ProductId", bean.getProductId());
			hp.put("CashDepositBank", bean.getCashDepositBank());
			hp.put("CashAmount", bean.getCashAmount());
			hp.put("CashChellanNo", bean.getCashChellanNo());
			hp.put("CashInstrumentDate", bean.getCashInstrumentDate());
			hp.put("ChequeDate", bean.getChequeDate());
			hp.put("ChequeAmount", bean.getChequeAmount());
			hp.put("BankName", bean.getBankName());
			hp.put("MicrCode", bean.getMicrCode());
			if("101".equalsIgnoreCase(bean.getModeOfPayment())) {
				hp.put("MobileNo", bean.getMtnMobileNo());
			}else if("102".equalsIgnoreCase(bean.getModeOfPayment())) {
				hp.put("MobileNo", bean.getAirtelMoneyNumber());
			}
			hp.put("InstallmentYN", "N");
			hp.put("ChequeNo", bean.getChequeNo());
			hp.put("PaymentType", bean.getModeOfPayment());
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				json1 = (JSONObject) json.get("Response");
				bean.setErrors((JSONArray) json.get("Errors"));
		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json1;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getVehicleTypeDetails(String make, String model, String branchCode,
			String applicationNo, String vehicleId) {
		
		JSONObject hp = new JSONObject();
		JSONObject json = null;
		JSONArray array = new JSONArray();
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getVehicleTypeDetails");
			authorization= getValueFromWebservice("marine.insurance.auth");
			
			hp.put("ApplicationNo", applicationNo);
			hp.put("VehicleId", vehicleId);
			hp.put("MakeId", make);
			hp.put("ModelId", model);
			hp.put("BranchCode", branchCode);
			
			String responseStr=callAPIPost(link, authorization, hp.toString().replaceAll("\"\"", "null"));
			if(responseStr!=null && responseStr.length()>0) {
				json = (JSONObject) parser.parse(responseStr);
				
				if("FAILED".equals(json.get("Message"))) {
					array = new JSONArray();
				}else {
				array = (JSONArray) json.get("Response");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getBankInfoAjax(String modeOfPay, String currencyType) {
		JSONObject hp = new JSONObject();
		JSONObject json,json1 = null;
		JSONParser parser = new JSONParser();
		
		try {
			String link=getValueFromWebservice("maan.client.Motor.getBankInfoAjax");
			authorization= getValueFromWebservice("marine.insurance.auth");
			link= getActualLink(link, hp);
			
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
