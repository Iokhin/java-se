package ru.iokhin.tm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.ProjectDTO;
import ru.iokhin.tm.entityDTO.TaskDTO;
import ru.iokhin.tm.entityDTO.UserDTO;

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
    private ArrayList<ProjectDTO> projects;

    @XmlElement(name = "tasks")
    @NotNull
    private ArrayList<TaskDTO> tasks;

    @XmlElement(name = "users")
    @NotNull
    private ArrayList<UserDTO> users;

}
