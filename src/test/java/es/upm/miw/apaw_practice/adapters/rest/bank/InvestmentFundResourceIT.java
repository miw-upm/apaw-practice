package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;



@RestTestConfig
public class InvestmentFundResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(InvestmentFundResource.FUNDS + InvestmentFundResource.NAME, "Fund1")
                .exchange()
                .expectStatus().isOk();
    }

}
