package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;

public class VideoGame {
    private String videoGameAlias;
    private int numberOfPlayer;
    private Boolean crossPlatform;
    private LocalDate releaseDate;

    private  VideoGame() {
        //empty for framework
    }

    public VideoGame(String videoGameAlias, int numberOfPlayer, Boolean crossPlatform, LocalDate releaseDate) {
        this.videoGameAlias = videoGameAlias;
        this.numberOfPlayer = numberOfPlayer;
        this.crossPlatform = crossPlatform;
        this.releaseDate = releaseDate;
    }
    public String getVideoGameAlias() {
        return videoGameAlias;
    }
    public void setVideoGameAlias(String videoGameAlias) {
        this.videoGameAlias = videoGameAlias;
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
                "videoGameAlias='" + videoGameAlias + '\'' +
                ", numberOfPlayer=" + numberOfPlayer +
                ", crossPlatform=" + crossPlatform +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
