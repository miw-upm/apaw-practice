package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concert {

    private String concertCode;
    private LocalDate date;
    private BigDecimal ticketPrice;
    private boolean isSoldOut;

    private Stage stage;
    private List<Artist> artists;

    public Concert() {
        this.artists = new ArrayList<>();
    }

    public Concert(String concertCode, LocalDate date, BigDecimal ticketPrice, boolean isSoldOut) {
        this.concertCode = concertCode;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.isSoldOut = isSoldOut;
        this.artists = new ArrayList<>();
    }


    public String getConcertCode() {
        return concertCode;
    }

    public void setConcertCode(String concertCode) {
        this.concertCode = concertCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "concertCode='" + concertCode + '\'' +
                ", date=" + date +
                ", ticketPrice=" + ticketPrice +
                ", isSoldOut=" + isSoldOut +
                ", stage=" + stage +
                ", artists=" + artists +
                '}';
    }
}
