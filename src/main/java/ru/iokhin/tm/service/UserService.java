package ru.iokhin.tm.service;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.util.MD5Util;

@Getter
@Setter
public class UserService extends AbstractService<User, IUserRepository> implements IUserService {

    @Nullable private User currentUser;

    public UserService(@NotNull final IUserRepository repository) {
        super(repository);
    }

    @Override
    public User add(@NotNull final RoleType roleType, @NotNull final String login, @NotNull final String password) {
        return repository.persist(new User(roleType, login, password));
    }

    //Method for testing data load
    @Override
    public User add(@NotNull RoleType roleType, @NotNull String id, @NotNull String login, @NotNull String password) {
        return repository.persist(new User(roleType, id, login, password));
    }

    @Override
    public User edit(@NotNull final String userId, @NotNull final String newLogin, @NotNull final String newPassword) {
        @Nullable final User user = findOne(userId);
        if (user == null) return null;
        user.setLogin(newLogin);
        user.setPasswordHash(MD5Util.passwordToHash(newPassword));
        return user;
    }

    @Override
    public User findByLogin(@NotNull final String login) {
        return repository.findByLogin(login);
    }
}
