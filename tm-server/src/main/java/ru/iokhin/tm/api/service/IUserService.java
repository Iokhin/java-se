package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.sql.SQLException;

public interface IUserService extends IService<User> {
    User add(@NotNull final RoleType roleType, @NotNull final String login, @NotNull final String password) throws SQLException;

    User add(@NotNull final RoleType roleType, @NotNull final String id, @NotNull final String login, @NotNull final String password) throws SQLException;

    User edit(@NotNull final String id, @NotNull final String newLogin, @NotNull final String newPasswordHash);

    User findByLogin(@NotNull final String login);

    User getCurrentUser();

    void setCurrentUser(@Nullable final User user);

    User authUser(@NotNull String login, @NotNull String password) throws AuthException;

    boolean changePassword(@NotNull String oldPassword, @NotNull String newPassword);

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
