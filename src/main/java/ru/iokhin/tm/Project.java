package ru.iokhin.tm;

public class Project {

    private String name;

    public TasksCollectionTools tasksCollectionTools = new TasksCollectionTools();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
