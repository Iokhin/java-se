package ru.iokhin.tm;

import de.svenjacobs.loremipsum.LoremIpsum;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.iokhin.tm.endpoint.*;
import ru.iokhin.tm.service.EndpointProducer;

public class TaskEndpointTest {

    private ProjectEndpointBean projectEndpointBean;

    private TaskEndpointBean taskEndpointBean;

    private UserEndpointBean userEndpointBean;

    private SessionEndpointBean sessionEndpointBean;

    private SessionDTO session;

    private UserDTO user;

    private LoremIpsum loremIpsum = new LoremIpsum();

    @Before
    public void setUp() throws Exception {
        EndpointProducer endpointProducer = new EndpointProducer();
        sessionEndpointBean = endpointProducer.getSessionEndpointBean();
        userEndpointBean = endpointProducer.getUserEndpointBean();
        projectEndpointBean = endpointProducer.getProjectEndpointBean();
        taskEndpointBean = endpointProducer.getTaskEndpointBean();
        user = userEndpointBean.findByLogin("user");
        session = sessionEndpointBean.create(user.getId());
    }

    @Test
    public void testCRUD() {
        ProjectDTO project = projectEndpointBean.addProject(session, loremIpsum.getWords(1));
        System.out.println(projectEndpointBean.findProject(session, project.getId()));
        @NotNull TaskDTO task1 = taskEndpointBean.addTask(session, project.getId(), loremIpsum.getWords(1, 17));
        @NotNull final TaskDTO task2 = taskEndpointBean.addTask(session, project.getId(), loremIpsum.getWords(1, 10));
        taskEndpointBean.findAllTaskByProjectId(session, project.getId()).forEach(System.out::println);
        System.out.println();
        task1 = taskEndpointBean.editTask(session, task1.getId(), loremIpsum.getWords(2, 20));
        taskEndpointBean.findAllTaskByProjectId(session, project.getId()).forEach(System.out::println);
        System.out.println();
        System.out.println("TASK REMOVE");
        taskEndpointBean.removeTask(session, task2.getId());
        taskEndpointBean.findAllTaskByProjectId(session, project.getId()).forEach(System.out::println);
        System.out.println();
        System.out.println("CASCADE REMOVE");
        projectEndpointBean.removeProject(session, project.getId());
        taskEndpointBean.findAllTaskByProjectId(session, project.getId()).forEach(System.out::println);
    }

    @After
    public void tearDown() throws Exception {
        taskEndpointBean = null;
        projectEndpointBean = null;
        userEndpointBean = null;
        sessionEndpointBean = null;
        session = null;
        user = null;
    }
}
