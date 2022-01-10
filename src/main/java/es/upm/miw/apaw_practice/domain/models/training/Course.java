package es.upm.miw.apaw_practice.domain.models.training;

import java.time.LocalDate;
import java.util.List;

public class Course {
    private List<Participant> participants;
    private List<TrainingItem> trainingItems;
    private String id;
    private Integer courseYear;
    private String courseLevel;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Course() {
        //empty from framework
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<TrainingItem> getTrainingItems() {
        return trainingItems;
    }

    public void setTrainingItems(List<TrainingItem> trainingItems) {
        this.trainingItems = trainingItems;
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
                "participants=" + participants +
                ", trainingItems=" + trainingItems +
                ", id='" + id + '\'' +
                ", courseYear=" + courseYear +
                ", courseLevel='" + courseLevel + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
