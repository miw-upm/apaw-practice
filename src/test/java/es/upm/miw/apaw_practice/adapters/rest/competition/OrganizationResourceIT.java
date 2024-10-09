package es.upm.miw.apaw_practice.adapters.rest.competition;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.CompetitionSeederService;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class OrganizationResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CompetitionSeederService competitionSeederService;

    @Test
    void testUpdateInternational() {
        this.webTestClient
                .patch()
                .uri(OrganizationResource.ORGANIZATION + OrganizationResource.ID_ID, "kk")
                .exchange()
                .expectStatus().isNotFound();

    }

    @Test
    void testCreateOrganization() {
        Organization organization = new Organization("F.S. Barcelona", LocalDateTime.now(), false);

        this.webTestClient
                .post()
                .uri(OrganizationResource.ORGANIZATION)
                .body(BodyInserters.fromValue(organization))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Organization.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testSumSalaryPlayerTeamsByNameOrganization() {
        String nameOrganization = "FEMAFUSA";
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(OrganizationResource.ORGANIZATION + OrganizationResource.NAME_ORGANIZATION)
                        .build(nameOrganization))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(sum -> {
                    assertEquals(sum, new BigDecimal("28.41"));
                });
    }
}
