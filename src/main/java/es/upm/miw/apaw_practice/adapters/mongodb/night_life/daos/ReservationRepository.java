package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.ReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<ReservationEntity, String> {

}
