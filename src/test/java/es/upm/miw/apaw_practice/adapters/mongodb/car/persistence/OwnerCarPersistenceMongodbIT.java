package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class OwnerCarPersistenceMongodbIT {

    @Autowired
    private OwnerCarPersistenceMongodb ownerCarPersistence;

    @Test
    public void testReadNotFound(){
        assertThrows(NotFoundException.class, () -> ownerCarPersistence.readByDriverLicense("345SDF"));
    }
}
