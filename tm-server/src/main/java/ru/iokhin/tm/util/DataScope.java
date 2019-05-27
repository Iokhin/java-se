package ru.iokhin.tm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "DataScope")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataScope implements Serializable {
    @XmlElement(name = "projects")
    @NotNull
    private ArrayList<Project> projects;

    @XmlElement(name = "tasks")
    @NotNull
    private ArrayList<Task> tasks;

    @XmlElement(name = "users")
    @NotNull
    private ArrayList<User> users;

}
