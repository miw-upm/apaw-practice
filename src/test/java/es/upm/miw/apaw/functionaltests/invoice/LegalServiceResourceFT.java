
package es.upm.miw.apaw.functionaltests.invoice;

import es.upm.miw.apaw.adapters.in.invoice.LegalServiceResource;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LegalServiceSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LegalServiceResourceFT {

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
    void testRead() {
        this.restTestClient.get()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + ID_0)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalService.class)
                .value(body -> assertThat(body)
                        .usingRecursiveComparison()
                        .isEqualTo(LEGAL_SERVICE_0));
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();

        this.restTestClient.get()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + id)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(Map.class)
                .value(body ->
                        assertThat((String) body.get("message"))
                                .contains(id.toString()));
    }

    @Test
    void testFindAll() {
        this.restTestClient.get()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalService[].class)
                .value(services -> assertThat(services)
                        .extracting(LegalService::getId)
                        .contains(
                                ID_0, ID_1, ID_2, ID_3, ID_4, ID_5));
    }

    @Test
    void testCreate() {
        LegalService service = this.newLegalService(
                "FT service " + UUID.randomUUID());

        this.restTestClient.post()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(service)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LegalService.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isNotNull();
                    assertThat(body.getName()).isEqualTo(service.getName());
                    assertThat(body.getDescription())
                            .isEqualTo(service.getDescription());
                    assertThat(body.getFee())
                            .isEqualByComparingTo(service.getFee());
                    assertThat(body.getCategory())
                            .isEqualTo(service.getCategory());
                    assertThat(body.getLegalArea())
                            .isEqualTo(service.getLegalArea());
                });
    }

    @Test
    void testCreateDuplicateName() {
        LegalService service =
                this.newLegalService(LEGAL_SERVICE_0.getName());

        this.restTestClient.post()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(service)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdate() {
        LegalService service = this.createLegalService();

        LegalService changes = this.newLegalService(service.getName());
        changes.setDescription("Updated description");
        changes.setFee(new BigDecimal("125.00"));
        changes.setRequiresAppointment(Boolean.FALSE);

        this.restTestClient.put()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + service.getId())
                .body(changes)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalService.class)
                .value(body -> {
                    assertThat(body).isNotNull();
                    assertThat(body.getId()).isEqualTo(service.getId());
                    assertThat(body.getName()).isEqualTo(service.getName());
                    assertThat(body.getDescription())
                            .isEqualTo(changes.getDescription());
                    assertThat(body.getFee())
                            .isEqualByComparingTo(changes.getFee());
                    assertThat(body.getRequiresAppointment())
                            .isEqualTo(changes.getRequiresAppointment());
                });
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();

        this.restTestClient.put()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + id)
                .body(this.newLegalService("Missing service " + UUID.randomUUID()))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateDuplicateName() {
        LegalService service = this.createLegalService();

        this.restTestClient.put()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + service.getId())
                .body(this.newLegalService(LEGAL_SERVICE_0.getName()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testDelete() {
        LegalService service = this.createLegalService();

        this.restTestClient.delete()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + service.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty();

        this.restTestClient.get()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + service.getId())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatch() {
        LegalService first = this.createLegalService();
        LegalService second = this.createLegalService();

        BigDecimal fee0 = new BigDecimal("91.00");
        BigDecimal fee1 = new BigDecimal("215.00");

        this.restTestClient.patch()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(List.of(
                        new LegalServiceUpdate(first.getId(), fee0),
                        new LegalServiceUpdate(second.getId(), fee1)))
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        this.assertFee(first.getId(), fee0);
        this.assertFee(second.getId(), fee1);
    }

    @Test
    void testPatchNotFoundChangesNothing() {
        LegalService service = this.createLegalService();
        UUID missingId = UUID.randomUUID();

        this.restTestClient.patch()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(List.of(
                        new LegalServiceUpdate(
                                service.getId(), new BigDecimal("999.00")),
                        new LegalServiceUpdate(
                                missingId, new BigDecimal("1000.00"))))
                .exchange()
                .expectStatus().isNotFound();

        this.assertFee(service.getId(), service.getFee());
    }

    @Test
    void testPatchRepeatedIdChangesNothing() {
        LegalService service = this.createLegalService();
        BigDecimal originalFee = service.getFee();

        this.restTestClient.patch()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(List.of(
                        new LegalServiceUpdate(
                                service.getId(), new BigDecimal("90.00")),
                        new LegalServiceUpdate(
                                service.getId(), new BigDecimal("95.00"))))
                .exchange()
                .expectStatus().isBadRequest();

        this.assertFee(service.getId(), originalFee);
    }

    @Test
    void testPatchEmptyList() {
        this.restTestClient.patch()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(List.of())
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingId() {
        this.restTestClient.patch()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(List.of(
                        new LegalServiceUpdate(null, new BigDecimal("90.00"))))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testPatchMissingFee() {
        this.restTestClient.patch()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(List.of(
                        new LegalServiceUpdate(ID_0, null)))
                .exchange()
                .expectStatus().isBadRequest();
    }

    private LegalService createLegalService() {
        return this.restTestClient.post()
                .uri(LegalServiceResource.LEGAL_SERVICES)
                .body(this.newLegalService(
                        "FT service " + UUID.randomUUID()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(LegalService.class)
                .returnResult()
                .getResponseBody();
    }

    private LegalService newLegalService(String name) {
        LegalService service = new LegalService();
        service.setName(name);
        service.setDescription("Description for " + name);
        service.setFee(new BigDecimal("75.00"));
        service.setRequiresAppointment(Boolean.TRUE);
        service.setCategory(LEGAL_SERVICE_0.getCategory());
        service.setLegalArea(LEGAL_SERVICE_0.getLegalArea());
        return service;
    }

    private void assertFee(UUID id, BigDecimal expectedFee) {
        this.restTestClient.get()
                .uri(LegalServiceResource.LEGAL_SERVICES + "/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LegalService.class)
                .value(body -> assertThat(body.getFee())
                        .isEqualByComparingTo(expectedFee));
    }
}