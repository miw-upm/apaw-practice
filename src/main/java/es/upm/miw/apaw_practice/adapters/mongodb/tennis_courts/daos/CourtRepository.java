package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.CourtEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CourtRepository extends MongoRepository<CourtEntity, String> {
    Optional<Integer> findByNumber(Integer number);
}
