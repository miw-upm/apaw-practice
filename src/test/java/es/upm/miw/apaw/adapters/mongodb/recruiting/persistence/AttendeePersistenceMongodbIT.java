package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.AttendeePersistenceMongodb;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendeePersistenceMongodbIT {

    @Mock
    private AttendeeRepository attendeeRepository;

    @InjectMocks
    private AttendeePersistenceMongodb attendeePersistenceMongodb;

    private AttendeeEntity attendeeEntity;
    private UUID userId;
    private static final String EMAIL = "test@example.com";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        attendeeEntity = AttendeeEntity.builder()
                .id(UUID.randomUUID())
                .emailAddress(EMAIL)
                .fullName("Test User")
                .phoneNumber("123456789")
                .user(userId)
                .build();
    }

    @Test
    void testReadByEmailAddressFound() {
        // given
        when(attendeeRepository.findByEmailAddress(EMAIL)).thenReturn(Optional.of(attendeeEntity));

        // when
        Attendee attendee = attendeePersistenceMongodb.readByEmailAddress(EMAIL);

        // then
        assertNotNull(attendee);
        assertEquals(EMAIL, attendee.getEmailAddress());
        assertEquals("Test User", attendee.getFullName());
        assertEquals("123456789", attendee.getPhoneNumber());
        assertNotNull(attendee.getUser());
        assertEquals(userId, attendee.getUser().getId());

        verify(attendeeRepository, times(1)).findByEmailAddress(EMAIL);
    }

    @Test
    void testReadByEmailAddressNotFound() {
        // given
        String email = "notfound@example.com";
        when(attendeeRepository.findByEmailAddress(email)).thenReturn(Optional.empty());

        // when & then
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> attendeePersistenceMongodb.readByEmailAddress(email)
        );

        assertTrue(exception.getMessage().contains("No existing Email Address: " + email));
        verify(attendeeRepository, times(1)).findByEmailAddress(email);
    }
}
