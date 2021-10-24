package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Critic;
import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class VideoGameEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private LocalDate releaseDate;
    private String rating;
    private CriticEntity criticEntity;
    @DBRef
    private List<PlatformEntity> platformEntities;

    public VideoGameEntity() {
        //empty for framework
    }

    public VideoGameEntity(String title, LocalDate releaseDate, String rating, CriticEntity criticEntity, List<PlatformEntity> platformEntities) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.criticEntity = criticEntity;
        this.platformEntities = platformEntities;
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

    public CriticEntity getCriticEntity() {
        return criticEntity;
    }

    public void setCriticEntity(CriticEntity criticEntity) {
        this.criticEntity = criticEntity;
    }

    public List<PlatformEntity> getPlatformEntities() {
        return platformEntities;
    }

    public void setPlatformEntities(List<PlatformEntity> platformEntities) {
        this.platformEntities = platformEntities;
    }

    public VideoGame toVideoGame() {
        Critic critic = this.criticEntity.toCritic();
        List<Platform> platforms = this.platformEntities.stream()
                .map(PlatformEntity::toPlatform)
                .collect(Collectors.toList());

        return VideoGame.builder()
                .title(title)
                .platforms(platforms)
                .rating(rating)
                .releaseDate(releaseDate)
                .critic(critic)
                .build();
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (title.equals(((VideoGameEntity) obj).title));
    }

    @Override
    public String toString() {
        return "VideoGameEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating='" + rating + '\'' +
                ", criticEntity=" + criticEntity +
                ", platformEntities=" + platformEntities +
                '}';
    }
}
