package es.upm.miw.apaw_practice.domain.models.cinema;

public class Movie {
    private String id; // único
    private String title;
    private String releaseDate; // formato ISO
    private String description;
    private String directorId;

    public Movie() {}

    public Movie(String id, String title, String releaseDate, String description, String directorId) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
        this.directorId = directorId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDirectorId() { return directorId; }
    public void setDirectorId(String directorId) { this.directorId = directorId; }
}