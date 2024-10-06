package es.upm.miw.apaw_practice.adapters.mongodb.wushu_sport.entities;

import es.upm.miw.apaw_practice.domain.models.wuhshu_sport.CompetitionForm;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.UUID;

@Document
public class CompetitionFormEntity {

    @Id
    private String id;
    private Double score;
    private Duration duration;
    private String category;

    public CompetitionFormEntity() {
        //empty from framework
    }

    public CompetitionFormEntity(CompetitionForm competitionForm) {
        BeanUtils.copyProperties(competitionForm, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CompetitionFormEntity{" +
                "id='" + id + '\'' +
                ", score=" + score +
                ", duration=" + duration +
                ", category='" + category + '\'' +
                '}';
    }
}
