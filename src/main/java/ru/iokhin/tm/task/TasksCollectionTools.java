package ru.iokhin.tm.task;

import java.util.ArrayList;

public class TasksCollectionTools {

    public ArrayList<Task> tasksCollection = new ArrayList<>(0);

    public void addTask(String name) {
        this.tasksCollection.add(new Task(name));
        System.out.println("[OK]");
    }

    public void removeTask(String name) {
        for (Task task : this.tasksCollection) {
            if (task.getName().equals(name)) {
                this.tasksCollection.remove(this.tasksCollection.indexOf(task));
                System.out.println("[OK]");
                return;
            }
            System.out.println("[NO TASK WITH SUCH NAME]");
        }
    }

    public void removeTask(int index) {
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

    public void editTask(int i, String newName) {
        this.tasksCollection.get(i - 1).setName(newName);
        System.out.println("[OK]");
    }

    public void editTask(String projectName, String newName) {
        for (Task task: this.tasksCollection) {
            if (task.getName().equals(projectName)) {
                this.tasksCollection.get(this.tasksCollection.indexOf(task)).setName(newName);
                System.out.println("[OK]");
                return;
            }
            System.out.println("[NO TASK WITH SUCH NAME]");
        }
    }

    public void listTask () {
        System.out.println("[TASK LIST FOR THIS PROJECT]");
        for (int i = 0; i < tasksCollection.size(); i++) {
            System.out.println((i + 1) + ". " + tasksCollection.get(i).getName());
        }
    }

}
