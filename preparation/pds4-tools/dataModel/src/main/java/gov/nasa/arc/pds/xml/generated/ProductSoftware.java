//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.19 at 05:23:51 PM PDT 
//


package gov.nasa.arc.pds.xml.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *  Product Software is a product consisting of a set of one or more software formats. 
 * 
 * <p>Java class for Product_Software complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Product_Software">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pds.nasa.gov/pds4/pds/v03}Product">
 *       &lt;sequence>
 *         &lt;element name="Reference_List" type="{http://pds.nasa.gov/pds4/pds/v03}Reference_List" minOccurs="0"/>
 *         &lt;element name="Software" type="{http://pds.nasa.gov/pds4/pds/v03}Software"/>
 *         &lt;element name="Software_Binary" type="{http://pds.nasa.gov/pds4/pds/v03}Software_Binary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Software_Script" type="{http://pds.nasa.gov/pds4/pds/v03}Software_Script" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Software_Source" type="{http://pds.nasa.gov/pds4/pds/v03}Software_Source" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product_Software", propOrder = {
    "referenceList",
    "software",
    "softwareBinaries",
    "softwareScripts",
    "softwareSources"
})
@XmlRootElement(name = "Product_Software")
public class ProductSoftware
    extends Product
{

    @XmlElement(name = "Reference_List")
    protected ReferenceList referenceList;
    @XmlElement(name = "Software", required = true)
    protected Software software;
    @XmlElement(name = "Software_Binary")
    protected List<SoftwareBinary> softwareBinaries;
    @XmlElement(name = "Software_Script")
    protected List<SoftwareScript> softwareScripts;
    @XmlElement(name = "Software_Source")
    protected List<SoftwareSource> softwareSources;

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
     * Gets the value of the software property.
     * 
     * @return
     *     possible object is
     *     {@link Software }
     *     
     */
    public Software getSoftware() {
        return software;
    }

    /**
     * Sets the value of the software property.
     * 
     * @param value
     *     allowed object is
     *     {@link Software }
     *     
     */
    public void setSoftware(Software value) {
        this.software = value;
    }

    /**
     * Gets the value of the softwareBinaries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the softwareBinaries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoftwareBinaries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoftwareBinary }
     * 
     * 
     */
    public List<SoftwareBinary> getSoftwareBinaries() {
        if (softwareBinaries == null) {
            softwareBinaries = new ArrayList<SoftwareBinary>();
        }
        return this.softwareBinaries;
    }

    /**
     * Gets the value of the softwareScripts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the softwareScripts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoftwareScripts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoftwareScript }
     * 
     * 
     */
    public List<SoftwareScript> getSoftwareScripts() {
        if (softwareScripts == null) {
            softwareScripts = new ArrayList<SoftwareScript>();
        }
        return this.softwareScripts;
    }

    /**
     * Gets the value of the softwareSources property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the softwareSources property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoftwareSources().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoftwareSource }
     * 
     * 
     */
    public List<SoftwareSource> getSoftwareSources() {
        if (softwareSources == null) {
            softwareSources = new ArrayList<SoftwareSource>();
        }
        return this.softwareSources;
    }

}
