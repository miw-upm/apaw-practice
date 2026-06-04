package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.OrderResource;
import es.upm.miw.apaw.domain.models.clothingstore.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OrderResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    @BeforeEach
    void resetDb() {
        this.clothingstoreSeeder.deleteAll();
        this.clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testReadAll() {
        this.webTestClient.get()
                .uri(OrderResource.ORDERS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class)
                .value(orders -> assertThat(orders)
                        .extracting(Order::getStatus)
                        .contains("PAID"));
    }

    @Test
    void testCreate() {
        Order order = Order.builder()
                .date(LocalDate.of(2026, 1, 10))
                .total(new BigDecimal("39.99"))
                .itemCount(1)
                .status("PENDING")
                .paymentMethod("CASH")
                .build();

        this.webTestClient.post()
                .uri(OrderResource.ORDERS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(order)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class)
                .value(created -> {
                    assertThat(created.getDate()).isEqualTo(LocalDate.of(2026, 1, 10));
                    assertThat(created.getTotal()).isEqualByComparingTo("39.99");
                    assertThat(created.getItemCount()).isEqualTo(1);
                    assertThat(created.getStatus()).isEqualTo("PENDING");
                    assertThat(created.getPaymentMethod()).isEqualTo("CASH");
                });
    }
}
