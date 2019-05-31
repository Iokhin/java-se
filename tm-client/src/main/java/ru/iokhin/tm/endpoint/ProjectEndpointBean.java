package ru.iokhin.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-05-31T13:04:49.275+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.iokhin.ru/", name = "ProjectEndpointBean")
@XmlSeeAlso({ObjectFactory.class})
public interface ProjectEndpointBean {

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findOneRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findOneResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findOne/Fault/AuthException")})
    @RequestWrapper(localName = "findOne", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindOne")
    @ResponseWrapper(localName = "findOneResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindOneResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.Project findOne(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    ) throws AuthException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/removeRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/removeResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/remove/Fault/AuthException")})
    @RequestWrapper(localName = "remove", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.Remove")
    @ResponseWrapper(localName = "removeResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.RemoveResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.Project remove(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    ) throws AuthException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findByPartOfNameOrDescriptionRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findByPartOfNameOrDescriptionResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findByPartOfNameOrDescription/Fault/AuthException")})
    @RequestWrapper(localName = "findByPartOfNameOrDescription", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindByPartOfNameOrDescription")
    @ResponseWrapper(localName = "findByPartOfNameOrDescriptionResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindByPartOfNameOrDescriptionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.iokhin.tm.endpoint.Project> findByPartOfNameOrDescription(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session,
        @WebParam(name = "part", targetNamespace = "")
        java.lang.String part
    ) throws AuthException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/addRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/addResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/add/Fault/AuthException")})
    @RequestWrapper(localName = "add", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.AddResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.Project add(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    ) throws AuthException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/sortByUserIdRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/sortByUserIdResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/sortByUserId/Fault/AuthException")})
    @RequestWrapper(localName = "sortByUserId", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.SortByUserId")
    @ResponseWrapper(localName = "sortByUserIdResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.SortByUserIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.iokhin.tm.endpoint.Project> sortByUserId(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session,
        @WebParam(name = "comparator", targetNamespace = "")
        java.lang.String comparator
    ) throws AuthException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/removeAllByUserIdRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/removeAllByUserIdResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/removeAllByUserId/Fault/AuthException")})
    @RequestWrapper(localName = "removeAllByUserId", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.RemoveAllByUserId")
    @ResponseWrapper(localName = "removeAllByUserIdResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.RemoveAllByUserIdResponse")
    public void removeAllByUserId(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session
    ) throws AuthException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findAllByUserIdRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findAllByUserIdResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/findAllByUserId/Fault/AuthException")})
    @RequestWrapper(localName = "findAllByUserId", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindAllByUserId")
    @ResponseWrapper(localName = "findAllByUserIdResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindAllByUserIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.iokhin.tm.endpoint.Project> findAllByUserId(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session
    ) throws AuthException_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/editRequest", output = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/editResponse", fault = {@FaultAction(className = AuthException_Exception.class, value = "http://endpoint.tm.iokhin.ru/ProjectEndpointBean/edit/Fault/AuthException")})
    @RequestWrapper(localName = "edit", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.Edit")
    @ResponseWrapper(localName = "editResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.EditResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.Project edit(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "name", targetNamespace = "")
        java.lang.String name
    ) throws AuthException_Exception;
}
