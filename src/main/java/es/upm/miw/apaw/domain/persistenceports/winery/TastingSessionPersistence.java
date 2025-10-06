package es.upm.miw.apaw.domain.persistenceports.winery;

import es.upm.miw.apaw.domain.models.winery.TastingSession;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TastingSessionPersistence {

    TastingSession readById(UUID id);

}
