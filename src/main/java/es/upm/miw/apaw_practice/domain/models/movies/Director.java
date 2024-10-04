package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.List;

public class Director {
    private String id;
    private String name;
    private boolean wonAnOscar;
    private LocalDate birthDate;
    private List<Movie> moviesDirected;

    public Director() {
        //empty for framework
    }

    public Director(String id, String name, boolean wonAnOscar, LocalDate birthDate, List<Movie> moviesDirected) {
        this.id = id;
        this.name = name;
        this.wonAnOscar = wonAnOscar;
        this.birthDate = birthDate;
        this.moviesDirected = moviesDirected;
    }

    public String getId() { return id; }

    public void setId(String id){ this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Boolean hasWonAnOscar() { return wonAnOscar; }

    public void setHasWonAnOscar (Boolean wonAnOscar) { this.wonAnOscar = wonAnOscar; }

    public LocalDate getBirthDate () { return birthDate; }

    public void setBirthDate (LocalDate birthDate) { this.birthDate = birthDate; }

    public List<Movie> getMoviesDirected() { return moviesDirected; }

    public void setMoviesDirected (List<Movie> moviesDirected) { this.moviesDirected = moviesDirected; }

    @Override
    public String toString() {
        return  "Director {\n" +
                "  id: " + id + '\n' +
                "  name: \"" + name + "\",\n" +
                "  wonAnOscar: " + wonAnOscar + '\n' +
                "  birthDate: \"" + birthDate + "\",\n" +
                "  moviesDirected: " + moviesDirected + '\n' +
                "}";
    }
}
