
package dm.com.vodafone.dm.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import dm.com.vodafone.dm.is.Proposition;


/**
 * <p>Java class for PropositionArray complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PropositionArray">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Propositions" type="{java:com.vodafone.dm.is.dto}Proposition" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropositionArray", propOrder = {
    "propositions"
})
public class PropositionArray {

    @XmlElement(name = "Propositions", nillable = true)
    protected List<Proposition> propositions;

    /**
     * Gets the value of the propositions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propositions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropositions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Proposition }
     * 
     * 
     */
    public List<Proposition> getPropositions() {
        if (propositions == null) {
            propositions = new ArrayList<Proposition>();
        }
        return this.propositions;
    }

}
