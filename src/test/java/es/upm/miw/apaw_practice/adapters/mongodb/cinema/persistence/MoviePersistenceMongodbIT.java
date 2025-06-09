package es.upm.miw.apaw_practice.adapters.mongodb.cinema.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MoviePersistenceMongodbIT {

    @Autowired
    private MoviePersistenceMongodb moviePersistence;

    // ... otros tests ...

    @Test
    void testUpdate() {
        // Arrange: crear y guardar una película
        Movie movie = new Movie(
                "update-id-1",
                "OriginalTitle",
                "2020-01-01",
                "Original description",
                "director-abc"
        );
        this.moviePersistence.create(movie);

        // Act: actualizar la película (por título)
        Movie updated = new Movie(
                "update-id-1",
                "OriginalTitle", // El título es la clave de actualización
                "2021-02-02",
                "Updated description",
                "director-xyz"
        );
        Movie result = this.moviePersistence.update("OriginalTitle", updated);

        // Assert
        assertAll(
                () -> assertEquals("OriginalTitle", result.getTitle()),
                () -> assertEquals("2021-02-02", result.getReleaseDate()),
                () -> assertEquals("Updated description", result.getDescription()),
                () -> assertEquals("director-xyz", result.getDirectorId())
        );
    }

    @Test
    void testFindAll() {
        // Arrange: crear varias películas con títulos únicos
        Movie movie1 = new Movie(
                "findall-id-1",
                "FindAllTitle1",
                "2010-10-10",
                "Desc 1",
                "dir-1"
        );
        Movie movie2 = new Movie(
                "findall-id-2",
                "FindAllTitle2",
                "2011-11-11",
                "Desc 2",
                "dir-2"
        );
        this.moviePersistence.create(movie1);
        this.moviePersistence.create(movie2);

        // Act
        List<Movie> movies = this.moviePersistence.findAll();

        // Assert: al menos deben estar los dos recientes
        assertTrue(
                movies.stream().anyMatch(m -> "FindAllTitle1".equals(m.getTitle())),
                "Debe contener FindAllTitle1"
        );
        assertTrue(
                movies.stream().anyMatch(m -> "FindAllTitle2".equals(m.getTitle())),
                "Debe contener FindAllTitle2"
        );
    }
}