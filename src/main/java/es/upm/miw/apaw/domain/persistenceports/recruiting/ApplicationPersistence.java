package es.upm.miw.apaw.domain.persistenceports.recruiting;

import es.upm.miw.apaw.domain.models.recruiting.Application;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApplicationPersistence {

    Application readById(UUID id);

    Application update(Application application);
}