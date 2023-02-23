package com.maan.webservice.jax;

import java.io.StringReader;
import java.text.MessageFormat;
//import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;

//import com.maan.common.DBConstants;
import com.maan.common.SimpleLogger;
import com.maan.common.dao.CommonDAO;
import com.maan.payment.PaymentService;
import com.maan.webservice.auth.SoapHeaderIn;
import com.maan.webservice.auth.LoginAuthentication;

public class ZamtelWS {
	PaymentService service=new PaymentService();
	CommonDAO commonDAO = new CommonDAO();
	private final String properties="webservice"; 
	private ResourceBundle bundle=null;
	private String responseStatus="";

	public ZamtelWS(){
	try{
	bundle = ResourceBundle.getBundle(properties);
	}catch (Exception e) {
	e.printStackTrace();
	}	
	}
	private String getXML(String key) {
	String query=bundle.getString(key);
	return query;
	} 
	private String getXMLString(String key, Object... params) {
	return MessageFormat.format(getXML(key),params);
	}
	
	public String QuoteInfo(String quoteNo, final SoapHeaderIn shIn) {
		String returnResult="";
		try{
			SimpleLogger.info("Request::"+quoteNo);
			String reqTime = "";
			String resStatus = "";
			reqTime = commonDAO.getRequestedTime();
			if(shIn != null){
			final LoginAuthentication auth = new LoginAuthentication();
			final boolean authenticated = auth.getAuthenticateLogin(shIn.getUserName(), shIn.getPassword());
			if(authenticated){
				if(StringUtils.isNotEmpty(quoteNo) && quoteNo != null) {
					Map<String,Object> quoteInfo = service.getwsQuoteInfo(quoteNo);
					if(quoteInfo.size()==0) {
						responseStatus="Quote No is Not Found";
						returnResult = getXMLString("RESPONSE_INTG", responseStatus);
					} else {
						
						Object[] params={quoteInfo.get("QUOTE_NO").toString(),
								quoteInfo.get("PREMIUM").toString(),
								quoteInfo.get("BILL_TO_FORENAME"),
								quoteInfo.get("BILL_TO_SURNAME"),
								quoteInfo.get("MOBILE_NO"),
								quoteInfo.get("PRODUCT_NAME")};
						returnResult = getXMLString("QUOTE_INFO_INTG", params);
						/*quoteInfo.get("QUOTE_NO")
						+ DBConstants.DELIM_OUTPUT + quoteInfo.get("PREMIUM")
						+ DBConstants.DELIM_OUTPUT + quoteInfo.get("BILL_TO_FORENAME")
						+ DBConstants.DELIM_OUTPUT + quoteInfo.get("BILL_TO_SURNAME")
						+ DBConstants.DELIM_OUTPUT + quoteInfo.get("MOBILE_NO")
						+ DBConstants.DELIM_OUTPUT + quoteInfo.get("PRODUCT_NAME");*/
						}
						resStatus="Sucessfully";
					} else {
						responseStatus="Quote No Should not be blank";
						returnResult = getXMLString("RESPONSE_INTG", responseStatus);
						resStatus="Error";
					}
				} else {
					responseStatus="Authentification Failed";
					returnResult = getXMLString("RESPONSE_INTG", responseStatus);
					resStatus="Authentification Failed";
					}
			}
			else {
				responseStatus="User name and Pasword Should not be blank";
				returnResult = getXMLString("RESPONSE_INTG", responseStatus);
				resStatus="User name and Pasword Should not be blank";
			}
			SimpleLogger.info("Response==>" + returnResult);
			service.insWSReq(quoteNo,reqTime,resStatus);
		}
			catch(Exception e){
			e.printStackTrace();
		}
		SimpleLogger.info("Result ==> "+returnResult);
		return returnResult;	
	}
	
	//QUOTE_NO~FIRST_NAME~LAST_NAME~PREMIUM~RESPONSE_TRAN_NO~RESPONSE_MESSAGE~DECISION
	public String PaymentConfirmationMsge(String paymentInfo, final SoapHeaderIn shIn) {
		String returnResult="";
		try{
			SimpleLogger.info("Request::"+paymentInfo);
			if(shIn != null){
			final LoginAuthentication auth = new LoginAuthentication();
			final boolean authenticated = auth.getAuthenticateLogin(shIn.getUserName(), shIn.getPassword());
			if(authenticated){
				 final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
				  final DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder(); 
				  InputSource is = new InputSource();
				  is.setCharacterStream(new StringReader(paymentInfo));
				  Document doc = docBuilder.parse(is);
				  NodeList root = doc.getElementsByTagName("root");
				  String result[]=null;
				  for (int i = 0; i < root.getLength(); i++){
					  Element element = (Element) root.item(i);
					  NodeList paymentDetail = element.getElementsByTagName("PaymentDetail");
						  for (int j = 0; j < paymentDetail.getLength(); j++){
							  NodeList QuoteNo = doc.getElementsByTagName("QuoteNo");
							  NodeList FirstName = doc.getElementsByTagName("FirstName");
							  NodeList LastName = doc.getElementsByTagName("LastName");
							  NodeList Premium = doc.getElementsByTagName("Premium");
							  NodeList ResponseTranNo = doc.getElementsByTagName("ResponseTranNo");
							  NodeList ResponseMessage = doc.getElementsByTagName("ResponseMessage");
							  NodeList Decision = doc.getElementsByTagName("Decision");
							  result=new String[]{QuoteNo.item(0).getTextContent(),FirstName.item(0).getTextContent(),
									  LastName.item(0).getTextContent(),Premium.item(0).getTextContent(),ResponseTranNo.item(0).getTextContent(),
									  ResponseMessage.item(0).getTextContent(),Decision.item(0).getTextContent()};
							//String[] result = paymentInfo.split(DBConstants.DELIM);
						  }
				  }
				for(int i = 0;i<result.length;i++){
					if(result[i] == null || result[i].isEmpty()){
						responseStatus="Mandatory Fields should not be Blank";
						returnResult = getXMLString("RESPONSE_INTG", responseStatus);
					}
				}
				if(returnResult.isEmpty()) {
					String quoteNo = result[0];
					Map<String,Object> quoteInfo = service.getwsQuoteInfo(quoteNo);
					double paymentPremium = Double.parseDouble(quoteInfo.get("PREMIUM")==null?"0":quoteInfo.get("PREMIUM").toString());
					if( Double.parseDouble(result[3])>=paymentPremium ) {
						service.updatePaymentWSDetails(result);
						if("ACCEPT".equals(result[6])) {
							commonDAO.updatePolicyStatus(quoteNo,"SP");
							commonDAO.homePolicyGeneration(quoteNo, commonDAO.getHomeProductIdByQuoteNo(quoteNo),commonDAO.getHomeBranchCodeByQuoteNo(quoteNo));
							responseStatus="Payment Successfully";
							returnResult = getXMLString("RESPONSE_INTG", responseStatus);
						} else {
							responseStatus="Payment Failure";
							returnResult = getXMLString("RESPONSE_INTG", responseStatus);
						}

					} else {
						responseStatus="Payment Amount less then Premium Amount";
						returnResult = getXMLString("RESPONSE_INTG", responseStatus);
					}
				}
			} else {
				responseStatus="Authentification Failed";
				returnResult = getXMLString("RESPONSE_INTG", responseStatus);
			}
			}
			else {
				responseStatus="User name and Pasword Should not be blank";
				returnResult = getXMLString("RESPONSE_INTG", responseStatus);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		SimpleLogger.info("Result ==> "+returnResult);
		return returnResult;	
	}
}
