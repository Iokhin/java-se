package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.entity.User;

public class UserRepository extends AbstractRepository<User> implements IUserRepository {

    @Override
    public User findByLogin(@NotNull String login) {
        for (User user : findAll()) {
            if (user.getLogin().equals(login))
                return user;
        }
        return null;
    }
}
