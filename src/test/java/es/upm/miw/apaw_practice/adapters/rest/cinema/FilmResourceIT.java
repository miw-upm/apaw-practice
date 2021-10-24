package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.hamcrest.CoreMatchers.equalTo;
import static es.upm.miw.apaw_practice.adapters.rest.cinema.FilmResource.*;

@RestTestConfig
class FilmResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSearchFilmsByScreenNumber() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(FilmResource.FILMS + FilmResource.SEARCH)
                        .queryParam("q", "number:2")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Film.class)
                .value(filmDtoList -> filmDtoList.get(0).getScreen().getNumber(), equalTo(2))
                .value(filmDtoList -> filmDtoList.get(0).getName(), equalTo("The hunger games"))
                .value(filmDtoList -> filmDtoList.get(0).getActors().size(), equalTo(4));
    }

    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(FILMS + BARCODE, "7891")
                .exchange()
                .expectStatus().isOk();
    }
}

