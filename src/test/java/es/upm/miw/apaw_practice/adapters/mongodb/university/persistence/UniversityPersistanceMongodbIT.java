package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.University;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class UniversityPersistanceMongodbIT {

    @Autowired
    private UniversityPersistenceMongodb universityPersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> universityPersistence.read("uah.es"));
    }

    @Test
    void testCodeNotExists() {
        assertFalse(universityPersistence.existTopDomain("uah.es"));
    }

    @Test
    void testCodeExists() {
        assertTrue(universityPersistence.existTopDomain("lse.ac.uk"));
    }

    @Test
    void testReadById() {
        Optional<University> studentOptional = universityPersistence.readAll().filter(degree -> degree.getTopDomain().equals("lse.ac.uk")).findFirst();
        assertTrue(studentOptional.isPresent());
        University university = studentOptional.get();
        assertEquals("London School of Economics and Political Science", university.getName());
        assertTrue(university.getAllowsInternationalStudents());
        assertEquals(3, university.getNumberOfFaculties());
        assertEquals(2, university.getDegreesOffered().size());
    }

    @Test
    void testCreateAndUpdate() {
        University university = new University("upm.es", "Universidad Complutense de Madrid", Boolean.TRUE, 7, new ArrayList<>());
        universityPersistence.create(university);
        University readedUniversity = universityPersistence.read(university.getTopDomain());
        assertEquals(university, readedUniversity);
        assertEquals("Universidad Complutense de Madrid", readedUniversity.getName());
        assertTrue(readedUniversity.getAllowsInternationalStudents());
        assertEquals(7, readedUniversity.getNumberOfFaculties());
        assertTrue(readedUniversity.getDegreesOffered().isEmpty());
        university.setName("new name");
        university.setAllowsInternationalStudents(false);
        university.setNumberOfFaculties(11);
        universityPersistence.update(university.getTopDomain(), university);
        readedUniversity = universityPersistence.read(university.getTopDomain());
        assertEquals(university, readedUniversity);
        assertEquals("new name", readedUniversity.getName());
        assertFalse(readedUniversity.getAllowsInternationalStudents());
        assertEquals(11, readedUniversity.getNumberOfFaculties());
    }
}
