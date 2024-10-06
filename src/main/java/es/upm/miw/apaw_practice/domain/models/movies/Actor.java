package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Actor {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    private String artisticName;
    private String realName;
    private boolean available;
    private LocalDate birthDate;
    private Set<Movie> appearsIn;
    private Set<Award> awardsWon;

    public Actor(){
        //empty for framework
    }

    public Actor(String artisticName, String realName, boolean avaliable, LocalDate birthDate, Set<Movie> moviesList, Set<Award> awardsWon){
        this.artisticName = artisticName;
        this.realName = realName;
        this.available = avaliable;
        this.birthDate = birthDate;
        this.appearsIn = new HashSet<>(moviesList);
        this.awardsWon = new HashSet<>(awardsWon);
    }

    public String getName() { return artisticName; }

    public void setName(String artisticName) { this.artisticName = artisticName; }

    public String getRealName() { return realName; }

    public void setRealName(String realName) { this.realName = realName; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public LocalDate getBirthDate(){ return birthDate; }

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate; }

    public Set<Movie> getAppearsIn() { return appearsIn; }

    public void setAppearsIn(Set<Movie> appearsIn) { this.appearsIn = appearsIn; }

    public void addMovie(Movie movie) { appearsIn.add(movie); }

    public void removeMovie(Movie movie) { appearsIn.remove(movie); }

    public Set<Award> getAwardsWon() { return awardsWon; }

    public void setAwardsWon(Set<Award> awardsWon) { this.awardsWon = awardsWon; }

    public void addAward(Award award) { awardsWon.add(award); }

    public void removeAward(Award award) { awardsWon.remove(award); }

    @Override
    public String toString(){
        return "Actor {\n" +
                "  artisticName: \"" + artisticName + "\"" + NEWLINE_WITH_COMMA +
                "  realName: \"" + realName + "\"" + NEWLINE_WITH_COMMA +
                "  available: " + available + NEWLINE_WITH_COMMA +
                "  birthDate: \"" + birthDate + "\"" + NEWLINE_WITH_COMMA +
                "  appearsIn: " + appearsIn + NEWLINE_WITH_COMMA +
                "  awardsWon: " + awardsWon + "\n" +
                "}";
    }
}
