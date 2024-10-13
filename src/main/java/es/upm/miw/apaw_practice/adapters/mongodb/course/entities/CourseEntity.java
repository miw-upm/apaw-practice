package es.upm.miw.apaw_practice.adapters.mongodb.course.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class CourseEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private Boolean paymentRequired;
    private LocalDate startDate;
    private LocalDate endDate;
    @DBRef
    private List<TutoringSessionEntity> tutoringSessions;
    private List<UserEntity> users;
    private List<VideoEntity> videos;

    public CourseEntity() {
        //Empty for framework
    }

    public CourseEntity(String title, Boolean paymentRequired, LocalDate startDate, LocalDate endDate, List<TutoringSessionEntity> tutoringSessions, List<UserEntity> users, List<VideoEntity> videos) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.paymentRequired = paymentRequired;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tutoringSessions = tutoringSessions;
        this.users = users;
        this.videos = videos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getPaymentRequired() {
        return paymentRequired;
    }

    public void setPaymentRequired(Boolean paymentRequired) {
        this.paymentRequired = paymentRequired;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<TutoringSessionEntity> getTutoringSessions() {
        return tutoringSessions;
    }

    public void setTutoringSessions(List<TutoringSessionEntity> tutoringSessions) {
        this.tutoringSessions = tutoringSessions;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<VideoEntity> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoEntity> videos) {
        this.videos = videos;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (title.equals(((CourseEntity) obj).title));
    }

    @Override
    public int hashCode() {
        return this.title.hashCode();
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "title='" + title + '\'' +
                ", paymentRequired=" + paymentRequired +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tutoringSessions=" + tutoringSessions +
                ", users=" + users +
                ", videos=" + videos +
                '}';
    }
}
