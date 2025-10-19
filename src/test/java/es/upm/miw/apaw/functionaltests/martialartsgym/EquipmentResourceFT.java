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
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void testFullUpdateEquipment() {
        int barCode = 1003;

        String updatedJson = """
        {
            "barCode": 1003,
            "itemLabel": "Updated Helmet",
            "unitCost": 60.00
        }
        """;

        this.webTestClient
                .put()
                .uri(EquipmentResource.EQUIPMENT + "/" + barCode)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.itemLabel").isEqualTo("Updated Helmet")
                .jsonPath("$.unitCost").isEqualTo(60.00);
    }
    @Test
    void testFindMobilesByItemLabel() {
        String itemLabel = "Tatami Mats";

        this.webTestClient
                .get()
                .uri(EquipmentResource.EQUIPMENT + "/mobiles/" + itemLabel)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(mobiles -> assertThat(mobiles).isNotEmpty());
    }

}
