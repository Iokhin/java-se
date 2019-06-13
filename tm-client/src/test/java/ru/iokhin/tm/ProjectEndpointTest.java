package ru.iokhin.tm;

import de.svenjacobs.loremipsum.LoremIpsum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.iokhin.tm.endpoint.*;
import ru.iokhin.tm.service.EndpointProducer;

public class ProjectEndpointTest {

    private ProjectEndpointBean projectEndpointBean;

    private UserEndpointBean userEndpointBean;

    private SessionEndpointBean sessionEndpointBean;

    private SessionDTO session;

    private UserDTO user;

    private LoremIpsum loremIpsum = new LoremIpsum();

    @Before
    public void setUp() throws Exception {
        EndpointProducer endpointProducer = new EndpointProducer();
        projectEndpointBean = endpointProducer.getProjectEndpointBean();
        userEndpointBean = endpointProducer.getUserEndpointBean();
        sessionEndpointBean = endpointProducer.getSessionEndpointBean();
        user = userEndpointBean.findByLogin("user");
        session = sessionEndpointBean.create(user.getId());
    }

    @Test
    public void testCRUD() {
        ProjectDTO project = projectEndpointBean.addProject(session, loremIpsum.getWords(1));
        System.out.println(projectEndpointBean.findProject(session, project.getId()));
        projectEndpointBean.editProject(session, project.getId(), loremIpsum.getWords(2));
        projectEndpointBean.removeProject(session, project.getId());
    }

    @After
    public void tearDown() throws Exception {
        projectEndpointBean = null;
        userEndpointBean = null;
        sessionEndpointBean = null;
        session = null;
        user = null;
    }
}
