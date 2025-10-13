package es.upm.miw.apaw.functionaltests.videoWebsite;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.videoWebsite.CommentResource.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CommentResourceFT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testDeleteComment() {
        UUID commentId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000");

        this.webTestClient
                .delete()
                .uri(COMMENTS + "/" + commentId)
                .exchange()
                .expectStatus().isNoContent();


    }
}
