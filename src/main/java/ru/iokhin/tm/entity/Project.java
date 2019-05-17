package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public final class Project {

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

    @NotNull
    private String userId;

    public Project(@NotNull String name, @NotNull String userId) {
        if (!name.trim().isEmpty()) {
            this.name = name;
            this.id = UUID.randomUUID().toString();
            this.userId = userId;
        } else System.out.println("Illegal argument");
    }

    public Project(@NotNull String name, @NotNull String id, @NotNull String userId) {
        if (!name.trim().isEmpty()) {
            this.name = name;
            this.id = id;
            this.userId = userId;
        } else System.out.println("Illegal argument");
    }

    public Project() {

    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.name, this.id);
    }
}
