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

    public Actor(){
        //empty for framework
    }

    public Actor(String artisticName, String realName, boolean available, LocalDate birthDate){
        this.artisticName = artisticName;
        this.realName = realName;
        this.available = available;
        this.birthDate = birthDate;
    }

    public String getName() { return artisticName; }

    public void setName(String artisticName) { this.artisticName = artisticName; }

    public String getRealName() { return realName; }

    public void setRealName(String realName) { this.realName = realName; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public LocalDate getBirthDate(){ return birthDate; }

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate; }


    @Override
    public String toString(){
        return "Actor {\n" +
                "  artisticName: \"" + artisticName + "\"" + NEWLINE_WITH_COMMA +
                "  realName: \"" + realName + "\"" + NEWLINE_WITH_COMMA +
                "  available: " + available + NEWLINE_WITH_COMMA +
                "  birthDate: \"" + birthDate +  "\n" +
                "}";
    }
}
