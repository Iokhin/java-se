package ru.iokhin.tm.entity;

import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.enumerated.RoleType;

import java.util.UUID;

public class User {
    private String userId;
    private String login;
    private String passwordHash;
    private RoleType roleType;

    public User(RoleType roleType, String login, String password) {
        this.roleType = roleType;
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
        this.userId = UUID.randomUUID().toString();
    }

    public User(RoleType roleType, String login, String password, String userId) {
        this.roleType = roleType;
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
        this.userId = userId;
    }

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = MD5Util.passwordToHash(password);
    }
}
