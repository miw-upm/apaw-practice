package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.GarmentPersistence;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
public class GarmentService {

    private final GarmentPersistence garmentPersistence;

    public GarmentService(GarmentPersistence garmentPersistence) {
        this.garmentPersistence = garmentPersistence;
    }

    public Stream<Garment> findByPriceBetween(BigDecimal min, BigDecimal max) {
        return this.garmentPersistence.findByPriceBetween(min, max);
    }
}


