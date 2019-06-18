package ru.iokhin.tm.command.system;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ServerInfo;
import ru.iokhin.tm.endpoint.ServerInfoEndpointBean;

import javax.inject.Inject;

public class ServerInfoCommand extends AbstractCommand {

    @Inject
    private ServerInfoEndpointBean serverInfoEndpointBean;

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public boolean admin() {
        return false;
    }

    @Override
    public String name() {
        return "server-info";
    }

    @Override
    public String description() {
        return "Shows info about server";
    }

    @Override
    public void execute() {
        ServerInfo serverInfo = serverInfoEndpointBean.getServerInformation();
        if (serverInfo == null) {
            System.out.println("INVALID");
            return;
        }
        System.out.println("SERVER INFO:");
        System.out.println("HOST: " + serverInfo.getAdress());
        System.out.println("PORT: " + serverInfo.getPort());
        System.out.println();
    }
}
