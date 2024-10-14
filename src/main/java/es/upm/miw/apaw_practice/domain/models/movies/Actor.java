package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.Objects;

public class Actor {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    private String artisticName;
    private String realName;
    private boolean isAvailable;
    private LocalDate birthDate;

    public Actor(){
        //empty for framework
    }

    public Actor(String artisticName, String realName, boolean isAvailable, LocalDate birthDate){
        this.artisticName = artisticName;
        this.realName = realName;
        this.isAvailable = isAvailable;
        this.birthDate = birthDate;
    }

    public String getArtisticName() { return artisticName; }

    public void setArtisticName(String artisticName) { this.artisticName = artisticName; }

    public String getRealName() { return realName; }

    public void setRealName(String realName) { this.realName = realName; }

    public boolean isAvailable() { return isAvailable; }

    public void setIsAvailable(boolean available) { this.isAvailable = available; }

    public LocalDate getBirthDate(){ return birthDate; }

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate; }


    @Override
    public String toString(){
        return "Actor {\n" +
                "  artisticName: \"" + artisticName + "\"" + NEWLINE_WITH_COMMA +
                "  realName: \"" + realName + "\"" + NEWLINE_WITH_COMMA +
                "  isAvailable: " + isAvailable + NEWLINE_WITH_COMMA +
                "  birthDate: \"" + birthDate +  "\"\n" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return artisticName.equals(actor.artisticName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artisticName);
    }

}
