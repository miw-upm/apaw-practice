package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class CustomerPersistenceMongodbIT {

    @Autowired
    private CustomerPersistenceMongodb customerPersistenceMongodb;

    @Test
    void testReadAll() {
        assertEquals(4, customerPersistenceMongodb.readAll().count());
    }

}
