package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ParticipantEntityRepositoryIT {

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.participantRepository.findByDni("4823468X").isPresent());
        ParticipantEntity participant = this.participantRepository.findByDni("4823468X").get();
        assertEquals("Luis", participant.getName());
        assertEquals(true, participant.getGraduate());
        assertEquals("luis3215@gmail.com", participant.getEmail());
        assertEquals("874326783", participant.getPhone());
    }
}
