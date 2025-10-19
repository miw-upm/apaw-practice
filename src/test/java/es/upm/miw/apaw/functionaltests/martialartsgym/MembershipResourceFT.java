package es.upm.miw.apaw.adapters.rest.martialartsgym;

import es.upm.miw.apaw.adapters.resources.martialartsgym.MembershipResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class MembershipResourceFT {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetAllMemberships() {

        this.webTestClient
                .get()
                .uri("http://localhost:" + this.port + MembershipResource.MEMBERSHIPS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Object.class)
                .value(memberships -> {
                    assertThat(memberships).isNotEmpty();
                });
    }

    @Test
    void testGetAllMembershipsContainsExpectedFields() {

        this.webTestClient
                .get()
                .uri("http://localhost:" + this.port + MembershipResource.MEMBERSHIPS)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].monthlyFee").isNotEmpty()
                .jsonPath("$[0].isCurrentlyActive").isBoolean()
                .jsonPath("$[0].activationDate").isNotEmpty();
    }
}
