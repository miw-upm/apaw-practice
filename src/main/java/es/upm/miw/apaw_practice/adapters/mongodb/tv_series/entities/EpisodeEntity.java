package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities;

import es.upm.miw.apaw_practice.domain.models.tv_series.Episode;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class EpisodeEntity {
    @Id
    private String id;
    private Integer number;
    private Integer season;
    private Integer duration;
    @DBRef
    private TvSeriesEntity tvSeriesEntity;

    public EpisodeEntity() {
        // empty for framework
    }

    public EpisodeEntity(Integer number, Integer season, Integer duration, TvSeriesEntity tvSeriesEntity) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
        this.season = season;
        this.duration = duration;
        this.tvSeriesEntity = tvSeriesEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public TvSeriesEntity getTvSeriesEntity() {
        return tvSeriesEntity;
    }

    public void setTvSeriesEntity(TvSeriesEntity tvSeriesEntity) {
        this.tvSeriesEntity = tvSeriesEntity;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass()
                || ((number.equals(((EpisodeEntity) obj).number))
                && (season.equals(((EpisodeEntity) obj).season))
                && (duration.equals(((EpisodeEntity) obj).duration))
                && (tvSeriesEntity.equals(((EpisodeEntity) obj).tvSeriesEntity)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, season, duration, tvSeriesEntity);
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id='" + this.id + '\'' +
                ", number=" + this.number +
                ", season=" + this.season +
                ", duration=" + this.duration +
                ", tvSeriesEntity=" + this.tvSeriesEntity +
                '}';
    }
}
