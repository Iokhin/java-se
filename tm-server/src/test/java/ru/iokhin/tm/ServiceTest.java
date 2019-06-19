package ru.iokhin.tm;

import org.junit.Assert;
import org.junit.Test;
//import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.config.SpringConfig;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.EntityFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class ServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ISessionService sessionService;

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

