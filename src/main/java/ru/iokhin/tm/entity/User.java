package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.enumerated.RoleType;

import java.util.UUID;

@Getter
@Setter
public final class User {

    @NotNull
    private String userId;

    @Nullable
    private String login;

    @Nullable
    private String passwordHash;

    @Nullable
    private RoleType roleType;

    public User(@NotNull RoleType roleType, @NotNull String login, @NotNull String password) {
        this.roleType = roleType;
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
        this.userId = UUID.randomUUID().toString();
    }

    public User() {

    }

    public void setPasswordHash(String password) {
        this.passwordHash = MD5Util.passwordToHash(password);
    }
}
