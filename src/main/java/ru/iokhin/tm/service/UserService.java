package ru.iokhin.tm.service;

import ru.iokhin.tm.api.IUserService;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.UserRepository;

import java.util.Map;

public class UserService implements IUserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


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
        for (User user : userRepository.userMap.values()) {
            if (user.getUserId().equals(userId)) {
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
    public void editUser(RoleType roleType, String userId, String newLogin, String newPasswordHash) {
        for (User user : userRepository.userMap.values()) {
            if (user.getUserId().equals(userId)) {
                User newUser = new User(roleType, newLogin, newPasswordHash);
                userRepository.merge(newUser);
                return;
            }
        }
    }

    public Map<String, User> getAllUsers() {
        return userRepository.getUserMap();
    }
}
