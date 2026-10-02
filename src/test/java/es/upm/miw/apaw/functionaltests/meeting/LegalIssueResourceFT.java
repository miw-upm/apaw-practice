package es.upm.miw.apaw.functionaltests.meeting;

import es.upm.miw.apaw.adapters.in.meeting.LegalIssueResource;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.LegalIssueResolvedUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LegalIssueResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testCreate() {
        LegalIssue legalIssue = this.newIssue();
        this.restTestClient.post().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(legalIssue)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LegalIssue.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getCreationDate()).isNotNull();
                    assertThat(body.getTitle()).isEqualTo(legalIssue.getTitle());
                    assertThat(body.getPriority()).isEqualTo(legalIssue.getPriority());
                    assertThat(body.getResolved()).isFalse();
                });
    }

    @Test
    void testCreateBlankTitle() {
        this.restTestClient.post().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(LegalIssue.builder().title(" ").priority(1).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateWithoutPriority() {
        this.restTestClient.post().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(LegalIssue.builder().title("FT legal issue " + UUID.randomUUID()).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateTitle() {
        LegalIssue legalIssue = this.newIssue();
        legalIssue.setTitle(ISSUE_0.getTitle());
        this.restTestClient.post().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(legalIssue)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(LegalIssueResource.LEGAL_ISSUES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalIssue.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(ISSUE_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(LegalIssueResource.LEGAL_ISSUES + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        LegalIssue extra = this.createIssue();
        this.restTestClient.get().uri(LegalIssueResource.LEGAL_ISSUES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalIssue[].class)
                .value(body -> {
                    assertThat(body).extracting(LegalIssue::getId)
                            .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5, extra.getId());
                    assertThat(body).extracting(LegalIssue::getId)
                            .containsSubsequence(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
                    assertThat(body).extracting(LegalIssue::getTitle).isSorted();
                });
    }

    @Test
    void testUpdate() {
        LegalIssue legalIssue = this.createIssue();
        String newTitle = "Updated legal issue " + UUID.randomUUID();
        this.restTestClient.put().uri(LegalIssueResource.LEGAL_ISSUES + "/" + legalIssue.getId())
                .body(LegalIssue.builder().title(newTitle).priority(9).resolved(true).build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalIssue.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(legalIssue.getId());
                    assertThat(body.getTitle()).isEqualTo(newTitle);
                    assertThat(body.getDescription()).isNull();
                    assertThat(body.getPriority()).isEqualTo(9);
                    assertThat(body.getResolved()).isTrue();
                    assertThat(body.getCreationDate()).isNotNull();
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(LegalIssueResource.LEGAL_ISSUES + "/" + UUID.randomUUID())
                .body(LegalIssue.builder().title("Missing legal issue").priority(1).build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateTitle() {
        LegalIssue legalIssue = this.createIssue();
        this.restTestClient.put().uri(LegalIssueResource.LEGAL_ISSUES + "/" + legalIssue.getId())
                .body(LegalIssue.builder().title(ISSUE_0.getTitle()).priority(1).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateBlankTitle() {
        LegalIssue legalIssue = this.createIssue();
        this.restTestClient.put().uri(LegalIssueResource.LEGAL_ISSUES + "/" + legalIssue.getId())
                .body(LegalIssue.builder().title(" ").priority(1).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatch() {
        LegalIssue first = this.createIssue();
        LegalIssue second = this.createIssue();
        this.restTestClient.patch().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(List.of(new LegalIssueResolvedUpdate(first.getId(), true),
                        new LegalIssueResolvedUpdate(second.getId(), true)))
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
        this.assertResolved(first.getId(), true);
        this.assertResolved(second.getId(), true);
    }

    @Test
    void testPatchLeavesAbsentFieldsUntouched() {
        LegalIssue original = this.createIssue();
        this.restTestClient.patch().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(List.of(new LegalIssueResolvedUpdate(original.getId(), true)))
                .exchange()
                .expectStatus().isOk();
        this.restTestClient.get().uri(LegalIssueResource.LEGAL_ISSUES + "/" + original.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalIssue.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getResolved()).isTrue();
                    assertThat(body).usingRecursiveComparison()
                            .ignoringFields("resolved", "creationDate").isEqualTo(original);
                });
    }

    @Test
    void testPatchNotFoundChangesNothing() {
        LegalIssue legalIssue = this.createIssue();
        UUID missingId = UUID.randomUUID();
        this.restTestClient.patch().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(List.of(new LegalIssueResolvedUpdate(legalIssue.getId(), true),
                        new LegalIssueResolvedUpdate(missingId, true)))
                .exchange()
                .expectStatus().isNotFound();
        this.assertResolved(legalIssue.getId(), false);
    }

    @Test
    void testPatchRepeatedIdChangesNothing() {
        LegalIssue legalIssue = this.createIssue();
        this.restTestClient.patch().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(List.of(new LegalIssueResolvedUpdate(legalIssue.getId(), true),
                        new LegalIssueResolvedUpdate(legalIssue.getId(), false)))
                .exchange()
                .expectStatus().isBadRequest();
        this.assertResolved(legalIssue.getId(), false);
    }

    @Test
    void testPatchEmptyList() {
        this.restTestClient.patch().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(List.of())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingId() {
        this.restTestClient.patch().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(List.of(new LegalIssueResolvedUpdate(null, true)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingResolved() {
        this.restTestClient.patch().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(List.of(new LegalIssueResolvedUpdate(ID_0, null)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testDelete() {
        LegalIssue legalIssue = this.createIssue();
        this.restTestClient.delete().uri(LegalIssueResource.LEGAL_ISSUES + "/" + legalIssue.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get().uri(LegalIssueResource.LEGAL_ISSUES + "/" + legalIssue.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteMissingLegalIssue() {
        this.restTestClient.delete().uri(LegalIssueResource.LEGAL_ISSUES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteReferencedLegalIssue() {
        this.restTestClient.delete().uri(LegalIssueResource.LEGAL_ISSUES + "/" + ID_0)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT)
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(ID_0.toString()));
        this.restTestClient.get().uri(LegalIssueResource.LEGAL_ISSUES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalIssue.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(ISSUE_0));
    }

    private LegalIssue newIssue() {
        return LegalIssue.builder()
                .title("FT legal issue " + UUID.randomUUID())
                .description("FT description")
                .priority(5)
                .build();
    }

    private LegalIssue createIssue() {
        return this.restTestClient.post().uri(LegalIssueResource.LEGAL_ISSUES)
                .body(this.newIssue())
                .exchange().expectStatus().isCreated()
                .expectBody(LegalIssue.class).returnResult().getResponseBody();
    }

    private void assertResolved(UUID id, boolean resolved) {
        this.restTestClient.get().uri(LegalIssueResource.LEGAL_ISSUES + "/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalIssue.class)
                .value(body -> assertThat(body).isNotNull().extracting(LegalIssue::getResolved)
                        .isEqualTo(resolved));
    }
}
