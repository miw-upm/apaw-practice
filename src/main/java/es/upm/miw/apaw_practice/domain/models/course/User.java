package es.upm.miw.apaw_practice.domain.models.course;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private TypeUser role;
    private List<Course> courses;

    public User() {
        //empty for framework
    }

    public User(String firstName, String lastName, TypeUser role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.courses = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TypeUser getRole() {
        return role;
    }

    public void setRole(TypeUser role) {
        this.role = role;
    }

    public void setCourse(Course course) {
        this.courses.add(course);
    }

    public Course getCourse(int idCourse) {
        return this.courses.get(idCourse);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", courses=" + courses +
                '}';
    }

    public enum TypeUser {
        STUDENT,
        STUDENTUTOR
    }
}
