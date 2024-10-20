package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.movies.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.MoviePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
class MovieResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private MoviePersistence moviePersistence;

    @Test
    void testCreateExisting() {
        Movie movie = new Movie("tt9876543", "Existing Movie", new BigDecimal("2000000.00"),
                LocalDate.of(2023, 5, 20), new HashSet<>(), null);

        assertFalse(moviePersistence.existsImdbId(movie.getImdbId()));
        createMovie(movie).expectStatus().isCreated();
        assertTrue(moviePersistence.existsImdbId(movie.getImdbId()));
    }

    private WebTestClient.ResponseSpec createMovie(Movie movie) {
        return webTestClient
                .post()
                .uri(MovieResource.MOVIES)
                .body(BodyInserters.fromValue(movie))
                .exchange();
    }
}
