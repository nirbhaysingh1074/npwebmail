//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.31 at 05:59:11 PM IST 
//


package webmail.wsdlnew;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="webmailDetailId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="webmailDetailPassword" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="webmailDetailUrl" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="webmailDetailBase" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="webmailDetailSearchId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "webmailDetailId",
    "webmailDetailPassword",
    "webmailDetailUrl",
    "webmailDetailBase",
    "webmailDetailSearchId"
})
@XmlRootElement(name = "getContactDetailRequest", namespace = "http://webmail.com/LdapAttribute")
public class GetContactDetailRequest {

    @XmlElement(namespace = "http://webmail.com/LdapAttribute", required = true)
    protected String webmailDetailId;
    @XmlElement(namespace = "http://webmail.com/LdapAttribute", required = true)
    protected String webmailDetailPassword;
    @XmlElement(namespace = "http://webmail.com/LdapAttribute", required = true)
    protected String webmailDetailUrl;
    @XmlElement(namespace = "http://webmail.com/LdapAttribute", required = true)
    protected String webmailDetailBase;
    @XmlElement(namespace = "http://webmail.com/LdapAttribute", required = true)
    protected String webmailDetailSearchId;

    /**
     * Gets the value of the webmailDetailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebmailDetailId() {
        return webmailDetailId;
    }

    /**
     * Sets the value of the webmailDetailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebmailDetailId(String value) {
        this.webmailDetailId = value;
    }

    /**
     * Gets the value of the webmailDetailPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebmailDetailPassword() {
        return webmailDetailPassword;
    }

    /**
     * Sets the value of the webmailDetailPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebmailDetailPassword(String value) {
        this.webmailDetailPassword = value;
    }

    /**
     * Gets the value of the webmailDetailUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebmailDetailUrl() {
        return webmailDetailUrl;
    }

    /**
     * Sets the value of the webmailDetailUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebmailDetailUrl(String value) {
        this.webmailDetailUrl = value;
    }

    /**
     * Gets the value of the webmailDetailBase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebmailDetailBase() {
        return webmailDetailBase;
    }

    /**
     * Sets the value of the webmailDetailBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebmailDetailBase(String value) {
        this.webmailDetailBase = value;
    }

    /**
     * Gets the value of the webmailDetailSearchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebmailDetailSearchId() {
        return webmailDetailSearchId;
    }

    /**
     * Sets the value of the webmailDetailSearchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebmailDetailSearchId(String value) {
        this.webmailDetailSearchId = value;
    }

}
