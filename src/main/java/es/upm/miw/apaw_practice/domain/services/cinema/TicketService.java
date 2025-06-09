package es.upm.miw.apaw_practice.domain.services.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Ticket;
import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.TicketPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.ScreeningPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.MoviePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.cinema.DirectorPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service("ticketServiceCinema")
public class TicketService {

    private final TicketPersistence ticketPersistence;
    private final ScreeningPersistence screeningPersistence;
    private final MoviePersistence moviePersistence;
    private final DirectorPersistence directorPersistence;

    @Autowired
    public TicketService(
            TicketPersistence ticketPersistence,
            ScreeningPersistence screeningPersistence,
            MoviePersistence moviePersistence,
            DirectorPersistence directorPersistence
    ) {
        this.ticketPersistence = ticketPersistence;
        this.screeningPersistence = screeningPersistence;
        this.moviePersistence = moviePersistence;
        this.directorPersistence = directorPersistence;
    }

    public List<Ticket> findAll() {
        return this.ticketPersistence.findAll();
    }

    public Optional<Ticket> findById(String id) {
        return this.ticketPersistence.findById(id);
    }

    public Ticket create(Ticket ticket) {
        return this.ticketPersistence.create(ticket);
    }

    public Ticket update(String id, Ticket ticket) {
        return this.ticketPersistence.update(id, ticket);
    }

    public void delete(String id) {
        this.ticketPersistence.delete(id);
    }

    public BigDecimal sumTotalPriceByDirectorDni(String dni) {
        return this.ticketPersistence.findAll().stream()
                .filter(ticket -> dni.equals(getDirectorDniByScreeningId(ticket.getScreeningId())))
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String getDirectorDniByScreeningId(String screeningId) {
        Optional<Screening> screeningOpt = screeningPersistence.findById(screeningId);
        if (screeningOpt.isEmpty()) {
            return null;
        }
        Screening screening = screeningOpt.get();
        Optional<Movie> movieOpt = moviePersistence.findById(screening.getMovieId());
        if (movieOpt.isEmpty()) {
            return null;
        }
        Movie movie = movieOpt.get();
        if (movie.getDirectorId() == null) {
            return null;
        }
        Optional<Director> directorOpt = directorPersistence.findById(movie.getDirectorId());
        return directorOpt.map(Director::getDni).orElse(null);
    }
}