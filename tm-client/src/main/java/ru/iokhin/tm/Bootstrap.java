package ru.iokhin.tm;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IEndpointServiceLocator;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.*;
import ru.iokhin.tm.exception.NonexistentCommandException;
import ru.iokhin.tm.service.EndpointServiceLocator;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
class Bootstrap {

    @NotNull
    private final Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

    private IEndpointServiceLocator endpointServiceLocator = new EndpointServiceLocator(commandMap);

    void init(Class[] CLASSES) {
        for (Class commandClass : CLASSES) {
            try {
                AbstractCommand abstractCommand = (AbstractCommand) commandClass.newInstance();
                commandRegister(abstractCommand);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("***WELCOME TO TM-CLIENT***");
        @NotNull String input = "";
        while (!input.equals("exit")) {
            input = endpointServiceLocator.getTerminalService().nextLine();
            AbstractCommand command = this.commandMap.get(input);
            try {
                execute(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void commandRegister(AbstractCommand abstractCommand) {
        abstractCommand.setEndpointServiceLocator(endpointServiceLocator);
        this.commandMap.put(abstractCommand.name(), abstractCommand);
    }

    private void execute(@Nullable AbstractCommand command) throws AuthException_Exception, NonexistentCommandException, JAXBException_Exception, IOException_Exception, ClassNotFoundException_Exception {
        if (command == null) throw new NonexistentCommandException();
        if (command.security() && endpointServiceLocator.getSession() == null) throw new AuthException_Exception();
        if (command.admin() &&
                endpointServiceLocator.getUserEndpointBean().findById(endpointServiceLocator.getSession().getParentId()).getRoleType() != RoleType.ADMIN)
            throw new AuthException_Exception("THIS COMMAND ALLOWS ONLY FOR ADMIN");
        command.execute();
    }
}

