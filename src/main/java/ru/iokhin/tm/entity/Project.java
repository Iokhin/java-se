package ru.iokhin.tm.entity;

import java.util.Date;
import java.util.UUID;

public class Project {

    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String userId;


    public Project(String name, String userId) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
            this.id = UUID.randomUUID().toString();
            this.userId = userId;
        } else System.out.println("Illegal argument");
    }

    public Project(String name, String id, String userId) {
        this.name = name;
        this.id = id;
        this.userId = userId;
    }

    public Project() {

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

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.name, this.id);
    }
}
