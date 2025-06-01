package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities;

import es.upm.miw.apaw_practice.domain.models.music_festival.Concert;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class ConcertEntity {

    private String code;
    private LocalDate date;
    private BigDecimal ticketPrice;
    private boolean isSoldOut;

    @DBRef(lazy = true)
    private StageEntity stage;

    @DBRef(lazy = true)
    private List<ConcertArtistEntity> artists;

    public ConcertEntity() {
        // empty for framework
    }

    public ConcertEntity(String code, LocalDate date, BigDecimal ticketPrice, boolean isSoldOut,
                         StageEntity stage, List<ConcertArtistEntity> artists) {
        this.code = code;
        this.date = date;
        this.ticketPrice = ticketPrice;
        this.isSoldOut = isSoldOut;
        this.stage = stage;
        this.artists = artists;
    }

    public Concert toDomain() {
        Concert concert = new Concert();
        BeanUtils.copyProperties(this, concert, "stage", "artists");
        concert.setStage(this.stage != null ? this.stage.toDomain() : null);
        concert.setArtists(this.artists != null
                ? this.artists.stream().map(ConcertArtistEntity::toDomain).toList()
                : null);
        return concert;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public StageEntity getStage() {
        return stage;
    }

    public void setStage(StageEntity stage) {
        this.stage = stage;
    }

    public List<ConcertArtistEntity> getArtists() {
        return artists;
    }

    public void setArtists(List<ConcertArtistEntity> artists) {
        this.artists = artists;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.code);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ConcertEntity other)) {
            return false;
        }
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "ConcertEntity{" +
                " concertCode='" + code + '\'' +
                ", date=" + date +
                ", ticketPrice=" + ticketPrice +
                ", isSoldOut=" + isSoldOut +
                ", stage=" + stage +
                ", artists=" + artists +
                '}';
    }
}
