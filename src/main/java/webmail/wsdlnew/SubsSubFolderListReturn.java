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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubsSubFolderListReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubsSubFolderListReturn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="SubsSubFolderListReturn" type="{http://webmail.com/Folder}ArrayOfSubsSubFolder" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubsSubFolderListReturn", namespace = "http://webmail.com/Folder", propOrder = {
    "success",
    "subsSubFolderListReturn"
})
public class SubsSubFolderListReturn {

    @XmlElement(name = "Success")
    protected boolean success;
    @XmlElement(name = "SubsSubFolderListReturn")
    protected ArrayOfSubsSubFolder subsSubFolderListReturn;

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the subsSubFolderListReturn property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSubsSubFolder }
     *     
     */
    public ArrayOfSubsSubFolder getSubsSubFolderListReturn() {
        return subsSubFolderListReturn;
    }

    /**
     * Sets the value of the subsSubFolderListReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSubsSubFolder }
     *     
     */
    public void setSubsSubFolderListReturn(ArrayOfSubsSubFolder value) {
        this.subsSubFolderListReturn = value;
    }

}
