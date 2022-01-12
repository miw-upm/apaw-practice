package es.upm.miw.apaw_practice.domain.models.training;

import java.util.List;
import java.util.stream.Collectors;

public class Participant {
    private String name;
    private Boolean graduate;
    private Integer phone;
    private String email;
    private List<Course> courses;

    public Participant() {
        //empty from framework
    }

    public Participant(String name, Boolean graduate, Integer phone, String email, List<Course> courses) {
        this.name = name;
        this.graduate = graduate;
        this.phone = phone;
        this.email = email;
        this.courses = courses;
    }

    public static Participant ofCourseIdentity(Participant participant) {
        participant.setCourses(
                participant.courses.stream()
                        .map(Course::ofIdentity)
                        .collect(Collectors.toList())
        );
        return participant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGraduate() {
        return graduate;
    }

    public void setGraduate(Boolean graduate) {
        this.graduate = graduate;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", graduate=" + graduate +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                '}';
    }
}
