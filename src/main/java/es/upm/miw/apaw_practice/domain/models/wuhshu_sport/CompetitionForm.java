package es.upm.miw.apaw_practice.domain.models.wuhshu_sport;

import java.time.Duration;
import java.util.Objects;

public class CompetitionForm {

    private Double score;
    private Duration duration;
    private String category;

    public CompetitionForm() {
        //empty for framework
    }

    public static CompetitionFormBuilders.Score builder() {
        return new Builder();
    }

    public CompetitionForm(Double score, Duration duration, String category) {
        this.score = score;
        this.duration = duration;
        this.category = category;
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
        return "CompetitionForm{" +
                "score=" + score +
                ", duration=" + duration +
                ", category='" + category + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionForm that = (CompetitionForm) o;
        return Objects.equals(score, that.score) && Objects.equals(duration, that.duration) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, duration, category);
    }

    public static class Builder implements CompetitionFormBuilders.Score, CompetitionFormBuilders.Category,CompetitionFormBuilders.Duration,CompetitionFormBuilders.Optionals {

        private final CompetitionForm competitionForm;

        public Builder() {
            this.competitionForm = new CompetitionForm();
        }

        @Override
        public CompetitionFormBuilders.Duration score(double score){
            this.competitionForm.score = score;
            return this;
        }

        @Override
        public CompetitionFormBuilders.Category duration(java.time.Duration duration){
            this.competitionForm.duration = duration;
            return this;
        }
        @Override
        public CompetitionFormBuilders.Optionals category(String category){
            this.competitionForm.category = category;
            return this;
        }
        @Override
        public CompetitionForm build() {
            return this.competitionForm;
        }
    }
}
