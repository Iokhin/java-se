package ru.iokhin.tm.util;

import de.svenjacobs.loremipsum.LoremIpsum;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.enumerated.Status;

public class EntityFactory {

    private static LoremIpsum loremIpsum = new LoremIpsum();

    public static User getUser() {
        User user = new User();
        user.setRole(RoleType.USER);
        user.setLogin(loremIpsum.getWords(1, (int)(Math.random()*49)));
        user.setPasswordHash(MD5Util.passwordToHash(loremIpsum.getWords(1, (int)(Math.random()*49))));
        return user;
    }

    public static Project getProject() {
        Project project = new Project();
        project.setName(loremIpsum.getWords(1, (int)(Math.random()*49)));
        project.setDescription(loremIpsum.getWords(2, (int)(Math.random()*49)));
        project.setStatus(Status.PROCCESSING);
        return project;
    }

    public static Task getTask() {
        Task task = new Task();
        task.setName(loremIpsum.getWords(1, (int)(Math.random()*49)));
        task.setDescription(loremIpsum.getWords(2, (int)(Math.random()*49)));
        task.setStatus(Status.PROCCESSING);
        return task;
    }
}
