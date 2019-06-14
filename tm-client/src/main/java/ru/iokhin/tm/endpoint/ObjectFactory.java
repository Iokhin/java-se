
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

    private final static QName _AddUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "addUser");
    private final static QName _AddUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "addUserResponse");
    private final static QName _AuthUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "authUser");
    private final static QName _AuthUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "authUserResponse");
    private final static QName _DataBinLoad_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataBinLoad");
    private final static QName _DataBinLoadResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataBinLoadResponse");
    private final static QName _DataBinSave_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataBinSave");
    private final static QName _DataBinSaveResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataBinSaveResponse");
    private final static QName _DataFasterJSONLoad_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterJSONLoad");
    private final static QName _DataFasterJSONLoadResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterJSONLoadResponse");
    private final static QName _DataFasterJSONSave_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterJSONSave");
    private final static QName _DataFasterJSONSaveResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterJSONSaveResponse");
    private final static QName _DataFasterXMLLoad_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterXMLLoad");
    private final static QName _DataFasterXMLLoadResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterXMLLoadResponse");
    private final static QName _DataFasterXMLSave_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterXMLSave");
    private final static QName _DataFasterXMLSaveResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataFasterXMLSaveResponse");
    private final static QName _DataJAXBJSONLoad_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBJSONLoad");
    private final static QName _DataJAXBJSONLoadResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBJSONLoadResponse");
    private final static QName _DataJAXBJSONSave_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBJSONSave");
    private final static QName _DataJAXBJSONSaveResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBJSONSaveResponse");
    private final static QName _DataJAXBXMLLoad_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBXMLLoad");
    private final static QName _DataJAXBXMLLoadResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBXMLLoadResponse");
    private final static QName _DataJAXBXMLSave_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBXMLSave");
    private final static QName _DataJAXBXMLSaveResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "dataJAXBXMLSaveResponse");
    private final static QName _EditUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "editUser");
    private final static QName _EditUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "editUserResponse");
    private final static QName _FindByLogin_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findByLogin");
    private final static QName _FindByLoginResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findByLoginResponse");
    private final static QName _FindUserById_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findUserById");
    private final static QName _FindUserByIdResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "findUserByIdResponse");
    private final static QName _GetCurrentUser_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "getCurrentUser");
    private final static QName _GetCurrentUserResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "getCurrentUserResponse");
    private final static QName _PassChange_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "passChange");
    private final static QName _PassChangeResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "passChangeResponse");
    private final static QName _RemoveUserById_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "removeUserById");
    private final static QName _RemoveUserByIdResponse_QNAME = new QName("http://endpoint.tm.iokhin.ru/", "removeUserByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.iokhin.tm.endpoint
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link DataBinLoad }
     * 
     */
    public DataBinLoad createDataBinLoad() {
        return new DataBinLoad();
    }

    /**
     * Create an instance of {@link DataBinLoadResponse }
     * 
     */
    public DataBinLoadResponse createDataBinLoadResponse() {
        return new DataBinLoadResponse();
    }

    /**
     * Create an instance of {@link DataBinSave }
     * 
     */
    public DataBinSave createDataBinSave() {
        return new DataBinSave();
    }

    /**
     * Create an instance of {@link DataBinSaveResponse }
     * 
     */
    public DataBinSaveResponse createDataBinSaveResponse() {
        return new DataBinSaveResponse();
    }

    /**
     * Create an instance of {@link DataFasterJSONLoad }
     * 
     */
    public DataFasterJSONLoad createDataFasterJSONLoad() {
        return new DataFasterJSONLoad();
    }

    /**
     * Create an instance of {@link DataFasterJSONLoadResponse }
     * 
     */
    public DataFasterJSONLoadResponse createDataFasterJSONLoadResponse() {
        return new DataFasterJSONLoadResponse();
    }

    /**
     * Create an instance of {@link DataFasterJSONSave }
     * 
     */
    public DataFasterJSONSave createDataFasterJSONSave() {
        return new DataFasterJSONSave();
    }

    /**
     * Create an instance of {@link DataFasterJSONSaveResponse }
     * 
     */
    public DataFasterJSONSaveResponse createDataFasterJSONSaveResponse() {
        return new DataFasterJSONSaveResponse();
    }

    /**
     * Create an instance of {@link DataFasterXMLLoad }
     * 
     */
    public DataFasterXMLLoad createDataFasterXMLLoad() {
        return new DataFasterXMLLoad();
    }

    /**
     * Create an instance of {@link DataFasterXMLLoadResponse }
     * 
     */
    public DataFasterXMLLoadResponse createDataFasterXMLLoadResponse() {
        return new DataFasterXMLLoadResponse();
    }

    /**
     * Create an instance of {@link DataFasterXMLSave }
     * 
     */
    public DataFasterXMLSave createDataFasterXMLSave() {
        return new DataFasterXMLSave();
    }

    /**
     * Create an instance of {@link DataFasterXMLSaveResponse }
     * 
     */
    public DataFasterXMLSaveResponse createDataFasterXMLSaveResponse() {
        return new DataFasterXMLSaveResponse();
    }

    /**
     * Create an instance of {@link DataJAXBJSONLoad }
     * 
     */
    public DataJAXBJSONLoad createDataJAXBJSONLoad() {
        return new DataJAXBJSONLoad();
    }

    /**
     * Create an instance of {@link DataJAXBJSONLoadResponse }
     * 
     */
    public DataJAXBJSONLoadResponse createDataJAXBJSONLoadResponse() {
        return new DataJAXBJSONLoadResponse();
    }

    /**
     * Create an instance of {@link DataJAXBJSONSave }
     * 
     */
    public DataJAXBJSONSave createDataJAXBJSONSave() {
        return new DataJAXBJSONSave();
    }

    /**
     * Create an instance of {@link DataJAXBJSONSaveResponse }
     * 
     */
    public DataJAXBJSONSaveResponse createDataJAXBJSONSaveResponse() {
        return new DataJAXBJSONSaveResponse();
    }

    /**
     * Create an instance of {@link DataJAXBXMLLoad }
     * 
     */
    public DataJAXBXMLLoad createDataJAXBXMLLoad() {
        return new DataJAXBXMLLoad();
    }

    /**
     * Create an instance of {@link DataJAXBXMLLoadResponse }
     * 
     */
    public DataJAXBXMLLoadResponse createDataJAXBXMLLoadResponse() {
        return new DataJAXBXMLLoadResponse();
    }

    /**
     * Create an instance of {@link DataJAXBXMLSave }
     * 
     */
    public DataJAXBXMLSave createDataJAXBXMLSave() {
        return new DataJAXBXMLSave();
    }

    /**
     * Create an instance of {@link DataJAXBXMLSaveResponse }
     * 
     */
    public DataJAXBXMLSaveResponse createDataJAXBXMLSaveResponse() {
        return new DataJAXBXMLSaveResponse();
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
     * Create an instance of {@link FindUserById }
     * 
     */
    public FindUserById createFindUserById() {
        return new FindUserById();
    }

    /**
     * Create an instance of {@link FindUserByIdResponse }
     * 
     */
    public FindUserByIdResponse createFindUserByIdResponse() {
        return new FindUserByIdResponse();
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
     * Create an instance of {@link RemoveUserById }
     * 
     */
    public RemoveUserById createRemoveUserById() {
        return new RemoveUserById();
    }

    /**
     * Create an instance of {@link RemoveUserByIdResponse }
     * 
     */
    public RemoveUserByIdResponse createRemoveUserByIdResponse() {
        return new RemoveUserByIdResponse();
    }

    /**
     * Create an instance of {@link UserDTO }
     * 
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link AbstractEntityDTO }
     * 
     */
    public AbstractEntityDTO createAbstractEntityDTO() {
        return new AbstractEntityDTO();
    }

    /**
     * Create an instance of {@link SessionDTO }
     * 
     */
    public SessionDTO createSessionDTO() {
        return new SessionDTO();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DataBinLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataBinLoad")
    public JAXBElement<DataBinLoad> createDataBinLoad(DataBinLoad value) {
        return new JAXBElement<DataBinLoad>(_DataBinLoad_QNAME, DataBinLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataBinLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataBinLoadResponse")
    public JAXBElement<DataBinLoadResponse> createDataBinLoadResponse(DataBinLoadResponse value) {
        return new JAXBElement<DataBinLoadResponse>(_DataBinLoadResponse_QNAME, DataBinLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataBinSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataBinSave")
    public JAXBElement<DataBinSave> createDataBinSave(DataBinSave value) {
        return new JAXBElement<DataBinSave>(_DataBinSave_QNAME, DataBinSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataBinSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataBinSaveResponse")
    public JAXBElement<DataBinSaveResponse> createDataBinSaveResponse(DataBinSaveResponse value) {
        return new JAXBElement<DataBinSaveResponse>(_DataBinSaveResponse_QNAME, DataBinSaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterJSONLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterJSONLoad")
    public JAXBElement<DataFasterJSONLoad> createDataFasterJSONLoad(DataFasterJSONLoad value) {
        return new JAXBElement<DataFasterJSONLoad>(_DataFasterJSONLoad_QNAME, DataFasterJSONLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterJSONLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterJSONLoadResponse")
    public JAXBElement<DataFasterJSONLoadResponse> createDataFasterJSONLoadResponse(DataFasterJSONLoadResponse value) {
        return new JAXBElement<DataFasterJSONLoadResponse>(_DataFasterJSONLoadResponse_QNAME, DataFasterJSONLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterJSONSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterJSONSave")
    public JAXBElement<DataFasterJSONSave> createDataFasterJSONSave(DataFasterJSONSave value) {
        return new JAXBElement<DataFasterJSONSave>(_DataFasterJSONSave_QNAME, DataFasterJSONSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterJSONSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterJSONSaveResponse")
    public JAXBElement<DataFasterJSONSaveResponse> createDataFasterJSONSaveResponse(DataFasterJSONSaveResponse value) {
        return new JAXBElement<DataFasterJSONSaveResponse>(_DataFasterJSONSaveResponse_QNAME, DataFasterJSONSaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterXMLLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterXMLLoad")
    public JAXBElement<DataFasterXMLLoad> createDataFasterXMLLoad(DataFasterXMLLoad value) {
        return new JAXBElement<DataFasterXMLLoad>(_DataFasterXMLLoad_QNAME, DataFasterXMLLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterXMLLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterXMLLoadResponse")
    public JAXBElement<DataFasterXMLLoadResponse> createDataFasterXMLLoadResponse(DataFasterXMLLoadResponse value) {
        return new JAXBElement<DataFasterXMLLoadResponse>(_DataFasterXMLLoadResponse_QNAME, DataFasterXMLLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterXMLSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterXMLSave")
    public JAXBElement<DataFasterXMLSave> createDataFasterXMLSave(DataFasterXMLSave value) {
        return new JAXBElement<DataFasterXMLSave>(_DataFasterXMLSave_QNAME, DataFasterXMLSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataFasterXMLSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataFasterXMLSaveResponse")
    public JAXBElement<DataFasterXMLSaveResponse> createDataFasterXMLSaveResponse(DataFasterXMLSaveResponse value) {
        return new JAXBElement<DataFasterXMLSaveResponse>(_DataFasterXMLSaveResponse_QNAME, DataFasterXMLSaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBJSONLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBJSONLoad")
    public JAXBElement<DataJAXBJSONLoad> createDataJAXBJSONLoad(DataJAXBJSONLoad value) {
        return new JAXBElement<DataJAXBJSONLoad>(_DataJAXBJSONLoad_QNAME, DataJAXBJSONLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBJSONLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBJSONLoadResponse")
    public JAXBElement<DataJAXBJSONLoadResponse> createDataJAXBJSONLoadResponse(DataJAXBJSONLoadResponse value) {
        return new JAXBElement<DataJAXBJSONLoadResponse>(_DataJAXBJSONLoadResponse_QNAME, DataJAXBJSONLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBJSONSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBJSONSave")
    public JAXBElement<DataJAXBJSONSave> createDataJAXBJSONSave(DataJAXBJSONSave value) {
        return new JAXBElement<DataJAXBJSONSave>(_DataJAXBJSONSave_QNAME, DataJAXBJSONSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBJSONSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBJSONSaveResponse")
    public JAXBElement<DataJAXBJSONSaveResponse> createDataJAXBJSONSaveResponse(DataJAXBJSONSaveResponse value) {
        return new JAXBElement<DataJAXBJSONSaveResponse>(_DataJAXBJSONSaveResponse_QNAME, DataJAXBJSONSaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBXMLLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBXMLLoad")
    public JAXBElement<DataJAXBXMLLoad> createDataJAXBXMLLoad(DataJAXBXMLLoad value) {
        return new JAXBElement<DataJAXBXMLLoad>(_DataJAXBXMLLoad_QNAME, DataJAXBXMLLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBXMLLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBXMLLoadResponse")
    public JAXBElement<DataJAXBXMLLoadResponse> createDataJAXBXMLLoadResponse(DataJAXBXMLLoadResponse value) {
        return new JAXBElement<DataJAXBXMLLoadResponse>(_DataJAXBXMLLoadResponse_QNAME, DataJAXBXMLLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBXMLSave }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBXMLSave")
    public JAXBElement<DataJAXBXMLSave> createDataJAXBXMLSave(DataJAXBXMLSave value) {
        return new JAXBElement<DataJAXBXMLSave>(_DataJAXBXMLSave_QNAME, DataJAXBXMLSave.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataJAXBXMLSaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "dataJAXBXMLSaveResponse")
    public JAXBElement<DataJAXBXMLSaveResponse> createDataJAXBXMLSaveResponse(DataJAXBXMLSaveResponse value) {
        return new JAXBElement<DataJAXBXMLSaveResponse>(_DataJAXBXMLSaveResponse_QNAME, DataJAXBXMLSaveResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "findUserById")
    public JAXBElement<FindUserById> createFindUserById(FindUserById value) {
        return new JAXBElement<FindUserById>(_FindUserById_QNAME, FindUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "findUserByIdResponse")
    public JAXBElement<FindUserByIdResponse> createFindUserByIdResponse(FindUserByIdResponse value) {
        return new JAXBElement<FindUserByIdResponse>(_FindUserByIdResponse_QNAME, FindUserByIdResponse.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "removeUserById")
    public JAXBElement<RemoveUserById> createRemoveUserById(RemoveUserById value) {
        return new JAXBElement<RemoveUserById>(_RemoveUserById_QNAME, RemoveUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.iokhin.ru/", name = "removeUserByIdResponse")
    public JAXBElement<RemoveUserByIdResponse> createRemoveUserByIdResponse(RemoveUserByIdResponse value) {
        return new JAXBElement<RemoveUserByIdResponse>(_RemoveUserByIdResponse_QNAME, RemoveUserByIdResponse.class, null, value);
    }

}
