package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class TyreSpecificationPersistenceMongodbIT {

    @Autowired
    TyreSpecificationPersistenceMongodb tyreSpecPersistence;

    @Test
    void testFindByDiameterGreaterThan() {
        List<TyreSpecification> tyreSpecs = this.tyreSpecPersistence.findByDiameterGreaterThan(16)
                .collect(Collectors.toList());
        assertEquals(2, tyreSpecs.size());
    }
}
