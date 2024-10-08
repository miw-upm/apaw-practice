package es.upm.miw.apaw_practice.domain.models.movies;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Movie {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    private String imdbId;
    private String title;
    private BigDecimal boxOffice;
    private LocalDate releaseDate;
    private Set<Actor> actorsFeaturing;
    private Award awardWon;

    public Movie() {
        //empty for framework
    }

    public Movie(String imdbId, String title, BigDecimal boxOffice, LocalDate releaseDate, Set<Actor> actorsFeaturing, Award awardWon) {
        this.imdbId = imdbId;
        this.title = title;
        this.boxOffice = boxOffice;
        this.releaseDate = releaseDate;
        this.actorsFeaturing = new HashSet<>(actorsFeaturing);
        this.awardWon = awardWon;
    }

    public String getImdbId() { return imdbId; }

    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public BigDecimal getBoxOffice() { return boxOffice; }

    public void setBoxOffice(BigDecimal boxOffice) { this.boxOffice = boxOffice; }

    public LocalDate getReleaseDate() { return releaseDate; }

    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Set<Actor> getActorsFeaturing() { return actorsFeaturing; }

    public void setActorsFeaturing(Set<Actor> actorsFeaturing) {
        this.actorsFeaturing = actorsFeaturing;
    }

    public Actor getActorByArtisticName(String artisticName) {
        for (Actor actor : actorsFeaturing) {
            if (actor.getArtisticName().equals(artisticName)) {
                return actor;
            }
        }
        return null;
    }

    public void addActor(Actor actor) { actorsFeaturing.add(actor); }

    public void removeActor(Actor actor) { actorsFeaturing.remove(actor); }

    public Award getAwardWon() { return awardWon; }

    public void setAwardWon(Award awardWon) { this.awardWon = awardWon; }

    @Override
    public String toString() {
        return "Movie {\n" +
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
        Movie movie = (Movie) o;
        return imdbId.equals(movie.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdbId);
    }

}
