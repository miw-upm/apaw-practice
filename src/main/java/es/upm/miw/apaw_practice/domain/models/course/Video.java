package es.upm.miw.apaw_practice.domain.models.course;

import java.time.LocalDateTime;

public class Video {

    private String name;
    private String duration;
    private LocalDateTime dateCreation;
    private Course course;

    public Video() {
        //empty for framework
    }

    public Video(String name, String duration, LocalDateTime dateCreation, Course course) {
        this.name = name;
        this.duration = duration;
        this.dateCreation = dateCreation;
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

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
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
                ", dateCreation=" + dateCreation +
                ", course=" + course +
                '}';
    }
}
