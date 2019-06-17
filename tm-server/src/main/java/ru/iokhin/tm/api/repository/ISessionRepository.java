package ru.iokhin.tm.api.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import ru.iokhin.tm.entity.Session;

@Repository
public interface ISessionRepository extends EntityRepository<Session, String> {

}
