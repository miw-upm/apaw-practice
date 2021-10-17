package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class FilmEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String barcode;
    private String name;
    private String description;
    @DBRef
    private ScreenEntity screen;
    @DBRef
    private List<ActorEntity> actors;

    public FilmEntity(){
        //empty for framework
    }

    public FilmEntity(String barcode, String name, String description, ScreenEntity screen, List<ActorEntity> actor) {
        this.id = UUID.randomUUID().toString();
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.screen = screen;
        this.actors = actor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScreenEntity getScreen() {
        return screen;
    }

    public void setScreen(ScreenEntity screen) {
        this.screen = screen;
    }

    public List<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorEntity> actors) {
        this.actors = actors;
    }

    public Film toFilm() {
        List<Actor> actorList = this.actors.stream()
                .map(ActorEntity::toActor)
                .collect(Collectors.toList());
        return new Film(barcode, name, description, actorList,this.screen.toScreen());
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", screens=" + screen +
                ", actors=" + actors +
                '}';
    }
}
