
package dm.com.vodafone.dm.is;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for GenericExtension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericExtension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FieldName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FieldType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PersistFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AlphaNumericValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DateValue" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DoubleValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericExtension", propOrder = {
    "fieldName",
    "fieldType",
    "persistFlag",
    "alphaNumericValue",
    "dateValue",
    "doubleValue"
})
public class GenericExtension {

    @XmlElement(name = "FieldName", required = true, nillable = true)
    protected String fieldName;
    @XmlElement(name = "FieldType", required = true, nillable = true)
    protected String fieldType;
    @XmlElement(name = "PersistFlag")
    protected boolean persistFlag;
    @XmlElement(name = "AlphaNumericValue", required = true, nillable = true)
    protected String alphaNumericValue;
    @XmlElement(name = "DateValue", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateValue;
    @XmlElement(name = "DoubleValue", required = true, type = Double.class, nillable = true)
    protected Double doubleValue;

    /**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Gets the value of the fieldType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Sets the value of the fieldType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldType(String value) {
        this.fieldType = value;
    }

    /**
     * Gets the value of the persistFlag property.
     * 
     */
    public boolean isPersistFlag() {
        return persistFlag;
    }

    /**
     * Sets the value of the persistFlag property.
     * 
     */
    public void setPersistFlag(boolean value) {
        this.persistFlag = value;
    }

    /**
     * Gets the value of the alphaNumericValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlphaNumericValue() {
        return alphaNumericValue;
    }

    /**
     * Sets the value of the alphaNumericValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlphaNumericValue(String value) {
        this.alphaNumericValue = value;
    }

    /**
     * Gets the value of the dateValue property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateValue() {
        return dateValue;
    }

    /**
     * Sets the value of the dateValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateValue(XMLGregorianCalendar value) {
        this.dateValue = value;
    }

    /**
     * Gets the value of the doubleValue property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleValue() {
        return doubleValue;
    }

    /**
     * Sets the value of the doubleValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleValue(Double value) {
        this.doubleValue = value;
    }

}
