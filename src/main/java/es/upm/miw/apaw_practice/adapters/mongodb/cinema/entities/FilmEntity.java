package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Actor;
import es.upm.miw.apaw_practice.domain.models.cinema.Film;
import es.upm.miw.apaw_practice.domain.models.cinema.Screen;
import org.springframework.beans.BeanUtils;
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
    @DBRef
    private ScreenEntity screen;

    public FilmEntity(){
        //empty for framework
    }

    public FilmEntity(String barcode, String name, String description, ScreenEntity screen) {
        this.id = UUID.randomUUID().toString();
        this.barcode = barcode;
        this.name = name;
        this.description = description;
        this.screen = screen;
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

    public void setScreens(ScreenEntity screen) {
        this.screen = screen;
    }

    public Film toFilm() {
        Film film = new Film();
        BeanUtils.copyProperties(this, film.getBarcode(),film.getName(), film.getDescription());
        return film;
    }

    @Override
    public String toString() {
        return "FilmEntity{" +
                "id='" + id + '\'' +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", screens=" + screen +
                '}';
    }
}
