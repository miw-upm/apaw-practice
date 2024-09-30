package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;

public class VideoGame {
    private String name;
    private String genre;
    private String rate;
    private Boolean crossPlatform;
    private LocalDate releaseDate;

    private  VideoGame() {
        //empty for framework
    }

    public VideoGame(String name, String genre, String rate, Boolean crossPlatform, LocalDate releaseDate) {
        this.name = name;
        this.genre = genre;
        this.rate = rate;
        this.crossPlatform = crossPlatform;
        this.releaseDate = releaseDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public Boolean getCrossPlatform() {
        return crossPlatform;
    }
    public void setCrossPlatform(Boolean crossPlatform) {
        this.crossPlatform = crossPlatform;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "name='" + name + '\'' +
                ", genre=" + genre +
                ", rate=" + rate +
                ", crossPlatform=" + crossPlatform +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
