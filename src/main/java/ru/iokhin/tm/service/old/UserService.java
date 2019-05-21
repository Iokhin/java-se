package ru.iokhin.tm.service.old;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.old.UserRepositoryOLD;

import java.util.Map;

@RequiredArgsConstructor
public final class UserService implements IUserService {

    @NotNull
    private final UserRepositoryOLD userRepository;


    @Override
    public void addUser(RoleType roleType, String login, String password) {
        userRepository.add(new User(roleType, login, password));
    }

    @Override
    public void listUser() {
        userRepository.list();
    }

    @Override
    public void removeUser(String userId) {
        for (@NotNull User user : userRepository.userMap.values()) {
            if (user.getId().equals(userId)) {
                userRepository.delete(userId);
                return;
            }
        }
    }

    @Override
    public void clearUser() {
        userRepository.clear();
    }

    @Override
    public void editUser(@NotNull RoleType roleType, @NotNull String userId, @NotNull String newLogin, @NotNull String newPasswordHash) {
        for (User user : userRepository.userMap.values()) {
            if (user.getId().equals(userId)) {

                @NotNull
                User newUser = new User(roleType, newLogin, newPasswordHash);

                userRepository.merge(newUser);
                return;
            }
        }
    }

    public Map<String, User> getAllUsers() {
        return userRepository.getUserMap();
    }

    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }
}
