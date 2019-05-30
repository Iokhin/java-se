
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

    private final static QName _AuthException_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "AuthException");
    private final static QName _AddUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "addUser");
    private final static QName _AddUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "addUserResponse");
    private final static QName _AuthUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "authUser");
    private final static QName _AuthUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "authUserResponse");
    private final static QName _EditUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "editUser");
    private final static QName _EditUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "editUserResponse");
    private final static QName _FindById_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findById");
    private final static QName _FindByIdResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findByIdResponse");
    private final static QName _FindByLogin_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findByLogin");
    private final static QName _FindByLoginResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findByLoginResponse");
    private final static QName _GetCurrentUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "getCurrentUser");
    private final static QName _GetCurrentUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "getCurrentUserResponse");
    private final static QName _PassChange_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "passChange");
    private final static QName _PassChangeResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "passChangeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.iokhin.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthException }
     * 
     */
    public AuthException createAuthException() {
        return new AuthException();
    }

    /**
     * Create an instance of {@link AddUser }
     * 
     */
    public AddUser createAddUser() {
        return new AddUser();
    }

    /**
     * Create an instance of {@link AddUserResponse }
     * 
     */
    public AddUserResponse createAddUserResponse() {
        return new AddUserResponse();
    }

    /**
     * Create an instance of {@link AuthUser }
     * 
     */
    public AuthUser createAuthUser() {
        return new AuthUser();
    }

    /**
     * Create an instance of {@link AuthUserResponse }
     * 
     */
    public AuthUserResponse createAuthUserResponse() {
        return new AuthUserResponse();
    }

    /**
     * Create an instance of {@link EditUser }
     * 
     */
    public EditUser createEditUser() {
        return new EditUser();
    }

    /**
     * Create an instance of {@link EditUserResponse }
     * 
     */
    public EditUserResponse createEditUserResponse() {
        return new EditUserResponse();
    }

    /**
     * Create an instance of {@link FindById }
     * 
     */
    public FindById createFindById() {
        return new FindById();
    }

    /**
     * Create an instance of {@link FindByIdResponse }
     * 
     */
    public FindByIdResponse createFindByIdResponse() {
        return new FindByIdResponse();
    }

    /**
     * Create an instance of {@link FindByLogin }
     * 
     */
    public FindByLogin createFindByLogin() {
        return new FindByLogin();
    }

    /**
     * Create an instance of {@link FindByLoginResponse }
     * 
     */
    public FindByLoginResponse createFindByLoginResponse() {
        return new FindByLoginResponse();
    }

    /**
     * Create an instance of {@link GetCurrentUser }
     * 
     */
    public GetCurrentUser createGetCurrentUser() {
        return new GetCurrentUser();
    }

    /**
     * Create an instance of {@link GetCurrentUserResponse }
     * 
     */
    public GetCurrentUserResponse createGetCurrentUserResponse() {
        return new GetCurrentUserResponse();
    }

    /**
     * Create an instance of {@link PassChange }
     * 
     */
    public PassChange createPassChange() {
        return new PassChange();
    }

    /**
     * Create an instance of {@link PassChangeResponse }
     * 
     */
    public PassChangeResponse createPassChangeResponse() {
        return new PassChangeResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link AbstractEntity }
     * 
     */
    public AbstractEntity createAbstractEntity() {
        return new AbstractEntity();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "AuthException")
    public JAXBElement<AuthException> createAuthException(AuthException value) {
        return new JAXBElement<AuthException>(_AuthException_QNAME, AuthException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "addUser")
    public JAXBElement<AddUser> createAddUser(AddUser value) {
        return new JAXBElement<AddUser>(_AddUser_QNAME, AddUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "addUserResponse")
    public JAXBElement<AddUserResponse> createAddUserResponse(AddUserResponse value) {
        return new JAXBElement<AddUserResponse>(_AddUserResponse_QNAME, AddUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "authUser")
    public JAXBElement<AuthUser> createAuthUser(AuthUser value) {
        return new JAXBElement<AuthUser>(_AuthUser_QNAME, AuthUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "authUserResponse")
    public JAXBElement<AuthUserResponse> createAuthUserResponse(AuthUserResponse value) {
        return new JAXBElement<AuthUserResponse>(_AuthUserResponse_QNAME, AuthUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "editUser")
    public JAXBElement<EditUser> createEditUser(EditUser value) {
        return new JAXBElement<EditUser>(_EditUser_QNAME, EditUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "editUserResponse")
    public JAXBElement<EditUserResponse> createEditUserResponse(EditUserResponse value) {
        return new JAXBElement<EditUserResponse>(_EditUserResponse_QNAME, EditUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "findById")
    public JAXBElement<FindById> createFindById(FindById value) {
        return new JAXBElement<FindById>(_FindById_QNAME, FindById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "findByIdResponse")
    public JAXBElement<FindByIdResponse> createFindByIdResponse(FindByIdResponse value) {
        return new JAXBElement<FindByIdResponse>(_FindByIdResponse_QNAME, FindByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "findByLogin")
    public JAXBElement<FindByLogin> createFindByLogin(FindByLogin value) {
        return new JAXBElement<FindByLogin>(_FindByLogin_QNAME, FindByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "findByLoginResponse")
    public JAXBElement<FindByLoginResponse> createFindByLoginResponse(FindByLoginResponse value) {
        return new JAXBElement<FindByLoginResponse>(_FindByLoginResponse_QNAME, FindByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "getCurrentUser")
    public JAXBElement<GetCurrentUser> createGetCurrentUser(GetCurrentUser value) {
        return new JAXBElement<GetCurrentUser>(_GetCurrentUser_QNAME, GetCurrentUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "getCurrentUserResponse")
    public JAXBElement<GetCurrentUserResponse> createGetCurrentUserResponse(GetCurrentUserResponse value) {
        return new JAXBElement<GetCurrentUserResponse>(_GetCurrentUserResponse_QNAME, GetCurrentUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PassChange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "passChange")
    public JAXBElement<PassChange> createPassChange(PassChange value) {
        return new JAXBElement<PassChange>(_PassChange_QNAME, PassChange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PassChangeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "passChangeResponse")
    public JAXBElement<PassChangeResponse> createPassChangeResponse(PassChangeResponse value) {
        return new JAXBElement<PassChangeResponse>(_PassChangeResponse_QNAME, PassChangeResponse.class, null, value);
    }

}
