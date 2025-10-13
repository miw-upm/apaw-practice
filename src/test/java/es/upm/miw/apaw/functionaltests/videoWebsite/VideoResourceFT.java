package es.upm.miw.apaw.functionaltests.videoWebsite;

import es.upm.miw.apaw.domain.models.videoWebsite.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class VideoResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByTitle() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/videoWebsite/videos")
                        .queryParam("title", "title 1")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Video.class)
                .value(videos -> {
                    assertThat(videos).isNotEmpty();
                    assertThat(videos.getFirst().getTitle()).isEqualTo("title 1");
                });
    }
}
