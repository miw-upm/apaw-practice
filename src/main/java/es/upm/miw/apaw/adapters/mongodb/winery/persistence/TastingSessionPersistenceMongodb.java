package es.upm.miw.apaw.adapters.mongodb.winery.persistence;

import es.upm.miw.apaw.adapters.mongodb.winery.daos.TastingSessionRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import es.upm.miw.apaw.domain.persistenceports.winery.TastingSessionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("tastingSessionPersistence")
public class TastingSessionPersistenceMongodb implements TastingSessionPersistence {

    private final TastingSessionRepository tastingSessionRepository;

    @Autowired
    public TastingSessionPersistenceMongodb(TastingSessionRepository tastingSessionRepository) {
        this.tastingSessionRepository = tastingSessionRepository;
    }

    @Override
    public TastingSession readById(UUID id) {
        return this.tastingSessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(" TastingSession id: " + id))
                .toTastingSession();
    }
}
