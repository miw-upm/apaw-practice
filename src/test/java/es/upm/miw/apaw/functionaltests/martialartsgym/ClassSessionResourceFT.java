package es.upm.miw.apaw.functionaltests.martialartsgym;

import es.upm.miw.apaw.adapters.resources.martialartsgym.ClassSessionResource;
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
class ClassSessionResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDeleteExistingClassSession() {
        webTestClient.delete()
                .uri(ClassSessionResource.CLASS_SESSIONS + "/501")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteNonExistingClassSession() {
        webTestClient.delete()
                .uri(ClassSessionResource.CLASS_SESSIONS + "/1234")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
