package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.List;

public class Console {
    private String name;
    private LocalDate releaseDate;
    private List<VideoGame> videoGames;

    private  Console() {
        //empty for framework
    }

    public Console(String name, LocalDate releaseDate, List<VideoGame> videoGames) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.videoGames = videoGames;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    public List<VideoGame> getVideoGames() {
        return videoGames;
    }
    public void setVideoGames(List<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }
    public void addVideoGame(VideoGame videoGame) {
        this.videoGames.add(videoGame);
    }

    @Override
    public String toString() {
        return "Console{" +
                "name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", videoGame=" + videoGames +
                '}';
    }
}
