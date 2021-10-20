package es.upm.miw.apaw_practice.domain.models.videogame;

public class Critic {

    private Boolean mustPlay;
    private Integer expertScore;
    private Double userScore;

    public Critic() {
        //empty for framework
    }

    public Critic(Boolean mustPlay, Integer expertScore, Double userScore) {
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

    @Override
    public String toString() {
        return "Critic{" +
                "mustPlay=" + mustPlay +
                ", expertScore=" + expertScore +
                ", userScore=" + userScore +
                '}';
    }
}
