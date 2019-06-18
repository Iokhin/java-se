package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.api.endpoint.SessionEndpoint;
import ru.iokhin.tm.api.service.ISessionService;

import javax.jws.WebParam;
import javax.jws.WebService;

@Controller
@WebService
@NoArgsConstructor
public class SessionEndpointBean implements SessionEndpoint {

    @NotNull
    private ISessionService sessionService;

    @Autowired
    public SessionEndpointBean(@NotNull final ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    @SneakyThrows
    public SessionDTO create(@WebParam(name = "userId") @NotNull String userId) {
        return sessionService.create(userId);
    }

    @Override
    @SneakyThrows
    public void validate(@WebParam(name = "session") @Nullable SessionDTO session) {
        sessionService.validate(session);
    }

    @Override
    public SessionDTO findById(@WebParam(name = "session") @NotNull String id) {
        return sessionService.findById(id);
    }

    @Override
    public void remove(@WebParam(name = "id") @NotNull String id) {
        sessionService.removeById(id);
    }
}
