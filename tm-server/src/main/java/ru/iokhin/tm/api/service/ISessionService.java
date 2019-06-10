package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.DTO.SessionDTO;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.exeption.AuthException;

import javax.persistence.EntityManager;

public interface ISessionService extends IService<SessionDTO> {

    SessionDTO create(@NotNull final String userId);

    void validate(@Nullable final SessionDTO session) throws AuthException;

    String sign(@NotNull SessionDTO session);

    Session getSessionFromDTO(SessionDTO sessionDTO, EntityManager em);

    User getUser(SessionDTO sessionDTO, EntityManager em);
}
