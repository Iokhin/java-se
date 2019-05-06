package ru.iokhin.tm.entity;

import java.util.Date;
import java.util.UUID;

public class Task {

    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    public Task(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
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
}
