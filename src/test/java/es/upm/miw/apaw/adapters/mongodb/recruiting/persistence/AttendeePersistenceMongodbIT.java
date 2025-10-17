package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.AttendeePersistenceMongodb;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class AttendeePersistenceMongodbIT {

    @Autowired
    private AttendeePersistenceMongodb attendeePersistence;

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Test
    void testReadByEmail() {
        String email = "markus.urbanietz@test.com";

        Attendee attendee = attendeePersistence.readByEmailAddress(email);

        assertThat(attendee).isNotNull();
        assertThat(attendee.getEmailAddress()).isEqualTo(email);
        assertThat(attendee.getFullName()).isEqualTo("Markus Urbanietz");
        assertThat(attendee.getPhoneNumber()).isEqualTo("+4112345123");
        assertThat(attendee.getUser()).isNotNull();
        assertThat(attendee.getUser().getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0004"));
    }

    @Test
    void testReadByEmailNotFound() {
        String email = "notfound@example.com";

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> attendeePersistence.readByEmailAddress(email));
        assertThat(exception.getMessage()).contains("No existing Email Address: " + email);
    }


    @Test
    void testDeleteAttendee() {
        String email = "beate.magnie@test.com";

        assertThat(attendeePersistence.readByEmailAddress(email).getEmailAddress()).isEqualTo(email);
        attendeePersistence.delete(email);
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> attendeePersistence.readByEmailAddress(email));
        assertThat(exception.getMessage()).contains("No existing Email Address: " + email);
    }

    @Test
    void testDeleteAttendeeNotFound() {
        String email = "not.existent@test.com";
        assertThrows(NotFoundException.class, () -> attendeePersistence.delete(email));
    }
}