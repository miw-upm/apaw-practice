package es.upm.miw.apaw_practice.domain.models.martial_art;

public class Technique {
    private String name;
    private Integer duration;
    private Boolean isAdvanced;
    private Double difficulty;

    public Technique() {
        // empty for framework
    }

    public Technique(String name, Integer duration, Boolean isAdvanced, Double difficulty) {
        this.name = name;
        this.duration = duration;
        this.isAdvanced = isAdvanced;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getIsAdvanced() {
        return isAdvanced;
    }

    public void setIsAdvanced(Boolean isAdvanced) {
        this.isAdvanced = isAdvanced;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Technique{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", isAdvanced=" + isAdvanced +
                ", difficulty=" + difficulty +
                '}';
    }
}
