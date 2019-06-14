package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.UserDTO;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

public interface IUserService extends IService<UserDTO> {
    UserDTO add(@NotNull final RoleType roleType, @NotNull final String login, @NotNull final String password) throws SQLException;

    UserDTO add(@NotNull final RoleType roleType, @NotNull final String id, @NotNull final String login, @NotNull final String password) throws SQLException;

    UserDTO edit(@NotNull final String id, @NotNull final String newLogin, @NotNull final String newPasswordHash);

    UserDTO findByLogin(@NotNull final String login);

    UserDTO findOne(@NotNull String id);

    UserDTO removeById(@NotNull String id);

    UserDTO getCurrentUser();

    void setCurrentUser(@Nullable final UserDTO user);

    UserDTO authUser(@NotNull String login, @NotNull String password) throws AuthException;

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

    User getUserFromDTO(UserDTO userDTO);

}
