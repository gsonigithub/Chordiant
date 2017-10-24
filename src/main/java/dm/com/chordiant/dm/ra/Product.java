
package dm.com.chordiant.dm.ra;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Product complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Product">
 *   &lt;complexContent>
 *     &lt;extension base="{java:com.chordiant.dm.ra.bean}Proposition">
 *       &lt;sequence>
 *         &lt;element name="Owned" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Relevant" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TotalPriority" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="RelevantCustomersCount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="GenericExtension" type="{java:com.chordiant.dm.ra.bean}GenericExtension" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ProductColour" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ManufacturedHandset" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TariffCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReasonCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExtraInfo" type="{java:com.chordiant.dm.ra.bean}URLParameter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product", propOrder = {
    "owned",
    "priority",
    "relevant",
    "totalPriority",
    "relevantCustomersCount",
    "genericExtension",
    "productColour",
    "description",
    "manufacturedHandset",
    "tariffCode",
    "reasonCode",
    "extraInfo"
})
public class Product
    extends Proposition
{

    @XmlElement(name = "Owned")
    protected boolean owned;
    @XmlElement(name = "Priority")
    protected double priority;
    @XmlElement(name = "Relevant")
    protected boolean relevant;
    @XmlElement(name = "TotalPriority")
    protected double totalPriority;
    @XmlElement(name = "RelevantCustomersCount")
    protected double relevantCustomersCount;
    @XmlElement(name = "GenericExtension", nillable = true)
    protected List<GenericExtension> genericExtension;
    @XmlElement(name = "ProductColour", required = true, nillable = true)
    protected String productColour;
    @XmlElement(name = "Description", required = true, nillable = true)
    protected String description;
    @XmlElement(name = "ManufacturedHandset", required = true, nillable = true)
    protected String manufacturedHandset;
    @XmlElement(name = "TariffCode", required = true, nillable = true)
    protected String tariffCode;
    @XmlElement(name = "ReasonCode", required = true, nillable = true)
    protected String reasonCode;
    @XmlElement(name = "ExtraInfo", nillable = true)
    protected List<URLParameter> extraInfo;

    /**
     * Gets the value of the owned property.
     * 
     */
    public boolean isOwned() {
        return owned;
    }

    /**
     * Sets the value of the owned property.
     * 
     */
    public void setOwned(boolean value) {
        this.owned = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public double getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(double value) {
        this.priority = value;
    }

    /**
     * Gets the value of the relevant property.
     * 
     */
    public boolean isRelevant() {
        return relevant;
    }

    /**
     * Sets the value of the relevant property.
     * 
     */
    public void setRelevant(boolean value) {
        this.relevant = value;
    }

    /**
     * Gets the value of the totalPriority property.
     * 
     */
    public double getTotalPriority() {
        return totalPriority;
    }

    /**
     * Sets the value of the totalPriority property.
     * 
     */
    public void setTotalPriority(double value) {
        this.totalPriority = value;
    }

    /**
     * Gets the value of the relevantCustomersCount property.
     * 
     */
    public double getRelevantCustomersCount() {
        return relevantCustomersCount;
    }

    /**
     * Sets the value of the relevantCustomersCount property.
     * 
     */
    public void setRelevantCustomersCount(double value) {
        this.relevantCustomersCount = value;
    }

    /**
     * Gets the value of the genericExtension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genericExtension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenericExtension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericExtension }
     * 
     * 
     */
    public List<GenericExtension> getGenericExtension() {
        if (genericExtension == null) {
            genericExtension = new ArrayList<GenericExtension>();
        }
        return this.genericExtension;
    }

    /**
     * Gets the value of the productColour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductColour() {
        return productColour;
    }

    /**
     * Sets the value of the productColour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductColour(String value) {
        this.productColour = value;
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

    /**
     * Gets the value of the manufacturedHandset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturedHandset() {
        return manufacturedHandset;
    }

    /**
     * Sets the value of the manufacturedHandset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturedHandset(String value) {
        this.manufacturedHandset = value;
    }

    /**
     * Gets the value of the tariffCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariffCode() {
        return tariffCode;
    }

    /**
     * Sets the value of the tariffCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariffCode(String value) {
        this.tariffCode = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the extraInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link URLParameter }
     * 
     * 
     */
    public List<URLParameter> getExtraInfo() {
        if (extraInfo == null) {
            extraInfo = new ArrayList<URLParameter>();
        }
        return this.extraInfo;
    }

}
