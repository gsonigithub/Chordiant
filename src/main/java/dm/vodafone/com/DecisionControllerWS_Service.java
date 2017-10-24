package dm.vodafone.com;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.9
 * 2014-11-13T23:44:04.798+04:00
 * Generated source version: 2.7.9
 * 
 */
@WebServiceClient(name = "DecisionControllerWS", 
                  wsdlLocation = "http://pcho201zatcrh.vodacom.corp:7031/rtds/webservices-adapter/DecisionControllerWS?WSDL",
                  targetNamespace = "com.vodafone.dm") 
public class DecisionControllerWS_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("com.vodafone.dm", "DecisionControllerWS");
    public final static QName DecisionControllerWS = new QName("com.vodafone.dm", "DecisionControllerWS");
    static {
        URL url = null;
        try {
            url = new URL("http://pcho201zatcrh.vodacom.corp:7031/rtds/webservices-adapter/DecisionControllerWS?WSDL");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DecisionControllerWS_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://pcho201zatcrh.vodacom.corp:7031/rtds/webservices-adapter/DecisionControllerWS?WSDL");
        }
        WSDL_LOCATION = url;
    }

    public DecisionControllerWS_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DecisionControllerWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DecisionControllerWS_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DecisionControllerWS_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DecisionControllerWS_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public DecisionControllerWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns DecisionControllerWS
     */
    @WebEndpoint(name = "DecisionControllerWS")
    public DecisionControllerWS getDecisionControllerWS() {
        return super.getPort(DecisionControllerWS, DecisionControllerWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DecisionControllerWS
     */
    @WebEndpoint(name = "DecisionControllerWS")
    public DecisionControllerWS getDecisionControllerWS(WebServiceFeature... features) {
        return super.getPort(DecisionControllerWS, DecisionControllerWS.class, features);
    }

}