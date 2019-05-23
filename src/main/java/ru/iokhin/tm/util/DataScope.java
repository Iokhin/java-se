package ru.iokhin.tm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "DataScope")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataScope implements Serializable {
    @NotNull
    private ArrayList<Project> projectArrayList;

    @NotNull
    private ArrayList<Task> taskArrayList;

    @NotNull
    private ArrayList<User> userArrayList;

}
