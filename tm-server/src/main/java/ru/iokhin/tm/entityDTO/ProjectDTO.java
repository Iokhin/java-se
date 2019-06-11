package ru.iokhin.tm.entityDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class ProjectDTO extends AbstractEntityDTO {

    @Nullable
    protected String parentId;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @NotNull
    private Status status = Status.PLANNING;

    public ProjectDTO(@NotNull String userId, @NotNull String name) {
        this.name = name;
        this.parentId = userId;
        this.startDate = new Date();
    }

    public ProjectDTO(@NotNull String userId, @NotNull String name, @NotNull final String description) {
        this.name = name;
        this.parentId = userId;
        this.startDate = new Date();
        this.description = description;
    }

    //----------Constructor for testing status sorting
    public ProjectDTO(@NotNull String userId, @NotNull String name, Status status) {
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
