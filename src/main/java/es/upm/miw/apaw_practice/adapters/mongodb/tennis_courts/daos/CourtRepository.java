package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.CourtEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourtRepository extends MongoRepository<CourtEntity, String> {
    CourtEntity findByNumber(int number);
}
