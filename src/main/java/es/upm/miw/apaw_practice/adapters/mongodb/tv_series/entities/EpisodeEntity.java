package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities;

import es.upm.miw.apaw_practice.domain.models.tv_series.Episode;
import org.springframework.beans.BeanUtils;
import java.util.Objects;

public class EpisodeEntity {
    private Integer number;
    private Integer season;
    private Integer duration;

    public EpisodeEntity() {
        // empty for framework
    }

    public EpisodeEntity(Integer number, Integer season, Integer duration) {
        this.number = number;
        this.season = season;
        this.duration = duration;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Episode toEpisode() {
        return Episode.builder()
                .number(this.number)
                .season(this.season)
                .duration(this.duration)
                .build();
    }

    public void fromEpisode(Episode episode) {
        BeanUtils.copyProperties(episode, this);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass()
                && (number.equals(((EpisodeEntity) obj).number))
                && (season.equals(((EpisodeEntity) obj).season))
                && (duration.equals(((EpisodeEntity) obj).duration));
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, season, duration);
    }

    @Override
    public String toString() {
        return "Episode{" +
                ", number=" + this.number +
                ", season=" + this.season +
                ", duration=" + this.duration +
                '}';
    }
}
