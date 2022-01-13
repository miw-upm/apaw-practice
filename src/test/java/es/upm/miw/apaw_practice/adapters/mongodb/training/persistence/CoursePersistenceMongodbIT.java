package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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

    @Test
    void testCreateAndUpdate() {
        Course courseCreation = new Course("6622001",1, new BigDecimal("88.88"));
        Course courseBD = this.coursePersistence.create(courseCreation);
        courseBD.setPrice(BigDecimal.TEN);
        this.coursePersistence.update("6622001", courseBD);
        courseBD = this.coursePersistence.read("6622001");
        assertEquals(0, BigDecimal.TEN.compareTo(courseBD.getPrice()));
    }
}
