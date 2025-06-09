package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.TicketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DirectorService {

    private final DirectorPersistence directorPersistence;
    private final MoviePersistence moviePersistence;
    private final ScreeningPersistence screeningPersistence;
    private final TicketPersistence ticketPersistence;

    @Autowired
    public DirectorService(DirectorPersistence directorPersistence,
                           MoviePersistence moviePersistence,
                           ScreeningPersistence screeningPersistence,
                           TicketPersistence ticketPersistence) {
        this.directorPersistence = directorPersistence;
        this.moviePersistence = moviePersistence;
        this.screeningPersistence = screeningPersistence;
        this.ticketPersistence = ticketPersistence;
    }

    public List<Director> findAll() {
        return directorPersistence.findAll();
    }

    public Director findByDni(String dni) {
        return directorPersistence.findByDni(dni).orElse(null);
    }

    public Director create(Director director) {
        return directorPersistence.create(director);
    }

    public Director update(String dni, Director director) {
        return directorPersistence.update(dni, director);
    }

    public void delete(String dni) {
        directorPersistence.delete(dni);
    }

    // Método busqueda 1 para lo tests:
    public BigDecimal sumTotalPriceByDirectorDni(String dni) {
        // Suma el precio de los tickets de películas dirigidas por el director con ese dni
        List<Movie> movies = moviePersistence.findAll();
        List<String> movieIds = movies.stream()
                .filter(m -> dni.equals(m.getDirectorId()))
                .map(Movie::getId)
                .toList();

        List<Screening> screenings = screeningPersistence.findAll();
        List<String> screeningIds = screenings.stream()
                .filter(s -> movieIds.contains(s.getMovieId()))
                .map(Screening::getId)
                .toList();

        List<Ticket> tickets = ticketPersistence.findAll();
        return tickets.stream()
                .filter(t -> screeningIds.contains(t.getScreeningId()))
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}