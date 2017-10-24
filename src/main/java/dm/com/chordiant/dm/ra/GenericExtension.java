
package dm.com.chordiant.dm.ra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="FieldPersistFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="FieldType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FieldValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "fieldPersistFlag",
    "fieldType",
    "fieldValue"
})
public class GenericExtension {

    @XmlElement(name = "FieldName", required = true, nillable = true)
    protected String fieldName;
    @XmlElement(name = "FieldPersistFlag")
    protected boolean fieldPersistFlag;
    @XmlElement(name = "FieldType", required = true, nillable = true)
    protected String fieldType;
    @XmlElement(name = "FieldValue", required = true, nillable = true)
    protected String fieldValue;

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
     * Gets the value of the fieldPersistFlag property.
     * 
     */
    public boolean isFieldPersistFlag() {
        return fieldPersistFlag;
    }

    /**
     * Sets the value of the fieldPersistFlag property.
     * 
     */
    public void setFieldPersistFlag(boolean value) {
        this.fieldPersistFlag = value;
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
     * Gets the value of the fieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * Sets the value of the fieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldValue(String value) {
        this.fieldValue = value;
    }

}
