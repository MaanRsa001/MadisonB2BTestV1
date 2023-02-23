package com.maan.payment.airtel;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.maan.common.LogManager;
import com.maan.common.dao.CommonDAO;
import com.maan.common.service.RequestResponse;
import com.maan.payment.airtel.config.Config;
import com.maan.payment.airtel.model.Data;
import com.maan.payment.airtel.model.Registration;
import com.maan.payment.airtel.model.ReqToPayIpModelAirtel;
import com.maan.payment.airtel.model.ResOpModel;
import com.maan.payment.airtel.model.Status;
import com.maan.payment.airtel.model.Subscriber;
import com.maan.payment.airtel.model.TokenIpModel;
import com.maan.payment.airtel.model.TokenOpModel;
import com.maan.payment.airtel.model.Transaction;
import com.maan.payment.airtel.model.TransactionData;
import com.maan.payment.airtel.model.UserEnqData;
import com.maan.payment.airtel.model.UserEnqOpModel;

public class AirtelService {
	AirtelDao dao = new AirtelDao();
	Gson jsonConvertor = new Gson();
	
	private String quoteNo;
	private String referenceNo;
	private String productId;
	private String merchantReferenceNo;
	
	public AirtelService(){
	}
	
	public AirtelService(String quoteNo, String referenceNo, String merchantReferenceNo, String productId){
		setQuoteNo(quoteNo);
		setReferenceNo(referenceNo);
		setProductId(productId);
		setMerchantReferenceNo(merchantReferenceNo);
	}
	
	private String getAccessToken(String tokenType,String val){
		String token = "";
		try{
			token = dao.getJwtToken(tokenType);
			if(StringUtils.isBlank(token)){
				token = generateToken(tokenType,val);
			}
			if(StringUtils.isNotBlank(token)){
				token = "Bearer "+token;
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelService.getAccessToken(): "+e);
			e.printStackTrace();
		}
		return token;
	}
	
	private String generateToken(String type,String val) {
		try{
			Map<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put("Content-Type", "application/json");
			String tokerUrl="";
			if("AIRTEL_PAY_TOKEN".equalsIgnoreCase(type))
				tokerUrl=Config.getPaymentAccessTokenUrl();
			else if("AIRTEL_TXN_TOKEN".equalsIgnoreCase(type))
				tokerUrl=Config.getTxnEnqAccessTokenUrl();
			else if("AIRTEL_USERENQ_TOKEN".equalsIgnoreCase(type))
				tokerUrl=Config.getUserEnqAccessTokenUrl()+"/"+val+"/oauth2/token";
			
			TokenIpModel ip=new TokenIpModel();
			ip.setClientId(Config.getClientId());
			ip.setClientSecret(Config.getClientSecret());
			ip.setGrantType(Config.getGrantType());
			
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "AIRTEL_ACCESS_TOKEN", tokerUrl,
					"POST", requestMap, jsonConvertor.toJson(ip));
			if(responseMap!=null && responseMap.size()>0){
				if("200".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					TokenOpModel tom = new TokenOpModel();
					tom = jsonConvertor.fromJson(responseMap.get("RESPONSE_STRING")==null?"":
						responseMap.get("RESPONSE_STRING").toString(), TokenOpModel.class);
					if(StringUtils.isNotBlank(tom.getAccessToken())){
						CommonDAO cd = new CommonDAO();
						cd.saveJwtToken(type, tom.getTokenType(), tom.getAccessToken(), tom.getExpiresIn());
						return tom.getAccessToken();
					}
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelService.generateToken(): "+e);
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean requestToPay(ReqToPayIpModelAirtel rtp){
		try{
			Map<String, Object> headersMap = new HashMap<String, Object>();
			headersMap.put("Authorization", getAccessToken("AIRTEL_PAY_TOKEN",""));
			headersMap.put("Content-Type", "application/json");
			headersMap.put("X-Country", Config.getCountry());
			headersMap.put("X-Currency", Config.getCurrency());
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "AIRTEL_REQUEST_TO_PAY", Config.getPaymentUrl(),
					"POST", headersMap, jsonConvertor.toJson(rtp));
			if(responseMap!=null && responseMap.size()>0){
				if("200".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					ResOpModel rop = new ResOpModel();
					rop = jsonConvertor.fromJson(responseMap.get("RESPONSE_STRING")==null?"":
						responseMap.get("RESPONSE_STRING").toString(), ResOpModel.class);
					
					Data data=rop.getData();
					TransactionData txnData=new TransactionData();
					if(data!=null){
						txnData=data.getTransaction();
					}
					Status status=rop.getStatus();
					boolean sta=status.isSuccess();
					
						return sta;
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelService.requestToPay(): "+e);
			e.printStackTrace();
		}
		return false;
	}
	
	public String paymentStatus(ReqToPayIpModelAirtel rtp){
		try{
			Map<String, Object> headersMap = new HashMap<String, Object>();
			headersMap.put("Authorization", getAccessToken("AIRTEL_TXN_TOKEN",""));
			headersMap.put("X-Country", Config.getCountry());
			headersMap.put("X-Currency", Config.getCurrency());
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "AIRTEL_PAYMENT_STATUS",
					Config.getTxnEnqUrl()+"/"+merchantReferenceNo,"GET", headersMap, "");
			if(responseMap!=null && responseMap.size()>0){
				if("200".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					ResOpModel rop = new ResOpModel();
					rop = jsonConvertor.fromJson(responseMap.get("RESPONSE_STRING")==null?"":
						responseMap.get("RESPONSE_STRING").toString(), ResOpModel.class);
					if(rtp!=null){
						
						String country="";
						String currency="";
						int msisdn=0;
						
						String amount="";
						String countryTxn="";
						String currencyTxn="";
						String id="";
						
						Subscriber sub = rtp.getSubscriber();
						if(sub!=null){
							country=sub.getCountry();
							currency=sub.getCurrency();
							msisdn=sub.getMsisdn();
						}
						
						Transaction txn = rtp.getTransaction();
						if(txn!=null){
							amount = txn.getAmount();
							countryTxn=txn.getCountry();
							currencyTxn=txn.getCurrency();
							id = txn.getId();
						}
						
						Data data=rop.getData();
						TransactionData txnData=new TransactionData();
						if(data!=null){
							txnData=data.getTransaction();
						}
						Status status=rop.getStatus();
						
						dao.savePaymentDetail((StringUtils.isBlank(quoteNo)?"0":quoteNo),
								(StringUtils.isBlank(productId)?"0":productId),
								"102",(StringUtils.isBlank(merchantReferenceNo)?"":merchantReferenceNo),
								rtp.getReference(),country,currency,msisdn,amount,countryTxn,currencyTxn,id,
								txnData.getId(),txnData.getAirtelmoneyid(),txnData.getStatus(),txnData.getMessage(),
								status.getMessage(),status.getCode(),status.getResultcode(),status.isSuccess()
								);
						if(StringUtils.isNotBlank(txnData.getStatus())){
							return txnData.getStatus();
						}
					}
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelService.paymentStatus(): "+e);
			e.printStackTrace();
		}
		return "error";
	}
	
	public boolean userEnquiry(String airtelMoneyNum){
		try{
			Map<String, Object> headersMap = new HashMap<String, Object>();
			headersMap.put("Authorization", getAccessToken("AIRTEL_USERENQ_TOKEN",airtelMoneyNum));
			headersMap.put("Content-Type", "application/json");
			headersMap.put("X-Country", Config.getCountry());
			headersMap.put("X-Currency", Config.getCurrency());
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "AIRTEL_USER_ENQUIRY", Config.getUserEnqUrl()+"/"+airtelMoneyNum,
					"GET", headersMap, "");
			if(responseMap!=null && responseMap.size()>0){
				if("200".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					UserEnqOpModel uop = new UserEnqOpModel();
					uop = jsonConvertor.fromJson(responseMap.get("RESPONSE_STRING")==null?"":
						responseMap.get("RESPONSE_STRING").toString(), UserEnqOpModel.class);
					
					UserEnqData data=uop.getData();
					Registration reg= new Registration();
					if(data!=null){
						reg=data.getRegistration();
					}
					Status status=uop.getStatus();
					boolean userStat=status.isSuccess();
					//if("SUCCESS".equalsIgnoreCase(status.getMessage()) || "successful".equalsIgnoreCase(status.getMessage()))
						return userStat;
					
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ AirtelService.userEnquiry(): "+e);
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * @return the quoteNo
	 */
	public String getQuoteNo() {
		return quoteNo;
	}
	
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	
	/**
	 * @return the referenceNo
	 */
	public String getReferenceNo() {
		return referenceNo;
	}
	
	/**
	 * @param referenceNo the referenceNo to set
	 */
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	
	/**
	 * @return the merchantReferenceNo
	 */
	public String getMerchantReferenceNo() {
		return merchantReferenceNo;
	}
	
	/**
	 * @param merchantReferenceNo the merchantReferenceNo to set
	 */
	public void setMerchantReferenceNo(String merchantReferenceNo) {
		this.merchantReferenceNo = merchantReferenceNo;
	}

	public Map<String, Object> getPaymentDetails() {
		return dao.getPaymentDetails(getMerchantReferenceNo());
	}

	public Map<String, Object> getPaymentResult() {
		return dao.getPaymentResult(getMerchantReferenceNo());
	}
}