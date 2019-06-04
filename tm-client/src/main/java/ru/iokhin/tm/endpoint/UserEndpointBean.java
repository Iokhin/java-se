package ru.iokhin.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-06-04T10:28:45.155+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.iokhin.ru/", name = "UserEndpointBean")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndpointBean {

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterJSONLoadRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterJSONLoadResponse")
    @RequestWrapper(localName = "dataFasterJSONLoad", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterJSONLoad")
    @ResponseWrapper(localName = "dataFasterJSONLoadResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterJSONLoadResponse")
    public void dataFasterJSONLoad();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataBinLoadRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataBinLoadResponse")
    @RequestWrapper(localName = "dataBinLoad", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataBinLoad")
    @ResponseWrapper(localName = "dataBinLoadResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataBinLoadResponse")
    public void dataBinLoad();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/findUserByIdRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/findUserByIdResponse")
    @RequestWrapper(localName = "findUserById", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindUserById")
    @ResponseWrapper(localName = "findUserByIdResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindUserByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.User findUserById(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBJSONSaveRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBJSONSaveResponse")
    @RequestWrapper(localName = "dataJAXBJSONSave", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBJSONSave")
    @ResponseWrapper(localName = "dataJAXBJSONSaveResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBJSONSaveResponse")
    public void dataJAXBJSONSave();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/passChangeRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/passChangeResponse")
    @RequestWrapper(localName = "passChange", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.PassChange")
    @ResponseWrapper(localName = "passChangeResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.PassChangeResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean passChange(
        @WebParam(name = "oldPassword", targetNamespace = "")
        java.lang.String oldPassword,
        @WebParam(name = "newPassword", targetNamespace = "")
        java.lang.String newPassword
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBJSONLoadRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBJSONLoadResponse")
    @RequestWrapper(localName = "dataJAXBJSONLoad", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBJSONLoad")
    @ResponseWrapper(localName = "dataJAXBJSONLoadResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBJSONLoadResponse")
    public void dataJAXBJSONLoad();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterXMLLoadRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterXMLLoadResponse")
    @RequestWrapper(localName = "dataFasterXMLLoad", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterXMLLoad")
    @ResponseWrapper(localName = "dataFasterXMLLoadResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterXMLLoadResponse")
    public void dataFasterXMLLoad();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBXMLSaveRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBXMLSaveResponse")
    @RequestWrapper(localName = "dataJAXBXMLSave", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBXMLSave")
    @ResponseWrapper(localName = "dataJAXBXMLSaveResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBXMLSaveResponse")
    public void dataJAXBXMLSave();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/editUserRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/editUserResponse")
    @RequestWrapper(localName = "editUser", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.EditUser")
    @ResponseWrapper(localName = "editUserResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.EditUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.User editUser(
        @WebParam(name = "session", targetNamespace = "")
        ru.iokhin.tm.endpoint.Session session,
        @WebParam(name = "newLogin", targetNamespace = "")
        java.lang.String newLogin,
        @WebParam(name = "newPassword", targetNamespace = "")
        java.lang.String newPassword
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/authUserRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/authUserResponse")
    @RequestWrapper(localName = "authUser", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.AuthUser")
    @ResponseWrapper(localName = "authUserResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.AuthUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.Session authUser(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/findByLoginRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/findByLoginResponse")
    @RequestWrapper(localName = "findByLogin", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindByLogin")
    @ResponseWrapper(localName = "findByLoginResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.FindByLoginResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.User findByLogin(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBXMLLoadRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataJAXBXMLLoadResponse")
    @RequestWrapper(localName = "dataJAXBXMLLoad", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBXMLLoad")
    @ResponseWrapper(localName = "dataJAXBXMLLoadResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataJAXBXMLLoadResponse")
    public void dataJAXBXMLLoad();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/getCurrentUserRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/getCurrentUserResponse")
    @RequestWrapper(localName = "getCurrentUser", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.GetCurrentUser")
    @ResponseWrapper(localName = "getCurrentUserResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.GetCurrentUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.User getCurrentUser();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterXMLSaveRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterXMLSaveResponse")
    @RequestWrapper(localName = "dataFasterXMLSave", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterXMLSave")
    @ResponseWrapper(localName = "dataFasterXMLSaveResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterXMLSaveResponse")
    public void dataFasterXMLSave();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataBinSaveRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataBinSaveResponse")
    @RequestWrapper(localName = "dataBinSave", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataBinSave")
    @ResponseWrapper(localName = "dataBinSaveResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataBinSaveResponse")
    public void dataBinSave();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterJSONSaveRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/dataFasterJSONSaveResponse")
    @RequestWrapper(localName = "dataFasterJSONSave", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterJSONSave")
    @ResponseWrapper(localName = "dataFasterJSONSaveResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.DataFasterJSONSaveResponse")
    public void dataFasterJSONSave();

    @WebMethod
    @Action(input = "http://endpoint.tm.iokhin.ru/UserEndpointBean/addUserRequest", output = "http://endpoint.tm.iokhin.ru/UserEndpointBean/addUserResponse")
    @RequestWrapper(localName = "addUser", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.AddUser")
    @ResponseWrapper(localName = "addUserResponse", targetNamespace = "http://endpoint.tm.iokhin.ru/", className = "ru.iokhin.tm.endpoint.AddUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.iokhin.tm.endpoint.User addUser(
        @WebParam(name = "login", targetNamespace = "")
        java.lang.String login,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password
    );
}
