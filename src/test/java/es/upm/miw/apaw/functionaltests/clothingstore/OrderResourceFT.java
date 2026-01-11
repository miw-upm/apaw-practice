package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.OrderResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class OrderResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    private static final String DISTINCT_IDS_SEARCH_PATH = OrderResource.ORDERS + "/search/distinct-ids";

    private static final String KNOWN_INVOICE_NUMBER = "INV-2025-001";
    private static final UUID G1_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
    private static final UUID G2_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002");

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testFindDistinctGarmentIdsByInvoiceNumber_ok() {
        OrderResource.GarmentIdsDto response = this.webTestClient.get()
                .uri(uri -> uri.path(DISTINCT_IDS_SEARCH_PATH)
                        .queryParam("number", KNOWN_INVOICE_NUMBER)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderResource.GarmentIdsDto.class)
                .returnResult()
                .getResponseBody();

        assertThat(response).isNotNull();
        assertThat(Set.copyOf(response.ids()))
                .containsExactlyInAnyOrder(G1_ID, G2_ID);
    }
}
