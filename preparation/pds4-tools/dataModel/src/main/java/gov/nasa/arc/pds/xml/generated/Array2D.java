//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.19 at 05:23:51 PM PDT 
//


package gov.nasa.arc.pds.xml.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 *  The Array 2D class is the parent class for all two dimensional array base classes. 
 * 
 * <p>Java class for Array_2D complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Array_2D">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://pds.nasa.gov/pds4/pds/v03}Array">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed" minOccurs="0"/>
 *         &lt;element name="local_identifier" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed" minOccurs="0"/>
 *         &lt;element name="offset" type="{http://pds.nasa.gov/pds4/pds/v03}offset"/>
 *         &lt;element name="axes" type="{http://pds.nasa.gov/pds4/pds/v03}axes"/>
 *         &lt;element name="axis_index_order" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="encoding_type" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="description" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved" minOccurs="0"/>
 *         &lt;element name="Element_Array" type="{http://pds.nasa.gov/pds4/pds/v03}Element_Array"/>
 *         &lt;element name="Axis_Array" type="{http://pds.nasa.gov/pds4/pds/v03}Axis_Array" maxOccurs="2" minOccurs="2"/>
 *         &lt;element name="Special_Constants" type="{http://pds.nasa.gov/pds4/pds/v03}Special_Constants" minOccurs="0"/>
 *         &lt;element name="Object_Statistics" type="{http://pds.nasa.gov/pds4/pds/v03}Object_Statistics" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Array_2D")
@XmlSeeAlso({
    Array2DSpectrum.class,
    Array2DMap.class,
    Array2DImage.class
})
public class Array2D
    extends Array
{


}
