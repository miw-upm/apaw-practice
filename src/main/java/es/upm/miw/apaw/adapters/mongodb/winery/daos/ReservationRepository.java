package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.ReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ReservationRepository extends MongoRepository<ReservationEntity, UUID> {
}
