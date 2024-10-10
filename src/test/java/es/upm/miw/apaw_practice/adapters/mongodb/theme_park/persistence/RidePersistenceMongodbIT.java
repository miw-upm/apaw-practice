package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class RidePersistenceMongodbIT {

    @Autowired
    private RidePersistenceMongodb ridePersistence;

    @Test
    void testFindByThemeAndMaxCapacityLessThan(){
        assertEquals(this.ridePersistence.findByThemeAndMaxCapacityLessThan("Halloween", 80).toList().size(), 2);
    }
    
}
