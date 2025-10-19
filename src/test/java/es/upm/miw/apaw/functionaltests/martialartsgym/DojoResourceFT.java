package es.upm.miw.apaw.functionaltests.martialartsgym;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.DojoRepository;
import es.upm.miw.apaw.adapters.resources.martialartsgym.DojoResource;
import org.junit.jupiter.api.BeforeEach;
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
class DojoResourceFT {
    @Autowired
    private DojoRepository dojoRepository;

    @BeforeEach
    void cleanDatabase() {
        this.dojoRepository.deleteAll();
    }

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateDojo() {
        String dojoJson = """
            {
                "cadastralReference": "D-3000",
                "city": "Valencia",
                "foundationDate": "2024-05-20",
                "equipment": [],
                "classSessions": []
            }
            """;

        this.webTestClient
                .post()
                .uri(DojoResource.DOJOS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dojoJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.cadastralReference").isEqualTo("D-3000")
                .jsonPath("$.city").isEqualTo("Valencia")
                .jsonPath("$.foundationDate").isEqualTo("2024-05-20");
    }

    @Test
    void testGetTotalUnitCostByCity() {
        String dojoJson = """
            {
                "cadastralReference": "D-3100",
                "city": "Madrid",
                "foundationDate": "2022-10-10",
                "equipment": [
                    {"barCode": 1, "itemLabel": "Tatami", "unitCost": 100.00},
                    {"barCode": 2, "itemLabel": "Bag", "unitCost": 200.00}
                ],
                "classSessions": []
            }
            """;

        this.webTestClient
                .post()
                .uri(DojoResource.DOJOS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dojoJson)
                .exchange()
                .expectStatus().isOk();

        this.webTestClient
                .get()
                .uri(DojoResource.DOJOS + "/Madrid/total-unitcost")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .isEqualTo(new BigDecimal("300.00"));
    }

}
