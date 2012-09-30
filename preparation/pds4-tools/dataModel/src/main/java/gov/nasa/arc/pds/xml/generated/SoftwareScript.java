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
import javax.xml.bind.annotation.XmlType;


/**
 *  The Software Script class provides a description of a software code that is stored as a script. 
 * 
 * <p>Java class for Software_Script complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Software_Script">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="files" type="{http://pds.nasa.gov/pds4/pds/v03}files"/>
 *         &lt;element name="install_note" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved"/>
 *         &lt;element name="supported_environment_note" type="{http://pds.nasa.gov/pds4/pds/v03}ASCII_Text_Preserved"/>
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
@XmlType(name = "Software_Script", propOrder = {
    "files",
    "installNote",
    "supportedEnvironmentNote",
    "systemRequirementsNote"
})
public class SoftwareScript {

    protected int files;
    @XmlElement(name = "install_note", required = true)
    protected String installNote;
    @XmlElement(name = "supported_environment_note", required = true)
    protected String supportedEnvironmentNote;
    @XmlElement(name = "system_requirements_note", required = true)
    protected String systemRequirementsNote;

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
     * Gets the value of the installNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallNote() {
        return installNote;
    }

    /**
     * Sets the value of the installNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallNote(String value) {
        this.installNote = value;
    }

    /**
     * Gets the value of the supportedEnvironmentNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedEnvironmentNote() {
        return supportedEnvironmentNote;
    }

    /**
     * Sets the value of the supportedEnvironmentNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedEnvironmentNote(String value) {
        this.supportedEnvironmentNote = value;
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
