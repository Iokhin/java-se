
package ru.iokhin.tm.endpoint;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-05-31T11:32:36.581+03:00
 * Generated source version: 3.2.7
 */

@WebFault(name = "JAXBException", targetNamespace = "http://endpoint.tm.iokhin.ru/")
public class JAXBException_Exception extends Exception {

    private ru.iokhin.tm.endpoint.JAXBException jaxbException;

    public JAXBException_Exception() {
        super();
    }

    public JAXBException_Exception(String message) {
        super(message);
    }

    public JAXBException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public JAXBException_Exception(String message, ru.iokhin.tm.endpoint.JAXBException jaxbException) {
        super(message);
        this.jaxbException = jaxbException;
    }

    public JAXBException_Exception(String message, ru.iokhin.tm.endpoint.JAXBException jaxbException, java.lang.Throwable cause) {
        super(message, cause);
        this.jaxbException = jaxbException;
    }

    public ru.iokhin.tm.endpoint.JAXBException getFaultInfo() {
        return this.jaxbException;
    }
}