package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class FilmEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String barcode;
    private String name;
    private String description;
    private List<ActorEntity> actors;
    @DBRef
    private List<ScreenEntity> screens;

    public FilmEntity(){
        //empty for framework
    }

    public FilmEntity(String barcode, String name, String description, List<ActorEntity> actors, List<ScreenEntity> screens) {
        this.id = UUID.randomUUID().toString();
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.actors = actors;
        this.screens = screens;
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

    public List<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorEntity> actors) {
        this.actors = actors;
    }

    public List<ScreenEntity> getScreens() {
        return screens;
    }

    public void setScreens(List<ScreenEntity> screens) {
        this.screens = screens;
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actors=" + actors +
                ", screens=" + screens +
                '}';
    }
}
