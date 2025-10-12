package es.upm.miw.apaw.adapters.mongodb.recruiting.persistence;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.AttendeeRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.recruiting.Attendee;
import es.upm.miw.apaw.adapters.mongodb.recruiting.persistance.AttendeePersistenceMongodb;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class AttendeePersistenceMongodbIT {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private AttendeePersistenceMongodb attendeePersistence;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void setUp() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    @Test
    void testReadByEmailAddressFound() {

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
    void testReadByEmailAddressNotFound() {
        String email = "notfound@example.com";

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> attendeePersistence.readByEmailAddress(email)
        );

        assertThat(exception.getMessage()).contains("No existing Email Address: " + email);
    }


    @Test
    void testDeleteAttendeeFoundInMeetings() {
        String email = "beate.magnie@test.com";

        assertThat(attendeeRepository.findByEmailAddress(email)).isPresent();

        long countBefore = attendeeRepository.count();
        List<ApplicationEntity> appsBefore = applicationRepository.findAll();
        long totalMeetingsWithAttendee = appsBefore.stream()
                .flatMap(app -> app.getMeetingList().stream())
                .filter(meeting -> meeting.getAttendees().stream()
                        .anyMatch(a -> email.equals(a.getEmailAddress())))
                .count();

        assertThat(totalMeetingsWithAttendee).isGreaterThan(0);

        attendeePersistence.delete(email);

        Optional<AttendeeEntity> attendeeOpt = attendeeRepository.findByEmailAddress(email);
        assertThat(attendeeOpt).isEmpty();

        // Check attendee has been deleted from the meetings
        List<ApplicationEntity> appsAfter = applicationRepository.findAll();
        boolean stillPresent = appsAfter.stream()
                .flatMap(app -> app.getMeetingList().stream())
                .anyMatch(meeting -> meeting.getAttendees().stream()
                        .anyMatch(a -> email.equals(a.getEmailAddress())));
        assertThat(stillPresent).isFalse();

        assertThat(attendeeRepository.count()).isEqualTo(countBefore - 1);
    }

    @Test
    void testDeleteAttendeeNotInAnyMeeting() {
        String email = "karolyn.sanz@test.com";

        assertThat(attendeeRepository.findByEmailAddress(email)).isPresent();

        long countBefore = attendeeRepository.count();

        attendeePersistence.delete(email);

        assertThat(attendeeRepository.findByEmailAddress(email)).isEmpty();

        boolean presentInMeetings = applicationRepository.findAll().stream()
                .flatMap(app -> app.getMeetingList().stream())
                .anyMatch(m -> m.getAttendees().stream()
                        .anyMatch(a -> email.equals(a.getEmailAddress())));
        assertThat(presentInMeetings).isFalse();

        assertThat(attendeeRepository.count()).isEqualTo(countBefore - 1);
    }

    @Test
    void testDeleteAttendeeNotFoundThrowsException() {
        String email = "not.existent@test.com";
        assertThrows(NotFoundException.class, () -> attendeePersistence.delete(email));
    }

    @Test
    void testDeleteAttendeeRemovesFromMultipleMeetings() {

        String email = "beate.magnie@test.com";

        long meetingsBefore = applicationRepository.findAll().stream()
                .flatMap(app -> app.getMeetingList().stream())
                .filter(meeting -> meeting.getAttendees().stream()
                        .anyMatch(a -> email.equals(a.getEmailAddress())))
                .count();

        assertThat(meetingsBefore).isGreaterThan(1);

        attendeePersistence.delete(email);

        long meetingsAfter = applicationRepository.findAll().stream()
                .flatMap(app -> app.getMeetingList().stream())
                .filter(meeting -> meeting.getAttendees().stream()
                        .anyMatch(a -> email.equals(a.getEmailAddress())))
                .count();

        assertThat(meetingsAfter).isZero();
    }

    @Test
    void testDeleteAttendeeCleansReferencesButKeepsApplications() {
        String email = "markus.urbanietz@test.com";

        List<ApplicationEntity> before = applicationRepository.findAll();
        int totalApplications = before.size();

        attendeePersistence.delete(email);

        List<ApplicationEntity> after = applicationRepository.findAll();
        assertThat(after).hasSize(totalApplications); // Applications must never been deleted
    }
}