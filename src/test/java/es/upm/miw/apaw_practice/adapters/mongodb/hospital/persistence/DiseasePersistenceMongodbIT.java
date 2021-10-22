package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hospital.Disease;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
class DiseasePersistenceMongodbIT {

    @Autowired
    private DiseasePersistenceMongodb diseasePersistenceMongodb;

    @Test
    void testUpdateDescription() {
        Disease disease = this.diseasePersistenceMongodb.updateDescription("Common cold", "New description test");
        assertEquals("New description test", disease.getDescription());
    }
}
