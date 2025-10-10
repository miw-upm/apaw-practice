package es.upm.miw.apaw.domain.persistenceports.winery;

import es.upm.miw.apaw.domain.models.winery.TastingSession;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface TastingSessionPersistence {

    Stream<TastingSession> readAll();

    TastingSession readById(UUID id);

    TastingSession update(TastingSession tastingSession);
}
