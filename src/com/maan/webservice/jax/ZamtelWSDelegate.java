package com.maan.webservice.jax;


import javax.jws.WebParam;

import com.maan.webservice.auth.SoapHeaderIn;


@javax.jws.WebService(targetNamespace = "http://jax.webservice.maan.com/", serviceName = "ZamtelWSService", portName = "ZamtelWSPort", wsdlLocation = "WEB-INF/wsdl/ZamtelWSService.wsdl")
public class ZamtelWSDelegate {

	com.maan.webservice.jax.ZamtelWS zamtelWS = new com.maan.webservice.jax.ZamtelWS();

	public String QuoteInfo(@WebParam(name="quoteNo") String quoteNo,@WebParam(name="authentication") SoapHeaderIn shIn) {
		return zamtelWS.QuoteInfo(quoteNo, shIn);
	}

	public String PaymentConfirmationMsge(@WebParam(name="paymentInfo") String paymentInfo,@WebParam(name="authentication")SoapHeaderIn shIn) {
		return zamtelWS.PaymentConfirmationMsge(paymentInfo, shIn);
	}

}