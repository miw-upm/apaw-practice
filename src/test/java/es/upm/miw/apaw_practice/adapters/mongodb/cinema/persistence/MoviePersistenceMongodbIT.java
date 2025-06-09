package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.daos.MovieRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.MovieEntity;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class MoviePersistenceMongodbIT {

    @Autowired
    private MovieRepository movieRepository;

    private MoviePersistenceMongodb persistence;

    @BeforeEach
    void setup() {
        persistence = new MoviePersistenceMongodb(movieRepository);
        movieRepository.deleteAll();
    }

    @Test
    void testSaveAndFindByTitle() {
        Movie movie = new Movie();
        movie.setTitle("Persistence Test");
        movie.setReleaseDate(LocalDate.of(2020, 1, 1));
        movie.setDescription("Test description");
        movie.setDirectorId("d1");

        Movie saved = persistence.save(movie);

        Optional<Movie> found = persistence.findByTitle("Persistence Test");
        assertTrue(found.isPresent());
        assertEquals("Persistence Test", found.get().getTitle());
        assertEquals("Test description", found.get().getDescription());
    }
}