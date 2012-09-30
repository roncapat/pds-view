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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *  The Software Script class provides a description of a software code that is stored as source code. 
 * 
 * <p>Java class for Software_Source complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Software_Source">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compile_note" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved"/>
 *         &lt;element name="files" type="{http://pds.nasa.gov/pds4/pds/v03}files"/>
 *         &lt;element name="os_version" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="program_notes_id" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="software_dialect" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="software_format_type" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="software_language" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Short_String_Collapsed"/>
 *         &lt;element name="supported_architecture_note" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved" maxOccurs="unbounded"/>
 *         &lt;element name="supported_operating_system_note" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved" maxOccurs="unbounded"/>
 *         &lt;element name="system_requirements_note" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Software_Source", propOrder = {
    "compileNote",
    "files",
    "osVersion",
    "programNotesId",
    "softwareDialect",
    "softwareFormatType",
    "softwareLanguage",
    "supportedArchitectureNotes",
    "supportedOperatingSystemNotes",
    "systemRequirementsNote"
})
public class SoftwareSource {

    @XmlElement(name = "compile_note", required = true)
    protected String compileNote;
    protected int files;
    @XmlElement(name = "os_version", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String osVersion;
    @XmlElement(name = "program_notes_id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String programNotesId;
    @XmlElement(name = "software_dialect", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String softwareDialect;
    @XmlElement(name = "software_format_type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String softwareFormatType;
    @XmlElement(name = "software_language", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String softwareLanguage;
    @XmlElement(name = "supported_architecture_note", required = true)
    protected List<String> supportedArchitectureNotes;
    @XmlElement(name = "supported_operating_system_note", required = true)
    protected List<String> supportedOperatingSystemNotes;
    @XmlElement(name = "system_requirements_note", required = true)
    protected String systemRequirementsNote;

    /**
     * Gets the value of the compileNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompileNote() {
        return compileNote;
    }

    /**
     * Sets the value of the compileNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompileNote(String value) {
        this.compileNote = value;
    }

    /**
     * Gets the value of the files property.
     * 
     */
    public int getFiles() {
        return files;
    }

    /**
     * Sets the value of the files property.
     * 
     */
    public void setFiles(int value) {
        this.files = value;
    }

    /**
     * Gets the value of the osVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * Sets the value of the osVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsVersion(String value) {
        this.osVersion = value;
    }

    /**
     * Gets the value of the programNotesId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramNotesId() {
        return programNotesId;
    }

    /**
     * Sets the value of the programNotesId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramNotesId(String value) {
        this.programNotesId = value;
    }

    /**
     * Gets the value of the softwareDialect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareDialect() {
        return softwareDialect;
    }

    /**
     * Sets the value of the softwareDialect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareDialect(String value) {
        this.softwareDialect = value;
    }

    /**
     * Gets the value of the softwareFormatType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareFormatType() {
        return softwareFormatType;
    }

    /**
     * Sets the value of the softwareFormatType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareFormatType(String value) {
        this.softwareFormatType = value;
    }

    /**
     * Gets the value of the softwareLanguage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoftwareLanguage() {
        return softwareLanguage;
    }

    /**
     * Sets the value of the softwareLanguage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoftwareLanguage(String value) {
        this.softwareLanguage = value;
    }

    /**
     * Gets the value of the supportedArchitectureNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedArchitectureNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedArchitectureNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedArchitectureNotes() {
        if (supportedArchitectureNotes == null) {
            supportedArchitectureNotes = new ArrayList<String>();
        }
        return this.supportedArchitectureNotes;
    }

    /**
     * Gets the value of the supportedOperatingSystemNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedOperatingSystemNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedOperatingSystemNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSupportedOperatingSystemNotes() {
        if (supportedOperatingSystemNotes == null) {
            supportedOperatingSystemNotes = new ArrayList<String>();
        }
        return this.supportedOperatingSystemNotes;
    }

    /**
     * Gets the value of the systemRequirementsNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemRequirementsNote() {
        return systemRequirementsNote;
    }

    /**
     * Sets the value of the systemRequirementsNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemRequirementsNote(String value) {
        this.systemRequirementsNote = value;
    }

}
