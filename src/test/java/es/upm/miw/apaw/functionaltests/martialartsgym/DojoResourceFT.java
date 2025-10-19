package es.upm.miw.apaw.functionaltests.martialartsgym;

import es.upm.miw.apaw.adapters.resources.martialartsgym.DojoResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class DojoResourceFT {

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
}
