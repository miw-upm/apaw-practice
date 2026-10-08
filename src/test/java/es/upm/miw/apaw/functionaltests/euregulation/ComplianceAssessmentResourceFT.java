package es.upm.miw.apaw.functionaltests.euregulation;

import es.upm.miw.apaw.adapters.in.euregulation.ComplianceAssessmentCreationDto;
import es.upm.miw.apaw.adapters.in.euregulation.ComplianceAssessmentResource;
import es.upm.miw.apaw.adapters.in.euregulation.EURegulationResource;
import es.upm.miw.apaw.config.seeders.ComplianceAssessmentSeederForDev;
import es.upm.miw.apaw.config.seeders.EURegulationSeederForDev;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceAssessment;
import es.upm.miw.apaw.domain.model.euregulation.ComplianceLevel;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.model.euregulation.RiskLevel;
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
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ComplianceAssessmentResourceFT {

    private static final UUID USER_ID = ComplianceAssessmentSeederForDev.USER_ID;
    private static final UUID USER_ID_1 = ComplianceAssessmentSeederForDev.USER_ID_1;

    @LocalServerPort
    private int port;

    @MockitoBean
    private UserFinder userFinder;

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
        UserSnapshot user0 = UserSnapshot.builder()
                .id(USER_ID)
                .mobile("600000100")
                .firstName("cliente0")
                .build();
        UserSnapshot user1 = UserSnapshot.builder()
                .id(USER_ID_1)
                .mobile("600000101")
                .firstName("cliente1")
                .build();
        when(this.userFinder.read(USER_ID)).thenReturn(user0);
        when(this.userFinder.read(USER_ID_1)).thenReturn(user1);
        when(this.userFinder.findByIds(Set.of(USER_ID, USER_ID_1))).thenReturn(List.of(user0, user1));
        when(this.userFinder.findByIds(anySet())).thenAnswer(invocation -> {
            Set<UUID> userIds = invocation.getArgument(0);
            return List.of(user0, user1).stream()
                    .filter(user -> userIds.contains(user.getId()))
                    .toList();
        });
    }

    @Test
    void testReadSeederAssessment() {
        this.restTestClient.get()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/"
                        + ComplianceAssessmentSeederForDev.ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(ComplianceAssessmentSeederForDev.ID_0);
                    assertThat(body.getResponsibleLawyer()).isEqualTo("Laura García");
                    assertThat(body.getAssessmentDate()).isEqualTo(LocalDate.of(2025, 1, 15));
                    assertThat(body.getComplianceDeadline()).isEqualTo(LocalDate.of(2025, 6, 30));
                    assertThat(body.getNextReviewDate()).isEqualTo(LocalDate.of(2025, 4, 15));
                    assertThat(body.getComplianceLevel()).isEqualTo(ComplianceLevel.PARTIALLY_COMPLIANT);
                    assertThat(body.getRiskLevel()).isEqualTo(RiskLevel.MEDIUM);
                    assertThat(body.getAiGenerated()).isFalse();
                    assertThat(body.getEuRegulations()).hasSize(1)
                            .extracting(regulation -> regulation.getOfficialReferenceNumber())
                            .containsExactly(EURegulationSeederForDev.REFERENCE_NUMBER_0);
                });
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAllContainsSeederAndHasDeterministicOrder() {
        List<ComplianceAssessment> assessments = this.findAll();
        List<ComplianceAssessment> assessmentsAgain = this.findAll();

        assertThat(assessments).extracting(ComplianceAssessment::getId)
                .contains(ComplianceAssessmentSeederForDev.ID_0, ComplianceAssessmentSeederForDev.ID_1);
        assertThat(assessments).isSortedAccordingTo(Comparator
                .comparing(ComplianceAssessment::getAssessmentDate)
                .thenComparing(assessment -> assessment.getId().toString()));
        assertThat(assessmentsAgain).extracting(ComplianceAssessment::getId)
                .containsExactlyElementsOf(assessments.stream().map(ComplianceAssessment::getId).toList());
    }

    @Test
    void testFindByAssessmentFieldAndRelatedRegulation() {
        ComplianceAssessment[] assessments = this.restTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                        .queryParam("responsibleLawyer", "Laura García")
                        .queryParam("applicationArea", ApplicationArea.DATA_PROTECTION)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment[].class)
                .returnResult()
                .getResponseBody();

        assertThat(assessments).isNotNull()
                .extracting(ComplianceAssessment::getId)
                .contains(ComplianceAssessmentSeederForDev.ID_0, ComplianceAssessmentSeederForDev.ID_2)
                .doesNotContain(ComplianceAssessmentSeederForDev.ID_1,
                        ComplianceAssessmentSeederForDev.ID_3, ComplianceAssessmentSeederForDev.ID_4);
    }

    @Test
    void testFindByDerivedDaysToNearestDeadline() {
        ComplianceAssessment[] assessments = this.restTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                        .queryParam("daysToNearestDeadline", 5)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment[].class)
                .returnResult()
                .getResponseBody();

        assertThat(assessments).isNotNull()
                .extracting(ComplianceAssessment::getId)
                .contains(ComplianceAssessmentSeederForDev.ID_3)
                .doesNotContain(ComplianceAssessmentSeederForDev.ID_2,
                        ComplianceAssessmentSeederForDev.ID_4);
    }

    @Test
    void testFindByUserFirstNameUsesOneBatchLookup() {
        ComplianceAssessment[] assessments = this.restTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                        .queryParam("userFirstName", "CLIENTE1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment[].class)
                .returnResult()
                .getResponseBody();

        assertThat(assessments).isNotNull()
                .extracting(ComplianceAssessment::getId)
                .contains(ComplianceAssessmentSeederForDev.ID_3, ComplianceAssessmentSeederForDev.ID_4)
                .doesNotContain(ComplianceAssessmentSeederForDev.ID_0,
                        ComplianceAssessmentSeederForDev.ID_1, ComplianceAssessmentSeederForDev.ID_2);
        verify(this.userFinder, times(1)).findByIds(Set.of(USER_ID, USER_ID_1));
    }

    @Test
    void testFindWithAllCriteriaReturnsOnlyMatchingAssessment() {
        ComplianceAssessment[] assessments = this.restTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                        .queryParam("responsibleLawyer", "Miguel Torres")
                        .queryParam("daysToNearestDeadline", 5)
                        .queryParam("applicationArea", ApplicationArea.DATA_PROTECTION)
                        .queryParam("userFirstName", "cliente1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment[].class)
                .returnResult()
                .getResponseBody();

        assertThat(assessments).isNotNull()
                .extracting(ComplianceAssessment::getId)
                .containsExactly(ComplianceAssessmentSeederForDev.ID_3);
        verify(this.userFinder, times(1)).findByIds(Set.of(USER_ID_1));
    }

    @Test
    void testCreate() {
        ComplianceAssessmentCreationDto creation = this.creation(
                List.of(this.regulationId(EURegulationSeederForDev.REFERENCE_NUMBER_1)));
        this.restTestClient.post()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                .body(creation)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ComplianceAssessment.class)
                .value(body -> {
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getAssessmentDate()).isNotNull();
                    assertThat(body.getResponsibleLawyer()).isEqualTo(creation.responsibleLawyer());
                    assertThat(body.getComplianceLevel()).isEqualTo(creation.complianceLevel());
                    assertThat(body.getRiskLevel()).isEqualTo(creation.riskLevel());
                    assertThat(body.getAiGenerated()).isFalse();
                    assertThat(body.getUserSnapshot().getId()).isEqualTo(USER_ID);
                    assertThat(body.getEuRegulations()).extracting(regulation -> regulation.getId())
                            .containsExactly(this.regulationId(EURegulationSeederForDev.REFERENCE_NUMBER_1));
                });
    }

    @Test
    void testCreateWithRepeatedEURegulationIds() {
        UUID regulationId = this.regulationId(EURegulationSeederForDev.REFERENCE_NUMBER_0);
        this.restTestClient.post()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                .body(this.creation(List.of(regulationId, regulationId)))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreateWithMissingEURegulation() {
        UUID missingRegulationId = UUID.randomUUID();
        this.restTestClient.post()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                .body(this.creation(List.of(missingRegulationId)))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(missingRegulationId.toString()));
    }

    @Test
    void testUpdateReplacesEditableFieldsAndKeepsAssessmentDate() {
        ComplianceAssessment original = this.createAssessment(List.of());
        ComplianceAssessmentCreationDto update = this.creation(
                List.of(this.regulationId(EURegulationSeederForDev.REFERENCE_NUMBER_2)));

        this.restTestClient.put()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + original.getId())
                .body(update)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(original.getId());
                    assertThat(body.getAssessmentDate()).isEqualTo(original.getAssessmentDate());
                    assertThat(body.getResponsibleLawyer()).isEqualTo(update.responsibleLawyer());
                    assertThat(body.getNotes()).isEqualTo(update.notes());
                    assertThat(body.getEuRegulations())
                            .extracting(regulation -> regulation.getOfficialReferenceNumber())
                            .containsExactly(EURegulationSeederForDev.REFERENCE_NUMBER_2);
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + UUID.randomUUID())
                .body(this.creation(List.of()))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchUpdatesOnlyPresentFields() {
        ComplianceAssessment original = this.createAssessment(
                List.of(this.regulationId(EURegulationSeederForDev.REFERENCE_NUMBER_0)));
        String notes = "Patched notes " + UUID.randomUUID();

        this.restTestClient.patch()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + original.getId())
                .body(Map.of("notes", notes))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment.class)
                .value(body -> {
                    assertThat(body.getNotes()).isEqualTo(notes);
                    assertThat(body.getResponsibleLawyer()).isEqualTo(original.getResponsibleLawyer());
                    assertThat(body.getRiskLevel()).isEqualTo(original.getRiskLevel());
                    assertThat(body.getAssessmentDate()).isEqualTo(original.getAssessmentDate());
                    assertThat(body.getUserSnapshot().getId()).isEqualTo(USER_ID);
                    assertThat(body.getEuRegulations())
                            .extracting(regulation -> regulation.getId())
                            .containsExactlyElementsOf(original.getEuRegulations().stream()
                                    .map(regulation -> regulation.getId())
                                    .toList());
                });
    }

    @Test
    void testPatchCanClearRegulationAssociations() {
        ComplianceAssessment original = this.createAssessment(
                List.of(this.regulationId(EURegulationSeederForDev.REFERENCE_NUMBER_0)));

        this.restTestClient.patch()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + original.getId())
                .body(Map.of("euRegulationIds", List.of()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment.class)
                .value(body -> assertThat(body.getEuRegulations()).isEmpty());
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + UUID.randomUUID())
                .body(Map.of("notes", "Updated note"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteUnreferencedAssessment() {
        ComplianceAssessment assessment = this.createAssessment(List.of());

        this.restTestClient.delete()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + assessment.getId())
                .exchange()
                .expectStatus().isNoContent();
        this.restTestClient.get()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + assessment.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteAssessmentReferencedByEURegulation() {
        this.restTestClient.delete()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/"
                        + ComplianceAssessmentSeederForDev.ID_0)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDeleteNotFound() {
        this.restTestClient.delete()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    private List<ComplianceAssessment> findAll() {
        ComplianceAssessment[] assessments = this.restTestClient.get()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ComplianceAssessment[].class)
                .returnResult()
                .getResponseBody();
        return assessments == null ? List.of() : Arrays.asList(assessments);
    }

    private ComplianceAssessment createAssessment(List<UUID> regulationIds) {
        return this.restTestClient.post()
                .uri(ComplianceAssessmentResource.COMPLIANCE_ASSESSMENTS)
                .body(this.creation(regulationIds))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ComplianceAssessment.class)
                .returnResult()
                .getResponseBody();
    }

    private UUID regulationId(String officialReferenceNumber) {
        EURegulation[] regulations = this.restTestClient.get()
                .uri(EURegulationResource.EU_REGULATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EURegulation[].class)
                .returnResult()
                .getResponseBody();
        return Arrays.stream(regulations == null ? new EURegulation[0] : regulations)
                .filter(regulation -> officialReferenceNumber.equals(regulation.getOfficialReferenceNumber()))
                .map(EURegulation::getId)
                .findFirst()
                .orElseThrow();
    }

    private ComplianceAssessmentCreationDto creation(List<UUID> euRegulationIds) {
        return new ComplianceAssessmentCreationDto(
                "FT Lawyer " + UUID.randomUUID(),
                LocalDate.of(2026, 12, 31),
                LocalDate.of(2026, 6, 30),
                "Review controls " + UUID.randomUUID(),
                "FT supporting document",
                "FT assessment notes",
                null,
                ComplianceLevel.PENDING_REVIEW,
                RiskLevel.LOW,
                USER_ID,
                euRegulationIds);
    }
}
