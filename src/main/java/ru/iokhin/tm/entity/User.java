package ru.iokhin.tm.entity;

import ru.iokhin.tm.MD5Util;
import ru.iokhin.tm.RoleType;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                login.equals(user.login) &&
                passwordHash.equals(user.passwordHash) &&
                roleType == user.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, passwordHash, roleType);
    }
}
