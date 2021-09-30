package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScreenRepository extends MongoRepository<ScreenEntity, String> {
}
