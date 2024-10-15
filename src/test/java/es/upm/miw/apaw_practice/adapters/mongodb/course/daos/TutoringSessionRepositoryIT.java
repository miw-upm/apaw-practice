package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.TutoringSessionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class TutoringSessionRepositoryIT {

    @Autowired
    private TutoringSessionRepository tutoringSessionRepository;

    @Test
    void findByTitle(){
        assertTrue(this.tutoringSessionRepository.findByTitle("Lenguaje C Básico").isPresent());
        TutoringSessionEntity tutoringSession = this.tutoringSessionRepository.findByTitle("Lenguaje C Básico").get();
        assertEquals("Lenguaje C Básico", tutoringSession.getTitle());
        assertEquals(LocalDateTime.of(2024, 12, 5, 16, 0), tutoringSession.getDateTime());
        assertEquals(BigDecimal.valueOf(20.00), tutoringSession.getPrice());
        assertFalse(tutoringSession.toString().isEmpty());
    }
}
