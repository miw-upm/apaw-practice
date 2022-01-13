package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class LecturerRepositoryIT {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.lecturerRepository.findAll().stream()
                .anyMatch(lecturer ->
                        "1468048B".equals(lecturer.getDni()) &&
                                lecturer.getId() != null &&
                                LocalDate.of(2005, 10, 13).equals(lecturer.getStartDate()) &&
                                17 == lecturer.getExperience()
                ));
    }
}
