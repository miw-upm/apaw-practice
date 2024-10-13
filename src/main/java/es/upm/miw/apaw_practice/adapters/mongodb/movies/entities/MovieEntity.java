package es.upm.miw.apaw_practice.adapters.mongodb.movies.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Document
public class MovieEntity {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    @Id
    private String id;
    @Indexed(unique = true)
    private String imdbId;
    private String title;
    private BigDecimal boxOffice;
    private LocalDate releaseDate;
    private AwardEntity awardWon;
    @DBRef
    private Set<ActorEntity> actorsFeaturing;

    public MovieEntity() {
        //empty for framework
    }

    public MovieEntity(String imdbId, String title, BigDecimal boxOffice, LocalDate releaseDate, Set<ActorEntity> actorsFeaturing, AwardEntity awardWon) {
        this.id = UUID.randomUUID().toString();
        this.imdbId = imdbId;
        this.title = title;
        this.boxOffice = boxOffice;
        this.releaseDate = releaseDate;
        this.actorsFeaturing = actorsFeaturing;
        this.awardWon = awardWon;
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getImdbId() { return imdbId; }

    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public BigDecimal getBoxOffice() { return boxOffice; }

    public void setBoxOffice(BigDecimal boxOffice) { this.boxOffice = boxOffice; }

    public LocalDate getReleaseDate() { return releaseDate; }

    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Set<ActorEntity> getActorsFeaturing() { return actorsFeaturing; }

    public void setActorsFeaturing(Set<ActorEntity> actorsFeaturing) { this.actorsFeaturing = actorsFeaturing; }

    public AwardEntity getAwardWon() { return awardWon; }

    public void setAwardWon(AwardEntity awardWon) { this.awardWon = awardWon; }

    @Override
    public String toString() {
        return "MovieEntity {\n" +
                "  imdbId: " + imdbId + NEWLINE_WITH_COMMA +
                "  title: \"" + title + NEWLINE_WITH_COMMA +
                "  boxOffice: " + boxOffice + NEWLINE_WITH_COMMA +
                "  releaseDate: " + releaseDate + NEWLINE_WITH_COMMA +
                "  actorsFeaturing: " + actorsFeaturing + NEWLINE_WITH_COMMA +
                "  awardWon: " + awardWon + '\n' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity movie = (MovieEntity) o;
        return imdbId.equals(movie.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbId);
    }
}
