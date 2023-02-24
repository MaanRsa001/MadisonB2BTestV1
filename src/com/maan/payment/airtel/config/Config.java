package com.maan.payment.airtel.config;

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
	
	private static String clientId;
	private static String clientSecret;
	private static String grantType;
	
	private static String country;
	private static String currency;
	
	private static String paymentAccessTokenUrl;
	private static String txnEnqAccessTokenUrl;
	private static String balanceEnqAccessTokenUrl;
	private static String userEnqAccessTokenUrl;
	
	private static String paymentUrl;
	private static String txnEnqUrl;
	private static String balanceEnqUrl;
	private static String userEnqUrl;
	
	static{
		try{
			setClientId(getValue("airtel.clientid"));
			setClientSecret(getValue("airtel.clientsecret"));
			setGrantType(getValue("airtel.granttype"));
			
			setCountry(getValue("airtel.country"));
			setCurrency(getValue("airtel.currency"));
			
			setPaymentAccessTokenUrl(getValue("airtel.pay.accesstoken.url"));
			setTxnEnqAccessTokenUrl(getValue("airtel.txn.accesstoken.url"));
			setBalanceEnqAccessTokenUrl(getValue("airtel.bal.accesstoken.url"));
			setUserEnqAccessTokenUrl(getValue("airtel.usr.accesstoken.url"));
			
			setPaymentUrl(getValue("airtel.payment.url"));
			setTxnEnqUrl(getValue("airtel.txn.enq.url"));
			setBalanceEnqUrl(getValue("airtel.bal.enq.url"));
			setUserEnqUrl(getValue("airtel.usr.enq.url"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static String getValue(String str) {
		try {
			return ResourceBundle.getBundle("airtel_payment").getString(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	public static String getClientId() {
		return clientId;
	}

	public static void setClientId(String clientId) {
		Config.clientId = clientId;
	}

	public static String getClientSecret() {
		return clientSecret;
	}

	public static void setClientSecret(String clientSecret) {
		Config.clientSecret = clientSecret;
	}

	public static String getGrantType() {
		return grantType;
	}

	public static void setGrantType(String grantType) {
		Config.grantType = grantType;
	}

	public static String getPaymentAccessTokenUrl() {
		return paymentAccessTokenUrl;
	}

	public static void setPaymentAccessTokenUrl(String paymentAccessTokenUrl) {
		Config.paymentAccessTokenUrl = paymentAccessTokenUrl;
	}

	public static String getTxnEnqAccessTokenUrl() {
		return txnEnqAccessTokenUrl;
	}

	public static void setTxnEnqAccessTokenUrl(String txnEnqAccessTokenUrl) {
		Config.txnEnqAccessTokenUrl = txnEnqAccessTokenUrl;
	}

	public static String getBalanceEnqAccessTokenUrl() {
		return balanceEnqAccessTokenUrl;
	}

	public static void setBalanceEnqAccessTokenUrl(String balanceEnqAccessTokenUrl) {
		Config.balanceEnqAccessTokenUrl = balanceEnqAccessTokenUrl;
	}

	public static String getUserEnqAccessTokenUrl() {
		return userEnqAccessTokenUrl;
	}

	public static void setUserEnqAccessTokenUrl(String userEnqAccessTokenUrl) {
		Config.userEnqAccessTokenUrl = userEnqAccessTokenUrl;
	}

	public static String getPaymentUrl() {
		return paymentUrl;
	}

	public static void setPaymentUrl(String paymentUrl) {
		Config.paymentUrl = paymentUrl;
	}

	public static String getTxnEnqUrl() {
		return txnEnqUrl;
	}

	public static void setTxnEnqUrl(String txnEnqUrl) {
		Config.txnEnqUrl = txnEnqUrl;
	}

	public static String getBalanceEnqUrl() {
		return balanceEnqUrl;
	}

	public static void setBalanceEnqUrl(String balanceEnqUrl) {
		Config.balanceEnqUrl = balanceEnqUrl;
	}

	public static String getUserEnqUrl() {
		return userEnqUrl;
	}

	public static void setUserEnqUrl(String userEnqUrl) {
		Config.userEnqUrl = userEnqUrl;
	}

	public static String getCountry() {
		return country;
	}

	public static void setCountry(String country) {
		Config.country = country;
	}

	public static String getCurrency() {
		return currency;
	}

	public static void setCurrency(String currency) {
		Config.currency = currency;
	}
	
	
	
}
