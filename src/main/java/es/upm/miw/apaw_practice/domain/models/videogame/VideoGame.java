package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VideoGame {

    private String title;
    private LocalDate releaseDate;
    private String rating;
    private Critic critic;
    private List<Platform> platforms;

    public VideoGame() {
        //empty for framework
    }

    public VideoGame(String title, LocalDate releaseDate, String rating, Critic critic, List<Platform> platforms) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.critic = critic;
        this.platforms = platforms;
    }

    public static VideoGame ofPlatformCriticConsoleName(VideoGame game) {
        game.setPlatforms(
                game.platforms.stream()
                        .map(Platform::ofPlatform)
                        .collect(Collectors.toList())
        );
        game.setCritic(game.getCritic());
        return game;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Critic getCritic() {
        return critic;
    }

    public void setCritic(Critic critic) {
        this.critic = critic;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating='" + rating + '\'' +
                ", critic=" + critic +
                ", platforms=" + platforms +
                '}';
    }
}
