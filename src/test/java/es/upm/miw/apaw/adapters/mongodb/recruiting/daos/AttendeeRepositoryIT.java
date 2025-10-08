package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class AttendeeRepositoryIT {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Test
    void testFindByEmailAddress() {
        assertTrue(this.attendeeRepository.findByEmailAddress("markus.urbanietz@test.com").isPresent());
        AttendeeEntity attendee = this.attendeeRepository.findByEmailAddress("markus.urbanietz@test.com").get();
        assertThat(attendee.getEmailAddress()).isEqualTo("markus.urbanietz@test.com");
        assertThat(attendee.getFullName()).isEqualTo("Markus Urbanietz");
        assertThat(attendee.getPhoneNumber()).isEqualTo("+4112345123");
        assertThat(attendee.getUser()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"));
    }
}
