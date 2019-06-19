package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.exeption.AuthException;

public interface ISessionService extends IService<SessionDTO> {

    SessionDTO create(@NotNull final String userId);

    void validate(@Nullable final SessionDTO session) throws AuthException;

    String sign(@NotNull SessionDTO session);

    Session getSessionFromDTO(@NotNull SessionDTO sessionDTO);

    User getUser(@NotNull SessionDTO sessionDTO);

    SessionDTO findById(@NotNull String id);

    SessionDTO findOne(@NotNull String id);

    void removeById(@NotNull String id);

    void merge(@NotNull SessionDTO entity);
}
