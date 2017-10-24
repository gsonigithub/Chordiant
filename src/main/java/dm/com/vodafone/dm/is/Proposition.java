
package dm.com.vodafone.dm.is;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Proposition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Proposition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExtensionValues" type="{java:com.vodafone.dm.is.dto}GenericExtension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Identifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Rank" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Proposition", propOrder = {
    "extensionValues",
    "identifier",
    "rank"
})
public class Proposition {

    @XmlElement(name = "ExtensionValues", nillable = true)
    protected List<GenericExtension> extensionValues;
    @XmlElement(name = "Identifier", required = true, nillable = true)
    protected String identifier;
    @XmlElement(name = "Rank", required = true, type = Long.class, nillable = true)
    protected Long rank;

    /**
     * Gets the value of the extensionValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extensionValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtensionValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericExtension }
     * 
     * 
     */
    public List<GenericExtension> getExtensionValues() {
        if (extensionValues == null) {
            extensionValues = new ArrayList<GenericExtension>();
        }
        return this.extensionValues;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the rank property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRank(Long value) {
        this.rank = value;
    }

}
