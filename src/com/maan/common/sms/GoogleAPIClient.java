package com.maan.common.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;


 

public class GoogleAPIClient {
	protected static String CONFIG_URL;
	protected static String APS_URL;
	protected static String APS_REQUEST;
	protected static String BITY_ACCESSKEY;
	/*public String getShorternURL(String quoteNo) throws Exception {
		try{
			String apsURLAsString = MessageFormat.format(APS_URL,new Object[]{quoteNo});
			String actualRequest ="{\"longUrl\": \""+apsURLAsString+"\"}";
			System.out.println("GoogleAPIClient.apsURLAsString "+ apsURLAsString);
			System.out.println("GoogleAPIClient.actualRequest "+ actualRequest);
			HttpResponse<String> response = Unirest.post(CONFIG_URL)
			.header("content-type", "application/json")
			.body(actualRequest).asString();

			System.out.println("Status "+ response.getStatus());
			if(response.getStatus()<=400){
				String body = response.getBody();
				System.out.println("body "+ response.getStatus());
				Gson gsonObj=new Gson();
				URLPojo obj=(URLPojo)gsonObj.fromJson(body, URLPojo.class);
				return " click here "+obj.getId()+" ";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	 */public String getShorternURL(String quoteNo) throws Exception {
			try{
				String apsURLAsString = MessageFormat.format(APS_URL,new Object[]{quoteNo});
				String actualRequest ="{\"longUrl\": \""+apsURLAsString+"\"}";
				System.out.println("GoogleAPIClient.apsURLAsString "+ apsURLAsString);
				System.out.println("GoogleAPIClient.actualRequest "+ actualRequest);
				
				 final String tinyUrl = "http://tinyurl.com/api-create.php?url=";
				String tinyUrlLookup = tinyUrl + apsURLAsString;
				BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(tinyUrlLookup).openStream()));
				String result=reader.readLine();
				System.out.println("Link is.."+result);
				reader.close();
				return " click here "+result+" ";  
			}catch (Exception e) {
				e.printStackTrace();
			}
			return ""; 
		}
		static{
			ResourceBundle bundle = ResourceBundle.getBundle("com.maan.common.sms.package");
			CONFIG_URL=bundle.getString("google.urlshorten.auth.url");
			APS_URL=bundle.getString("maan.pdfdownload.url");
			APS_REQUEST=bundle.getString("google.urlshoren.request");
			BITY_ACCESSKEY=bundle.getString("bitly.accesskey");
		}
		/*public static void main(String args[]){
			BitlyClient client = new BitlyClient("894d8f31e66e25c0269743e8d1c32ba98513285b");
			Response<ShortenResponse> response = client.shorten().setLongUrl("https://eportal.ggi-sa.com/eportalTest/Loginpage.do").call();
			System.out.println("status_code "+ response.status_code);
			System.out.println("status_txt "+ response.status_txt);
			System.out.println("data "+ response.data);
			if(response.status_code<=400){
				ShortenResponse data = response.data;
				System.out.println("body "+ data.url);
				System.out.println(" click here "+data.url+" ");
			}
		} */
	
}
