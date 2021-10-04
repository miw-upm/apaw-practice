package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities;

import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.tv_series.Player;
import es.upm.miw.apaw_practice.domain.models.tv_series.TvSeries;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class PlayerSeriesEntity {
    @Id
    private String id;
    private String name;
    private LocalDate birth;
    private String nationality;
    @DBRef
    private List<TvSeriesEntity> tvSeriesEntities;

    public PlayerSeriesEntity() {
        // empty for framework
    }

    public PlayerSeriesEntity(String name, LocalDate birth, String nationality, List<TvSeriesEntity> tvSeriesEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.birth = birth;
        this.nationality = nationality;
        this.tvSeriesEntities = tvSeriesEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<TvSeriesEntity> getTvSeriesEntities() {
        return tvSeriesEntities;
    }

    public void setTvSeriesEntities(List<TvSeriesEntity> tvSeriesEntities) {
        this.tvSeriesEntities = tvSeriesEntities;
    }

    public Player toPlayer() {
        return new Player(this.name,this.birth,this.nationality,
                this.tvSeriesEntities.stream()
                        .map(TvSeriesEntity::toTvSeries)
                        .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass()
                || ((name.equals(((PlayerSeriesEntity) obj).name))
                && (birth.equals(((PlayerSeriesEntity) obj).birth))
                && (nationality.equals(((PlayerSeriesEntity) obj).nationality))
                && (tvSeriesEntities.equals(((PlayerSeriesEntity) obj).tvSeriesEntities)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birth, nationality, tvSeriesEntities);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", birth=" + this.birth +
                ", nationality='" + this.nationality + '\'' +
                ", tvSeriesEntities=" + this.tvSeriesEntities +
                '}';
    }
}
