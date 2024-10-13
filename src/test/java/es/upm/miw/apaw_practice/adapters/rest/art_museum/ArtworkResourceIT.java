package es.upm.miw.apaw_practice.adapters.rest.art_museum;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artist;
import es.upm.miw.apaw_practice.domain.models.art_museum.Artwork;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class ArtworkResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        String inventoryNumber = "27006";
        Artwork updateArtwork = new Artwork();
        updateArtwork.setTitleName("Last Supper");
        updateArtwork.setYear(1499);
        updateArtwork.setArtist(new Artist("Leonardo di ser Piero da Vinci", 66, "Italian Renaissance"));

        this.webTestClient
                .put()
                .uri(ArtworkResource.ARTWORKS + ArtworkResource.ID_INVENTORY_NUMBER, inventoryNumber)
                .body(BodyInserters.fromValue(updateArtwork))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Artwork.class)
                .value(artwork -> {
                    assertEquals("Last Supper", artwork.getTitleName());
                    assertEquals(1499, artwork.getYear());
                    assertEquals("Leonardo di ser Piero da Vinci", artwork.getArtist().getArtistName());
                });
    }
    @Test
    void testUpdateNotFound() {
        String inventoryNumberNotExist = "27000";
        Artwork updateArtwork = new Artwork();
        updateArtwork.setTitleName("Monalisa");
        updateArtwork.setYear(1503);
        updateArtwork.setArtist(new Artist("Leonardo di ser Piero da Vinci", 66, "Italian Renaissance"));
        this.webTestClient
                .put()
                .uri(ArtworkResource.ARTWORKS + ArtworkResource.ID_INVENTORY_NUMBER, inventoryNumberNotExist)
                .body(BodyInserters.fromValue(updateArtwork))
                .exchange()
                .expectStatus().isNotFound();
    }
}
