package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concert {

    private String code;
    private LocalDate date;
    private BigDecimal ticketPrice;
    private boolean isSoldOut;

    private Stage stage;
    private List<ConcertArtist> artists;

    public Concert() {
        this.artists = new ArrayList<>();
    }

    public Concert(String code, LocalDate date, BigDecimal ticketPrice, boolean isSoldOut) {
        this.code = code;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.isSoldOut = isSoldOut;
        this.artists = new ArrayList<>();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public List<ConcertArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<ConcertArtist> artists) {
        this.artists = artists;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "code='" + code + '\'' +
                ", date=" + date +
                ", ticketPrice=" + ticketPrice +
                ", isSoldOut=" + isSoldOut +
                ", stage=" + stage +
                ", artists=" + artists +
                '}';
    }
}
