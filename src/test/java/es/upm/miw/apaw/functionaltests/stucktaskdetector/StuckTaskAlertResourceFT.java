package es.upm.miw.apaw.functionaltests.stucktaskdetector;

import es.upm.miw.apaw.adapters.in.stucktaskdetector.StuckTaskAlertResource;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlert;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertCreation;
import es.upm.miw.apaw.domain.model.stucktaskdetector.StuckTaskAlertPatch;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.StuckTaskDetectorSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StuckTaskAlertResourceFT {
    private static final String ALERTS = StuckTaskAlertResource.STUCK_TASK_ALERTS;
    private static final String SEARCH = ALERTS + StuckTaskAlertResource.SEARCH;
    private static final String EMAIL_0 = "cliente0@example.com";
    private static final String EMAIL_1 = "cliente1@example.com";

    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(ALERTS + "/" + ALERT_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskAlert.class)
                .value(body -> assertThat(body).usingRecursiveComparison()
                        .ignoringFields("stuckTaskRule.createdByUser").isEqualTo(ALERT_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(ALERTS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(ALERTS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskAlert[].class)
                .value(body -> assertThat(body).extracting(StuckTaskAlert::getId)
                        .containsSubsequence(ALERT_ID_0, ALERT_ID_1, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4));
    }

    @Test
    void testCreate() {
        StuckTaskAlertCreation creation = this.newCreation();
        this.restTestClient.post().uri(ALERTS)
                .body(creation)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StuckTaskAlert.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getReference()).isEqualTo(creation.getReference());
                    assertThat(body.getDetectedAt()).isEqualTo(LocalDate.now());
                    assertThat(body.getEscalated()).isFalse();
                    assertThat(body.getStuckTaskRule().getId()).isEqualTo(RULE_ID_0);
                });
    }

    @Test
    void testCreateWithoutStuckTaskRule() {
        this.restTestClient.post().uri(ALERTS)
                .body(StuckTaskAlertCreation.builder().reference("FT-" + UUID.randomUUID()).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateStuckTaskRuleNotFound() {
        StuckTaskAlertCreation creation = this.newCreation();
        creation.setStuckTaskRuleId(UUID.randomUUID());
        this.restTestClient.post().uri(ALERTS)
                .body(creation)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testCreateDuplicateReference() {
        StuckTaskAlertCreation creation = this.newCreation();
        creation.setReference(ALERT_0.getReference());
        this.restTestClient.post().uri(ALERTS)
                .body(creation)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        StuckTaskAlert alert = this.createAlert();
        this.restTestClient.put().uri(ALERTS + "/" + alert.getId())
                .body(StuckTaskAlert.builder().reference(alert.getReference())
                        .resolvedAt(LocalDate.of(2026, 2, 1)).escalated(true).resolutionNotes("Resolved").build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskAlert.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(alert.getId());
                    assertThat(body.getResolvedAt()).isEqualTo(LocalDate.of(2026, 2, 1));
                    assertThat(body.getEscalated()).isTrue();
                    assertThat(body.getResolutionNotes()).isEqualTo("Resolved");
                    assertThat(body.getDetectedAt()).isEqualTo(alert.getDetectedAt());
                    assertThat(body.getStuckTaskRule().getId()).isEqualTo(RULE_ID_0);
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(ALERTS + "/" + UUID.randomUUID())
                .body(StuckTaskAlert.builder().escalated(true).build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateReference() {
        StuckTaskAlert alert = this.createAlert();
        this.restTestClient.put().uri(ALERTS + "/" + alert.getId())
                .body(StuckTaskAlert.builder().reference(ALERT_0.getReference()).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        StuckTaskAlert alert = this.createAlert();
        this.restTestClient.delete().uri(ALERTS + "/" + alert.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get().uri(ALERTS + "/" + alert.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteNotFound() {
        this.restTestClient.delete().uri(ALERTS + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatch() {
        StuckTaskAlert alert = this.createAlert();
        this.restTestClient.patch().uri(ALERTS + "/" + alert.getId())
                .body(new StuckTaskAlertPatch(null, null, true, null))
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskAlert.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getEscalated()).isTrue();
                    assertThat(body.getReference()).isEqualTo(alert.getReference());
                    assertThat(body.getDetectedAt()).isEqualTo(alert.getDetectedAt());
                });
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(ALERTS + "/" + UUID.randomUUID())
                .body(new StuckTaskAlertPatch(null, null, true, null))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchDuplicateReference() {
        StuckTaskAlert alert = this.createAlert();
        this.restTestClient.patch().uri(ALERTS + "/" + alert.getId())
                .body(new StuckTaskAlertPatch(ALERT_0.getReference(), null, null, null))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testFindWithoutCriteria() {
        this.mockUsers();
        this.restTestClient.get().uri(SEARCH)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskAlert[].class)
                .value(body -> assertThat(body).extracting(StuckTaskAlert::getId)
                        .contains(ALERT_ID_0, ALERT_ID_1, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4));
    }

    @Test
    void testFindByCreatorEmail() {
        this.mockUsers();
        this.restTestClient.get().uri(SEARCH + "?creatorEmail=" + EMAIL_1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskAlert[].class)
                .value(body -> assertThat(body).extracting(StuckTaskAlert::getId)
                        .contains(ALERT_ID_2, ALERT_ID_3).doesNotContain(ALERT_ID_0, ALERT_ID_1, ALERT_ID_4));
    }

    @Test
    void testFindByAllCriteria() {
        this.mockUsers();
        this.restTestClient.get()
                .uri(SEARCH + "?procedureKeyword=tax&withPenalty=true&escalated=true&creatorEmail=" + EMAIL_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StuckTaskAlert[].class).value(body -> {
                    assertThat(body).extracting(StuckTaskAlert::getId)
                            .contains(ALERT_ID_1).doesNotContain(ALERT_ID_0, ALERT_ID_2, ALERT_ID_3, ALERT_ID_4);
                    assertThat(body).filteredOn(alert -> alert.getId().equals(ALERT_ID_1))
                            .singleElement().satisfies(alert -> assertThat(
                                    alert.getStuckTaskRule().getCreatedByUser().getEmail()).isEqualTo(EMAIL_0));
                });
    }

    @Test
    void testFindUserNotFound() {
        when(this.userFinder.findByIds(anySet())).thenReturn(List.of());
        this.restTestClient.get().uri(SEARCH)
                .exchange()
                .expectStatus().isNotFound();
    }

    private void mockUsers() {
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> ids = invocation.getArgument(0);
            return ids.stream()
                    .map(id -> UserSnapshot.builder().id(id).email(this.emailOf(id)).build())
                    .toList();
        });
    }

    private String emailOf(UUID userId) {
        if (userId.equals(RULE_0.getCreatedByUser().getId())) {
            return EMAIL_0;
        }
        if (userId.equals(RULE_1.getCreatedByUser().getId())) {
            return EMAIL_1;
        }
        return "other@example.com";
    }

    private StuckTaskAlertCreation newCreation() {
        return StuckTaskAlertCreation.builder()
                .reference("FT-" + UUID.randomUUID())
                .stuckTaskRuleId(RULE_ID_0)
                .build();
    }

    private StuckTaskAlert createAlert() {
        return this.restTestClient.post().uri(ALERTS)
                .body(this.newCreation())
                .exchange().expectStatus().isCreated()
                .expectBody(StuckTaskAlert.class).returnResult().getResponseBody();
    }
}
