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
    private Set<Award> awardsWon;

    public Movie() {
        //empty for framework
    }

    public Movie(String imdbId, String title, BigDecimal boxOffice, LocalDate releaseDate, Set<Actor> actorsFeaturing, Set<Award> awardsWon) {
        this.imdbId = imdbId;
        this.title = title;
        this.boxOffice = boxOffice;
        this.releaseDate = releaseDate;
        this.actorsFeaturing = new HashSet<>(actorsFeaturing);
        this.awardsWon = new HashSet<>(awardsWon);
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

    public Set<Award> getAwardsWon() { return awardsWon; }

    public void setAwardsWon(Set<Award> awardsWon) { this.awardsWon = awardsWon; }

    public Award getAwardByNameCategoryAndYear(String nameCategoryAndYear) {
        for (Award award : awardsWon) {
            if (award.getNameCategoryAndYear().equals(nameCategoryAndYear)) {
                return award;
            }
        }
        return null;
    }

    public void addAward(Award award) { awardsWon.add(award); }

    public void removeAward(Award award) { awardsWon.remove(award); }

    @Override
    public String toString() {
        return "Movie {\n" +
                "  imdbId: " + imdbId + NEWLINE_WITH_COMMA +
                "  title: \"" + title + NEWLINE_WITH_COMMA +
                "  boxOffice: " + boxOffice + NEWLINE_WITH_COMMA +
                "  releaseDate: " + releaseDate + NEWLINE_WITH_COMMA +
                "  actorsFeaturing: " + actorsFeaturing + NEWLINE_WITH_COMMA +
                "  awardsWon: " + awardsWon + '\n' +
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
