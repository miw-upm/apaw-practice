package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.List;

public class Actor {
    private String id;
    private String name;
    private boolean wonAnOscar;
    private LocalDate birthDate;
    private List<Movie> appearsIn;

    public Actor(){
        //empty for framework
    }

    public Actor(String id, String name, boolean wonAnOscar, LocalDate birthDate, List<Movie> appearsIn){
        this.id = id;
        this.name = name;
        this.wonAnOscar = wonAnOscar;
        this.birthDate = birthDate;
        this.appearsIn = appearsIn;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean hasWonAnOscar() { return wonAnOscar; }

    public void setHasWonAnOscar(boolean wonAnOscar) { this.wonAnOscar = wonAnOscar; }

    public LocalDate getBirthDate(){ return birthDate; }

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate; }

    public List<Movie> getAppearsIn(){ return appearsIn; }

    public void setAppearsIn(List<Movie> appearsIn) { this.appearsIn = appearsIn; }

    @Override
    public String toString(){
        return "Actor {\n" +
                "  id: " + id + '\n' +
                "  name: \"" + name + "\",\n" +
                "  wonAnOscar: " + wonAnOscar + '\n' +
                "  birthDate: \"" + birthDate + "\",\n" +
                "  appearsIn: " + appearsIn + '\n' +
                "}";
    }
}
