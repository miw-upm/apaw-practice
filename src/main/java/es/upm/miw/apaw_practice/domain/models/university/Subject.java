package es.upm.miw.apaw_practice.domain.models.university;

import java.util.List;

public class Subject {

    private String topic;
    private Integer reference;
    private Integer credits;
    private Classroom classroom;
    private List<Student> students;

    public Subject() {
        //empty for framework
    }

    public Subject(String topic, Integer reference, Integer credits, Classroom classroom, List<Student> students) {
        this.topic = topic;
        this.reference = reference;
        this.credits = credits;
        this.classroom = classroom;
        this.students = students;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
