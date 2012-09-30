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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *  The Parsable Byte Stream class defines byte streams that have standard parsing rules. The Parsable Byte Stream class is the parent class for all parsable byte streams. 
 * 
 * <p>Java class for Parsable_Byte_Stream complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Parsable_Byte_Stream">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pds.nasa.gov/pds4/pds/v03}Byte_Stream">
 *       &lt;sequence>
 *         &lt;element name="offset" type="{http://pds.nasa.gov/pds4/pds/v03}offset"/>
 *         &lt;element name="object_length" type="{http://pds.nasa.gov/pds4/pds/v03}object_length" minOccurs="0"/>
 *         &lt;element name="external_standard_id" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="encoding_type" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="description" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parsable_Byte_Stream", propOrder = {
    "offset",
    "objectLength",
    "externalStandardId",
    "encodingType",
    "description"
})
@XmlSeeAlso({
    Header.class,
    SPICEKernel.class,
    ServiceDescription.class,
    XMLSchema.class,
    TableDelimited.class,
    StreamText.class
})
public class ParsableByteStream
    extends ByteStream
{

    @XmlElement(required = true)
    protected Offset offset;
    @XmlElement(name = "object_length")
    protected ObjectLength objectLength;
    @XmlElement(name = "external_standard_id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String externalStandardId;
    @XmlElement(name = "encoding_type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String encodingType;
    protected String description;

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setOffset(Offset value) {
        this.offset = value;
    }

    /**
     * Gets the value of the objectLength property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectLength }
     *     
     */
    public ObjectLength getObjectLength() {
        return objectLength;
    }

    /**
     * Sets the value of the objectLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectLength }
     *     
     */
    public void setObjectLength(ObjectLength value) {
        this.objectLength = value;
    }

    /**
     * Gets the value of the externalStandardId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalStandardId() {
        return externalStandardId;
    }

    /**
     * Sets the value of the externalStandardId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalStandardId(String value) {
        this.externalStandardId = value;
    }

    /**
     * Gets the value of the encodingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodingType() {
        return encodingType;
    }

    /**
     * Sets the value of the encodingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodingType(String value) {
        this.encodingType = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
