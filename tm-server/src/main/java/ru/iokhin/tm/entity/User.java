package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.enumerated.RoleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public final class User extends AbstractEntity {

    @Nullable
    @Column(unique = true)
    private String login;

    @Nullable
    private String passwordHash;

    @Nullable
    private RoleType roleType;

    public User(@NotNull RoleType roleType, @NotNull String login, @NotNull String password) {
        this.roleType = roleType;
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
    }

    //Constructor for permanent id of user
    public User(@NotNull RoleType roleType, @NotNull String id, @NotNull String login, @NotNull String password) {
        this.roleType = roleType;
        this.id = id;
        this.login = login;
        this.passwordHash = MD5Util.passwordToHash(password);
    }
    //------------------------------------

    @Override
    public String toString() {
        return login + ", " + id;
    }

}
