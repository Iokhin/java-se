package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;

public interface UserEndpoint {

    User addUser(@NotNull final RoleType roleType,
                 @WebParam(name = "login") @NotNull final String login,
                 @WebParam(name = "password") @NotNull final String password);

//    User addUser(@NotNull final RoleType roleType, @NotNull final String id, @NotNull final String login, @NotNull final String password);

    User editUser(@WebParam(name = "session") @NotNull final Session session,
                  @WebParam(name = "newLogin") @NotNull final String newLogin,
                  @WebParam(name = "newPasswordHash") @NotNull final String newPasswordHash) throws AuthException;

    User findByLogin(@WebParam(name = "login") @NotNull final String login);

    User getCurrentUser();

//    void setCurrentUser(@Nullable final Session session);

    User authUser(@WebParam(name = "login") @NotNull String login,
                  @WebParam(name = "password") @NotNull String password) throws AuthException;

}
