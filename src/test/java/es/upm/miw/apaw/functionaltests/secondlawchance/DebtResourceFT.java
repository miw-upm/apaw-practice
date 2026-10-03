package es.upm.miw.apaw.functionaltests.secondlawchance;

import es.upm.miw.apaw.adapters.in.secondlawchance.DebtResource;
import es.upm.miw.apaw.domain.model.secondlawchance.CreditorType;
import es.upm.miw.apaw.domain.model.secondlawchance.Debt;
import es.upm.miw.apaw.domain.model.secondlawchance.DebtPatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.SecondLawChanceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DebtResourceFT {
    @LocalServerPort
    private int port;
    private RestTestClient restTestClient;

    @BeforeEach
    void setUp() {
        this.restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + this.port).build();
    }

    @Test
    void testRead() {
        this.restTestClient.get().uri(DebtResource.DEBTS + "/" + DEBT_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Debt.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(DEBT_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(DebtResource.DEBTS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(DebtResource.DEBTS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Debt[].class)
                .value(body -> assertThat(body).extracting(Debt::getId).containsSubsequence(
                        DEBT_ID_2, DEBT_ID_3, DEBT_ID_0, DEBT_ID_1, DEBT_ID_4, DEBT_ID_5));
    }

    @Test
    void testCreateDefaults() {
        this.restTestClient.post().uri(DebtResource.DEBTS)
                .body(this.buildDebt().type(null).guarantee(null).build())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Debt.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getType()).isEqualTo(CreditorType.PRIVATE);
                    assertThat(body.getGuarantee()).isFalse();
                });
    }

    @Test
    void testCreateKeepsGivenValues() {
        Debt debt = this.createDebt();
        assertThat(debt.getType()).isEqualTo(CreditorType.PUBLIC);
        assertThat(debt.getGuarantee()).isTrue();
        this.restTestClient.get().uri(DebtResource.DEBTS + "/" + debt.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Debt.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(debt));
    }

    @Test
    void testCreateBlankContractNumber() {
        this.restTestClient.post().uri(DebtResource.DEBTS)
                .body(this.buildDebt().contractNumber(" ").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateBlankCreditorName() {
        this.restTestClient.post().uri(DebtResource.DEBTS)
                .body(this.buildDebt().creditorName(" ").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingIssueDate() {
        this.restTestClient.post().uri(DebtResource.DEBTS)
                .body(this.buildDebt().issueDate(null).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateMissingAmount() {
        this.restTestClient.post().uri(DebtResource.DEBTS)
                .body(this.buildDebt().amount(null).build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateDuplicateContractNumber() {
        this.restTestClient.post().uri(DebtResource.DEBTS)
                .body(this.buildDebt().contractNumber(DEBT_0.getContractNumber()).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        Debt debt = this.createDebt();
        Debt replacement = this.buildDebt().issueDate(LocalDate.of(2023, 5, 5)).creditorName("Updated creditor")
                .amount(new BigDecimal("250.50")).type(null).guarantee(null).build();
        this.restTestClient.put().uri(DebtResource.DEBTS + "/" + debt.getId())
                .body(replacement)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Debt.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(debt.getId());
                    assertThat(body.getContractNumber()).isEqualTo(replacement.getContractNumber());
                    assertThat(body.getCreditorName()).isEqualTo("Updated creditor");
                    assertThat(body.getAmount()).isEqualByComparingTo("250.50");
                    assertThat(body.getType()).isEqualTo(CreditorType.PRIVATE);
                    assertThat(body.getGuarantee()).isFalse();
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(DebtResource.DEBTS + "/" + UUID.randomUUID())
                .body(this.buildDebt().build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateBlankCreditorName() {
        Debt debt = this.createDebt();
        this.restTestClient.put().uri(DebtResource.DEBTS + "/" + debt.getId())
                .body(this.buildDebt().creditorName(" ").build())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdateDuplicateContractNumber() {
        Debt debt = this.createDebt();
        this.restTestClient.put().uri(DebtResource.DEBTS + "/" + debt.getId())
                .body(this.buildDebt().contractNumber(DEBT_0.getContractNumber()).build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testPatch() {
        Debt debt = this.createDebt();
        this.restTestClient.patch().uri(DebtResource.DEBTS + "/" + debt.getId())
                .body(new DebtPatch(null, null, null, new BigDecimal("999.99"), null, false))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Debt.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getAmount()).isEqualByComparingTo("999.99");
                    assertThat(body.getGuarantee()).isFalse();
                    assertThat(body.getContractNumber()).isEqualTo(debt.getContractNumber());
                    assertThat(body.getCreditorName()).isEqualTo(debt.getCreditorName());
                    assertThat(body.getType()).isEqualTo(debt.getType());
                });
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(DebtResource.DEBTS + "/" + UUID.randomUUID())
                .body(new DebtPatch(null, null, "Missing", null, null, null))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchBlankCreditorName() {
        Debt debt = this.createDebt();
        this.restTestClient.patch().uri(DebtResource.DEBTS + "/" + debt.getId())
                .body(new DebtPatch(null, null, " ", null, null, null))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchDuplicateContractNumber() {
        Debt debt = this.createDebt();
        this.restTestClient.patch().uri(DebtResource.DEBTS + "/" + debt.getId())
                .body(new DebtPatch(DEBT_0.getContractNumber(), null, null, null, null, null))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        Debt debt = this.createDebt();
        this.restTestClient.delete().uri(DebtResource.DEBTS + "/" + debt.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();
        this.restTestClient.get().uri(DebtResource.DEBTS + "/" + debt.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteMissingDebt() {
        this.restTestClient.delete().uri(DebtResource.DEBTS + "/" + UUID.randomUUID())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteReferencedDebt() {
        this.restTestClient.delete().uri(DebtResource.DEBTS + "/" + DEBT_ID_2)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT)
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(DEBT_ID_2.toString()));
        this.restTestClient.get().uri(DebtResource.DEBTS + "/" + DEBT_ID_2)
                .exchange()
                .expectStatus().isOk();
    }

    private Debt createDebt() {
        return this.restTestClient.post().uri(DebtResource.DEBTS)
                .body(this.buildDebt().build())
                .exchange().expectStatus().isCreated()
                .expectBody(Debt.class).returnResult().getResponseBody();
    }

    private Debt.DebtBuilder buildDebt() {
        return Debt.builder().contractNumber("FT-" + UUID.randomUUID()).issueDate(LocalDate.of(2024, 3, 1))
                .creditorName("FT creditor").amount(new BigDecimal("100.00"))
                .type(CreditorType.PUBLIC).guarantee(true);
    }
}
