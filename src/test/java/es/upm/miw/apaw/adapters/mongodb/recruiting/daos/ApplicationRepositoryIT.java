package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.MeetingEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationRepositoryIT {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void testFindById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0032");

        assertTrue(this.applicationRepository.findById(id).isPresent());

        ApplicationEntity application = this.applicationRepository.findById(id).get();

        assertThat(application.getId()).isEqualTo(id);
        assertThat(application.getStatus()).isEqualTo(Status.Hired);
        assertThat(application.getCreated()).isBeforeOrEqualTo(LocalDate.now());
        assertThat(application.getUser()).isNotNull();
        assertThat(application.getPositionEntity()).isInstanceOf(PositionEntity.class);

        List<MeetingEntity> meetings = application.getMeetingList();
        if (meetings != null && !meetings.isEmpty()) {
            assertThat(meetings.get(0).getDate()).isNotNull();
            assertThat(meetings.get(0).getUrl()).contains("meeting-4");
        }
    }

    @Test
    void testSaveAndFind() {
        UUID newId = UUID.randomUUID();

        ApplicationEntity newApplication = ApplicationEntity.builder()
                .id(newId)
                .status(Status.Open)
                .created(LocalDate.now())
                .referral(false)
                .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                .positionEntity(PositionEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .name("Java Developer")
                        .description("Spring Boot backend position")
                        .build())
                .meetingList(List.of())
                .build();

        this.applicationRepository.save(newApplication);

        // Verify persistence
        assertTrue(this.applicationRepository.findById(newId).isPresent());
        ApplicationEntity saved = this.applicationRepository.findById(newId).get();

        assertThat(saved.getStatus()).isEqualTo(Status.Open );
        assertThat(saved.getReferral()).isFalse();
        assertThat(saved.getUser()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
    }
}
