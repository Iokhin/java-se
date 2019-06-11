package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.entityDTO.UserDTO;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.sql.SQLException;

public interface UserEndpoint {

    UserDTO addUser(@WebParam(name = "login") @NotNull final String login,
                    @WebParam(name = "password") @NotNull final String password) throws SQLException;

    UserDTO editUser(@WebParam(name = "session") @NotNull final SessionDTO session,
                     @WebParam(name = "newLogin") @NotNull final String newLogin,
                     @WebParam(name = "newPasswordHash") @NotNull final String newPasswordHash) throws AuthException;

    UserDTO findByLogin(@WebParam(name = "login") @NotNull final String login);

    UserDTO getCurrentUser();

    @WebMethod
    SessionDTO authUser(@WebParam(name = "login") @NotNull String login,
                        @WebParam(name = "password") @NotNull String password) throws AuthException, SOAPException, SQLException;

    UserDTO findUserById(@WebParam(name = "id") @NotNull final String id);

    boolean passChange(@WebParam(name = "oldPassword") @NotNull final String oldPassword,
                       @WebParam(name = "newPassword") @NotNull final String newPassword);

    void dataBinSave() throws IOException;

    void dataBinLoad() throws IOException, ClassNotFoundException;

    void dataJAXBXMLSave() throws JAXBException;

    void dataJAXBXMLLoad() throws JAXBException;

    void dataJAXBJSONSave() throws JAXBException;

    void dataJAXBJSONLoad() throws JAXBException;

    void dataFasterXMLSave() throws IOException;

    void dataFasterXMLLoad() throws IOException;

    void dataFasterJSONLoad() throws IOException;

    void dataFasterJSONSave() throws IOException;
}
