
package ru.iokhin.tm.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.iokhin.tm.endpoint package. 
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

    private final static QName _GetServerInformation_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "getServerInformation");
    private final static QName _GetServerInformationResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "getServerInformationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.iokhin.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetServerInformation }
     * 
     */
    public GetServerInformation createGetServerInformation() {
        return new GetServerInformation();
    }

    /**
     * Create an instance of {@link GetServerInformationResponse }
     * 
     */
    public GetServerInformationResponse createGetServerInformationResponse() {
        return new GetServerInformationResponse();
    }

    /**
     * Create an instance of {@link ServerInfo }
     * 
     */
    public ServerInfo createServerInfo() {
        return new ServerInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "getServerInformation")
    public JAXBElement<GetServerInformation> createGetServerInformation(GetServerInformation value) {
        return new JAXBElement<GetServerInformation>(_GetServerInformation_QNAME, GetServerInformation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetServerInformationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "getServerInformationResponse")
    public JAXBElement<GetServerInformationResponse> createGetServerInformationResponse(GetServerInformationResponse value) {
        return new JAXBElement<GetServerInformationResponse>(_GetServerInformationResponse_QNAME, GetServerInformationResponse.class, null, value);
    }

}
