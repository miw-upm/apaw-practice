package es.upm.miw.apaw.functionaltests.fighters;

import es.upm.miw.apaw.adapters.mongodb.fighters.daos.FightersSeeder;
import es.upm.miw.apaw.adapters.resources.fighters.MartialArtResource;
import es.upm.miw.apaw.domain.models.fighters.MartialArt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class MartialArtResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private FightersSeeder seeder;


    @Test
    void testPut_ok() {
        MartialArt body = MartialArt.builder()
                .discipline("ANY")
                .origin("Thailand UPDATED")
                .description("The art of the eight limbs (UPDATED)")
                .striking(true)
                .grappling(false)
                .build();

        webTestClient.put()
                .uri(MartialArtResource.MARTIAL_ARTS + MartialArtResource.DISCIPLINE, "Muay Thai")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MartialArt.class)
                .value(ma -> {
                    assertThat(ma.getDiscipline()).isEqualTo("Muay Thai");
                    assertThat(ma.getOrigin()).isEqualTo("Thailand UPDATED");
                    assertThat(ma.getDescription()).isEqualTo("The art of the eight limbs (UPDATED)");
                    assertThat(ma.getStriking()).isTrue();
                    assertThat(ma.getGrappling()).isFalse();
                });
        seeder.deleteAll();
        seeder.seedDatabase();
    }

    @Test
    void testPut_notFound() {
        MartialArt body = MartialArt.builder()
                .origin("Brazil")
                .description("not seeded")
                .striking(true)
                .grappling(true)
                .build();

        webTestClient.put()
                .uri(MartialArtResource.MARTIAL_ARTS + MartialArtResource.DISCIPLINE, "Capoeira")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .exchange()
                .expectStatus().isNotFound();
        seeder.deleteAll();
        seeder.seedDatabase();
    }
}
