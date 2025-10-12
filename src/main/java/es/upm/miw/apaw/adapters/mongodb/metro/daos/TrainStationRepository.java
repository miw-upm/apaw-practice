package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainStationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TrainStationRepository extends MongoRepository<TrainStationEntity, UUID> {
}

