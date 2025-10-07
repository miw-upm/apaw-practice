package es.upm.miw.apaw.functionaltests.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.ApplicationRepository;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.ApplicationEntity;
import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import es.upm.miw.apaw.adapters.resources.recruiting.ApplicationResource;
import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ApplicationResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ApplicationRepository applicationRepository;

    private ApplicationEntity savedEntity;

    @BeforeEach
    void setUp() {
        applicationRepository.deleteAll();

        savedEntity = applicationRepository.save(
                ApplicationEntity.builder()
                        .id(UUID.randomUUID())
                        .status(Status.Open)
                        .created(LocalDate.now())
                        .referral(false)
                        .user(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                        .positionEntity(PositionEntity.builder()
                                .id(UUID.randomUUID())
                                .name("Java Developer")
                                .build())
                        .meetingList(List.of())
                        .build()
        );
    }

    @Test
    void testUpdateMeetings() {
        Meeting meeting1 = Meeting.builder()
                .date(LocalDateTime.now().plusDays(1))
                .url("https://meet1.com")
                .build();

        Meeting meeting2 = Meeting.builder()
                .date(LocalDateTime.now().plusDays(2))
                .url("https://meet2.com")
                .build();

        List<Meeting> meetingList = List.of(meeting1, meeting2);

        webTestClient.put()
                .uri(ApplicationResource.APPLICATIONS + ApplicationResource.ID_ID + ApplicationResource.MEETINGS,
                        savedEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(meetingList)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Application.class)
                .value(application -> {
                    assertThat(application.getId()).isEqualTo(savedEntity.getId());
                    assertThat(application.getMeetingList()).hasSize(2);
                    assertThat(application.getMeetingList().get(0).getUrl()).isEqualTo("https://meet1.com");
                    assertThat(application.getMeetingList().get(1).getUrl()).isEqualTo("https://meet2.com");
                    assertThat(application.getStatus()).isEqualTo(Status.Open);
                    assertThat(application.getUser()).isNotNull();
                    assertThat(application.getUser().getId()).isEqualTo(savedEntity.getUser());
                });
    }

    @Test
    void testUpdateMeetingsNotFound() {
        UUID fakeId = UUID.randomUUID();

        Meeting meeting = Meeting.builder()
                .date(LocalDateTime.now().plusDays(1))
                .url("https://fake-meeting.com")
                .build();

        webTestClient.put()
                .uri(ApplicationResource.APPLICATIONS + ApplicationResource.ID_ID + ApplicationResource.MEETINGS, fakeId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(List.of(meeting))
                .exchange()
                .expectStatus().isNotFound();
    }
}
