package es.upm.miw.apaw_practice.domain.models.cinema;

import es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities.ScreenEntity;

import java.util.List;

public class Film {
    private String barcode;
    private String name;
    private String description;
    private List<Actor> actors;
    private Screen screen;

    public Film() {
        //empty for framework
    }
    public Film(String barcode, String name, String description, Integer screenNumber) {
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.screen.setNumber(screenNumber);
    }

    public Film(String barcode, String name, String description, List<Actor> actors, Screen screen) {
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.actors = actors;
        this.screen = screen;
    }

    public Film(String barcode) {
        this.barcode = barcode;
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

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Film{" +
                "barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actors=" + actors +
                ", screen=" + screen +
                '}';
    }
}
