package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.ReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends MongoRepository<ReservationEntity, String> {

    List<ReservationEntity> findByOwnerName(String ownerName);

}
