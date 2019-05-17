package ru.iokhin.tm.entity;

import java.util.Date;
import java.util.UUID;

public class Task {

    private String userId;
    private String projectId;
    private String id;
    private String name;
    private String description;
    private Date startDate;
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
