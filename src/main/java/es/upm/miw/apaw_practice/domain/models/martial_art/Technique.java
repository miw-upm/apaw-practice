package es.upm.miw.apaw_practice.domain.models.martial_art;

import java.util.ArrayList;
import java.util.List;

public class Technique {
    private String name;
    private Integer duration;
    private Boolean advanced;
    private Double difficulty;
    private List<Instructor> instructors;
    private Style style;

    public Technique() {
        // empty for framework
    }

    public Technique(String name, Integer duration, Boolean advanced, Double difficulty, Style style) {
        this.name = name;
        this.duration = duration;
        this.advanced = advanced;
        this.difficulty = difficulty;
        this.style = style;
        this.instructors = new ArrayList<>();
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
        return advanced;
    }

    public void setAdvanced(Boolean advanced) {
        this.advanced = advanced;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }


    public List<Instructor> getInstructors() { return instructors; }
    public void setInstructors(Instructor instructor) { this.instructors.add(instructor); }

    public Style getStyle() { return style; }
    public void setStyle(Style style) { this.style = style; }

    @Override
    public String toString() {
        return "Technique{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", advanced=" + advanced +
                ", difficulty=" + difficulty +
                '}';
    }
}
