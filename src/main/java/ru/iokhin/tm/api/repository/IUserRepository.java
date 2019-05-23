package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.User;

public interface IUserRepository extends IRepository<User> {

    User findByLogin(@NotNull final String login);

}
