package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.SubjectEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class SubjectRepositoryIT {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void testFindByCode() {
        assertTrue(this.subjectRepository.findByReference(613000095).isPresent());
        SubjectEntity degree = this.subjectRepository.findByReference(613000095).get();
        assertEquals("Ingeniería Web: Visión General", degree.getTopic());
        assertEquals(6, degree.getCredits());
    }

}
