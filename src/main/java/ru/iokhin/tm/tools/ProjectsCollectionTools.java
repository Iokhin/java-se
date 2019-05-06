package ru.iokhin.tm.tools;

import ru.iokhin.tm.entity.Project;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectsCollectionTools {

    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Project> projectsCollection = new ArrayList<>(0);

    public void addProject() {
        System.out.println("[PROJECT CREATE]");
        System.out.println("ENTER NAME:");
        String name = scanner.nextLine();
        this.projectsCollection.add(new Project(name));
        System.out.println("[OK]");
    }

    public void removeProject() {
        listProjects();
        System.out.println("INPUT INDEX OF PROJECT TO REMOVE");
        int index = scanner.nextInt();
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

    public void editProject() {
        listProjects();
        System.out.println("INPUT INDEX OF PROJECT TO EDIT");
        int i = scanner.nextInt();
        if (i-1 < this.projectsCollection.size() && i-1 >= 0) {
            System.out.println("ENTER A NEW NAME OF THE PROJECT");
            String newName = scanner.nextLine();
            this.projectsCollection.get(i - 1).setName(newName);
            System.out.println("[OK]");
            return;
        }
        System.out.println("NO PROJECT WITH SUCH INDEX");
    }

    public void listProjects () {
        System.out.println("[PROJECT LIST]");
        for (int i = 0; i < projectsCollection.size(); i++) {
            System.out.println((i + 1) + ". " + projectsCollection.get(i).getName());
        }
    }
}
