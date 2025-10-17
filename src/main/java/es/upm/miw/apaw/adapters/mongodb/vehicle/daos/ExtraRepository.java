package es.upm.miw.apaw.adapters.mongodb.vehicle.daos;

import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.ExtraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExtraRepository extends MongoRepository<ExtraEntity, UUID> {

    Optional<ExtraEntity> findByCategoryAndDescription(String category, String description);

}
