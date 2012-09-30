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
 *  The Product Attribute Definition provides an attribute definition in XML encoding. 
 * 
 * <p>Java class for Product_Attribute_Definition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Product_Attribute_Definition">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pds.nasa.gov/pds4/pds/v03}Product">
 *       &lt;sequence>
 *         &lt;element name="Reference_List" type="{http://pds.nasa.gov/pds4/pds/v03}Reference_List" minOccurs="0"/>
 *         &lt;element name="DD_Attribute_Full" type="{http://pds.nasa.gov/pds4/pds/v03}DD_Attribute_Full"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product_Attribute_Definition", propOrder = {
    "referenceList",
    "ddAttributeFull"
})
@XmlRootElement(name = "Product_Attribute_Definition")
public class ProductAttributeDefinition
    extends Product
{

    @XmlElement(name = "Reference_List")
    protected ReferenceList referenceList;
    @XmlElement(name = "DD_Attribute_Full", required = true)
    protected DDAttributeFull ddAttributeFull;

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
     * Gets the value of the ddAttributeFull property.
     * 
     * @return
     *     possible object is
     *     {@link DDAttributeFull }
     *     
     */
    public DDAttributeFull getDDAttributeFull() {
        return ddAttributeFull;
    }

    /**
     * Sets the value of the ddAttributeFull property.
     * 
     * @param value
     *     allowed object is
     *     {@link DDAttributeFull }
     *     
     */
    public void setDDAttributeFull(DDAttributeFull value) {
        this.ddAttributeFull = value;
    }

}
