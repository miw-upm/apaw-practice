package es.upm.miw.apaw.adapters.mongodb.airport.daos;

import es.upm.miw.apaw.adapters.mongodb.airport.entities.BoardingGateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface BoardingGateRepository extends MongoRepository<BoardingGateEntity, UUID> {
}
