package ru.iokhin.tm.api.endpoint;

import ru.iokhin.tm.entity.ServerInfo;

import javax.jws.WebService;

@WebService
public interface ServerInfoEndpoint {

    ServerInfo getServerInformation();

}
