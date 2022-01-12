package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CoursePersistenceMongodbIT {
    @Autowired
    private CoursePersistenceMongodb coursePersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.coursePersistence.read("0"));
    }

    @Test
    void testIdentityNotExist() {
        assertFalse(this.coursePersistence.existIdentity("0"));
    }

    @Test
    void testIdentityExist() {
        assertTrue(this.coursePersistence.existIdentity("62003"));
    }
}
