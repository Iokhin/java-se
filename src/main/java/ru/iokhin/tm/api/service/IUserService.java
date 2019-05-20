package ru.iokhin.tm.api.service;

import ru.iokhin.tm.enumerated.RoleType;

public interface IUserService {

    void addUser(RoleType roleType, String login, String password);

    void listUser();

    void removeUser(String id);

    void clearUser();

    void editUser(RoleType roleType, String userId, String newLogin,
                  String newPasswordHash);

}
