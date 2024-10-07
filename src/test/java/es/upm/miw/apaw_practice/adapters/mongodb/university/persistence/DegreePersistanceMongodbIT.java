package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class DegreePersistanceMongodbIT {

    @Autowired
    private DegreePersistenceMongodb degreePersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> degreePersistence.read(0));
    }

    @Test
    void testCodeNotExists() {
        assertFalse(degreePersistence.existCode(0));
    }

    @Test
    void testCodeExists() {
        assertTrue(degreePersistence.existCode(2000));
    }

    @Test
    void testReadById() {
        Optional<Degree> degreeOptional = degreePersistence.readAll().filter(degree -> degree.getCode().equals(2004)).findFirst();
        assertTrue(degreeOptional.isPresent());
        Degree degree = degreeOptional.get();
        assertEquals(15, degree.getCapacity());
        assertEquals("Political Science", degree.getKnowledgeArea());
        assertEquals("Training in political theory, government systems and international relations, focused on understanding power, institutions and decision-making at global and local levels.", degree.getDescription());
    }

    @Test
    void testCreateAndUpdate() {
        Degree degree = new Degree(1234, 33, "knowledgeArea", "testDescription");
        degreePersistence.create(degree);
        Degree readedDegree = degreePersistence.read(degree.getCode());
        assertEquals(degree, readedDegree);
        assertEquals(1234, readedDegree.getCode());
        assertEquals(33, readedDegree.getCapacity());
        assertEquals("knowledgeArea", readedDegree.getKnowledgeArea());
        assertEquals("testDescription", readedDegree.getDescription());
        degree.setCapacity(44);
        degree.setKnowledgeArea("otherKnowledgeArea");
        degree.setDescription("otherDescription");
        degreePersistence.update(degree.getCode(), degree);
        readedDegree = degreePersistence.read(degree.getCode());
        assertEquals(44, readedDegree.getCapacity());
        assertEquals("otherKnowledgeArea", readedDegree.getKnowledgeArea());
        assertEquals("otherDescription", readedDegree.getDescription());
    }
}
