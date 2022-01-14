package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.training.TrainingSeederService;
import es.upm.miw.apaw_practice.domain.models.training.Lecturer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class LecturerPersistenceMongodbIT {

    @Autowired
    private LecturerPersistenceMongodb lecturerPersistenceMongodb;

    @Autowired
    private TrainingSeederService trainingSeederService;

    @Test
    void testReadByDni() {
        Optional<Lecturer> lecturer = this.lecturerPersistenceMongodb.readAll()
                .filter(lecturer1 -> LocalDate.of(1998,4,6).equals(lecturer1.getStartDate()))
                .findFirst();
        assertTrue(lecturer.isPresent());
        assertNotNull(lecturer.get().getDni());
        assertNotNull(lecturer.get().getStartDate());
        assertNotNull(lecturer.get().getExperience());
    }

    @Test
    void testUpdate() {
        Lecturer lecturer = this.lecturerPersistenceMongodb.readByDni("3386275R");
        assertEquals(LocalDate.of(1998,4,6),lecturer.getStartDate());
        assertEquals(24, lecturer.getExperience());
        lecturer.setStartDate(LocalDate.of(2002,9,18));
        lecturer.setExperience(20);
        this.lecturerPersistenceMongodb.update(lecturer);
        Lecturer lecturerUpdated = this.lecturerPersistenceMongodb.readByDni("3386275R");
        assertEquals(LocalDate.of(2002,9,18), lecturer.getStartDate());
        assertEquals(20, lecturerUpdated.getExperience());
        trainingSeederService.deleteAll();
        trainingSeederService.seedDatabase();
    }
}
