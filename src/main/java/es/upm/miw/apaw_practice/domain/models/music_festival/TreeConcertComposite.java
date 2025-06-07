package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreeConcertComposite implements TreeConcert {

    private final LocalDate date;
    private final BigDecimal ticketPrice;
    private final boolean isSoldOut;
    private final Stage stage;
    private final List<ConcertArtist> artists;

    private final List<TreeConcert> treeConcerts;

    public TreeConcertComposite(LocalDate date, BigDecimal ticketPrice, boolean isSoldOut, Stage stage, List<ConcertArtist> artists) {
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.isSoldOut = isSoldOut;
        this.stage = stage;
        this.artists = artists;
        this.treeConcerts = new ArrayList<>();
    }

    @Override
    public LocalDate date() {
        return this.date;
    }

    @Override
    public BigDecimal ticketPrice() {
        return this.ticketPrice;
    }

    @Override
    public boolean isSoldOut() {
        return this.isSoldOut;
    }

    @Override
    public Stage stage() {
        return this.stage;
    }

    @Override
    public List<ConcertArtist> artists() {
        return this.artists;
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public void add(TreeConcert concertComponent) {
        this.treeConcerts.add(concertComponent);
    }

    @Override
    public void delete(TreeConcert concertComponent) {
        this.treeConcerts.remove(concertComponent);
    }

    @Override
    public int countConcerts() {
        return this.treeConcerts.size();
    }

    @Override
    public BigDecimal totalTicketPrice() {
        return this.treeConcerts.stream()
                .map(TreeConcert::totalTicketPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
