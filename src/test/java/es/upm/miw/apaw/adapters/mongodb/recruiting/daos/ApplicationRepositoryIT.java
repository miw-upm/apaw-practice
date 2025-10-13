package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.AttendeeEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationRepositoryIT {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void testUpdateMeetings() {
        UUID applicationId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0031");

        ApplicationEntity application = applicationRepository.findById(applicationId)
                .orElseThrow();

        assertThat(application.getMeetingList()).isNotEmpty();

        AttendeeEntity existingAttendee = AttendeeEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0015")) // Andrea Schulz
                .fullName("Andrea Schulz")
                .phoneNumber("+4165889667")
                .emailAddress("andrea.schulz@test.com")
                .build();

        MeetingEntity newMeeting = MeetingEntity.builder()
                .id(UUID.randomUUID())
                .date(java.time.LocalDateTime.now().plusDays(5))
                .url("//new-meeting-url")
                .attendees(List.of(existingAttendee))
                .build();

        application.setMeetingList(List.of(newMeeting));
        applicationRepository.save(application);

        ApplicationEntity updated = applicationRepository.findById(applicationId)
                .orElseThrow();

        assertThat(updated.getMeetingList()).hasSize(1);

        MeetingEntity m = updated.getMeetingList().getFirst();
        assertThat(m.getUrl()).isEqualTo("//new-meeting-url");
        assertThat(m.getAttendees()).hasSize(1);

        AttendeeEntity a = m.getAttendees().getFirst();
        assertThat(a.getEmailAddress()).isEqualTo("andrea.schulz@test.com");
        assertThat(a.getFullName()).isEqualTo("Andrea Schulz");

        assertThat(updated.getStatus()).isEqualTo(Status.In_process);
        assertThat(updated.getPositionEntity().getReference()).isEqualTo(1002);
    }

    @Test
    void testFindById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0030");

        Optional<ApplicationEntity> optionalApp = this.applicationRepository.findById(id);

        assertThat(optionalApp).isPresent();
        ApplicationEntity application = optionalApp.get();

        assertThat(application.getStatus()).isEqualTo(Status.Open);
        assertThat(application.getReferral()).isTrue();
        assertThat(application.getPositionEntity().getReference()).isEqualTo(1001);
        assertThat(application.getMeetingList()).hasSize(3);
        assertThat(application.getMeetingList().getFirst().getUrl()).contains("url-for-meeting");
    }

    @Test
    void testApplicationPositionRelation() {
        ApplicationEntity app = applicationRepository.findById(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0031")
        ).orElseThrow();

        assertThat(app.getPositionEntity()).isNotNull();
        assertThat(app.getPositionEntity().getReference()).isEqualTo(1002);
        assertThat(app.getPositionEntity().getName()).contains("CPI consultant");
    }

    @Test
    void testStatusEnumPersistence() {
        ApplicationEntity rejected = applicationRepository.findById(
                UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0033")
        ).orElseThrow();

        assertThat(rejected.getStatus()).isEqualTo(Status.In_process);
    }
}