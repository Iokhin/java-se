package ru.iokhin.tm.api.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iokhin.tm.entity.Session;

@Repository("sessionRepository")
@Scope("singleton")
public interface ISessionRepository extends JpaRepository<Session, String> {

}
