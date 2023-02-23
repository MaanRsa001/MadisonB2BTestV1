package com.maan.notification.fcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.maan.common.LogManager;
import com.maan.common.service.RequestResponse;
import com.maan.notification.fcm.config.Config;
import com.maan.notification.fcm.model.Notification;
import com.maan.notification.fcm.model.Results;
import com.maan.notification.fcm.model.SendNotificationIpModel;
import com.maan.notification.fcm.model.SendNotificationOpModel;

public class NotificationService {
	NotificationDao dao = new NotificationDao();
	Gson jsonConvertor = new Gson();
	
	private String loginId;
	private String userType;
	private String token;
	
	private String notificationKey;
	private String quoteNo;
	private String policyNo;
	
	public NotificationService(String loginId, String userType, String token){
		setLoginId(loginId);
		setUserType(userType);
		setToken(token);
	}
	
	public NotificationService(String notificationKey, String quoteNo,
			String policyNo, String loginId, String userType){
		setNotificationKey(notificationKey);
		setQuoteNo(quoteNo);
		setPolicyNo(policyNo);
		setLoginId(loginId);
		setUserType(userType);
	}
	
	public void send(){
		try{
			if(StringUtils.isNotBlank(notificationKey)){
				if("referral".equalsIgnoreCase(notificationKey) && StringUtils.isNotBlank(quoteNo)){
					List<Map<String, Object>> listToSend = dao.commonList("AdminList", "admin");
					if(listToSend!=null && listToSend.size()>0){
						for(int i=0;i<listToSend.size();i++){
							Map<String, Object> mapToSend = listToSend.get(i);
							String loginIdToSend = mapToSend.get("LOGIN_ID")==null?"":mapToSend.get("LOGIN_ID").toString();
							String userTypeToSend = mapToSend.get("USER_TYPE")==null?"":mapToSend.get("USER_TYPE").toString();
							String multicast_id = "",success = "",failure = "",canonical_ids = "",messageId = "",error = "";
							if(StringUtils.isNotBlank(loginIdToSend)){
								String tokenToSend = dao.lastLoginToken(loginIdToSend);
								String notificationTitle = Config.getReferralNotificationTitle();
								String notificationBody = StringUtils.isBlank(Config.getReferralNotificationBody())
										?"":(Config.getReferralNotificationBody()
										.replace("{LOGIN_ID}", loginIdToSend)
										.replace("{QUOTE_NO}", quoteNo));
								if(StringUtils.isNotBlank(notificationTitle) && StringUtils.isNotBlank(notificationBody)){
									if(StringUtils.isNotBlank(tokenToSend)){
										SendNotificationOpModel snom = pushNotification(tokenToSend,
												notificationTitle, notificationBody);
										if(snom!=null){
											multicast_id = snom.getMulticast_id()==null?"":snom.getMulticast_id();
											success = snom.getSuccess()==null?"":snom.getSuccess();
											failure = snom.getFailure()==null?"":snom.getFailure();
											canonical_ids = snom.getCanonical_ids()==null?"":snom.getCanonical_ids();
											List<Results> results = snom.getResults();
											if(results!=null && results.size()>0){
												messageId = results.get(0).getMessage_id()==null?"":results.get(0).getMessage_id();
												error = results.get(0).getError()==null?"":results.get(0).getError();
											}
										}
									}
									dao.saveNotificationMsg(loginId, userType, loginIdToSend, userTypeToSend,
											quoteNo, policyNo, tokenToSend, notificationTitle, notificationBody, multicast_id,
											success, failure, canonical_ids, messageId, error);
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ NotificationService.send(): "+e);
			e.printStackTrace();
		}
	}
	
	private SendNotificationOpModel pushNotification(String token, String title, String body){
		try{
			if(StringUtils.isNotBlank(token) && StringUtils.isNotBlank(title) && StringUtils.isNotBlank(body)){
				
				SendNotificationIpModel snim = new SendNotificationIpModel();
				snim.setTo(token);
				Notification notification = new Notification();
				notification.setTitle(title);
				notification.setBody(body);
				notification.setSound("default");
				notification.setIcon(Config.getApiNotificationSendIcon());
				notification.setPriority("high");
				notification.setContent_available(true);
				snim.setNotification(notification);
				
				String requestString = jsonConvertor.toJson(snim);
				
				Map<String, Object> requestHeaderMap = new HashMap<String, Object>();
				requestHeaderMap.put("Authorization", Config.getApiNotificationSendAuthorizationKey());
				requestHeaderMap.put("Content-Type", "application/json");
				
				RequestResponse rr = new RequestResponse();
				Map<String, Object> responseMap = rr.sendRequest(notificationKey+"~"+quoteNo+"~"+policyNo,
						"notificationKey", Config.getApiNotificationSendUrl(), "POST", requestHeaderMap, requestString);
				
				if(responseMap != null && responseMap.size()>0){
					SendNotificationOpModel snom = jsonConvertor.fromJson((responseMap.get("RESPONSE_STRING")==null
							?"":responseMap.get("RESPONSE_STRING").toString()), SendNotificationOpModel.class);
					return snom;
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ NotificationService.pushNotification(): "+e);
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean saveToken(){
		try{
			if(StringUtils.isNotBlank(loginId)
					&& StringUtils.isNotBlank(userType)
					&& StringUtils.isNotBlank(token)){
				if(!dao.isTokenAvail(loginId, userType, token)){
					return dao.saveTokenValues(loginId, userType, token);
				}
			}
		}catch(Exception e){
			LogManager.info("Exception @ NotificationService.saveToken(): "+e);
			e.printStackTrace();
		}
		return false;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNotificationKey() {
		return notificationKey;
	}

	public void setNotificationKey(String notificationKey) {
		this.notificationKey = notificationKey;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public List<Map<String, Object>> notificationList() {
		return dao.notificationList(loginId,userType,token);
	}

}
