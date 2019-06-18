package ru.iokhin.tm.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class ServerInfo implements Serializable {

    private String adress;

    private String port;

    public ServerInfo() {
        adress = "localhost";
        port = System.getProperty("server.port");
    }
}

