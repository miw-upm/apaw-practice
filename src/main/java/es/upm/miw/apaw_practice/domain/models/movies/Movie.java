package es.upm.miw.apaw_practice.domain.models.movies;

import java.math.BigDecimal;
import java.util.List;

public class Movie {

    private String id;
    private String title;
    private boolean wonAnOscar;
    private BigDecimal boxOffice;
    private List<Actor> actorsFeaturing;
    private Studio studio;

    public Movie(String id, String title, boolean wonAnOscar, BigDecimal boxOffice, List<Actor> actorsFeaturing, Studio studio) {
        this.id = id;
        this.title = title;
        this.wonAnOscar = wonAnOscar;
        this.boxOffice = boxOffice;
        this.actorsFeaturing = actorsFeaturing;
        this.studio = studio;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public boolean hasWonAnOscar() { return wonAnOscar; }

    public void setHasWonAnOscar(boolean wonAnOscar) { this.wonAnOscar = wonAnOscar; }

    public BigDecimal getBoxOffice() { return boxOffice; }

    public void setBoxOffice(BigDecimal boxOffice) { this.boxOffice = boxOffice; }

    public List<Actor> getActorsFeaturing() { return actorsFeaturing; }

    public void setActorsFeaturing(List<Actor> actorsFeaturing) { this.actorsFeaturing = actorsFeaturing; }

    public Studio getStudio() { return studio; }

    public void setStudio(Studio studio) { this.studio = studio; }

    @Override
    public String toString() {
        return "Movie {\n" +
                "  id: " + id + '\n' +
                "  title: \"" + title + "\",\n" +
                "  wonAnOscar: " + wonAnOscar + '\n' +
                "  boxOffice: " + boxOffice + "\n" +
                "  actorsFeaturing: " + actorsFeaturing + '\n' +
                "  studio: " + studio + '\n' +
                "}";
    }
}
