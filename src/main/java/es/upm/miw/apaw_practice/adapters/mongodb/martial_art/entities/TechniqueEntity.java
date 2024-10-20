package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities;


import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.RoomEntity;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import es.upm.miw.apaw_practice.domain.models.martial_art.Style;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TechniqueEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private Integer duration;
    private Boolean advanced;
    private Double difficulty;
    @DBRef
    private List<InstructorEntity> instructors;
    @DBRef
    private StyleEntity style;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getAdvanced() {
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

    public List<InstructorEntity> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorEntity> instructors) {
        this.instructors = instructors;
    }

    public StyleEntity getStyle() {
        return style;
    }

    public void setStyle(StyleEntity style) {
        this.style = style;
    }
    public TechniqueEntity(String name, Integer duration, Boolean advanced, Double difficulty, List<InstructorEntity> instructors, StyleEntity style) {
        this.name = name;
        this.duration = duration;
        this.advanced = advanced;
        this.difficulty = difficulty;
        this.instructors = instructors;
        this.style = style;
    }
    public Technique toTechnique() {
       Technique technique = new Technique();
       BeanUtils.copyProperties(this, technique);
       technique.setStyle(this.style.toStyle());
       List<Instructor> instructors = this.instructors.stream()
               .map(InstructorEntity::toInstructor)
               .collect(Collectors.toList());
       technique.setInstructors((List<Instructor>) instructors);
       return technique;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechniqueEntity that = (TechniqueEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(duration, that.duration) && Objects.equals(advanced, that.advanced) && Objects.equals(difficulty, that.difficulty) && Objects.equals(instructors,
                that.instructors) && Objects.equals(style, that.style);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, duration, advanced, difficulty, instructors, style);
    }
    @Override
    public String toString() {
        return "TechniqueEntity{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", advanced=" + advanced +
                ", difficulty=" + difficulty +
                ", Instructor=" + instructors +
                ", style=" + style +
                '}';
    }
}
