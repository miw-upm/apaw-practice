package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class VideoGamerEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String videoGameAlias;
    private Integer numberOfPlayer;
    private Boolean crossPlatform;
    private LocalDate LocalDate;

    public VideoGamerEntity() {
        //empty for framework
    }
    public VideoGamerEntity(VideoGame videoGame) {
        BeanUtils.copyProperties(videoGame, this);
        this.id = UUID.randomUUID().toString();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getVideoGameAlias() {
        return videoGameAlias;
    }
    public void setVideoGameAlias(String videoGameAlias) {
        this.videoGameAlias = videoGameAlias;
    }
    public Integer getNumberOfPlayer() {
        return numberOfPlayer;
    }
    public void setNumberOfPlayer(Integer numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }
    public Boolean getCrossPlatform() {
        return crossPlatform;
    }
    public void setCrossPlatform(Boolean crossPlatform) {
        this.crossPlatform = crossPlatform;
    }
    public LocalDate getLocalDate() {
        return LocalDate;
    }
    public void setLocalDate(LocalDate localDate) {
        this.LocalDate = localDate;
    }

    public VideoGame toVideoGame() {
        VideoGame videoGame = new VideoGame();
        BeanUtils.copyProperties(this, videoGame);
        return videoGame;
    }

    public void fromVideoGame(VideoGame videoGame) {
        BeanUtils.copyProperties(this, videoGame);
    }

    @Override
    public int hashCode() {
        return videoGameAlias.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (videoGameAlias.equals(((VideoGamerEntity) obj).videoGameAlias));
    }

    @Override
    public String toString() {
        return "VideoGameEntity{" +
                "id='" + id + '\'' +
                ", videoGameAlias='" + videoGameAlias + '\'' +
                ", numberOfPlayer='" + numberOfPlayer + '\'' +
                ", crossPlatform=" + crossPlatform +
                ", LocalDate=" + LocalDate +
                '}';
    }
}
