package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import es.upm.miw.apaw_practice.domain.models.art_museum.Exhibition;
import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

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
                .uri(MuseumResource.MUSEUMS + MuseumResource.NAME_MUSEUM, "El Prado")
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
                .uri(MuseumResource.MUSEUMS + MuseumResource.NAME_MUSEUM, "Reina Sofia")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteByName() {
        this.webTestClient
                .delete()
                .uri(MuseumResource.MUSEUMS + MuseumResource.NAME_MUSEUM, "Reina Sofia")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateExhibitionPrice() {
        this.webTestClient
                .patch()
                .uri(MuseumResource.MUSEUMS + MuseumResource.NAME_MUSEUM +
                        MuseumResource.EXHIBITIONS + MuseumResource.NAME_EXHIBITION, "Thyssen", "Century 15th")
                .body(BodyInserters.fromValue(BigDecimal.valueOf(20.00)))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateExhibitionPriceNotFound() {
        this.webTestClient
                .patch()
                .uri(MuseumResource.MUSEUMS + MuseumResource.NAME_MUSEUM +
                        MuseumResource.EXHIBITIONS + MuseumResource.NAME_EXHIBITION, "Reina Sofia", "Century 15th")
                .body(BodyInserters.fromValue(BigDecimal.valueOf(20.00)))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testSearchByArtistNameSumPricesExhibitions() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(MuseumResource.MUSEUMS + MuseumResource.SEARCH)
                                .queryParam("q", "artistName:Vincent van Gogh")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(sum -> assertEquals(BigDecimal.valueOf(105.00), sum));
    }

}
