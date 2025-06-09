package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TreeConcert {

    LocalDate date();

    BigDecimal ticketPrice();

    boolean isSoldOut();

    Stage stage();

    List<ConcertArtist> artists();

    boolean isComposite();

    void add(TreeConcert concertComponent);

    void delete(TreeConcert concertComponent);

    int countConcerts();

    BigDecimal totalTicketPrice();

}
