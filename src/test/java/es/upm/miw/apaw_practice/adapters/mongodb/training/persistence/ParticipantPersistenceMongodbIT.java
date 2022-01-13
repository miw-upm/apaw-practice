package es.upm.miw.apaw_practice.adapters.mongodb.training.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.training.Course;
import es.upm.miw.apaw_practice.domain.models.training.Participant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
public class ParticipantPersistenceMongodbIT {

    @Autowired
    private ParticipantPersistenceMongodb participantPersistence;

    @Test
    void testReadByEmail() {
        Participant participant = this.participantPersistence.readByEmail("juan99@gmail.com");
        assertEquals("Juan", participant.getName());
        assertFalse(participant.getGraduate());
        assertEquals(643167221, participant.getPhone());
        assertEquals(2, participant.getCourses().size());
        assertTrue(participant.getCourses().stream()
                .map(Course::getIdentity)
                .collect(Collectors.toList())
                .containsAll(List.of("62001", "62003")));
    }

    @Test
    void testReadByPhone() {
        Participant participant = this.participantPersistence.readByPhone(643167221);
        assertEquals("Juan", participant.getName());
        assertFalse(participant.getGraduate());
        assertEquals("juan99@gmail.com", participant.getEmail());
        assertEquals(2, participant.getCourses().size());
        assertTrue(participant.getCourses().stream()
                .map(Course::getIdentity)
                .collect(Collectors.toList())
                .containsAll(List.of("62001", "62003")));
    }
}
