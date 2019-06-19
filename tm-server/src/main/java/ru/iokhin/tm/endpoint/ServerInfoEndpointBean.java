package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.api.endpoint.ServerInfoEndpoint;
import ru.iokhin.tm.entity.ServerInfo;

import javax.jws.WebMethod;
import javax.jws.WebService;

@Controller
@WebService
@NoArgsConstructor
public class ServerInfoEndpointBean implements ServerInfoEndpoint {

    @Override
    @WebMethod
    public ServerInfo getServerInformation() {
        ServerInfo serverInfo = new ServerInfo();
        return serverInfo;
    }

}
