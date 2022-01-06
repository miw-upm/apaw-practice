package es.upm.miw.apaw_practice.adapters.mongodb.training.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
public class CourseEntity {
    @DBRef
    private List<ParticipantEntity> participantEntities;
    @DBRef
    private List<TrainingItemEntity> trainingItemEntities;
    @Id
    private String id;
    private Integer courseYear;
    private String courseLevel;
    private LocalDate startDate;
    private LocalDate finishDate;

    public CourseEntity() {
        //empty for framework
    }

    public CourseEntity(List<ParticipantEntity> participantEntities, List<TrainingItemEntity> trainingItemEntities, Integer courseYear, String courseLevel, LocalDate startDate, LocalDate finishDate) {
        this.participantEntities = participantEntities;
        this.trainingItemEntities = trainingItemEntities;
        this.courseYear = courseYear;
        this.courseLevel = courseLevel;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public List<ParticipantEntity> getParticipantEntities() {
        return participantEntities;
    }

    public void setParticipantEntities(List<ParticipantEntity> participantEntities) {
        this.participantEntities = participantEntities;
    }

    public List<TrainingItemEntity> getTrainingItemEntities() {
        return trainingItemEntities;
    }

    public void setTrainingItemEntities(List<TrainingItemEntity> trainingItemEntities) {
        this.trainingItemEntities = trainingItemEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Integer courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "participantEntities=" + participantEntities +
                ", trainingItemEntities=" + trainingItemEntities +
                ", id='" + id + '\'' +
                ", courseYear=" + courseYear +
                ", courseLevel='" + courseLevel + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
