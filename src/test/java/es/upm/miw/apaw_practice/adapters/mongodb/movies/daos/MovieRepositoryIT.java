package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;


import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.MovieEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class MovieRepositoryIT {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testFindByImdbIdNotExists(){
        System.out.println(this.movieRepository.findByImdbId("tt1234567"));
        assertTrue(this.movieRepository.findByImdbId("tt1234567").isEmpty());
    }

    @Test
    void testFindByImdbId(){
        assertTrue(this.movieRepository.findByImdbId("tt1375666").isPresent());
        MovieEntity movie = this.movieRepository.findByImdbId("tt1375666").get();
        assertEquals("tt1375666", movie.getImdbId());
        assertEquals("Inception",movie.getTitle());
        assertEquals(LocalDate.of(2010,7,8), movie.getReleaseDate());
    }
}
