package es.upm.miw.apaw.functionaltests.euregulation;

import es.upm.miw.apaw.adapters.in.euregulation.EURegulationResource;
import es.upm.miw.apaw.config.seeders.EURegulationSeederForDev;
import es.upm.miw.apaw.domain.model.euregulation.ApplicationArea;
import es.upm.miw.apaw.domain.model.euregulation.EURegulation;
import es.upm.miw.apaw.domain.model.euregulation.IssuingBody;
import es.upm.miw.apaw.domain.model.euregulation.LegalInstrumentType;
import es.upm.miw.apaw.domain.model.euregulation.LegalStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EURegulationResourceFT {

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
    }

    @Test
    void testSeederRegulationsRemainUnchanged() {
        this.assertSeededRegulation(
                EURegulationSeederForDev.REFERENCE_NUMBER_0,
                "General Data Protection Regulation",
                LegalInstrumentType.REGULATION,
                ApplicationArea.DATA_PROTECTION,
                LocalDate.of(2016, 5, 24),
                null,
                "https://eur-lex.europa.eu/eli/reg/2016/679/oj",
                "Regulation on the protection of natural persons with regard to personal data.");
        this.assertSeededRegulation(
                EURegulationSeederForDev.REFERENCE_NUMBER_1,
                "Artificial Intelligence Act",
                LegalInstrumentType.REGULATION,
                ApplicationArea.DIGITAL_TECHNOLOGY,
                LocalDate.of(2024, 8, 1),
                null,
                "https://eur-lex.europa.eu/eli/reg/2024/1689/oj",
                "Regulation laying down harmonised rules on artificial intelligence.");
        this.assertSeededRegulation(
                EURegulationSeederForDev.REFERENCE_NUMBER_2,
                "NIS2 Directive",
                LegalInstrumentType.DIRECTIVE,
                ApplicationArea.DIGITAL_TECHNOLOGY,
                LocalDate.of(2023, 1, 16),
                LocalDate.of(2024, 10, 17),
                "https://eur-lex.europa.eu/eli/dir/2022/2555/oj",
                "Directive on measures for a high common level of cybersecurity across the Union.");
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAllContainsSeederAndHasDeterministicOrder() {
        List<EURegulation> regulations = this.findAll();
        List<EURegulation> regulationsAgain = this.findAll();

        assertThat(regulations).extracting(EURegulation::getOfficialReferenceNumber)
                .contains(EURegulationSeederForDev.REFERENCE_NUMBER_0,
                        EURegulationSeederForDev.REFERENCE_NUMBER_1,
                        EURegulationSeederForDev.REFERENCE_NUMBER_2);
        assertThat(regulationsAgain).extracting(EURegulation::getId)
                .containsExactlyElementsOf(regulations.stream().map(EURegulation::getId).toList());
    }

    @Test
    void testCreate() {
        EURegulation request = this.newRegulation();
        this.restTestClient.post()
                .uri(EURegulationResource.EU_REGULATIONS)
                .body(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EURegulation.class)
                .value(body -> {
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getSequentialId()).isNotNull();
                    assertThat(body.getEntryIntoForceDate()).isNotNull();
                    assertThat(body.getRegulationName()).isEqualTo(request.getRegulationName());
                    assertThat(body.getOfficialReferenceNumber()).isEqualTo(request.getOfficialReferenceNumber());
                });
    }

    @Test
    void testCreateDuplicateOfficialReferenceNumber() {
        EURegulation request = this.newRegulation();
        request.setOfficialReferenceNumber(EURegulationSeederForDev.REFERENCE_NUMBER_0);
        this.restTestClient.post()
                .uri(EURegulationResource.EU_REGULATIONS)
                .body(request)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateReplacesEditableFields() {
        EURegulation original = this.createRegulation();
        EURegulation replacement = this.newRegulation();
        replacement.setRegulationName("Updated " + UUID.randomUUID());

        this.restTestClient.put()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + original.getId())
                .body(replacement)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EURegulation.class)
                .value(body -> {
                    assertThat(body.getId()).isEqualTo(original.getId());
                    assertThat(body.getSequentialId()).isEqualTo(original.getSequentialId());
                    assertThat(body.getEntryIntoForceDate()).isEqualTo(original.getEntryIntoForceDate());
                    assertThat(body.getRegulationName()).isEqualTo(replacement.getRegulationName());
                    assertThat(body.getOfficialReferenceNumber())
                            .isEqualTo(replacement.getOfficialReferenceNumber());
                });
    }

    @Test
    void testUpdateDuplicateOfficialReferenceNumber() {
        EURegulation original = this.createRegulation();
        EURegulation update = this.newRegulation();
        update.setOfficialReferenceNumber(EURegulationSeederForDev.REFERENCE_NUMBER_0);

        this.restTestClient.put()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + original.getId())
                .body(update)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + UUID.randomUUID())
                .body(this.newRegulation())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchUpdatesOnlyPresentFields() {
        EURegulation original = this.createRegulation();
        String updatedName = "Patched " + UUID.randomUUID();

        this.restTestClient.patch()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + original.getId())
                .body(Map.of("regulationName", updatedName))
                .exchange()
                .expectStatus().isOk()
                .expectBody(EURegulation.class)
                .value(body -> {
                    assertThat(body.getRegulationName()).isEqualTo(updatedName);
                    assertThat(body.getOfficialReferenceNumber()).isEqualTo(original.getOfficialReferenceNumber());
                    assertThat(body.getInstrumentType()).isEqualTo(original.getInstrumentType());
                    assertThat(body.getSequentialId()).isEqualTo(original.getSequentialId());
                    assertThat(body.getEntryIntoForceDate()).isEqualTo(original.getEntryIntoForceDate());
                });
    }

    @Test
    void testPatchDuplicateOfficialReferenceNumber() {
        EURegulation original = this.createRegulation();
        this.restTestClient.patch()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + original.getId())
                .body(Map.of("officialReferenceNumber", EURegulationSeederForDev.REFERENCE_NUMBER_0))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + UUID.randomUUID())
                .body(Map.of("regulationName", "Unknown regulation"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        EURegulation regulation = this.createRegulation();
        this.restTestClient.delete()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + regulation.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        this.restTestClient.get()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + regulation.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteNotFound() {
        this.restTestClient.delete()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    private EURegulation findByReferenceNumber(String officialReferenceNumber) {
        return this.findAll().stream()
                .filter(regulation -> officialReferenceNumber.equals(regulation.getOfficialReferenceNumber()))
                .findFirst()
                .orElseThrow();
    }

    private void assertSeededRegulation(
            String officialReferenceNumber,
            String regulationName,
            LegalInstrumentType instrumentType,
            ApplicationArea applicationArea,
            LocalDate entryIntoForceDate,
            LocalDate transpositionDeadline,
            String officialJournalLink,
            String summary) {
        EURegulation seededRegulation = this.findByReferenceNumber(officialReferenceNumber);
        this.restTestClient.get()
                .uri(EURegulationResource.EU_REGULATIONS + "/" + seededRegulation.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(EURegulation.class)
                .value(body -> {
                    assertThat(body.getOfficialReferenceNumber()).isEqualTo(officialReferenceNumber);
                    assertThat(body.getRegulationName()).isEqualTo(regulationName);
                    assertThat(body.getInstrumentType()).isEqualTo(instrumentType);
                    assertThat(body.getApplicationArea()).isEqualTo(applicationArea);
                    assertThat(body.getLegalStatus()).isEqualTo(LegalStatus.IN_FORCE);
                    assertThat(body.getIssuingBody()).isEqualTo(IssuingBody.EUROPEAN_PARLIAMENT);
                    assertThat(body.getEntryIntoForceDate()).isEqualTo(entryIntoForceDate);
                    assertThat(body.getTranspositionDeadline()).isEqualTo(transpositionDeadline);
                    assertThat(body.getOfficialJournalLink()).isEqualTo(officialJournalLink);
                    assertThat(body.getSummary()).isEqualTo(summary);
                });
    }

    private List<EURegulation> findAll() {
        EURegulation[] regulations = this.restTestClient.get()
                .uri(EURegulationResource.EU_REGULATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(EURegulation[].class)
                .returnResult()
                .getResponseBody();
        return regulations == null ? List.of() : Arrays.asList(regulations);
    }

    private EURegulation createRegulation() {
        return this.restTestClient.post()
                .uri(EURegulationResource.EU_REGULATIONS)
                .body(this.newRegulation())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EURegulation.class)
                .returnResult()
                .getResponseBody();
    }

    private EURegulation newRegulation() {
        return EURegulation.builder()
                .regulationName("FT Regulation " + UUID.randomUUID())
                .officialReferenceNumber("FT Reference " + UUID.randomUUID())
                .instrumentType(LegalInstrumentType.REGULATION)
                .applicationArea(ApplicationArea.DATA_PROTECTION)
                .legalStatus(LegalStatus.IN_FORCE)
                .issuingBody(IssuingBody.EUROPEAN_COMMISSION)
                .entryIntoForceDate(LocalDate.of(2025, 1, 1))
                .build();
    }
}
