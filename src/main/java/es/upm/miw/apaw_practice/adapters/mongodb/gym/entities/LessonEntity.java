package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class LessonEntity {
    @Id
    private String id ;
    private String lessonName ;
    private LocalDateTime lessonTime;
    private String lessonDescription ;
    private Boolean lessonFinished ;
    @DBRef
    private List<AthleteEntity> athlete ;

    public LessonEntity(){
        //empty for framework
    }

    public LessonEntity(String id, String lessonName, LocalDateTime lessonTime, String lessonDescription, Boolean lessonFinished, List<AthleteEntity> athlete) {
        this.id = UUID.randomUUID().toString();
        this.lessonName = lessonName;
        this.lessonTime = lessonTime;
        this.lessonDescription = lessonDescription;
        this.lessonFinished = lessonFinished;
        this.athlete = athlete;
    }

    public String getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public LocalDateTime getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LocalDateTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public Boolean isLessonFinished() {
        return lessonFinished;
    }

    public void setLessonFinished(Boolean lessonFinished) {
        this.lessonFinished = lessonFinished;
    }

    public List<AthleteEntity> getAthlete() {
        return athlete;
    }

    public void setAthlete(List<AthleteEntity> athlete) {
        this.athlete = athlete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonEntity that = (LessonEntity) o;
        return id.equals(that.id) && lessonName.equals(that.lessonName) && Objects.equals(lessonTime, that.lessonTime) && Objects.equals(lessonDescription, that.lessonDescription) && Objects.equals(lessonFinished, that.lessonFinished) && Objects.equals(athlete, that.athlete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonName, lessonTime, lessonDescription, lessonFinished, athlete);
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id='" + id + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", lessonTime=" + lessonTime +
                ", lessonDescription='" + lessonDescription + '\'' +
                ", lessonFinished=" + lessonFinished +
                ", athlete=" + athlete +
                '}';
    }
}

