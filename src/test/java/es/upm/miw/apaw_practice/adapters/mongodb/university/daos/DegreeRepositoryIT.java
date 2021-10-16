package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DegreeRepositoryIT {

    @Autowired
    private DegreeRepository degreeRepository;

    @Test
    void testFindByCode() {
        assertTrue(this.degreeRepository.findByCode(4313004).isPresent());
        DegreeEntity degree = this.degreeRepository.findByCode(4313004).get();
        assertEquals("Máster en Ingeniería Web", degree.getTitle());
        assertEquals(LocalDate.parse("2011-09-01"), degree.getImplementationDate());
    }

}
