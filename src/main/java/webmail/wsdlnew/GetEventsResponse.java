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
 *         &lt;element name="eventList" type="{http://webmail.com/calendar}eventArray"/&gt;
 *         &lt;element name="calendarname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="calendarcolor" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "eventList",
    "calendarname",
    "calendarcolor"
})
@XmlRootElement(name = "getEventsResponse", namespace = "http://webmail.com/calendar")
public class GetEventsResponse {

    @XmlElement(namespace = "http://webmail.com/calendar", required = true)
    protected EventArray eventList;
    @XmlElement(namespace = "http://webmail.com/calendar", required = true)
    protected String calendarname;
    @XmlElement(namespace = "http://webmail.com/calendar", required = true)
    protected String calendarcolor;

    /**
     * Gets the value of the eventList property.
     * 
     * @return
     *     possible object is
     *     {@link EventArray }
     *     
     */
    public EventArray getEventList() {
        return eventList;
    }

    /**
     * Sets the value of the eventList property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventArray }
     *     
     */
    public void setEventList(EventArray value) {
        this.eventList = value;
    }

    /**
     * Gets the value of the calendarname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarname() {
        return calendarname;
    }

    /**
     * Sets the value of the calendarname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarname(String value) {
        this.calendarname = value;
    }

    /**
     * Gets the value of the calendarcolor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalendarcolor() {
        return calendarcolor;
    }

    /**
     * Sets the value of the calendarcolor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalendarcolor(String value) {
        this.calendarcolor = value;
    }

}
