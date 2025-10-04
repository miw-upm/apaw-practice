package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.TastingSessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TastingSessionRepository extends MongoRepository<TastingSessionEntity, Long> {
}
