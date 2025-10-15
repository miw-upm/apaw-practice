package es.upm.miw.apaw.functionaltests.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.WebAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class WebAccountResourceFT {
    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testReadById() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0010");

        webTestClient.get()
                .uri("/videoWebsite/webAccount/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(WebAccount.class)
                .value(webAccount -> {
                    assertEquals(id, webAccount.getId());
                    assertEquals("Account 1", webAccount.getUserName());
                });
    }
}
