package ru.iokhin.tm.config;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.endpoint.*;
import ru.iokhin.tm.endpoint.ServerInfoEndpointBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;

@Getter
@Setter
@ApplicationScoped
public class Bootstrap {

    @Inject
    @NotNull
    private SessionEndpoint sessionEndpoint;

    @Inject
    @NotNull
    private UserEndpoint userEndpoint;

    @Inject
    @NotNull
    private ProjectEndpoint projectEndpoint;

    @Inject
    @NotNull
    private TaskEndpoint taskEndpoint;

    @Inject
    @NotNull
    private ServerInfoEndpoint serverInfoEndpoint;

    @Inject
    @NotNull
    private TestDataGenerator testDataGenerator;

    @SneakyThrows
    public void init() {
        if (System.getProperty("server.port") == null) System.setProperty("server.port", "8080");
        @NotNull final String PORT = System.getProperty("server.port");
        Endpoint.publish("http://localhost:" + PORT + "/UserEndpointBean", userEndpoint);
        Endpoint.publish("http://localhost:" + PORT + "/SessionEndpointBean", sessionEndpoint);  //+ (int)(Math.random()*10) +
        Endpoint.publish("http://localhost:" + PORT + "/ProjectEndpointBean", projectEndpoint);
        Endpoint.publish("http://localhost:" + PORT + "/TaskEndpointBean", taskEndpoint);
        Endpoint.publish("http://localhost:" + PORT + "/ServerInfoEndpointBean", serverInfoEndpoint);
        testDataGenerator.generateTestData();
        System.out.println("***WELCOME TO TM-SERVER***");
        System.out.println("http://localhost:" + PORT + "/UserEndpointBean?wsdl");
        System.out.println("http://localhost:" + PORT + "/SessionEndpointBean?wsdl");
        System.out.println("http://localhost:" + PORT + "/ProjectEndpointBean?wsdl");
        System.out.println("http://localhost:" + PORT + "/TaskEndpointBean?wsdl");
        System.out.println("http://localhost:" + PORT + "/ServerInfoEndpointBean?wsdl");
    }


}
