package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.Status;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "task")
public final class Task extends AbstractEntity {

    @Nullable
    protected String parentId;

    @Nullable
    private String projectId;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @NotNull
    private Status status = Status.PLANNING;

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name) {
        this.parentId = userId;
        this.projectId = projectId;
        this.name = name;
        this.startDate = new Date();
    }

    //----------Constructor for test sorting by status
    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name, Status status) {
        this.parentId = userId;
        this.projectId = projectId;
        this.name = name;
        this.startDate = new Date();
        this.status = status;
    }
    //------------------------------------------------

    @Override
    public String toString() {
        return name + ", " + id;
    }

}
