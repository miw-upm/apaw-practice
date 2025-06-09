package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TreeConcertLeaf  implements TreeConcert {

    private final Concert concert;

    public TreeConcertLeaf(Concert concert) {
        this.concert = concert;
    }

    @Override
    public LocalDate date() {
        return this.concert.getDate();
    }

    @Override
    public BigDecimal ticketPrice() {
        return this.concert.getTicketPrice();
    }

    @Override
    public boolean isSoldOut() {
        return this.concert.isSoldOut();
    }

    @Override
    public Stage stage() {
        return this.concert.getStage();
    }

    @Override
    public List<ConcertArtist> artists() {
        return this.concert.getArtists();
    }

    @Override
    public boolean isComposite() {
        return false;
    }

    @Override
    public void add(TreeConcert concertComponent) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public void delete(TreeConcert concertComponent) {
        throw new UnsupportedOperationException("Unsupported operation in leaf");
    }

    @Override
    public int countConcerts() {
        return 1;
    }

    @Override
    public BigDecimal totalTicketPrice() {
        return this.concert.getTicketPrice();
    }
}
