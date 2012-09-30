//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.19 at 05:23:51 PM PDT 
//


package gov.nasa.arc.pds.xml.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *  The Product_Subscription_PDS3 class provides the list of subscriptions for a PDS3 subscriber. 
 * 
 * <p>Java class for Product_Subscription_PDS3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Product_Subscription_PDS3">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pds.nasa.gov/pds4/pds/v03}Product">
 *       &lt;sequence>
 *         &lt;element name="Reference_List" type="{http://pds.nasa.gov/pds4/pds/v03}Reference_List" minOccurs="0"/>
 *         &lt;element name="Subscriber_PDS3" type="{http://pds.nasa.gov/pds4/pds/v03}Subscriber_PDS3"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product_Subscription_PDS3", propOrder = {
    "referenceList",
    "subscriberPDS3"
})
@XmlRootElement(name = "Product_Subscription_PDS3")
public class ProductSubscriptionPDS3
    extends Product
{

    @XmlElement(name = "Reference_List")
    protected ReferenceList referenceList;
    @XmlElement(name = "Subscriber_PDS3", required = true)
    protected SubscriberPDS3 subscriberPDS3;

    /**
     * Gets the value of the referenceList property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceList }
     *     
     */
    public ReferenceList getReferenceList() {
        return referenceList;
    }

    /**
     * Sets the value of the referenceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceList }
     *     
     */
    public void setReferenceList(ReferenceList value) {
        this.referenceList = value;
    }

    /**
     * Gets the value of the subscriberPDS3 property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberPDS3 }
     *     
     */
    public SubscriberPDS3 getSubscriberPDS3() {
        return subscriberPDS3;
    }

    /**
     * Sets the value of the subscriberPDS3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberPDS3 }
     *     
     */
    public void setSubscriberPDS3(SubscriberPDS3 value) {
        this.subscriberPDS3 = value;
    }

}
