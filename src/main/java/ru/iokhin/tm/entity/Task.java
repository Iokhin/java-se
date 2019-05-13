package ru.iokhin.tm.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Task {

    private String projectId;
    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    public Task(String projectId, String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.projectId = projectId;
            this.name = name;
            this.id = UUID.randomUUID().toString();
        }
        else System.out.println("Illegal argument");
    }

    public  Task(String projectId ,String name, String id) {
        this.projectId = projectId;
        this.name = name;
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return projectId.equals(task.projectId) &&
                id.equals(task.id) &&
                name.equals(task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(startDate, task.startDate) &&
                Objects.equals(endDate, task.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, id, name, description, startDate, endDate);
    }

    @Override
    public String toString() {
        return name + ", " + id;
    }

}
