package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.enumerated.ReadinessStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class Task extends AbstractEntity {

    @NotNull
    private String parentId;

    @NotNull
    private String projectId;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @NotNull
    private final ReadinessStatus status = ReadinessStatus.PLANNIG;

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name) {
        this.parentId = userId;
        this.projectId = projectId;
        this.name = name;
        this.startDate = new Date();
    }

    @Override
    public String toString() {
        return name + ", " + id;
    }

}
