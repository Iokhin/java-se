package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;

public interface IUserService extends IService<User> {
    User add(@NotNull final RoleType roleType, @NotNull final String login, @NotNull final String password);

    User add(@NotNull final RoleType roleType, @NotNull final String id, @NotNull final String login, @NotNull final String password);

    User edit(@NotNull final String userId, @NotNull final String newLogin, @NotNull final String newPasswordHash);

    User findByLogin(@NotNull final String login);

    User getCurrentUser();

    void setCurrentUser(@Nullable final User user);

    User authUser(@NotNull String login, @NotNull String password) throws AuthException;
}
