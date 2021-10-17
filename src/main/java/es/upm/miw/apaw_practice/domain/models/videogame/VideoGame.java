package es.upm.miw.apaw_practice.domain.models.videogame;

import java.time.LocalDate;
import java.util.List;

public class VideoGame {

    private String title;
    private LocalDate releaseDate;
    private String rating;
    private Critic critic;
    private List<Console> consoles;

    public VideoGame(String title, LocalDate releaseDate, String rating, Critic critic, List<Console> consoles) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.critic = critic;
        this.consoles = consoles;
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

    public List<Console> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating='" + rating + '\'' +
                ", critic=" + critic +
                ", consoles=" + consoles +
                '}';
    }
}
