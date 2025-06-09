package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScreeningServiceTest {

    private DirectorPersistence directorPersistence;
    private MoviePersistence moviePersistence;
    private ScreeningPersistence screeningPersistence;
    private ScreeningService screeningService;

    @BeforeEach
    void init() {
        directorPersistence = mock(DirectorPersistence.class);
        moviePersistence = mock(MoviePersistence.class);
        screeningPersistence = mock(ScreeningPersistence.class);
        screeningService = new ScreeningService(screeningPersistence, directorPersistence, moviePersistence);
    }

    @Test
    void testFindAvailableSeatsByDirectorStyle() {

        Director director1 = new Director("dir1", "Director Test 1", "12345678A", "1970-01-01", "Neorrealismo");
        Director director2 = new Director("dir2", "Director Test 2", "87654321B", "1980-05-10", "Surrealismo");

        // Movie: id, title, releaseDate (String), description, directorId (enlaza con Director.id)
        Movie movie1 = new Movie("mov1", "Pelicula Neorrealista 1", "2000-01-01", "Desc. peli 1", director1.getId());
        Movie movie2 = new Movie("mov2", "Pelicula Neorrealista 2", "2005-03-15", "Desc. peli 2", director1.getId());
        Movie movie3 = new Movie("mov3", "Pelicula Surrealista 1", "2010-07-20", "Desc. peli 3", director2.getId()); // Película de otro director

        // Screening: id, movieId (enlaza con Movie.id), dateTime (String), threeDFormat, roomNumber
        Screening screening1 = new Screening("scr1", movie1.getId(), LocalDateTime.now().toString(), false, 50); // Room number 50
        Screening screening2 = new Screening("scr2", movie1.getId(), LocalDateTime.now().plusHours(2).toString(), true, 30); // Room number 30
        Screening screening3 = new Screening("scr3", movie2.getId(), LocalDateTime.now().plusDays(1).toString(), false, 20); // Room number 20
        Screening screening4 = new Screening("scr4", movie3.getId(), LocalDateTime.now().plusDays(2).toString(), true, 40); // Room number 40 (de otra película/director)

        // Configuración del comportamiento de los mocks
        when(directorPersistence.findAll()).thenReturn(Arrays.asList(director1, director2));
        when(moviePersistence.findAll()).thenReturn(Arrays.asList(movie1, movie2, movie3));
        when(screeningPersistence.findAll()).thenReturn(Arrays.asList(screening1, screening2, screening3, screening4));

        // When: Se llama al método a probar
        List<Integer> roomNumbers = screeningService.findAvailableSeatsByDirectorStyle("Neorrealismo");

        // Then: Se verifica el resultado
        // Se esperan los roomNumbers de las proyecciones asociadas a películas de directores "Neorrealismo"
        assertEquals(List.of(50, 30, 20), roomNumbers);
        // Se verifica que los métodos de persistencia fueron llamados una vez
        verify(directorPersistence, times(1)).findAll();
        verify(moviePersistence, times(1)).findAll();
        verify(screeningPersistence, times(1)).findAll();
    }

    @Test
    void testFindAvailableSeatsByDirectorStyleWhenNoDirector() {
        // Given
        when(directorPersistence.findAll()).thenReturn(Collections.emptyList());

        // When
        List<Integer> seats = screeningService.findAvailableSeatsByDirectorStyle("Neorrealismo");

        // Then
        assertEquals(Collections.emptyList(), seats);
        verify(directorPersistence, times(1)).findAll();
        verifyNoInteractions(moviePersistence); // No interacciones esperadas con moviePersistence
        verifyNoInteractions(screeningPersistence); // No interacciones esperadas con screeningPersistence
    }

    @Test
    void testFindAvailableSeatsByDirectorStyleWhenNoMoviesForDirector() {
        // Given
        Director director1 = new Director("dir1", "Director Test 1", "12345678A", "1970-01-01", "Neorrealismo");
        when(directorPersistence.findAll()).thenReturn(List.of(director1));
        when(moviePersistence.findAll()).thenReturn(Collections.emptyList()); // No películas para este director

        // When
        List<Integer> seats = screeningService.findAvailableSeatsByDirectorStyle("Neorrealismo");

        // Then
        assertEquals(Collections.emptyList(), seats);
        verify(directorPersistence, times(1)).findAll();
        verify(moviePersistence, times(1)).findAll();
        verifyNoInteractions(screeningPersistence); // No interacciones esperadas
    }

    @Test
    void testFindAvailableSeatsByDirectorStyleWhenNoScreeningsForMovies() {
        // Given
        Director director1 = new Director("dir1", "Director Test 1", "12345678A", "1970-01-01", "Neorrealismo");
        Movie movie1 = new Movie("mov1", "Pelicula Neorrealista 1", "2000-01-01", "Desc. peli 1", director1.getId());

        when(directorPersistence.findAll()).thenReturn(List.of(director1));
        when(moviePersistence.findAll()).thenReturn(List.of(movie1));
        when(screeningPersistence.findAll()).thenReturn(Collections.emptyList()); // No proyecciones para esta película

        // When
        List<Integer> seats = screeningService.findAvailableSeatsByDirectorStyle("Neorrealismo");

        // Then
        assertEquals(Collections.emptyList(), seats);
        verify(directorPersistence, times(1)).findAll();
        verify(moviePersistence, times(1)).findAll();
        verify(screeningPersistence, times(1)).findAll();
    }

    @Test
    void testFindAvailableSeatsByDirectorStyleWithMultipleDirectorsAndMovies() {
        // Given
        Director directorA = new Director("dirA", "Director A", "DNIA", "1960-01-01", "Action");
        Director directorB = new Director("dirB", "Director B", "DNIB", "1975-02-02", "Comedy");
        Director directorC = new Director("dirC", "Director C", "DNIC", "1990-03-03", "Action"); // Otro director de acción

        Movie movieA1 = new Movie("movA1", "Movie A1", "2015-04-01", "Desc A1", directorA.getId());
        Movie movieA2 = new Movie("movA2", "Movie A2", "2017-05-01", "Desc A2", directorA.getId());
        Movie movieB1 = new Movie("movB1", "Movie B1", "2018-06-01", "Desc B1", directorB.getId());
        Movie movieC1 = new Movie("movC1", "Movie C1", "2020-07-01", "Desc C1", directorC.getId());

        Screening screenA1_1 = new Screening("scrA1_1", movieA1.getId(), LocalDateTime.now().toString(), false, 100);
        Screening screenA1_2 = new Screening("scrA1_2", movieA1.getId(), LocalDateTime.now().plusHours(1).toString(), true, 80);
        Screening screenA2_1 = new Screening("scrA2_1", movieA2.getId(), LocalDateTime.now().plusHours(2).toString(), false, 120);
        Screening screenB1_1 = new Screening("scrB1_1", movieB1.getId(), LocalDateTime.now().plusHours(3).toString(), false, 50);
        Screening screenC1_1 = new Screening("scrC1_1", movieC1.getId(), LocalDateTime.now().plusHours(4).toString(), true, 90);

        when(directorPersistence.findAll()).thenReturn(Arrays.asList(directorA, directorB, directorC));
        when(moviePersistence.findAll()).thenReturn(Arrays.asList(movieA1, movieA2, movieB1, movieC1));
        when(screeningPersistence.findAll()).thenReturn(Arrays.asList(screenA1_1, screenA1_2, screenA2_1, screenB1_1, screenC1_1));

        // When
        List<Integer> roomNumbers = screeningService.findAvailableSeatsByDirectorStyle("Action");

        // Then
        assertEquals(List.of(100, 80, 120, 90), roomNumbers);
        verify(directorPersistence, times(1)).findAll();
        verify(moviePersistence, times(1)).findAll();
        verify(screeningPersistence, times(1)).findAll();
    }
}