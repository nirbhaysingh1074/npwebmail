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
 * <p>Java class for VCFFileListReturn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VCFFileListReturn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="VCFSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="VCFFileListResult" type="{http://edms.com/documentModule}ArrayOfVCFFiles" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VCFFileListReturn", propOrder = {
    "vcfSuccess",
    "vcfFileListResult"
})
public class VCFFileListReturn {

    @XmlElement(name = "VCFSuccess")
    protected boolean vcfSuccess;
    @XmlElement(name = "VCFFileListResult")
    protected ArrayOfVCFFiles vcfFileListResult;

    /**
     * Gets the value of the vcfSuccess property.
     * 
     */
    public boolean isVCFSuccess() {
        return vcfSuccess;
    }

    /**
     * Sets the value of the vcfSuccess property.
     * 
     */
    public void setVCFSuccess(boolean value) {
        this.vcfSuccess = value;
    }

    /**
     * Gets the value of the vcfFileListResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfVCFFiles }
     *     
     */
    public ArrayOfVCFFiles getVCFFileListResult() {
        return vcfFileListResult;
    }

    /**
     * Sets the value of the vcfFileListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfVCFFiles }
     *     
     */
    public void setVCFFileListResult(ArrayOfVCFFiles value) {
        this.vcfFileListResult = value;
    }

}
