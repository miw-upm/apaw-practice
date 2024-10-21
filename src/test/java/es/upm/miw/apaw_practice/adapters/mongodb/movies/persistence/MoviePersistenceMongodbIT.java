package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.movies.Movie;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.MoviePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MoviePersistenceMongodbIT {

    @Autowired
    private MoviePersistence moviePersistence;

    @Test
    void testCreateAndExists() {
        Movie movie = new Movie("tt9999999", "Test Movie", new BigDecimal("1000000"), LocalDate.of(2024, 10, 20), Set.of(), null);
        moviePersistence.create(movie);
        assertTrue(moviePersistence.existsImdbId("tt9999999"));
    }

    @Test
    void testFindByActorRealName() {
        List<Movie> movies = moviePersistence.findByActorRealName("Robert Anthony De Niro");
        assertNotNull(movies);
        assertTrue(movies.stream().anyMatch(movie -> "Taxi Driver".equals(movie.getTitle())));
    }
}
