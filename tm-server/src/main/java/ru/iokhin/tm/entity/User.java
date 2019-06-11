package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.UserDTO;
import ru.iokhin.tm.enumerated.RoleType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable {

    @Nullable
    @Column(unique = true)
    private String login = "";

    @Nullable
    private String passwordHash = "";

    @Nullable
    @Enumerated(EnumType.STRING)
    private RoleType role = RoleType.USER;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Project> projects;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    @Nullable
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Session> sessions;

    public UserDTO getUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setLogin(login);
        userDTO.setPasswordHash(passwordHash);
        userDTO.setRoleType(role);
        return userDTO;
    }
}
