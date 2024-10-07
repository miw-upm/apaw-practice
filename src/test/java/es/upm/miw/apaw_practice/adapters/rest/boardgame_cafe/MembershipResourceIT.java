package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class MembershipResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadMembership() {
        this.webTestClient
                .get()
                .uri(MembershipResource.MEMBERSHIP + MembershipResource.MEMBERSHIPID_ID, 0 )
                .exchange()
                .expectStatus().isOk()
                .expectBody(Membership.class)
                .value(Assertions::assertNotNull)
                .value(membership -> {
                    assertEquals(0, membership.getMembershipId());
                    assertEquals("Gold", membership.getType());
                    assertEquals(1, membership.getDuration());
                    assertEquals(new BigDecimal("2.0"), membership.getDiscount());
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(MembershipResource.MEMBERSHIP + MembershipResource.MEMBERSHIPID_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }
}
