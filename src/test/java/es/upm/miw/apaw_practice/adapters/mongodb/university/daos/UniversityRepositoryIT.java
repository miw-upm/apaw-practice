package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.UniversityEntity;
import es.upm.miw.apaw_practice.domain.models.university.University;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class UniversityRepositoryIT {

    @Autowired
    private UniversityRepository universityRepository;

    @Test
    void testFindByTopDomainNonExisting() {
        assertTrue(universityRepository.findByTopDomain("urjc.es").isEmpty());
    }

    @Test
    void testFindByTopDomain() {
        Optional<UniversityEntity> universityEntity = universityRepository.findByTopDomain("cam.ac.uk");
        assertFalse(universityEntity.isEmpty());
        University university = universityEntity.get().toUniversity();
        assertEquals("University of Cambridge", university.getName());
        assertFalse(university.getAllowsInternationalStudents());
        assertEquals(3, university.getNumberOfFaculties());
        assertEquals(3, university.getDegreesOffered().size());
    }
}