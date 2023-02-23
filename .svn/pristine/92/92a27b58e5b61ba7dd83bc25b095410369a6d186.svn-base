package com.maan.payment.mtn;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.maan.common.LogManager;
import com.maan.common.dao.CommonDAO;
import com.maan.common.service.RequestResponse;
import com.maan.payment.mtn.config.Config;
import com.maan.payment.mtn.model.Payer;
import com.maan.payment.mtn.model.ReqToPayIpModel;
import com.maan.payment.mtn.model.TokenOpModel;

public class MtnService {
	MtnDao dao = new MtnDao();
	Gson jsonConvertor = new Gson();
	
	private String quoteNo;
	private String referenceNo;
	private String productId;
	private String merchantReferenceNo;
	
	public MtnService(){
	}
	
	public MtnService(String quoteNo, String referenceNo, String merchantReferenceNo, String productId){
		setQuoteNo(quoteNo);
		setReferenceNo(referenceNo);
		setProductId(productId);
		setMerchantReferenceNo(merchantReferenceNo);
	}
	
	private String getAccessToken(){
		String token = "";
		try{
			token = dao.getJwtToken();
			if(StringUtils.isBlank(token)){
				token = generateToken();
			}
			if(StringUtils.isNotBlank(token)){
				token = "Bearer "+token;
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnService.getAccessToken(): "+e);
			e.printStackTrace();
		}
		return token;
	}

	private String generateToken() {
		try{
			Map<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put("Authorization", Config.getApiBasicAuthentication());
			requestMap.put("Ocp-Apim-Subscription-Key", Config.getApiSubscriptionKey());
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "MTN_ACCESS_TOKEN", Config.getApiAccessTokenUrl(),
					"POST", requestMap, "");
			if(responseMap!=null && responseMap.size()>0){
				if("200".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					TokenOpModel tom = new TokenOpModel();
					tom = jsonConvertor.fromJson(responseMap.get("RESPONSE_STRING")==null?"":
						responseMap.get("RESPONSE_STRING").toString(), TokenOpModel.class);
					if(StringUtils.isNotBlank(tom.getAccessToken())){
						CommonDAO cd = new CommonDAO();
						cd.saveJwtToken("MTN_PAYMENT", tom.getTokenType(), tom.getAccessToken(), tom.getExpiresIn());
						return tom.getAccessToken();
					}
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnService.generateToken(): "+e);
			e.printStackTrace();
		}
		return "";
	}
	
	public String getUuidNo(){
		String refNo = "";
		try{
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "UUID_VERSION4", Config.getApiUuidV4Url(),
					"GET", null, "");
			if(responseMap!=null && responseMap.size()>0){
				if("200".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					refNo = responseMap.get("RESPONSE_STRING")==null?"":
						responseMap.get("RESPONSE_STRING").toString();
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnService.getReferenceNo(): "+e);
			e.printStackTrace();
		}
		return refNo;
	}
	
	public boolean requestToPay(ReqToPayIpModel rtp){
		try{
			Map<String, Object> headersMap = new HashMap<String, Object>();
			headersMap.put("Authorization", getAccessToken());
			headersMap.put("X-Reference-Id", referenceNo);
			headersMap.put("X-Target-Environment", Config.getApiTargetEnvironment());
			headersMap.put("Ocp-Apim-Subscription-Key", Config.getApiSubscriptionKey());
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "MTN_REQUEST_TO_PAY", Config.getApiRequestToPayUrl(),
					"POST", headersMap, jsonConvertor.toJson(rtp));
			if(responseMap!=null && responseMap.size()>0){
				if("202".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					return true;
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnService.requestToPay(): "+e);
			e.printStackTrace();
		}
		return false;
	}
	
	public String paymentStatus(){
		try{
			Map<String, Object> headersMap = new HashMap<String, Object>();
			headersMap.put("Authorization", getAccessToken());
			headersMap.put("X-Target-Environment", Config.getApiTargetEnvironment());
			headersMap.put("Ocp-Apim-Subscription-Key", Config.getApiSubscriptionKey());
			RequestResponse rr = new RequestResponse();
			Map<String, Object> responseMap = rr.sendRequest(merchantReferenceNo, "MTN_PAYMENT_STATUS",
					Config.getApiRequestToPayUrl()+"/"+referenceNo,"GET", headersMap, "");
			if(responseMap!=null && responseMap.size()>0){
				if("200".equalsIgnoreCase(responseMap.get("RESPONSE_STATUS")==null?"":responseMap.get("RESPONSE_STATUS").toString())){
					ReqToPayIpModel rtp = new ReqToPayIpModel();
					rtp = jsonConvertor.fromJson(responseMap.get("RESPONSE_STRING")==null?"":
						responseMap.get("RESPONSE_STRING").toString(), ReqToPayIpModel.class);
					if(rtp!=null){
						String payerId = "";
						String payerIdType = "";
						Payer payer = rtp.getPayer();
						if(payer!=null){
							payerId = payer.getPartyId();
							payerIdType = payer.getPartyIdType();
						}
						dao.savePaymentDetail((StringUtils.isBlank(quoteNo)?"0":quoteNo),
								referenceNo,"101",(StringUtils.isBlank(rtp.getAmount())?"0":rtp.getAmount()),
								rtp.getCurrency(),rtp.getPayerMessage(),rtp.getPayeeNote(),
								rtp.getStatus(),rtp.getReason(),payerId,
								payerIdType,(StringUtils.isBlank(productId)?"0":productId),
								(StringUtils.isBlank(merchantReferenceNo)?"":merchantReferenceNo), rtp.getFinancialTransactionId());
						if(StringUtils.isNotBlank(rtp.getStatus())){
							return rtp.getStatus();
						}
					}
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ MtnService.paymentStatus(): "+e);
			e.printStackTrace();
		}
		return "error";
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
