package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Repository
public class GarmentPersistenceMongodb implements GarmentPersistence {

    private final GarmentRepository garmentRepository;

    public GarmentPersistenceMongodb(GarmentRepository garmentRepository) {
        this.garmentRepository = garmentRepository;
    }

    @Override
    public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
        return this.garmentRepository.findByPriceBetween(min, max)
                .stream()
                .map(GarmentEntity::toGarment);
    }
}
