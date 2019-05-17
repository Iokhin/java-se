package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public final class Task {

    @NotNull
    private String userId;

    @NotNull
    private String projectId;

    @NotNull
    private String id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    public Task(@NotNull String userId, @NotNull String projectId, @NotNull String name) {
        if (!name.trim().isEmpty()) {
            this.userId = userId;
            this.projectId = projectId;
            this.name = name;
            this.id = UUID.randomUUID().toString();
        } else System.out.println("Illegal argument");
    }

    public Task() {

    }


    @Override
    public String toString() {
        return name + ", " + id;
    }

}
