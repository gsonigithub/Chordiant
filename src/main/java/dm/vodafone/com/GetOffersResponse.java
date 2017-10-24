
package dm.vodafone.com;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import dm.com.vodafone.dm.ws.ProductArray;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DecisionResult" type="{java:com.vodafone.dm.ws.bean}ProductArray"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "decisionResult"
})
@XmlRootElement(name = "getOffersResponse")
public class GetOffersResponse {

    @XmlElement(name = "DecisionResult", required = true)
    protected ProductArray decisionResult;

    /**
     * Gets the value of the decisionResult property.
     * 
     * @return
     *     possible object is
     *     {@link ProductArray }
     *     
     */
    public ProductArray getDecisionResult() {
        return decisionResult;
    }

    /**
     * Sets the value of the decisionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductArray }
     *     
     */
    public void setDecisionResult(ProductArray value) {
        this.decisionResult = value;
    }

}
