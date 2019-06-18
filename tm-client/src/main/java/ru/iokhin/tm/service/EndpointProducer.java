package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class EndpointProducer {

    @NotNull
    @Produces
    @ApplicationScoped
    public ProjectEndpointBean getProjectEndpointBean() {
        return new ProjectEndpointBeanService().getProjectEndpointBeanPort();
    }

    @NotNull
    @Produces
    @ApplicationScoped
    public TaskEndpointBean getTaskEndpointBean() {
        return new TaskEndpointBeanService().getTaskEndpointBeanPort();
    }

    @NotNull
    @Produces
    @ApplicationScoped
    public UserEndpointBean getUserEndpointBean() {
        return new UserEndpointBeanService().getUserEndpointBeanPort();
    }

    @NotNull
    @Produces
    @ApplicationScoped
    public SessionEndpointBean getSessionEndpointBean() {
        return new SessionEndpointBeanService().getSessionEndpointBeanPort();
    }

    @NotNull
    @Produces
    @ApplicationScoped
    public ServerInfoEndpointBean getServerInfoEndpointBean() {
        return new ServerInfoEndpointBeanService().getServerInfoEndpointBeanPort();
    }
}
