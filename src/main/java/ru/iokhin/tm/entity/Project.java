package ru.iokhin.tm.entity;

import ru.iokhin.tm.tools.TasksCollectionTools;

import java.util.Date;
import java.util.UUID;

public class Project {

    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    public TasksCollectionTools tasksCollectionTools = new TasksCollectionTools();

    public Project(String name) {
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
