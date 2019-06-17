package ru.iokhin.tm;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TaskService;
import ru.iokhin.tm.service.UserService;
import ru.iokhin.tm.util.EntityFactory;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class ServiceTest {

    @Inject
    private UserService userService;

    @Inject
    private ProjectService projectService;

    @Inject
    private TaskService taskService;

    @Inject
    private SessionService sessionService;

    @Test
    public void test() throws AuthException {
        User user = EntityFactory.getUser();
        Assert.assertNull(userService.findOne(user.getId()));
        userService.merge(user.getUserDTO());
        Assert.assertNotNull(userService.findOne(user.getId()));
        user.setLogin("ASD");
        userService.merge(user.getUserDTO());
        Assert.assertNotNull(userService.findByLogin("ASD"));

        Session session = new Session();
        session.setUser(user);
        session.setSignature(sessionService.sign(session.getSessionDTO()));
        Assert.assertNull(sessionService.findOne(session.getId()));
        sessionService.merge(session.getSessionDTO());
        Assert.assertNotNull(sessionService.findOne(session.getId()));

        Project project = EntityFactory.getProject();
        Assert.assertNull(projectService.findOne(project.getId()));
        project.setUser(user);
        projectService.merge(project.getProjectDTO());
        Assert.assertNotNull(projectService.findOne(project.getId()));
        project.setName("BIBA");
        projectService.merge(project.getProjectDTO());
        Assert.assertNotNull(projectService.findByPartOfNameOrDescription(user.getId(), "BIBA"));

        Task task = EntityFactory.getTask();
        task.setUser(user);
        task.setProject(project);
        Assert.assertNull(taskService.findOne(task.getId()));
        taskService.merge(task.getTaskDTO());
        Assert.assertNotNull(taskService.findOne(task.getId()));
        task.setName("QUQA");
        taskService.merge(task.getTaskDTO());
        Assert.assertNotNull(taskService.findByPartOfNameOrDescription(user.getId(), "QUQA"));

        userService.remove(user.getUserDTO());
        Assert.assertNull(userService.findOne(user.getId()));
        Assert.assertNull(projectService.findOne(project.getId()));
        Assert.assertNull(taskService.findOne(task.getId()));
        Assert.assertNull(sessionService.findOne(session.getId()));
    }
}

