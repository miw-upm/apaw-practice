package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainLineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TrainLineRepository extends MongoRepository<TrainLineEntity, UUID> {
}

