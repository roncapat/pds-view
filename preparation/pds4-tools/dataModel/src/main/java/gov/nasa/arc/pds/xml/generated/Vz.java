//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.19 at 05:23:51 PM PDT 
//


package gov.nasa.arc.pds.xml.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for Vz complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Vz">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://pds.nasa.gov/pds4/pds/v03>Vz_WO_Units">
 *       &lt;attribute name="unit" use="required" type="{http://pds.nasa.gov/pds4/pds/v03}UnitOfMeasure_Velocity" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vz", propOrder = {
    "value"
})
public class Vz {

    @XmlValue
    protected double value;
    @XmlAttribute(required = true)
    protected UnitOfMeasureVelocity unit;

    /**
     * Gets the value of the value property.
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitOfMeasureVelocity }
     *     
     */
    public UnitOfMeasureVelocity getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitOfMeasureVelocity }
     *     
     */
    public void setUnit(UnitOfMeasureVelocity value) {
        this.unit = value;
    }

}
