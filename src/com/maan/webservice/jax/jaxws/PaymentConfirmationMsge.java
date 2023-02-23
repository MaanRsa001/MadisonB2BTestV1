
package com.maan.webservice.jax.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PaymentConfirmationMsge", namespace = "http://jax.webservice.maan.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentConfirmationMsge", namespace = "http://jax.webservice.maan.com/", propOrder = {
    "paymentInfo",
    "authentication"
})
public class PaymentConfirmationMsge {

    @XmlElement(name = "paymentInfo", namespace = "")
    private String paymentInfo;
    @XmlElement(name = "authentication", namespace = "")
    private com.maan.webservice.auth.SoapHeaderIn authentication;

    /**
     * 
     * @return
     *     returns String
     */
    public String getPaymentInfo() {
        return this.paymentInfo;
    }

    /**
     * 
     * @param paymentInfo
     *     the value for the paymentInfo property
     */
    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    /**
     * 
     * @return
     *     returns SoapHeaderIn
     */
    public com.maan.webservice.auth.SoapHeaderIn getAuthentication() {
        return this.authentication;
    }

    /**
     * 
     * @param authentication
     *     the value for the authentication property
     */
    public void setAuthentication(com.maan.webservice.auth.SoapHeaderIn authentication) {
        this.authentication = authentication;
    }

}
