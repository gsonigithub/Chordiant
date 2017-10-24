
package dm.com.chordiant.dm.ra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Group" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Issue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Warning" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdviceShort" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdviceLong" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cost" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="DurationUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ApplicationID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ApplicationParameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResponseOptions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QuantityLimitedOffer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "id",
    "name",
    "group",
    "issue",
    "warning",
    "adviceShort",
    "adviceLong",
    "cost",
    "duration",
    "durationUnit",
    "price",
    "applicationID",
    "applicationParameter",
    "responseOptions",
    "image",
    "type",
    "quantityLimitedOffer"
})
@XmlSeeAlso({
    Product.class
})
public class Proposition {

    @XmlElement(name = "Id", required = true, nillable = true)
    protected String id;
    @XmlElement(name = "Name", required = true, nillable = true)
    protected String name;
    @XmlElement(name = "Group", required = true, nillable = true)
    protected String group;
    @XmlElement(name = "Issue", required = true, nillable = true)
    protected String issue;
    @XmlElement(name = "Warning", required = true, nillable = true)
    protected String warning;
    @XmlElement(name = "AdviceShort", required = true, nillable = true)
    protected String adviceShort;
    @XmlElement(name = "AdviceLong", required = true, nillable = true)
    protected String adviceLong;
    @XmlElement(name = "Cost")
    protected double cost;
    @XmlElement(name = "Duration")
    protected double duration;
    @XmlElement(name = "DurationUnit", required = true, nillable = true)
    protected String durationUnit;
    @XmlElement(name = "Price")
    protected double price;
    @XmlElement(name = "ApplicationID", required = true, nillable = true)
    protected String applicationID;
    @XmlElement(name = "ApplicationParameter", required = true, nillable = true)
    protected String applicationParameter;
    @XmlElement(name = "ResponseOptions", required = true, nillable = true)
    protected String responseOptions;
    @XmlElement(name = "Image", required = true, nillable = true)
    protected String image;
    @XmlElement(name = "Type", required = true, nillable = true)
    protected String type;
    @XmlElement(name = "QuantityLimitedOffer")
    protected boolean quantityLimitedOffer;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
    }

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssue() {
        return issue;
    }

    /**
     * Sets the value of the issue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssue(String value) {
        this.issue = value;
    }

    /**
     * Gets the value of the warning property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Sets the value of the warning property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarning(String value) {
        this.warning = value;
    }

    /**
     * Gets the value of the adviceShort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdviceShort() {
        return adviceShort;
    }

    /**
     * Sets the value of the adviceShort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdviceShort(String value) {
        this.adviceShort = value;
    }

    /**
     * Gets the value of the adviceLong property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdviceLong() {
        return adviceLong;
    }

    /**
     * Sets the value of the adviceLong property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdviceLong(String value) {
        this.adviceLong = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     */
    public double getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     */
    public void setCost(double value) {
        this.cost = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     */
    public void setDuration(double value) {
        this.duration = value;
    }

    /**
     * Gets the value of the durationUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDurationUnit() {
        return durationUnit;
    }

    /**
     * Sets the value of the durationUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDurationUnit(String value) {
        this.durationUnit = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the applicationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationID() {
        return applicationID;
    }

    /**
     * Sets the value of the applicationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationID(String value) {
        this.applicationID = value;
    }

    /**
     * Gets the value of the applicationParameter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationParameter() {
        return applicationParameter;
    }

    /**
     * Sets the value of the applicationParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationParameter(String value) {
        this.applicationParameter = value;
    }

    /**
     * Gets the value of the responseOptions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseOptions() {
        return responseOptions;
    }

    /**
     * Sets the value of the responseOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseOptions(String value) {
        this.responseOptions = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the quantityLimitedOffer property.
     * 
     */
    public boolean isQuantityLimitedOffer() {
        return quantityLimitedOffer;
    }

    /**
     * Sets the value of the quantityLimitedOffer property.
     * 
     */
    public void setQuantityLimitedOffer(boolean value) {
        this.quantityLimitedOffer = value;
    }

}
