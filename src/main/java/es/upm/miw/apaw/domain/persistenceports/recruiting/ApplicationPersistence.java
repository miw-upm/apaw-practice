package es.upm.miw.apaw.domain.persistenceports.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Application;

import java.util.UUID;

public interface ApplicationPersistence {

    Application readById(UUID id);

    Application update(Application application);
}