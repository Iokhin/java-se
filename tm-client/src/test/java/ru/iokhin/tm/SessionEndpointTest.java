package ru.iokhin.tm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.iokhin.tm.endpoint.*;
import ru.iokhin.tm.service.EndpointProducer;

public class SessionEndpointTest {

    private UserEndpointBean userEndpointBean;

    private SessionEndpointBean sessionEndpointBean;

    private SessionDTO session;

    private UserDTO user;

    @Before
    public void setUp() throws Exception {
        EndpointProducer endpointProducer = new EndpointProducer();
        userEndpointBean = endpointProducer.getUserEndpointBean();
        sessionEndpointBean = endpointProducer.getSessionEndpointBean();
        user = userEndpointBean.findByLogin("user");
    }

    @Test
    public void testCRD() {
        session = sessionEndpointBean.create(user.getId());
        System.out.println(sessionEndpointBean.findById(session.getId()));
        sessionEndpointBean.remove(session.getId());
        System.out.println(sessionEndpointBean.findById(session.getId()));
    }

    @After
    public void tearDown() throws Exception {
        userEndpointBean = null;
        sessionEndpointBean = null;
        session = null;
        user = null;
    }
}
