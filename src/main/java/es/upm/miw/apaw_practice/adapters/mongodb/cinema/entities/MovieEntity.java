package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Movie;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.UUID;

@Document(collection = "movies")
public class MovieEntity {
    @Id
    private String id;
    private String title;
    private LocalDate releaseDate;
    private String description;
    private String directorId;

    public MovieEntity() {
        // empty for framework
    }

    public MovieEntity(String title, LocalDate releaseDate, String description, String directorId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.directorId = directorId;
    }

    public static MovieEntity fromMovie(Movie movie) {
        MovieEntity entity = new MovieEntity();
        entity.title = movie.getTitle();
        entity.description = movie.getDescription();
        entity.directorId = movie.getDirectorId();
        // Conversión segura de String a LocalDate
        try {
            entity.releaseDate = movie.getReleaseDate() != null && !movie.getReleaseDate().isEmpty()
                    ? LocalDate.parse(movie.getReleaseDate())
                    : null;
        } catch (DateTimeParseException e) {
            entity.releaseDate = null;
        }
        return entity;
    }

    public void updateFromMovie(Movie movie) {
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.directorId = movie.getDirectorId();
        try {
            this.releaseDate = movie.getReleaseDate() != null && !movie.getReleaseDate().isEmpty()
                    ? LocalDate.parse(movie.getReleaseDate())
                    : null;
        } catch (DateTimeParseException e) {
            this.releaseDate = null;
        }
    }


    public Movie toMovie() {
        Movie movie = new Movie();
        movie.setTitle(this.title);
        movie.setDescription(this.description);
        movie.setDirectorId(this.directorId);
        movie.setReleaseDate(this.releaseDate != null ? this.releaseDate.toString() : null);
        return movie;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public String getDescription() { return description; }
    public String getDirectorId() { return directorId; }
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public void setDescription(String description) { this.description = description; }
    public void setDirectorId(String directorId) { this.directorId = directorId; }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MovieEntity other)) return false;
        return Objects.equals(this.title, other.title);
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", description='" + description + '\'' +
                ", directorId='" + directorId + '\'' +
                '}';
    }
}