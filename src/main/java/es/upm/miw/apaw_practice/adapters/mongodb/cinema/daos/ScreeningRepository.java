package es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreeningEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScreeningRepository extends MongoRepository<ScreeningEntity, String> {

}