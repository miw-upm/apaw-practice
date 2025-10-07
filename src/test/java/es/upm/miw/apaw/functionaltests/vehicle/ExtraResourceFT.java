package es.upm.miw.apaw.functionaltests.vehicle;

import es.upm.miw.apaw.adapters.resources.vehicle.ExtraResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ExtraResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDelete() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"); // Airbags del seeder
        webTestClient.delete()
                .uri(ExtraResource.EXTRAS + ExtraResource.EXTRA_ID, id)
                .exchange()
                .expectStatus().isOk();
    }
}
