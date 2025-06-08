package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "movies")
public class MovieEntity {
    @Id
    private String title;
    private LocalDate releaseDate;
    private String description;
    private List<ScreeningEntity> screenings;
    @DBRef
    private DirectorEntity director;

    public MovieEntity() {}

    public MovieEntity(String title, LocalDate releaseDate, String description, List<ScreeningEntity> screenings, DirectorEntity director) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.screenings = screenings;
        this.director = director;
    }

    // Getters and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ScreeningEntity> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<ScreeningEntity> screenings) {
        this.screenings = screenings;
    }

    public DirectorEntity getDirector() {
        return director;
    }

    public void setDirector(DirectorEntity director) {
        this.director = director;
    }
}