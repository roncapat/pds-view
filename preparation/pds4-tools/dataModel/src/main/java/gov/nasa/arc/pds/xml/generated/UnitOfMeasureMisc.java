//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.19 at 05:23:51 PM PDT 
//


package gov.nasa.arc.pds.xml.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitOfMeasure_Misc.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnitOfMeasure_Misc">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DN"/>
 *     &lt;enumeration value="electron/DN"/>
 *     &lt;enumeration value="pixel"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnitOfMeasure_Misc")
@XmlEnum
public enum UnitOfMeasureMisc {

    DN("DN"),
    @XmlEnumValue("electron/DN")
    ELECTRON_DN("electron/DN"),
    @XmlEnumValue("pixel")
    PIXEL("pixel");
    private final String value;

    UnitOfMeasureMisc(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnitOfMeasureMisc fromValue(String v) {
        for (UnitOfMeasureMisc c: UnitOfMeasureMisc.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
