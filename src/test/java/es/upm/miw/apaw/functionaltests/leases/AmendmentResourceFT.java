package es.upm.miw.apaw.functionaltests.leases;

import es.upm.miw.apaw.adapters.in.leases.AmendmentResource;
import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.AmendmentType;
import es.upm.miw.apaw.domain.model.leases.AmendmentUpdate;
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

import static es.upm.miw.apaw.config.seeders.LeaseSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AmendmentResourceFT {
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
        this.restTestClient.get().uri(AmendmentResource.AMENDMENTS + "/" + AMENDMENT_ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Amendment.class)
                .value(body -> assertThat(body).usingRecursiveComparison().isEqualTo(AMENDMENT_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        this.restTestClient.get().uri(AmendmentResource.AMENDMENTS + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body -> assertThat((String) body.get("message")).contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get().uri(AmendmentResource.AMENDMENTS)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Amendment[].class)
                .value(body -> assertThat(body).extracting(Amendment::getId).containsSubsequence(
                        AMENDMENT_ID_0, AMENDMENT_ID_2, AMENDMENT_ID_1, AMENDMENT_ID_3, AMENDMENT_ID_4));
    }

    @Test
    void testCreateDefaultsToNotApproved() {
        this.restTestClient.post().uri(AmendmentResource.AMENDMENTS)
                .body(this.newAmendment())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Amendment.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getApproved()).isFalse();
                });
    }

    @Test
    void testCreateBlankDescription() {
        Amendment amendment = this.newAmendment();
        amendment.setDescription(" ");
        this.restTestClient.post().uri(AmendmentResource.AMENDMENTS)
                .body(amendment)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testCreateWithoutEffectiveDate() {
        Amendment amendment = this.newAmendment();
        amendment.setEffectiveDate(null);
        this.restTestClient.post().uri(AmendmentResource.AMENDMENTS)
                .body(amendment)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testUpdate() {
        Amendment amendment = this.createAmendment();
        Amendment replacement = this.newAmendment();
        replacement.setDescription("Replaced description");
        replacement.setApproved(true);
        this.restTestClient.put().uri(AmendmentResource.AMENDMENTS + "/" + amendment.getId())
                .body(replacement)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Amendment.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(amendment.getId());
                    assertThat(body.getDescription()).isEqualTo("Replaced description");
                    assertThat(body.getApproved()).isTrue();
                });
    }

    @Test
    void testUpdateNotFound() {
        this.restTestClient.put().uri(AmendmentResource.AMENDMENTS + "/" + UUID.randomUUID())
                .body(this.newAmendment())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatch() {
        Amendment amendment = this.createAmendment();
        this.restTestClient.patch().uri(AmendmentResource.AMENDMENTS + "/" + amendment.getId())
                .body(new AmendmentUpdate(null, null, null, null, true, null))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Amendment.class).value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getApproved()).isTrue();
                    assertThat(body.getDescription()).isEqualTo(amendment.getDescription());
                });
    }

    @Test
    void testPatchBlankDescription() {
        Amendment amendment = this.createAmendment();
        this.restTestClient.patch().uri(AmendmentResource.AMENDMENTS + "/" + amendment.getId())
                .body(new AmendmentUpdate(null, " ", null, null, null, null))
                .exchange()
                .expectStatus().isBadRequest();
        this.restTestClient.get().uri(AmendmentResource.AMENDMENTS + "/" + amendment.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Amendment.class)
                .value(body -> assertThat(body.getDescription()).isEqualTo(amendment.getDescription()));
    }

    @Test
    void testPatchNotFound() {
        this.restTestClient.patch().uri(AmendmentResource.AMENDMENTS + "/" + UUID.randomUUID())
                .body(new AmendmentUpdate(null, null, null, null, true, null))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        Amendment amendment = this.createAmendment();
        this.restTestClient.delete().uri(AmendmentResource.AMENDMENTS + "/" + amendment.getId())
                .exchange()
                .expectStatus().isNoContent();
        this.restTestClient.get().uri(AmendmentResource.AMENDMENTS + "/" + amendment.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteReferencedAmendment() {
        this.restTestClient.delete().uri(AmendmentResource.AMENDMENTS + "/" + AMENDMENT_ID_0)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    private Amendment createAmendment() {
        return this.restTestClient.post().uri(AmendmentResource.AMENDMENTS)
                .body(this.newAmendment())
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Amendment.class)
                .returnResult().getResponseBody();
    }

    private Amendment newAmendment() {
        return Amendment.builder().amendmentNumber(1).description("FT amendment " + UUID.randomUUID())
                .effectiveDate(LocalDate.of(2026, 1, 1)).additionalAmount(new BigDecimal("10.00"))
                .amendmentType(AmendmentType.OTHER).build();
    }
}
