package es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities;

import es.upm.miw.apaw_practice.domain.models.videogame.Critic;
import org.springframework.beans.BeanUtils;

public class CriticEntity {

    private Boolean mustPlay;
    private Integer expertScore;
    private Double userScore;

    public CriticEntity() {
        //empty for framework
    }

    public CriticEntity(Boolean mustPlay, Integer expertScore, Double userScore) {
        this.mustPlay = mustPlay;
        this.expertScore = expertScore;
        this.userScore = userScore;
    }

    public Boolean getMustPlay() {
        return mustPlay;
    }

    public void setMustPlay(Boolean mustPlay) {
        this.mustPlay = mustPlay;
    }

    public Integer getExpertScore() {
        return expertScore;
    }

    public void setExpertScore(Integer expertScore) {
        this.expertScore = expertScore;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public Critic toCritic() {
        Critic critic = new Critic();
        BeanUtils.copyProperties(this, critic);
        return critic;
    }

    @Override
    public String toString() {
        return "CriticEntity{" +
                "mustPlay=" + mustPlay +
                ", expertScore=" + expertScore +
                ", userScore=" + userScore +
                '}';
    }
}
