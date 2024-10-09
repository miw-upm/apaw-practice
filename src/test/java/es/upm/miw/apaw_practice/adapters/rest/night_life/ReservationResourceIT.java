package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.math.BigDecimal;

@RestTestConfig
class ReservationResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Test
    void testUpdate() {
        BigDecimal newPrice = new BigDecimal("99.99");
        this.webTestClient
                .patch()
                .uri(ReservationResource.RESERVATIONS)
                .bodyValue(newPrice)
                .exchange()
                .expectStatus().isOk();
    }
}
