package ru.iokhin.tm.service;

import ru.iokhin.tm.RoleType;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.UserRepository;

public class UserService {

    UserRepository ur;

    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public void createUser(RoleType roleType, String login, String password) {
        ur.persistUserRepositoryItem(new User(roleType, login, password));
    }

    public void listUser() {
        ur.findAllUserRepositoryItem("");
    }

    public void clearUser() {
        ur.userMap.clear();
    }

    public void editUser(RoleType roleType ,String userId, String newLogin, String newPasswordHash) {
        for (User user : ur.userMap.values()) {
            if (user.getUserId().equals(userId)) {
                User newUser = new User(roleType, newLogin, newPasswordHash);
                ur.mergeUserRepositoryItem(newUser);
                return;
            }
        }
    }

    public void  removeUser(String userId) {
        for (User user : ur.userMap.values()) {
            if (user.getUserId().equals(userId)) {
                ur.removeUserRepositoryItem(userId);
                return;
            }
        }
    }
}
