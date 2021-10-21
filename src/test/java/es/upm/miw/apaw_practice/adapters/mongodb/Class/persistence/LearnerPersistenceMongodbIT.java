package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class LearnerPersistenceMongodbIT {
    @Autowired
    private LearnerPersistenceMongodb learnerPersistenceMongodb;

    @Test
    void testReadAll(){
        assertEquals(3,learnerPersistenceMongodb.readAll().count());
    }
}
