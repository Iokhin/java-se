package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.entityDTO.UserDTO;
import ru.iokhin.tm.api.endpoint.UserEndpoint;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.enumerated.RoleType;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
public class UserEndpointBean implements UserEndpoint {

    @NotNull
    private IUserService userService;

    @NotNull
    private ISessionService sessionService;

    @Inject
    public UserEndpointBean(@NotNull final IUserService userService, @NotNull final ISessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Override
    @SneakyThrows
    public UserDTO addUser(@WebParam(name = "login") @NotNull final String login,
                           @WebParam(name = "password") @NotNull final String password) {
        return userService.add(RoleType.USER, login, password);
    }

    @Override
    @SneakyThrows
    public UserDTO editUser(@WebParam(name = "session") @NotNull final SessionDTO session,
                            @WebParam(name = "newLogin") @NotNull final String newLogin,
                            @WebParam(name = "newPassword") @NotNull final String newPassword) {
        sessionService.validate(session);
        return userService.edit(session.getParentId(), newLogin, newPassword);
    }

    @Override
    @SneakyThrows
    public UserDTO findByLogin(@WebParam(name = "login") @NotNull final String login) {
        return userService.findByLogin(login);
    }

    @Override
    public UserDTO getCurrentUser() {
        return userService.getCurrentUser();
    }

    @Override
    @WebMethod
    @SneakyThrows
    public SessionDTO authUser(@WebParam(name = "login") @NotNull final String login,
                               @WebParam(name = "password") @NotNull final String password) {
        UserDTO user = userService.authUser(login, password);
        if (user == null) return null;
        return sessionService.create(user.getId());
    }

    @Override
    @SneakyThrows
    public UserDTO findUserById(@WebParam(name = "id") @NotNull final String id) {
        return userService.findOne(id);
    }

    @Override
    public UserDTO removeUserById(@WebParam(name = "id") @NotNull final String id) {
        return userService.removeById(id);
    }

    @Override
    @SneakyThrows
    public boolean passChange(@WebParam(name = "oldPassword") @NotNull final String oldPassword,
                              @WebParam(name = "newPassword") @NotNull final String newPassword) {
        return userService.changePassword(oldPassword, newPassword);
    }

    @Override
    @SneakyThrows
    public void dataBinSave() {
        userService.dataBinSave();
    }

    @Override
    @SneakyThrows
    public void dataBinLoad() {
        userService.dataBinLoad();
    }

    @Override
    @SneakyThrows
    public void dataJAXBXMLSave() {
        userService.dataJAXBXMLSave();
    }

    @Override
    @SneakyThrows
    public void dataJAXBXMLLoad() {
        userService.dataJAXBXMLLoad();
    }

    @Override
    @SneakyThrows
    public void dataJAXBJSONSave() {
        userService.dataJAXBJSONSave();
    }

    @Override
    @SneakyThrows
    public void dataJAXBJSONLoad() {
        userService.dataJAXBJSONLoad();
    }

    @Override
    @SneakyThrows
    public void dataFasterXMLSave() {
        userService.dataFasterXMLSave();
    }

    @Override
    @SneakyThrows
    public void dataFasterXMLLoad() {
        userService.dataFasterXMLLoad();
    }

    @Override
    @SneakyThrows
    public void dataFasterJSONLoad() {
        userService.dataFasterJSONLoad();
    }

    @Override
    @SneakyThrows
    public void dataFasterJSONSave() {
        userService.dataFasterJSONSave();
    }
}
