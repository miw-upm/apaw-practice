package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ParticipantRepositoryIT {

    @Autowired
    private ParticipantRepository participantRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.participantRepository.findByEmail("luis3215@gmail.com").isPresent());
        ParticipantEntity participant = this.participantRepository.findByEmail("luis3215@gmail.com").get();
        assertEquals("Luis", participant.getName());
        assertTrue(participant.getGraduate());
        assertEquals(874326783, participant.getPhone());
        assertTrue(participant.getCourseList().stream()
                .map(CourseEntity::getIdentity)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("62001", "62002")));
    }
}
