package com.maan.payment.mtn.config;

import java.util.ResourceBundle;

import sun.misc.BASE64Encoder;

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
	
	private static String apiUserId;
	private static String apiPrimaryKey;
	private static String apiBasicAuthentication;
	private static String apiSubscriptionKey;
	private static String apiTargetEnvironment;
	
	private static String apiAccessTokenUrl;
	private static String apiUuidV4Url;
	private static String apiRequestToPayUrl;
	
	static{
		try{
			setApiUserId(getValue("user.id"));
			setApiPrimaryKey(getValue("primary.key"));
			setApiSubscriptionKey(getValue("subscription.key"));
			setApiBasicAuthentication("Basic "+getEncode64Value(getApiUserId(),getApiPrimaryKey()));
			setApiTargetEnvironment(getValue("target.environment"));
			
			setApiAccessTokenUrl(getValue("access.token.url"));
			setApiUuidV4Url(getValue("uuid.version4.url"));
			setApiRequestToPayUrl(getValue("request.to.pay.url"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static String getValue(String str) {
		try {
			return ResourceBundle.getBundle("mtn_payment").getString(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String getEncode64Value(String str1,String str2) {
		String encodedStr = "";
		try{
//			String authString = str1+":"+str2;
//	    	final byte[] authBytes = authString.getBytes();
//	    	encodedStr = new BASE64Encoder().encode(authBytes).toString();
//	    	//encodedStr = Base64.getEncoder().encodeToString(authBytes);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "NWJiMzAyOTktNDQ1My00M2U2LWEwYWUtNjBlNDEyMDA0NGJiOjViMDUzNWUwNGM0MTRkMDE4NTBjZDQ1MjFlZGVlYWJj";
	}

	/**
	 * @return the apiUserId
	 */
	public static String getApiUserId() {
		return apiUserId;
	}
	/**
	 * @param apiUserId the apiUserId to set
	 */
	public static void setApiUserId(String apiUserId) {
		Config.apiUserId = apiUserId;
	}
	/**
	 * @return the apiPrimaryKey
	 */
	public static String getApiPrimaryKey() {
		return apiPrimaryKey;
	}
	/**
	 * @param apiPrimaryKey the apiPrimaryKey to set
	 */
	public static void setApiPrimaryKey(String apiPrimaryKey) {
		Config.apiPrimaryKey = apiPrimaryKey;
	}
	/**
	 * @return the apiSubscriptionKey
	 */
	public static String getApiSubscriptionKey() {
		return apiSubscriptionKey;
	}
	/**
	 * @param apiSubscriptionKey the apiSubscriptionKey to set
	 */
	public static void setApiSubscriptionKey(String apiSubscriptionKey) {
		Config.apiSubscriptionKey = apiSubscriptionKey;
	}

	/**
	 * @return the apiBasicAuthentication
	 */
	public static String getApiBasicAuthentication() {
		return apiBasicAuthentication;
	}

	/**
	 * @param apiBasicAuthentication the apiBasicAuthentication to set
	 */
	public static void setApiBasicAuthentication(String apiBasicAuthentication) {
		Config.apiBasicAuthentication = apiBasicAuthentication;
	}

	/**
	 * @return the apiAccessTokenUrl
	 */
	public static String getApiAccessTokenUrl() {
		return apiAccessTokenUrl;
	}

	/**
	 * @param apiAccessTokenUrl the apiAccessTokenUrl to set
	 */
	public static void setApiAccessTokenUrl(String apiAccessTokenUrl) {
		Config.apiAccessTokenUrl = apiAccessTokenUrl;
	}

	/**
	 * @param apiUuidV4Url the apiUuidV4Url to set
	 */
	public static void setApiUuidV4Url(String apiUuidV4Url) {
		Config.apiUuidV4Url = apiUuidV4Url;
	}

	/**
	 * @return the apiUuidV4Url
	 */
	public static String getApiUuidV4Url() {
		return apiUuidV4Url;
	}

	/**
	 * @return the apiTargetEnvironment
	 */
	public static String getApiTargetEnvironment() {
		return apiTargetEnvironment;
	}

	/**
	 * @param apiTargetEnvironment the apiTargetEnvironment to set
	 */
	public static void setApiTargetEnvironment(String apiTargetEnvironment) {
		Config.apiTargetEnvironment = apiTargetEnvironment;
	}

	/**
	 * @return the apiRequestToPayUrl
	 */
	public static String getApiRequestToPayUrl() {
		return apiRequestToPayUrl;
	}

	/**
	 * @param apiRequestToPayUrl the apiRequestToPayUrl to set
	 */
	public static void setApiRequestToPayUrl(String apiRequestToPayUrl) {
		Config.apiRequestToPayUrl = apiRequestToPayUrl;
	}
	
}
