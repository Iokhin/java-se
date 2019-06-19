package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.iokhin.tm.endpoint.*;

@Configuration
@ComponentScan("ru.iokhin.tm")
public class EndpointProducer {

    @NotNull
    @Bean
    @Scope("singleton")
    public ProjectEndpointBean getProjectEndpointBean() {
        return new ProjectEndpointBeanService().getProjectEndpointBeanPort();
    }

    @NotNull
    @Bean
    @Scope("singleton")
    public TaskEndpointBean getTaskEndpointBean() {
        return new TaskEndpointBeanService().getTaskEndpointBeanPort();
    }

    @NotNull
    @Bean
    @Scope("singleton")
    public UserEndpointBean getUserEndpointBean() {
        return new UserEndpointBeanService().getUserEndpointBeanPort();
    }

    @NotNull
    @Bean
    @Scope("singleton")
    public SessionEndpointBean getSessionEndpointBean() {
        return new SessionEndpointBeanService().getSessionEndpointBeanPort();
    }

    @NotNull
    @Bean
    @Scope("singleton")
    public ServerInfoEndpointBean getServerInfoEndpointBean() {
        return new ServerInfoEndpointBeanService().getServerInfoEndpointBeanPort();
    }
}
