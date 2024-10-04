package es.upm.miw.apaw_practice.domain.models.course;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Video {

    private String name;
    private String duration;
    private LocalDateTime dateCreation;
    private List<Course> courses;

    public Video() {
        //empty for framework
    }

    public Video(String name, String duration, LocalDateTime dateCreation) {
        this.name = name;
        this.duration = duration;
        this.dateCreation = dateCreation;
        this.courses = new ArrayList<>();
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

    public void setCourse(Course course) {
        this.courses.add(course);
    }

    public Course getCourse(int idCourse) {
        return this.courses.get(idCourse);
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", dateCreation=" + dateCreation +
                ", courses=" + courses +
                '}';
    }
}
