
package dm.com.vodafone.dm.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import dm.com.chordiant.dm.ra.Product;


/**
 * <p>Java class for ProductArray complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Products" type="{java:com.chordiant.dm.ra.bean}Product" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ConversationID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductArray", propOrder = {
    "products",
    "conversationID"
})
public class ProductArray {

    @XmlElement(name = "Products", nillable = true)
    protected List<Product> products;
    @XmlElement(name = "ConversationID", required = true, nillable = true)
    protected String conversationID;

    /**
     * Gets the value of the products property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the products property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Product }
     * 
     * 
     */
    public List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<Product>();
        }
        return this.products;
    }

    /**
     * Gets the value of the conversationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConversationID() {
        return conversationID;
    }

    /**
     * Sets the value of the conversationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConversationID(String value) {
        this.conversationID = value;
    }

}
