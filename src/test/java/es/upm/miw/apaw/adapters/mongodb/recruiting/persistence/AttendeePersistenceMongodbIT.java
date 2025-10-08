package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.AttendeePersistenceMongodb;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendeePersistenceMongodbIT {

    @Mock
    private AttendeeRepository attendeeRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private AttendeePersistenceMongodb attendeePersistenceMongodb;

    private AttendeeEntity attendeeEntity;
    private ApplicationEntity applicationEntity;
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

        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(UUID.randomUUID())
                .date(LocalDateTime.now())
                .url("https://meeting.test.com")
                .attendees(new ArrayList<>(List.of(attendeeEntity)))
                .build();

        applicationEntity = ApplicationEntity.builder()
                .id(UUID.randomUUID())
                .meetingList(new ArrayList<>(List.of(meetingEntity)))
                .build();
    }

    @Test
    void testReadByEmailAddressFound() {
        when(attendeeRepository.findByEmailAddress(EMAIL)).thenReturn(Optional.of(attendeeEntity));

        Attendee attendee = attendeePersistenceMongodb.readByEmailAddress(EMAIL);

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
        String email = "notfound@example.com";
        when(attendeeRepository.findByEmailAddress(email)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> attendeePersistenceMongodb.readByEmailAddress(email)
        );

        assertTrue(exception.getMessage().contains("No existing Email Address: " + email));
        verify(attendeeRepository, times(1)).findByEmailAddress(email);
    }

    @Test
    void testDeleteAttendeeFoundInMeeting() {
        // Attendee exists and is referenced inside applicationEntity.meetingList
        when(attendeeRepository.findByEmailAddress(EMAIL)).thenReturn(Optional.of(attendeeEntity));
        when(applicationRepository.findAll()).thenReturn(List.of(applicationEntity));
        when(applicationRepository.save(any(ApplicationEntity.class))).thenReturn(applicationEntity);

        attendeePersistenceMongodb.delete(EMAIL);

        verify(attendeeRepository, times(1)).findByEmailAddress(EMAIL);
        verify(applicationRepository, times(1)).findAll();
        verify(applicationRepository, times(1)).save(applicationEntity);
        verify(attendeeRepository, times(1)).delete(attendeeEntity);

        assertTrue(applicationEntity.getMeetingList().get(0).getAttendees().isEmpty());
    }

    @Test
    void testDeleteAttendeeNotInAnyMeeting() {
        when(attendeeRepository.findByEmailAddress(EMAIL)).thenReturn(Optional.of(attendeeEntity));

        ApplicationEntity appWithoutAttendee = ApplicationEntity.builder()
                .id(UUID.randomUUID())
                .meetingList(new ArrayList<>()) // Without any meeting
                .build();

        when(applicationRepository.findAll()).thenReturn(List.of(appWithoutAttendee));
        when(applicationRepository.save(any(ApplicationEntity.class))).thenReturn(appWithoutAttendee);

        attendeePersistenceMongodb.delete(EMAIL);

        verify(attendeeRepository, times(1)).findByEmailAddress(EMAIL);
        verify(applicationRepository, times(1)).findAll();
        verify(applicationRepository, times(1)).save(appWithoutAttendee);
        verify(attendeeRepository, times(1)).delete(attendeeEntity);
    }
}