package com.maan.common.service;

import java.io.BufferedReader;
//import java.io.DataOutputStream;
import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/*import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
*/
import com.maan.common.LogManager;
import com.maan.common.dao.CommonDAO;

public class RequestResponse {
	
	/*public Map<String, Object> sendRequest(String transactionId, String apiDescription, String requestURL,
			String requestMethod, Map<String, Object> requestHeaders,
			String requestString){
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String errorString = "";
		int httpStatus = 0;
		String reqHeaders = "";
		String resString = "";
		try {
				// URL THAT WILL BE CALLED TO SUBMIT THE MESSAGE
				LogManager.info("TranID : "+transactionId+" Url: "+requestURL);
				URL sendUrl = new URL(requestURL);
				HttpURLConnection httpConnection = (HttpURLConnection) sendUrl
				.openConnection();
				
				// THIS METHOD SETS THE METHOD TYPE TO POST SO THAT.
				// WILL BE SEND AS A POST REQUEST.
				LogManager.info("TranID : "+transactionId+" Method: "+requestMethod);
				httpConnection.setRequestMethod(requestMethod);
				if(requestHeaders!=null && requestHeaders.size()>0){
					List<String> reqHeadersList = new ArrayList<String>();
					for(Map.Entry<String, Object> map: requestHeaders.entrySet()){
						String headers = map.getKey()+" : "+map.getValue().toString();
						LogManager.info("TranID : "+transactionId+" Headers: "+headers);
						httpConnection.setRequestProperty(map.getKey(), map.getValue().toString());
						reqHeadersList.add(headers);
					}
					reqHeaders = StringUtils.join(reqHeadersList,",");
				}
				
				// THIS METHOD IS SET AS TRUE WINCE WE INTEND TO SEND.
				// INPUT TO THE SERVER.
				httpConnection.setDoInput(true);
				
				// THIS METHOD IMPLIES THAT WE INTEND TO RECEIVE DATA FROM SERVER.
				httpConnection.setDoOutput(true);
				
				// IMPLIES DO NOT USE CACHED DATA.
				httpConnection.setUseCaches(false);
				
				// DATA THAT WILL BE SENT OVER THE STREAM TO THE SERVER.
				if(StringUtils.isNotBlank(requestString)){
					DataOutputStream dataStreamToServer = new DataOutputStream(
					httpConnection.getOutputStream());
					dataStreamToServer.writeBytes(requestString);
					dataStreamToServer.flush();
					dataStreamToServer.close();
				}
				
				// GETTING HTTP STATUS CODE
				httpStatus = httpConnection.getResponseCode();
				LogManager.info("TranID : "+transactionId+" HTTP Response Status Code : "+httpStatus);
				responseMap.put("RESPONSE_STATUS", httpStatus);
				
				// HERE TAKE THE OUTPUT VALUE OF THE SERVER.
				BufferedReader dataStreamFromUrl = new BufferedReader(
				new InputStreamReader(httpConnection.getInputStream()));
				StringBuilder responseStrBuilder = new StringBuilder();
				String dataBuffer = "";
				
				// WRITING INFORMATION FROM THE STREAM TO THE BUFFER.
				while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
					responseStrBuilder.append(dataBuffer) ;
				}
				dataStreamFromUrl.close();
				resString = responseStrBuilder.toString();
				
				LogManager.info("TranID : "+transactionId+" Response String : " + resString);
				
				// PUTTING RESPONSE JSON TO RESPONSE MAP
				responseMap.put("RESPONSE_STRING", resString);
				
			}catch(Exception ex) {
				ex.printStackTrace();
				LogManager.info("TranID : "+transactionId+" JSON Request and Response Exception : " +ex);
				errorString = ex.getMessage().length()<500?ex.getMessage():ex.getMessage().substring(0, 500);
				responseMap.put("ERROR", errorString);
				responseMap.put("RESPONSE_STATUS", 0);
				responseMap.put("RESPONSE_STRING", "");
			}finally{
				try{
					CommonDAO cd = new CommonDAO();
					cd.saveApiResponse(transactionId, apiDescription, requestMethod, requestURL,
							reqHeaders, requestString, httpStatus, resString, errorString);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return responseMap;
		
	}*/
	
	public Map<String, Object> sendRequest(String transactionId, String apiDescription, String requestURL,
			String requestMethod, Map<String, Object> requestHeaders,
			String requestString){
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String errorString = "";
		int httpStatus = 0;
		String reqHeaders = "";
		String resString = "";
		StringEntity entity = null;
		HttpGet httpGet = null;
		HttpPost httpPost = null;
		CloseableHttpResponse response = null;
		try {
				LogManager.info("TranID : "+transactionId+" Url: "+requestURL);
				if(StringUtils.isNotBlank(requestString)){
					entity = new StringEntity(requestString,"UTF-8");
				}
				CloseableHttpClient httpclient = HttpClients.createDefault();
				LogManager.info("TranID : "+transactionId+" Method: "+requestMethod);
				if("get".equalsIgnoreCase(requestMethod)){
					httpGet = new HttpGet(requestURL);
					if(requestHeaders!=null && requestHeaders.size()>0){
						List<String> reqHeadersList = new ArrayList<String>();
						for(Map.Entry<String, Object> map: requestHeaders.entrySet()){
							String headers = map.getKey()+" : "+map.getValue().toString();
							LogManager.info("TranID : "+transactionId+" Headers: "+headers);
							httpGet.setHeader(map.getKey(), map.getValue().toString());
							reqHeadersList.add(headers);
						}
						reqHeaders = StringUtils.join(reqHeadersList,",");
					}
					response = httpclient.execute(httpGet);
				}else{
					httpPost = new HttpPost(requestURL);
					if(requestHeaders!=null && requestHeaders.size()>0){
						List<String> reqHeadersList = new ArrayList<String>();
						for(Map.Entry<String, Object> map: requestHeaders.entrySet()){
							String headers = map.getKey()+" : "+map.getValue().toString();
							LogManager.info("TranID : "+transactionId+" Headers: "+headers);
							httpPost.setHeader(map.getKey(), map.getValue().toString());
							reqHeadersList.add(headers);
						}
						reqHeaders = StringUtils.join(reqHeadersList,",");
					}
					if(entity!=null){
						httpPost.setEntity(entity);
					}
					response = httpclient.execute(httpPost);
				}
				
				// GETTING HTTP STATUS CODE
				httpStatus = response.getStatusLine().getStatusCode();
				LogManager.info("TranID : "+transactionId+" HTTP Response Status Code : "+httpStatus);
				responseMap.put("RESPONSE_STATUS", httpStatus);
				
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				StringBuffer responseAsString = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					responseAsString.append(line);
				}
				resString = responseAsString.toString();
				
				LogManager.info("TranID : "+transactionId+" Response String : " + resString);
				
				// PUTTING RESPONSE JSON TO RESPONSE MAP
				responseMap.put("RESPONSE_STRING", resString);
				
			}catch(Exception ex) {
				ex.printStackTrace();
				LogManager.info("TranID : "+transactionId+" JSON Request and Response Exception : " +ex);
				errorString = ex.getMessage().length()<500?ex.getMessage():ex.getMessage().substring(0, 500);
				responseMap.put("ERROR", errorString);
				responseMap.put("RESPONSE_STATUS", 0);
				responseMap.put("RESPONSE_STRING", "");
			}finally{
				try{
					CommonDAO cd = new CommonDAO();
					cd.saveApiResponse(transactionId, apiDescription, requestMethod, requestURL,
							reqHeaders, requestString, httpStatus, resString, errorString);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return responseMap;
		
	}
	

	
	/*private CloseableHttpClient createClient () {
	    try {
	        HttpClientBuilder builder = HttpClientBuilder.create();
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        
	     // CREATE A TRUST MANAGER THAT DOES NOT VALIDATE CERTIFICATE CHAINS
			TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}
			
						public void checkClientTrusted(X509Certificate[] certs, String authType) {
							// Trust always
						}
			
						public void checkServerTrusted(X509Certificate[] certs, String authType) {
							// Trust always
						}
					}
			};
	        ctx.init(null,trustAllCerts , null);
	        SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(ctx,new String[] { "TLSv1.2" },null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        builder.setSSLSocketFactory(scsf);
	        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
	                .register("https", scsf)
	                .build();

	        HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(registry);

	        builder.setConnectionManager(ccm);
	        return builder.build();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}*/

}
