package es.upm.miw.apaw.domain.services.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ActiveProfiles("test")
class AttendeeServiceTest {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AttendeeService attendeeService;

    @MockitoBean
    private UserRestClient userRestClient;

    private static final String EMAIL = "bob.dylan@test.com";

    @BeforeEach
    void setUp() {
        applicationRepository.deleteAll();
        attendeeRepository.deleteAll();

        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(invocation.getArgument(0)).mobile("123456789").firstName("mock").build());

        AttendeeEntity attendeeEntity = AttendeeEntity.builder()
                .id(UUID.randomUUID())
                .emailAddress(EMAIL)
                .fullName("Bob Dylan")
                .phoneNumber("+14611592787")
                .user(UserDto.builder().build().getId())
                .build();
        attendeeRepository.save(attendeeEntity);

        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(UUID.randomUUID())
                .date(LocalDateTime.now())
                .url("https://meeting.test.com")
                .attendees(new ArrayList<>(List.of(attendeeEntity)))
                .build();

        ApplicationEntity applicationEntity = ApplicationEntity.builder()
                .id(UUID.randomUUID())
                .meetingList(new ArrayList<>(List.of(meetingEntity)))
                .build();
        applicationRepository.save(applicationEntity);
    }

    @Test
    void testReadByEmailAddressFound() {
        Attendee attendee = attendeeService.read(EMAIL);

        assertThat(attendee).isNotNull();
        assertThat(attendee.getEmailAddress()).isEqualTo(EMAIL);
        assertThat(attendee.getFullName()).isEqualTo("Bob Dylan");
    }

    @Test
    void testReadByEmailAddressNotFound() {
        assertThrows(NotFoundException.class,
                () -> attendeeService.read("unknown@example.com"));
    }

    @Test
    void testDeleteAttendeeFoundAndRemovedFromMeeting() {
        // Precondition: attendee exists in the meeting
        assertThat(applicationRepository.findAll().getFirst()
                .getMeetingList().getFirst().getAttendees()).isNotEmpty();

        attendeeService.delete(EMAIL);

        // Check attendee was removed from the meeting
        ApplicationEntity app = applicationRepository.findAll().getFirst();
        assertThat(app.getMeetingList().getFirst().getAttendees()).isEmpty();

        // Check attendee was deleted from repository
        assertThat(attendeeRepository.findByEmailAddress(EMAIL)).isEmpty();
    }

    @Test
    void testDeleteAttendeeExistsButNotInAnyMeeting() {
        // New attendee not assigned to any meeting
        AttendeeEntity soloAttendee = AttendeeEntity.builder()
                .id(UUID.randomUUID())
                .emailAddress("notassignedtomeeting@test.com")
                .fullName("Not assigned")
                .phoneNumber("+34710986745")
                .user(UserDto.builder().build().getId())
                .build();
        attendeeRepository.save(soloAttendee);

        attendeeService.delete("notassignedtomeeting@test.com");

        assertThat(attendeeRepository.findByEmailAddress("notassignedtomeeting@test.com")).isEmpty();
    }

    @Test
    void testDeleteAttendeeNotFound() {
        assertThrows(NotFoundException.class,
                () -> attendeeService.delete("unknown@example.com"));
    }
}
