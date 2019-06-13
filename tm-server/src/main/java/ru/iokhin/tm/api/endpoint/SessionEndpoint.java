package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;

@WebService
public interface SessionEndpoint {

    SessionDTO create(@WebParam(name = "userId") @NotNull String userId) throws SQLException;

    void validate(@WebParam(name = "session") @Nullable SessionDTO session) throws AuthException;

    SessionDTO findById(@WebParam(name = "id") @NotNull String id);

    void remove(@WebParam(name = "id") @NotNull String id);
}
