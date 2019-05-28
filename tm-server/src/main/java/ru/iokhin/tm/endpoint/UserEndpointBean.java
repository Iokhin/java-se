package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.endpoint.UserEndpoint;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.MD5Util;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
public class UserEndpointBean implements UserEndpoint {

    @NotNull
    private IUserService userService;

    @NotNull
    private ISessionService sessionService;

    public UserEndpointBean(IServiceLocator serviceLocator) {
        userService = serviceLocator.getUserService();
        sessionService = serviceLocator.getSessionService();
    }

    @Override
    public User addUser(@NotNull RoleType roleType,
                        @WebParam(name = "login") @NotNull final String login,
                        @WebParam(name = "password") @NotNull final String password) {
        return userService.add(RoleType.USER, login, password);
    }

//    @Override
//    public User addUser(@NotNull RoleType roleType, @NotNull String id, @NotNull String login, @NotNull String password) {
//        return null;
//    }

    @Override
    public User editUser(@WebParam(name = "session") @NotNull final Session session,
                         @WebParam(name = "newLogin") @NotNull final String newLogin,
                         @WebParam(name = "newPasswordHash") @NotNull final String newPasswordHash) throws AuthException { //newPasswordHash????
        sessionService.validate(session);
        return userService.edit(session.getParentId(), newLogin, MD5Util.passwordToHash(newPasswordHash));
    }

    @Override
    public User findByLogin(@WebParam(name = "login") @NotNull final String login) {
        return userService.findByLogin(login);
    }

    @Override
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

//    @Override
//    public void setCurrentUser(@Nullable Session session) {
//        userService.setCurrentUser();
//    }

    @Override
    public User authUser(@WebParam(name = "login") @NotNull final String login,
                         @WebParam(name = "password") @NotNull final String password) throws AuthException {
        return userService.authUser(login, password);
    }
}
