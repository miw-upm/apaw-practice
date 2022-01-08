package es.upm.miw.apaw_practice.domain.models.training;

import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.ParticipantEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.TrainingItemEntity;

import java.time.LocalDate;
import java.util.List;

public class Course {
    private List<ParticipantEntity> participantEntities;
    private List<TrainingItemEntity> trainingItemEntities;
    private String id;
    private Integer courseYear;
    private String courseLevel;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Course() {
        //empty from framework
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
        return "Course{" +
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
