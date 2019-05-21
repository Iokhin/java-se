package ru.iokhin.tm.repository.old;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;

import java.util.HashMap;
import java.util.Map;

@Getter
public final class UserRepositoryOLD implements IUserRepository {

    @NotNull
    public Map<String, User> userMap = new HashMap<>(0);

    @Override
    public void add(@NotNull User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void list() {

        @NotNull
        int i = 0;

        for (@NotNull User user : userMap.values()) {
            System.out.println(++i + ". " + user.getLogin() + ", " + user.getId());
        }
    }

    @Override
    public void merge(@NotNull User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void delete(@NotNull String id) {
        userMap.remove(id);
    }

    @Override
    public void clear() {
        userMap.clear();
    }

    public User findUserByLogin(String login) {
        for (User user : userMap.values()) {
            if (user.getLogin().equals(login)) return user;
        }
        return null;
    }
}