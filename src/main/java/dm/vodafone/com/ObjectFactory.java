
package dm.vodafone.com;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dm.vodafone.com package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dm.vodafone.com
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetResponseResponse }
     * 
     */
    public SetResponseResponse createSetResponseResponse() {
        return new SetResponseResponse();
    }

    /**
     * Create an instance of {@link GetOffers }
     * 
     */
    public GetOffers createGetOffers() {
        return new GetOffers();
    }

    /**
     * Create an instance of {@link ReloadResponse }
     * 
     */
    public ReloadResponse createReloadResponse() {
        return new ReloadResponse();
    }

    /**
     * Create an instance of {@link GetOffersResponse }
     * 
     */
    public GetOffersResponse createGetOffersResponse() {
        return new GetOffersResponse();
    }

    /**
     * Create an instance of {@link SetResponse }
     * 
     */
    public SetResponse createSetResponse() {
        return new SetResponse();
    }

    /**
     * Create an instance of {@link Reload }
     * 
     */
    public Reload createReload() {
        return new Reload();
    }

}
