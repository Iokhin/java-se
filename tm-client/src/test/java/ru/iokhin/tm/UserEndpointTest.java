package ru.iokhin.tm;

import de.svenjacobs.loremipsum.LoremIpsum;
import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.iokhin.tm.endpoint.*;
import ru.iokhin.tm.service.EndpointProducer;

public class UserEndpointTest {

    private ProjectEndpointBean projectEndpointBean;

    private UserEndpointBean userEndpointBean;

    private SessionEndpointBean sessionEndpointBean;

    private SessionDTO session;

    private UserDTO user;

    private LoremIpsum loremIpsum = new LoremIpsum();

    @Before
    public void setUp() throws Exception {
        EndpointProducer endpointProducer = new EndpointProducer();
        userEndpointBean = endpointProducer.getUserEndpointBean();
        sessionEndpointBean = endpointProducer.getSessionEndpointBean();
    }

    @Test
    public void testCRUD() {
        @NotNull final String login = loremIpsum.getWords(1);
        @NotNull final String password = loremIpsum.getWords(1);
        user = userEndpointBean.addUser(login, password);
        System.out.println(userEndpointBean.findUserById(user.getId()).getLogin());
        session = userEndpointBean.authUser(login, password);
        sessionEndpointBean.validate(session);
        user = userEndpointBean.editUser(session, loremIpsum.getWords(1, 5),
                loremIpsum.getWords(1, 8));
        System.out.println(userEndpointBean.findUserById(user.getId()).getLogin());
        if (userEndpointBean.removeUserById(user.getId()) == null) {
            System.out.println("ERROR");
        }
        System.out.println(user.getLogin() + " WAS REMOVED");
    }

    @After
    public void tearDown() throws Exception {
        userEndpointBean = null;
        sessionEndpointBean = null;
        session = null;
        user = null;
    }
}
