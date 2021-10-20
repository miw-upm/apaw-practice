package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class ManagerPersistenceMongodbIT {

    @Autowired
    private ManagerPersistenceMongodb managerPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.managerPersistenceMongodb.readByEmail("ana@company1.com"));
    }

    @Test
    void testEmailNotExist() {
        assertFalse(this.managerPersistenceMongodb.existEmail("ana@company1.com"));
    }

    @Test
    void testEmailExist() {
        assertTrue(this.managerPersistenceMongodb.existEmail("l.ana@company1.com"));
    }

    @Test
    void testReadAndDelete(){
        String email = "b.ana@company1.com";
        assertTrue(this.managerPersistenceMongodb.existEmail(email));
        this.managerPersistenceMongodb.delete(email);
        assertFalse(this.managerPersistenceMongodb.existEmail(email));

    }
}
