package es.upm.miw.apaw.functionaltests.evidencemanagement;

import es.upm.miw.apaw.adapters.in.evidencemanagement.CustodyRecordDto;
import es.upm.miw.apaw.adapters.in.evidencemanagement.CustodyRecordPatchDto;
import es.upm.miw.apaw.adapters.in.evidencemanagement.CustodyRecordResource;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;
import es.upm.miw.apaw.domain.model.evidencemanagement.CustodyRecord;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.EvidenceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustodyRecordResourceFT {
    private static final UUID MISSING_CUSTODIAN_ID = UUID.randomUUID();

    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
        when(this.userFinder.read(any(UUID.class)))
                .thenAnswer(invocation -> this.newUser(invocation.getArgument(0)));
        when(this.userFinder.read(MISSING_CUSTODIAN_ID))
                .thenThrow(new NotFoundException("Not found on read user by id " + MISSING_CUSTODIAN_ID));
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Collection<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(this::newUser).toList();
        });
    }

    private UserSnapshot newUser(UUID id) {
        return UserSnapshot.builder().id(id).mobile("600000000").firstName("Ana").familyName("Lopez")
                .email("ana@example.com").build();
    }

    private UserSnapshot newSummary(UUID id) {
        return UserSnapshot.builder().id(id).mobile("600000000").firstName("Ana").build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustodyRecord.class)
                .value(body -> {
                    assertThat(body).usingRecursiveComparison().ignoringFields("custodian").isEqualTo(RECORD_0);
                    assertThat(body.getCustodian()).usingRecursiveComparison().isEqualTo(this.newSummary(CUSTODIAN_ID_0));
                });
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(CustodyRecordResource.CUSTODY_RECORDS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustodyRecord[].class)
                .value(body -> {
                    assertThat(body).extracting(CustodyRecord::getId).containsSubsequence(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
                    assertThat(body).allSatisfy(item -> assertThat(item.getCustodian()).usingRecursiveComparison()
                            .isEqualTo(this.newSummary(item.getCustodian().getId())));
                });
    }

    @Test
    void testCreate() {
        this.restTestClient.post().uri(CustodyRecordResource.CUSTODY_RECORDS)
                .body(new CustodyRecordDto(30, "TRANSFERRED", "Laboratory", "Sealed", CUSTODIAN_ID_0))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustodyRecord.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getRecordedAt()).isNotNull();
                    assertThat(body.getAction()).isEqualTo("TRANSFERRED");
                    assertThat(body.getDurationMinutes()).isEqualTo(30);
                    assertThat(body.getCustodian()).usingRecursiveComparison().isEqualTo(this.newUser(CUSTODIAN_ID_0));
                });
    }

    @Test
    void testCreateBlankAction() {
        this.restTestClient.post().uri(CustodyRecordResource.CUSTODY_RECORDS)
                .body(new CustodyRecordDto(null, " ", null, null, CUSTODIAN_ID_0))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateWithoutCustodian() {
        this.restTestClient.post().uri(CustodyRecordResource.CUSTODY_RECORDS)
                .body(new CustodyRecordDto(null, "TRANSFERRED", null, null, null))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateCustodianNotFound() {
        this.restTestClient.post().uri(CustodyRecordResource.CUSTODY_RECORDS)
                .body(new CustodyRecordDto(null, "TRANSFERRED", null, null, MISSING_CUSTODIAN_ID))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        CustodyRecord custodyRecord = this.createRecord();
        this.restTestClient.put().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .body(new CustodyRecordDto(null, "UPDATED", null, null, CUSTODIAN_ID_1))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustodyRecord.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(custodyRecord.getId());
                    assertThat(body.getAction()).isEqualTo("UPDATED");
                    assertThat(body.getDurationMinutes()).isNull();
                    assertThat(body.getLocation()).isNull();
                    assertThat(body.getNotes()).isNull();
                    assertThat(body.getCustodian()).usingRecursiveComparison().isEqualTo(this.newUser(CUSTODIAN_ID_1));
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + UUID.randomUUID())
                .body(new CustodyRecordDto(null, "UPDATED", null, null, CUSTODIAN_ID_0))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateBlankAction() {
        CustodyRecord custodyRecord = this.createRecord();
        this.restTestClient.put().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .body(new CustodyRecordDto(null, " ", null, null, CUSTODIAN_ID_0))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateCustodianNotFound() {
        CustodyRecord custodyRecord = this.createRecord();
        this.restTestClient.put().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .body(new CustodyRecordDto(null, "UPDATED", null, null, MISSING_CUSTODIAN_ID))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatch() {
        CustodyRecord custodyRecord = this.createRecord();
        this.restTestClient.patch().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .body(new CustodyRecordPatchDto(null, null, null, "Patched notes", null))
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustodyRecord.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(custodyRecord.getId());
                    assertThat(body.getNotes()).isEqualTo("Patched notes");
                    assertThat(body.getAction()).isEqualTo(custodyRecord.getAction());
                    assertThat(body.getDurationMinutes()).isEqualTo(custodyRecord.getDurationMinutes());
                    assertThat(body.getLocation()).isEqualTo(custodyRecord.getLocation());
                    assertThat(body.getCustodian()).usingRecursiveComparison().isEqualTo(this.newUser(CUSTODIAN_ID_0));
                });
    }

    @Test
    void testPatchBlankAction() {
        CustodyRecord custodyRecord = this.createRecord();
        this.restTestClient.patch().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .body(new CustodyRecordPatchDto(null, " ", null, null, null))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + UUID.randomUUID())
                .body(new CustodyRecordPatchDto(null, null, null, "Patched notes", null))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchCustodianNotFound() {
        CustodyRecord custodyRecord = this.createRecord();
        this.restTestClient.patch().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .body(new CustodyRecordPatchDto(null, null, null, null, MISSING_CUSTODIAN_ID))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        CustodyRecord custodyRecord = this.createRecord();
        this.restTestClient.delete().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + custodyRecord.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteMissingRecord() {
        this.restTestClient.delete().uri(CustodyRecordResource.CUSTODY_RECORDS + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNoContent();
    }

    private CustodyRecord createRecord() {
        return this.restTestClient.post().uri(CustodyRecordResource.CUSTODY_RECORDS)
                .body(new CustodyRecordDto(30, "FT action " + UUID.randomUUID(), "Laboratory", "Original notes",
                        CUSTODIAN_ID_0))
                .exchange().expectStatus().isCreated()
                .expectBody(CustodyRecord.class).returnResult().getResponseBody();
    }

    @Test
    void testFindActivityReport() {
        UUID custodianId = RECORD_0.getCustodian().getId();

        this.restTestClient.get().uri(CustodyRecordResource.CUSTODY_RECORDS + CustodyRecordResource.REPORT)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustodianActivityReport[].class)
                .value(body -> assertThat(body).filteredOn(item -> item.getCustodian().getId().equals(custodianId))
                        .singleElement().satisfies(item -> {
                            assertThat(item.getCustodian()).usingRecursiveComparison().isEqualTo(this.newSummary(custodianId));
                            assertThat(item.getRecordsCount()).isGreaterThanOrEqualTo(1);
                            assertThat(item.getEvidencesCount()).isBetween(1L, item.getRecordsCount());
                            assertThat(item.getTotalDurationMinutes()).isGreaterThanOrEqualTo(0);
                        }));
    }
}