package es.upm.miw.apaw.functionaltests.warehouse;

import es.upm.miw.apaw.adapters.resources.warehouse.OrderDetailResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OrderDetailResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSumUnitCostDistinctByPosition() {
        this.webTestClient.get()
                .uri(OrderDetailResource.ORDER_DETAILS+OrderDetailResource.SUM_UNITCOST_BY_POSITION, "A1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(sum -> assertThat(sum).isGreaterThan(BigDecimal.ZERO));
    }

    @Test
    void testSumUnitCostDistinctByPositionNotFound() {
        this.webTestClient.get()
                .uri(OrderDetailResource.ORDER_DETAILS + OrderDetailResource.SUM_UNITCOST_BY_POSITION,"Z9")
                .exchange()
                .expectStatus().isNotFound();
    }

}
