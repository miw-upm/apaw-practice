package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.*;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {
    @Test
    void testCount3DScreeningsByMovieWithDirectorOver50() {
        MoviePersistence moviePersistence = mock(MoviePersistence.class);
        DirectorPersistence directorPersistence = mock(DirectorPersistence.class);
        ScreeningPersistence screeningPersistence = mock(ScreeningPersistence.class);

        String title = "Epic Movie";
        Movie movie = new Movie();
        movie.setId("m1");
        movie.setTitle(title);
        movie.setDirectorId("d1");

        Director director = new Director.Builder()
                .id("director-1")
                .name("Nombre")
                .birthdate("1970-07-30")
                .build();
        director.setId("d1");

        Screening s1 = new Screening();
        s1.setId("s1");
        s1.setMovieId("m1");
        s1.setThreeDFormat(true);

        Screening s2 = new Screening();
        s2.setId("s2");
        s2.setMovieId("m1");
        s2.setThreeDFormat(false);

        when(moviePersistence.findByTitle(title)).thenReturn(Optional.of(movie));
        when(directorPersistence.findAll()).thenReturn(List.of(director));
        when(screeningPersistence.findAll()).thenReturn(List.of(s1, s2));

        MovieService service = new MovieService(moviePersistence, directorPersistence, screeningPersistence);

        long count = service.count3DScreeningsByMovieWithDirectorOver50(title);
        assertEquals(1, count);
    }
}