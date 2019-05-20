package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Getter
@Setter
//@NoArgsConstructor
public final class Task extends AbstractEntity {

    @NotNull
    private String userId;

    @NotNull
    private String projectId;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name) {
        this.userId = userId;
        this.projectId = projectId;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ", " + id;
    }

}
