package ru.iokhin.tm.config;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.api.endpoint.*;

import javax.xml.ws.Endpoint;

@Getter
@Setter
@Component("bootstrap")
//@Scope("singleton")
public class Bootstrap {

    @Autowired
    @NotNull
    private SessionEndpoint sessionEndpoint;

    @Autowired
    @NotNull
    private UserEndpoint userEndpoint;

    @Autowired
    @NotNull
    private ProjectEndpoint projectEndpoint;

    @Autowired
    @NotNull
    private TaskEndpoint taskEndpoint;

    @Autowired
    @NotNull
    private ServerInfoEndpoint serverInfoEndpoint;

    @Autowired
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
