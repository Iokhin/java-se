package ru.iokhin.tm.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Project {

    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;


    public Project(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
            this.id = UUID.randomUUID().toString();
        }
        else System.out.println("Illegal argument");
    }

    public  Project(String name, String id) {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id.equals(project.id) &&
                name.equals(project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(startDate, project.startDate) &&
                Objects.equals(endDate, project.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, startDate, endDate);
    }

    @Override
    public String toString() {
        return String.format("%s, %s", this.name, this.id);
    }
}
