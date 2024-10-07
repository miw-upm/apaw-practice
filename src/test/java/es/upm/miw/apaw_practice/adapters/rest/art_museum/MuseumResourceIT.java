package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.models.art_museum.Exhibition;
import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.art_museum.MuseumResource.MUSEUMS;
import static es.upm.miw.apaw_practice.adapters.rest.art_museum.MuseumResource.NAME_MUSEUM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
class MuseumResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        this.webTestClient
                .get()
                .uri(MUSEUMS + NAME_MUSEUM, "El Prado")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Museum.class)
                .value(museumData -> {
                    assertEquals(600, museumData.getCapacity());
                    assertTrue(museumData.getOpen());
                    assertEquals(2, museumData.getArtworks().size());
                    assertEquals(1, museumData.getExhibitions().size());
                    assertTrue(museumData.getArtworks().stream()
                            .map(Artwork::getInventoryNumber)
                            .toList()
                            .containsAll(List.of("27004", "27005")));
                    assertTrue(museumData.getExhibitions().stream()
                            .map(Exhibition::getName)
                            .toList()
                            .contains("Spanish authors"));
                });
    }

    @Test
    void testReadNotFound() {
        this.webTestClient
                .get()
                .uri(MUSEUMS + NAME_MUSEUM, "Reina Sofia")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteByName() {
        this.webTestClient
                .delete()
                .uri(MUSEUMS + NAME_MUSEUM, "Reina Sofia")
                .exchange()
                .expectStatus().isOk();
    }
}
