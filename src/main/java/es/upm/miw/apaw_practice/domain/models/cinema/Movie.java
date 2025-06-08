package es.upm.miw.apaw_practice.domain.models.cinema;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String title;
    private LocalDate releaseDate;
    private String description;
    private List<Screening> screenings;
    private Director director;

    public Movie() {
        // Default constructor
    }

    public Movie(String title, LocalDate releaseDate, String description, List<Screening> screenings, Director director) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.screenings = screenings;
        this.director = director;
    }

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

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}