package es.upm.miw.apaw_practice.adapters.rest.cinema.dto;

public class MovieDto {
    private String title;
    private String releaseDate; // ISO format, ej. "2024-06-08"
    private String description;

    public MovieDto() {}

    public MovieDto(String title, String releaseDate, String description) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}