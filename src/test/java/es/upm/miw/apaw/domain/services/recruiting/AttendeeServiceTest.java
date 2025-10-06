package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.AttendeePersistenceMongodb;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class AttendeeServiceTest {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private AttendeePersistenceMongodb attendeePersistenceMongodb;

    private AttendeeEntity storedEntity;

    @BeforeEach
    void setUp() {
        attendeeRepository.deleteAll();

        storedEntity = attendeeRepository.save(
                AttendeeEntity.builder()
                        .id(UUID.randomUUID())
                        .emailAddress("maria.smith@example.com")
                        .fullName("Maria Smith")
                        .phoneNumber("611223344")
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .build()
        );
    }

    @Test
    void testReadByEmailAddressFound() {
        Attendee attendee = attendeePersistenceMongodb.readByEmailAddress("maria.smith@example.com");

        assertThat(attendee).isNotNull();
        assertThat(attendee.getEmailAddress()).isEqualTo("maria.smith@example.com");
        assertThat(attendee.getFullName()).isEqualTo("Maria Smith");
        assertThat(attendee.getUser().getId()).isEqualTo(storedEntity.getUser());
    }

    @Test
    void testReadByEmailAddressNotFound() {
        assertThrows(NotFoundException.class,
                () -> attendeePersistenceMongodb.readByEmailAddress("unknown@example.com"));
    }
}
