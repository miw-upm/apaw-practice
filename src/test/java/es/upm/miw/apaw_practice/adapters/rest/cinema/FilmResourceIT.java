package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.FilmEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.SpectatorEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public @RestTestConfig
class FilmResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSearchFilmsByScreenNumber() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(FilmResource.FILMS + FilmResource.SEARCH)
                                .queryParam("number", 1)
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Film.class)
                .value(film -> assertTrue(film.size() > 0))
                .value(film -> assertEquals(1, film.get(0).getScreen().getNumber()));
    }
}

