package ru.iokhin.tm.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.UUID;

public class Task {

    @NotNull
    private String userId;

    @NotNull
    private String projectId;

    @NotNull
    private String id;

    @NotNull
    private String name;

    @Nullable
    private String description;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    public Task(String userId, String projectId, String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.userId = userId;
            this.projectId = projectId;
            this.name = name;
            this.id = UUID.randomUUID().toString();
        } else System.out.println("Illegal argument");
    }

    public Task(String userId, String projectId, String name, String id) {
        this.projectId = projectId;
        this.name = name;
        this.id = id;
    }

    public Task() {

    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return name + ", " + id;
    }

}
