package es.upm.miw.apaw.functionaltests.martialartsgym;

import es.upm.miw.apaw.adapters.resources.martialartsgym.EquipmentResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class EquipmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateUnitCost() {
        int barCode = 1003;
        BigDecimal newCost = new BigDecimal("50.00");

        this.webTestClient
                .patch()
                .uri(EquipmentResource.EQUIPMENT + "/" + barCode)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newCost)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.unitCost").isEqualTo(50.00);
    }
}
