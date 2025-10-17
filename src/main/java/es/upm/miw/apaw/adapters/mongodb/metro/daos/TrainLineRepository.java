package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.TrainLineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.UUID;
import java.util.Optional;

public interface TrainLineRepository extends MongoRepository<TrainLineEntity, UUID> {
    Optional<TrainLineEntity> findByNumber(Integer number);
}

