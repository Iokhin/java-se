package ru.iokhin.tm.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class HibernateFactoryProducer {
    @Produces
    private EntityManagerFactory produceHibernateFactory() {
        return Persistence.createEntityManagerFactory("ENTERPRISE");
    }

}
