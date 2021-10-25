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

    public static VideoGameBuilder.Title builder() {
        return new Builder();
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

    public static class Builder implements VideoGameBuilder.Platforms, VideoGameBuilder.Title,
            VideoGameBuilder.Rating, VideoGameBuilder.Optionals {

        private final VideoGame videoGame;

        public Builder() {
            this.videoGame = new VideoGame();
        }

        @Override
        public VideoGameBuilder.Platforms title(String title) {
            this.videoGame.title = title;
            return this;
        }

        @Override
        public VideoGameBuilder.Rating platforms(List<Platform> platforms) {
            this.videoGame.platforms = platforms;
            return this;
        }

        @Override
        public VideoGameBuilder.Optionals rating(String rating) {
            this.videoGame.rating = rating;
            return this;
        }

        @Override
        public VideoGameBuilder.Optionals releaseDate(LocalDate releaseDate) {
            this.videoGame.releaseDate = releaseDate;
            return this;
        }

        @Override
        public VideoGameBuilder.Optionals critic(Critic critic) {
            this.videoGame.critic = critic;
            return this;
        }

        @Override
        public VideoGame build() {
            return this.videoGame;
        }
    }
}
