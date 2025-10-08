package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.UUID;

public interface GarmentRepository extends MongoRepository<GarmentEntity, UUID> {
}
