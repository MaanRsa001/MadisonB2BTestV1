package com.maan.notification.fcm.config;

import java.util.ResourceBundle;

public class Config {
	
	private Config(){
	}
	
	private static Config instance;
	
	public static Config getInstance(){
		if(instance==null){
			instance=new Config();
		}
		return instance;
	}
	
	private static String apiNotificationSendUrl;
	private static String apiNotificationSendAuthorizationKey;
	private static String apiNotificationSendIcon;
	
	private static String referralNotificationTitle;
	private static String referralNotificationBody;
	
	static{
		try{
			setApiNotificationSendUrl(getValue("fcm.notification.send.url"));
			setApiNotificationSendAuthorizationKey(getValue("fcm.authorization.key"));
			setApiNotificationSendIcon(getValue("fcm.notification.send.icon"));
			
			setReferralNotificationTitle(getValue("fcm.referral.notification.title"));
			setReferralNotificationBody(getValue("fcm.referral.notification.body"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static String getValue(String str) {
		try {
			return ResourceBundle.getBundle("fcm_notification").getString(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getApiNotificationSendUrl() {
		return apiNotificationSendUrl;
	}

	public static void setApiNotificationSendUrl(String apiNotificationSendUrl) {
		Config.apiNotificationSendUrl = apiNotificationSendUrl;
	}

	public static String getApiNotificationSendAuthorizationKey() {
		return apiNotificationSendAuthorizationKey;
	}

	public static void setApiNotificationSendAuthorizationKey(String apiNotificationSendAuthorizationKey) {
		Config.apiNotificationSendAuthorizationKey = apiNotificationSendAuthorizationKey;
	}

	public static String getApiNotificationSendIcon() {
		return apiNotificationSendIcon;
	}

	public static void setApiNotificationSendIcon(String apiNotificationSendIcon) {
		Config.apiNotificationSendIcon = apiNotificationSendIcon;
	}

	public static String getReferralNotificationTitle() {
		return referralNotificationTitle;
	}

	public static void setReferralNotificationTitle(String referralNotificationTitle) {
		Config.referralNotificationTitle = referralNotificationTitle;
	}

	public static String getReferralNotificationBody() {
		return referralNotificationBody;
	}

	public static void setReferralNotificationBody(String referralNotificationBody) {
		Config.referralNotificationBody = referralNotificationBody;
	}

}
