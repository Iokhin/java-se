package ru.iokhin.tm.endpoint;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-05-31T13:04:49.911+03:00
 * Generated source version: 3.2.7
 *
 */
@WebServiceClient(name = "UserEndpointBeanService",
                  wsdlLocation = "http://localhost:8080/UserEndpointBean?wsdl",
                  targetNamespace = "http://endpoint.tm.iokhin.ru/")
public class UserEndpointBeanService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://endpoint.tm.iokhin.ru/", "UserEndpointBeanService");
    public final static QName UserEndpointBeanPort = new QName("http://endpoint.tm.iokhin.ru/", "UserEndpointBeanPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/UserEndpointBean?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserEndpointBeanService.class.getName())
                .log(java.util.logging.Level.INFO,
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/UserEndpointBean?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public UserEndpointBeanService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserEndpointBeanService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserEndpointBeanService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public UserEndpointBeanService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserEndpointBeanService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserEndpointBeanService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }




    /**
     *
     * @return
     *     returns UserEndpointBean
     */
    @WebEndpoint(name = "UserEndpointBeanPort")
    public UserEndpointBean getUserEndpointBeanPort() {
        return super.getPort(UserEndpointBeanPort, UserEndpointBean.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserEndpointBean
     */
    @WebEndpoint(name = "UserEndpointBeanPort")
    public UserEndpointBean getUserEndpointBeanPort(WebServiceFeature... features) {
        return super.getPort(UserEndpointBeanPort, UserEndpointBean.class, features);
    }

}
