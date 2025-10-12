package es.upm.miw.apaw.functionaltests.recruiting;

import es.upm.miw.apaw.adapters.mongodb.recruiting.daos.RecruitingSeeder;
import es.upm.miw.apaw.adapters.resources.recruiting.ApplicationResource;
import es.upm.miw.apaw.domain.models.recruiting.Application;
import es.upm.miw.apaw.domain.models.recruiting.Meeting;
import es.upm.miw.apaw.domain.models.recruiting.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
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
    private RecruitingSeeder recruitingSeeder;

    @BeforeEach
    void resetDb() {
        recruitingSeeder.deleteAll();
        recruitingSeeder.seedDatabase();
    }

    // --- UPDATE endpoint test ---------------------------------------------------------

    @Test
    void testUpdateMeetings() {
        UUID applicationId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0031");

        // New meetings
        Meeting newMeeting1 = Meeting.builder()
                .date(LocalDateTime.now().plusDays(2))
                .url("https://updated-meeting-1.com")
                .build();

        Meeting newMeeting2 = Meeting.builder()
                .date(LocalDateTime.now().plusDays(5))
                .url("https://updated-meeting-2.com")
                .build();

        List<Meeting> newMeetings = List.of(newMeeting1, newMeeting2);

        webTestClient.put()
                .uri(ApplicationResource.APPLICATIONS + ApplicationResource.ID_ID + ApplicationResource.MEETINGS,
                        applicationId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newMeetings)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Application.class)
                .value(updated -> {
                    assertThat(updated.getId()).isEqualTo(applicationId);
                    assertThat(updated.getMeetingList()).hasSize(2);
                    assertThat(updated.getMeetingList().get(0).getUrl()).isEqualTo("https://updated-meeting-1.com");
                    assertThat(updated.getMeetingList().get(1).getUrl()).isEqualTo("https://updated-meeting-2.com");
                    assertThat(updated.getStatus()).isEqualTo(Status.In_process);
                    assertThat(updated.getUser()).isNotNull();
                    assertThat(updated.getUser().getId())
                            .isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
                });
    }

    @Test
    void testUpdateMeetingsNotFound() {

        UUID applicationId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9999");

        // New meetings
        Meeting newMeeting1 = Meeting.builder()
                .date(LocalDateTime.now().plusDays(2))
                .url("https://updated-meeting-1.com")
                .build();

        Meeting newMeeting2 = Meeting.builder()
                .date(LocalDateTime.now().plusDays(5))
                .url("https://updated-meeting-2.com")
                .build();

        List<Meeting> newMeetings = List.of(newMeeting1, newMeeting2);

        webTestClient.put()
                .uri(ApplicationResource.APPLICATIONS + ApplicationResource.ID_ID + ApplicationResource.MEETINGS,
                        applicationId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newMeetings)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testUpdateMeetingsConflict() {
        // This Application is in status Rejected
        UUID rejectedApplicationId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0034");

        Meeting meeting = Meeting.builder()
                .date(LocalDateTime.now().plusDays(1))
                .url("https://conflict-meeting.com")
                .build();

        List<Meeting> meetingList = List.of(meeting);

        // Trying to update a rejected Application
        webTestClient.put()
                .uri(ApplicationResource.APPLICATIONS + ApplicationResource.ID_ID +
                         ApplicationResource.MEETINGS, rejectedApplicationId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(meetingList)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    // --- SEARCHES endpoints test ------------------------------------------------------

    // TEST Search #1

    @Test
    void testFindAccumulatedAnnualSalaryByFullName() {
        String fullName = "Markus Urbanietz";
        BigDecimal expectedSalary = new BigDecimal("176000.00");

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApplicationResource.APPLICATIONS + ApplicationResource.SEARCHES + ApplicationResource.ANNUALSALARY)
                        .queryParam("fullName", fullName)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(salary -> {
                    assertThat(salary).isEqualByComparingTo(expectedSalary);
                });
    }
}
