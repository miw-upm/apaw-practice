package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;

public class VideoGame {
    private String videoGame;
    private int numberOfPlayer;
    private Boolean crossPlatform;
    private LocalDate releaseDate;

    private  VideoGame() {
        //empty for framework
    }

    public VideoGame(String videoGame, int numberOfPlayer, Boolean crossPlatform, LocalDate releaseDate) {
        this.videoGame = videoGame;
        this.numberOfPlayer = numberOfPlayer;
        this.crossPlatform = crossPlatform;
        this.releaseDate = releaseDate;
    }
    public String getVideoGame() {
        return videoGame;
    }
    public void setVideoGame(String videoGame) {
        this.videoGame = videoGame;
    }
    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }
    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
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
                "videoGame='" + videoGame + '\'' +
                ", numberOfPlayer=" + numberOfPlayer +
                ", crossPlatform=" + crossPlatform +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
