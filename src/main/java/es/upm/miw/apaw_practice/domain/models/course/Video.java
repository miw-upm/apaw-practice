package es.upm.miw.apaw_practice.domain.models.course;

import java.time.LocalDateTime;

public class Video {

    private String name;
    private String duration;
    private LocalDateTime creationDate;
    private Course course;

    public Video() {
        //empty for framework
    }

    public Video(String name, String duration, LocalDateTime creationDate, Course course) {
        this.name = name;
        this.duration = duration;
        this.creationDate = creationDate;
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", creationDate=" + creationDate +
                ", course=" + course +
                '}';
    }
}
