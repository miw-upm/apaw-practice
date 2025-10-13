package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Stream;
import java.util.UUID;

@Service
public class GarmentService {

    private final GarmentPersistence garmentPersistence;

    public GarmentService(GarmentPersistence garmentPersistence) {
        this.garmentPersistence = garmentPersistence;
    }

    public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
        return this.garmentPersistence.findByPriceBetween(min, max);
    }
    public Garment update(UUID id, Garment garment) {
        return this.garmentPersistence.update(id, garment);
    }

    public Stream<Garment> readAll() {
        return this.garmentPersistence.readAll();
    }
}


