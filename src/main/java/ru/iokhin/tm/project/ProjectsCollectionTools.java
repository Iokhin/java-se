package ru.iokhin.tm.project;

import java.util.ArrayList;

public class ProjectsCollectionTools {

    public ArrayList<Project> projectsCollection = new ArrayList<>(0);

    public void addProject(String name) {
        this.projectsCollection.add(new Project(name));
        System.out.println("[OK]");
    }

    public void removeProject(String name) {
        for (Project project : this.projectsCollection) {
            if (project.getName().equals(name)) {
                this.projectsCollection.remove(this.projectsCollection.indexOf(project));
                System.out.println("[OK]");
                return;
            }
            System.out.println("NO PROJECT WITH SUCH NAME");
        }
    }

    public void removeProject(int index) {
        if (index-1 < this.projectsCollection.size() && index-1 >= 0) {
            this.projectsCollection.remove(index-1);
            System.out.println("[OK]");
            return;
        }
        System.out.println("NO PROJECT WITH SUCH INDEX");
    }

    public void clearProjects() {
        this.projectsCollection.clear();
        System.out.println("[ALL PROJECTS REMOVED]");
    }

    public void editProject(int i, String newName) {
        this.projectsCollection.get(i - 1).setName(newName);
        System.out.println("[OK]");
    }

    public void editProject(String projectName, String newName) {
        for (Project project : this.projectsCollection) {
            if (project.getName().equals(projectName)) {
                this.projectsCollection.get(this.projectsCollection.indexOf(project)).setName(newName);
                System.out.println("[OK]");
                return;
            }
            System.out.println("NO PROJECT WITH SUCH NAME");
        }
    }

    public void listProjects () {
        System.out.println("[PROJECT LIST]");
        for (int i = 0; i < projectsCollection.size(); i++) {
            System.out.println((i + 1) + ". " + projectsCollection.get(i).getName());
        }
    }
}
