package es.upm.miw.apaw.functionaltests.contract;

import es.upm.miw.apaw.adapters.in.contract.ClauseResource;
import es.upm.miw.apaw.adapters.out.contract.postgres.ClauseEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractEntity;
import es.upm.miw.apaw.adapters.out.contract.postgres.ContractRepository;
import es.upm.miw.apaw.domain.model.contract.Clause;
import es.upm.miw.apaw.domain.model.contract.ClauseType;
import es.upm.miw.apaw.domain.model.contract.ClauseUpdate;
import es.upm.miw.apaw.domain.model.contract.ContractType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ClauseResourceFT {

    @LocalServerPort
    private int port;

    private RestTestClient restTestClient;

    @Autowired
    private ContractRepository contractRepository;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port)
                .build();
    }

    @Test
    void testRead() {
        this.restTestClient.get()
                .uri(ClauseResource.CLAUSES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clause.class)
                .value(clause -> assertThat(clause)
                        .usingRecursiveComparison()
                        .isEqualTo(CLAUSE_0));
    }

    @Test
    void testReadNotFound() {
        this.restTestClient.get()
                .uri(ClauseResource.CLAUSES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindAll() {
        this.restTestClient.get()
                .uri(ClauseResource.CLAUSES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clause[].class)
                .value(body -> assertThat(body)
                        .extracting(Clause::getId)
                        .containsSubsequence(
                                ID_3,
                                ID_0,
                                ID_6,
                                ID_5,
                                ID_1,
                                ID_4,
                                ID_2));
    }

    @Test
    void testCreate() {
        this.restTestClient.post()
                .uri(ClauseResource.CLAUSES)
                .body(Clause.builder()
                        .title("Cláusula " + UUID.randomUUID())
                        .content("Contenido de prueba")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .version(1)
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Clause.class)
                .value(body -> assertThat(body)
                        .isNotNull()
                        .extracting(Clause::getVersion)
                        .isEqualTo(1));
    }

    @Test
    void testCreateBlankTitle() {
        this.restTestClient.post()
                .uri(ClauseResource.CLAUSES)
                .body(Clause.builder()
                        .title(" ")
                        .content("Contenido de prueba")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBlankContent() {
        this.restTestClient.post()
                .uri(ClauseResource.CLAUSES)
                .body(Clause.builder()
                        .title("Cláusula " + UUID.randomUUID())
                        .content(" ")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateAllowsDuplicateTitle() {
        this.restTestClient.post()
                .uri(ClauseResource.CLAUSES)
                .body(Clause.builder()
                        .title(CLAUSE_0.getTitle())
                        .content(CLAUSE_0.getContent())
                        .effectiveFrom(CLAUSE_0.getEffectiveFrom())
                        .version(1)
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Clause.class)
                .value(body -> assertThat(body)
                        .isNotNull()
                        .extracting(Clause::getTitle)
                        .isEqualTo(CLAUSE_0.getTitle()));
    }

    @Test
    void testUpdate() {
        Clause clause = this.createClause();

        restTestClient.put()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .body(Clause.builder()
                        .title(clause.getTitle())
                        .type(ClauseType.CONFIDENTIALITY)
                        .content("Contenido actualizado")
                        .effectiveFrom(LocalDate.of(2026, 2, 1))
                        .effectiveUntil(LocalDate.of(2027, 2, 1))
                        .notes("Notas actualizadas")
                        .version(2)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clause.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(clause.getId());
                    assertThat(body.getTitle()).isEqualTo(clause.getTitle());
                    assertThat(body.getType()).isEqualTo(ClauseType.CONFIDENTIALITY);
                    assertThat(body.getContent()).isEqualTo("Contenido actualizado");
                    assertThat(body.getEffectiveFrom()).isEqualTo(LocalDate.of(2026, 2, 1));
                    assertThat(body.getEffectiveUntil()).isEqualTo(LocalDate.of(2027, 2, 1));
                    assertThat(body.getNotes()).isEqualTo("Notas actualizadas");
                    assertThat(body.getVersion()).isEqualTo(2);
                });
    }

    @Test
    void testUpdateNotFound() {
        restTestClient.put()
                .uri(ClauseResource.CLAUSES + "/" + UUID.randomUUID())
                .body(Clause.builder()
                        .title("Cláusula inexistente")
                        .content("Contenido de prueba")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .version(1)
                        .build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateAllowsDuplicateTitle() {
        Clause clause = this.createClause();

        restTestClient.put()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .body(Clause.builder()
                        .title(CLAUSE_0.getTitle())
                        .type(ClauseType.OTHER)
                        .content("Contenido actualizado")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .version(1)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clause.class)
                .value(body -> assertThat(body)
                        .isNotNull()
                        .extracting(Clause::getTitle)
                        .isEqualTo(CLAUSE_0.getTitle()));
    }

    @Test
    void testDelete() {
        Clause clause = this.createClause();

        restTestClient.delete()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        restTestClient.get()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteNotFound() {
        restTestClient.delete()
                .uri(ClauseResource.CLAUSES + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteReferencedClause() {
        Clause clause = this.createClause();

        ContractEntity contract = ContractEntity.builder()
                .id(UUID.randomUUID())
                .title("Contract " + UUID.randomUUID())
                .type(ContractType.OTHER)
                .startDate(LocalDate.of(2026, 1, 1))
                .automaticRenewal(false)
                .createdAt(LocalDateTime.now())
                .userId(UUID.randomUUID())
                .clauses(List.of(new ClauseEntity(clause)))
                .build();

        this.contractRepository.saveAndFlush(contract);

        this.restTestClient.delete()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);

        this.restTestClient.get()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clause.class)
                .value(body -> assertThat(body.getId()).isEqualTo(clause.getId()));

        assertThat(this.contractRepository.existsByClauses_Id(clause.getId()))
                .isTrue();
    }

    @Test
    void testPatch() {
        Clause clause = this.createClause();

        ClauseUpdate patch = new ClauseUpdate(
                ClauseType.CONFIDENTIALITY,
                "Observaciones actualizadas",
                LocalDate.of(2027, 12, 31)
        );

        this.restTestClient.patch()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .body(patch)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clause.class)
                .value(updated -> {
                    assertThat(updated.getId()).isEqualTo(clause.getId());
                    assertThat(updated.getType()).isEqualTo(ClauseType.CONFIDENTIALITY);
                    assertThat(updated.getNotes()).isEqualTo("Observaciones actualizadas");
                    assertThat(updated.getEffectiveUntil())
                            .isEqualTo(LocalDate.of(2027, 12, 31));

                    assertThat(updated.getTitle()).isEqualTo(clause.getTitle());
                    assertThat(updated.getContent()).isEqualTo(clause.getContent());
                    assertThat(updated.getEffectiveFrom()).isEqualTo(clause.getEffectiveFrom());
                    assertThat(updated.getVersion()).isEqualTo(clause.getVersion());
                });
    }

    @Test
    void testPatchMissingFieldsRemainUnchanged() {
        Clause clause = this.createClause();

        ClauseUpdate patch = new ClauseUpdate(
                null,
                "Observaciones actualizadas",
                null
        );

        this.restTestClient.patch()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .body(patch)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Clause.class)
                .value(updated -> {
                    assertThat(updated.getNotes()).isEqualTo("Observaciones actualizadas");

                    assertThat(updated.getType()).isEqualTo(clause.getType());
                    assertThat(updated.getEffectiveUntil()).isEqualTo(clause.getEffectiveUntil());

                    assertThat(updated.getId()).isEqualTo(clause.getId());
                    assertThat(updated.getTitle()).isEqualTo(clause.getTitle());
                    assertThat(updated.getContent()).isEqualTo(clause.getContent());
                    assertThat(updated.getEffectiveFrom()).isEqualTo(clause.getEffectiveFrom());
                    assertThat(updated.getVersion()).isEqualTo(clause.getVersion());
                });
    }

    @Test
    void testPatchNotFound() {
        UUID missingId = UUID.randomUUID();

        ClauseUpdate patch = new ClauseUpdate(
                ClauseType.CONFIDENTIALITY,
                "Observaciones",
                LocalDate.of(2027, 12, 31)
        );

        this.restTestClient.patch()
                .uri(ClauseResource.CLAUSES + "/" + missingId)
                .body(patch)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchInvalidData() {
        Clause clause = this.createClause();

        this.restTestClient.patch()
                .uri(ClauseResource.CLAUSES + "/" + clause.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body("""
                    {
                        "effectiveUntil": "fecha-invalida"
                    }
                    """)
                .exchange()
                .expectStatus().isBadRequest();
    }

    private Clause createClause() {
        return restTestClient.post()
                .uri(ClauseResource.CLAUSES)
                .body(Clause.builder()
                        .title("Cláusula " + UUID.randomUUID())
                        .content("Contenido original")
                        .effectiveFrom(LocalDate.of(2026, 1, 1))
                        .version(1)
                        .build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Clause.class)
                .returnResult()
                .getResponseBody();
    }
}