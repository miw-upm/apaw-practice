package es.upm.miw.apaw_practice.adapters.mongodb.hospital.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class DiseasePersistenceMongodbIT {

    @Autowired
    private DiseasePersistenceMongodb diseasePersistenceMongodb;

    @Test
    void testUpdateDescription(){
        this.diseasePersistenceMongodb.updateDescription("Common cold", "New description test");
    }
}
