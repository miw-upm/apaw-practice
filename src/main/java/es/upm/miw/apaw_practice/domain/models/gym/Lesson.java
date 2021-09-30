package es.upm.miw.apaw_practice.domain.models.gym;


import java.time.LocalDateTime;
import java.util.List;

public class Lesson {
    private String lessonName ;
    private LocalDateTime lessonTime;
    private String lessonDescription ;
    private Boolean lessonFinished ;
    private List<Athlete> athletes ;


    public Lesson(){
        //empty for framework
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

    public Boolean getLessonFinished() {
        return lessonFinished;
    }

    public void setLessonFinished(Boolean lessonFinished) {
        this.lessonFinished = lessonFinished;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes) {
        this.athletes = athletes;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "lessonName='" + lessonName + '\'' +
                ", lessonTime=" + lessonTime +
                ", lessonDescription='" + lessonDescription + '\'' +
                ", lessonFinished=" + lessonFinished +
                ", athletes=" + athletes +
                '}';
    }
}
