package ru.iokhin.tm.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.RoleType;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.exception.ClientAuthException;
import ru.iokhin.tm.exception.ClientNonexistentCommandException;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@Scope("singleton")
public class Bootstrap {

    @Autowired
    private ApplicationContext context;

    @Autowired
    @NotNull
    private SessionService sessionService;

    @Autowired
    @NotNull
    private UserEndpointBean userEndpointBean;

    @Autowired
    @NotNull
    private TerminalService terminalService;

    @NotNull
    private Map<String, AbstractCommand> commandMap = new HashMap<>();


    public void init() {
        commandRegister();
        System.out.println("***WELCOME TO TM-CLIENT***");
        @NotNull String input = "";
        while (!input.equals("exit")) {
            input = terminalService.nextLine();
            AbstractCommand command = this.commandMap.get(input);
            try {
                execute(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    private void execute(@Nullable AbstractCommand command) {
        if (command == null) throw new ClientNonexistentCommandException();
        if (command.security() && sessionService.getSession() == null) throw new ClientAuthException();
        if (command.admin() &&
                userEndpointBean.findUserById(sessionService.getSession().getParentId()).getRoleType() != RoleType.ADMIN)
            throw new ClientAuthException("THIS COMMAND ALLOWS ONLY FOR ADMIN");
        command.execute();
    }

    private void commandRegister() {
        Map<String, AbstractCommand> commandMap1 = commandMap;
        for (Map.Entry<String, AbstractCommand> entry : context.getBeansOfType(AbstractCommand.class).entrySet()) {
            String key = entry.getValue().name();
            AbstractCommand value = entry.getValue();
            commandMap1.put(key, value);
        }
    }
}

