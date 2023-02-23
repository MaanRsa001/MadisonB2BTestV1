
package com.maan.webservice.jax.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "QuoteInfo", namespace = "http://jax.webservice.maan.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuoteInfo", namespace = "http://jax.webservice.maan.com/", propOrder = {
    "quoteNo",
    "authentication"
})
public class QuoteInfo {

    @XmlElement(name = "quoteNo", namespace = "")
    private String quoteNo;
    @XmlElement(name = "authentication", namespace = "")
    private com.maan.webservice.auth.SoapHeaderIn authentication;

    /**
     * 
     * @return
     *     returns String
     */
    public String getQuoteNo() {
        return this.quoteNo;
    }

    /**
     * 
     * @param quoteNo
     *     the value for the quoteNo property
     */
    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
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
