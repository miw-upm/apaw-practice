package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Critic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class VideoGameEntity {

    @Id
    private String id;
    @Indexed(unique = true)

    private String title;
    private LocalDate releaseDate;
    private String rating;
    private Critic critic;
    @DBRef
    private List<PlatformEntity> platforms;

    public VideoGameEntity() {
        //empty for framework
    }

    public VideoGameEntity(String title, LocalDate releaseDate, String rating, Critic critic, List<PlatformEntity> platforms) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.critic = critic;
        this.platforms = platforms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<PlatformEntity> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<PlatformEntity> platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "VideoGameEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating='" + rating + '\'' +
                ", critic=" + critic +
                ", platforms=" + platforms +
                '}';
    }
}
