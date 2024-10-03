package es.upm.miw.apaw_practice.domain.models.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private Map<String, Course> listCourses;
    private List<TutoringSession> listTutoringSession;

    public Student() {
        //empty for framework
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.listCourses = new HashMap<>();
        this.listTutoringSession = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(Course course){
        this.listCourses.put(course.getId(),course);
    }

    public Course getCourse(String idCourse){
        return this.listCourses.get(idCourse);
    }

    public int getSizeCourses(){
        return this.listCourses.size();
    }

    public void setTutoringSession(TutoringSession tutoringSession){
        this.listTutoringSession.add(tutoringSession);
    }

    public TutoringSession getTutoringSession(int id){
        return this.listTutoringSession.get(id);
    }

    public int getSizeTutoringSession(){
        return this.listTutoringSession.size();
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", listCourses=" + listCourses +
                ", listTutoringSession=" + listTutoringSession +
                '}';
    }
}
