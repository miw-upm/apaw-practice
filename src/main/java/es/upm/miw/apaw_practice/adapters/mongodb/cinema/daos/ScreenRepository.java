package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ScreenRepository extends MongoRepository<ScreenEntity, String> {
    Optional<ScreenEntity> findByNumber(Integer number);
}
