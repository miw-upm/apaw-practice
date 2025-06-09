package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.*;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DirectorServiceTest {

    @Test
    void testSumTotalPriceByDirectorDni() {
        DirectorPersistence directorPersistence = mock(DirectorPersistence.class);
        MoviePersistence moviePersistence = mock(MoviePersistence.class);
        ScreeningPersistence screeningPersistence = mock(ScreeningPersistence.class);
        TicketPersistence ticketPersistence = mock(TicketPersistence.class);

        String dni = "12345678A";
        Director director = new Director.Builder(dni)
                .birthdate(LocalDate.of(1970, 1, 1))
                .style("Drama")
                .build();
        director.setId("dir1");

        Movie movie = new Movie();
        movie.setId("mov1");
        movie.setDirectorId("dir1");

        Screening screening = new Screening();
        screening.setId("scr1");
        screening.setMovieId("mov1");

        Ticket ticket1 = new Ticket();
        ticket1.setScreeningId("scr1");
        ticket1.setTotalPrice(new BigDecimal("10.0"));

        Ticket ticket2 = new Ticket();
        ticket2.setScreeningId("scr1");
        ticket2.setTotalPrice(new BigDecimal("8.0"));

        when(directorPersistence.findByDni(dni)).thenReturn(Optional.of(director));
        when(moviePersistence.findAll()).thenReturn(List.of(movie));
        when(screeningPersistence.findAll()).thenReturn(List.of(screening));
        when(ticketPersistence.findAll()).thenReturn(List.of(ticket1, ticket2));

        DirectorService service = new DirectorService(
                directorPersistence, moviePersistence, screeningPersistence, ticketPersistence);

        BigDecimal result = service.sumTotalPriceByDirectorDni(dni);
        assertEquals(new BigDecimal("18.0"), result);
    }
}