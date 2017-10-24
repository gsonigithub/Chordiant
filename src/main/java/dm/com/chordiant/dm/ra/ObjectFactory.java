
package dm.com.chordiant.dm.ra;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the java.com_chordiant_dm_ra package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: java.com_chordiant_dm_ra
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Proposition }
     * 
     */
    public Proposition createProposition() {
        return new Proposition();
    }

    /**
     * Create an instance of {@link GenericExtension }
     * 
     */
    public GenericExtension createGenericExtension() {
        return new GenericExtension();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link URLParameter }
     * 
     */
    public URLParameter createURLParameter() {
        return new URLParameter();
    }

}
