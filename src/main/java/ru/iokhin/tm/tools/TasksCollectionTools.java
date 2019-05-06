package ru.iokhin.tm.tools;

import ru.iokhin.tm.entity.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class TasksCollectionTools {

    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Task> tasksCollection = new ArrayList<>(0);

    public void addTask() {
        System.out.println("[TASK CREATE]");
        System.out.println("ENTER A NAME OF TASK TO ADD");
        String name = scanner.nextLine();
        this.tasksCollection.add(new Task(name));
        System.out.println("[OK]");
    }

    public void removeTask() {
        listTask();
        System.out.println("INPUT INDEX OF TASK TO REMOVE");
        int index = scanner.nextInt();
        if (index-1 < this.tasksCollection.size() && index-1 >= 0) {
            this.tasksCollection.remove(index-1);
            System.out.println("[OK]");
            return;
        }
        System.out.println("[NO TASK WITH SUCH INDEX]");
    }

    public void clearTasks() {
        this.tasksCollection.clear();
        System.out.println("[ALL TASKS FOR THIS PROJECT WAS REMOVED]");
    }

    public void editTask() {
        listTask();
        System.out.println("INPUT INDEX OF TASK TO EDIT");
        int i = scanner.nextInt();
        if (i-1 < this.tasksCollection.size() && i-1 >= 0) {
            String newName = scanner.nextLine();
            this.tasksCollection.get(i - 1).setName(newName);
            System.out.println("[OK]");
            return;
        }
        System.out.println("[NO TASK WITH SUCH INDEX]");
    }

    public void listTask () {
        System.out.println("[TASK LIST FOR THIS PROJECT]");
        for (int i = 0; i < tasksCollection.size(); i++) {
            System.out.println((i + 1) + ". " + tasksCollection.get(i).getName());
        }
    }

}
