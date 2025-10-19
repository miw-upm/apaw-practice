package es.upm.miw.apaw.functionaltests.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.resources.music.StyleResource;
import es.upm.miw.apaw.domain.models.music.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class StyleResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MusicSeeder musicSeeder;

    @BeforeEach
    void setUp() {
        this.musicSeeder.deleteAll();
        this.musicSeeder.seedDatabase();
    }

    @Test
    void testPatchOk() {
        String genre = "ELECTRONIC";
        Style body = Style.builder()
                .popularityIndex(75)
                .mood("CHILL")
                .build();

        this.webTestClient.patch()
                .uri(StyleResource.STYLES + StyleResource.STYLE_ID, genre)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testPatchNotFound() {
        Style body = Style.builder().mood("CALM").build();

        this.webTestClient.patch()
                .uri(StyleResource.STYLES + StyleResource.STYLE_ID, "NO-SUCH-GENRE")
                .bodyValue(body)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testPatchBadRequest() {
        String genre = "ELECTRONIC";
        Style body = Style.builder().popularityIndex(200).build();

        this.webTestClient.patch()
                .uri(StyleResource.STYLES + StyleResource.STYLE_ID, genre)
                .bodyValue(body)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
