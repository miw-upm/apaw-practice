package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface GarmentRepository extends MongoRepository<GarmentEntity, UUID> {
    List<GarmentEntity> findByPriceBetween(BigDecimal min, BigDecimal max);
}
