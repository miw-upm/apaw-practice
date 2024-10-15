package es.upm.miw.apaw_practice.adapters.mongodb.movies.entities;

import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Document
public class ActorEntity {

    public static final String NEWLINE_WITH_COMMA = ",\n";

    @Id
    private String id;
    @Indexed(unique = true)
    private String artisticName;
    private String realName;
    private Boolean isAvailable;
    private LocalDate birthDate;

    public ActorEntity() {
        //empty for framework
    }

    public ActorEntity(String artisticName, String realName, Boolean isAvailable, LocalDate birthDate){
        this.id = UUID.randomUUID().toString();
        this.artisticName = artisticName;
        this.realName = realName;
        this.isAvailable = isAvailable;
        this.birthDate = birthDate;
    }

    public String getId(){ return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtisticName() { return artisticName; }

    public void setArtisticName(String artisticName) { this.artisticName = artisticName; }

    public String getRealName() { return realName; }

    public void setRealName(String realName) { this.realName = realName; }

    public Boolean getAvailable() { return isAvailable; }

    public void setAvailable(Boolean available) { isAvailable = available; }

    public LocalDate getBirthDate() { return birthDate; }

    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public Actor toActor() {
        return new Actor(this.artisticName, this.realName, this.isAvailable, this.birthDate);
    }

    public void fromActor(Actor actor) {
        this.artisticName = actor.getArtisticName();
        this.realName = actor.getRealName();
        this.isAvailable = actor.isAvailable();
        this.birthDate = actor.getBirthDate();
    }

    @Override
    public String toString(){
        return "ActorEntity {\n" +
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
        ActorEntity actor = (ActorEntity) o;
        return artisticName.equals(actor.artisticName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artisticName);
    }

}
