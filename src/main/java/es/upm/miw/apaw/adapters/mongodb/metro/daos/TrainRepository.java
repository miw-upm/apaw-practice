package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TrainRepository extends MongoRepository<TrainEntity, UUID> {
}

