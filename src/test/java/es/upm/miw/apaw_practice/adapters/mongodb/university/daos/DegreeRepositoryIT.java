package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import es.upm.miw.apaw_practice.domain.models.university.Degree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class DegreeRepositoryIT {

    @Autowired
    private DegreeRepository degreeRepository;

    @Test
    void testFindByCodeNonExisting() {
        assertTrue(degreeRepository.findByCode(123).isEmpty());
    }

    @Test
    void testFindByCode() {
        Optional<DegreeEntity> degreeEntity = degreeRepository.findByCode(2000);
        assertFalse(degreeEntity.isEmpty());
        assertNotNull(degreeEntity.get());
        Degree degree = degreeEntity.get().toDegree();
        assertEquals(30, degree.getCapacity());
        assertEquals("Fine Arts", degree.getKnowledgeArea());
        assertEquals("Program that explores artistic expression in painting, sculpture and digital media, developing creative skills and techniques for innovative visual projects.", degree.getDescription());
    }
}