package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.LecturerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class LecturerEntityRepositoryIT {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.lecturerRepository.findByDni("7732893D").isPresent());
        LecturerEntity lecturer = this.lecturerRepository.findByDni("7732893D").get();
        assertEquals("Teresa", lecturer.getName());
        assertEquals("245473227", lecturer.getPhone());
    }
}
