package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.Status;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class Project extends AbstractEntity {

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @NotNull
    private Status status = Status.PLANNING;

    public Project(@NotNull String userId, @NotNull String name) {
        this.name = name;
        this.parentId = userId;
        this.startDate = new Date();
    }

    public Project(@NotNull String userId, @NotNull String name, @NotNull final String description) {
        this.name = name;
        this.parentId = userId;
        this.startDate = new Date();
        this.description = description;
    }

    //----------Constructor for testing status sorting
    public Project(@NotNull String userId, @NotNull String name, Status status) {
        this.name = name;
        this.parentId = userId;
        this.startDate = new Date();
        this.status = status;
    }
    //------------------------------------------------

    @Override
    public String toString() {
        return String.format("%s, %s", name, id);
    }
}
