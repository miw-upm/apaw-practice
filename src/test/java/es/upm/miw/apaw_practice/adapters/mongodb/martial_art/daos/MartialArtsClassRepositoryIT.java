package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;
import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.InstructorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.MartialArtsClassEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestConfig
public class MartialArtsClassRepositoryIT {
    @Autowired
    private MartialArtsClassRepository martialArtsClassRepository;

    @Test
    void testFindByName() {
        assertTrue(this.martialArtsClassRepository.findByName("Taekwondo").isPresent());
        MartialArtsClassEntity martialArtsClassEntity = this.martialArtsClassRepository.findByName("Taekwondo").get();
        assertEquals("Taekwondo", martialArtsClassEntity.getName());
        assertEquals(LocalDate.of(2024, 10, 7), martialArtsClassEntity.getStartDate());
    }
}
