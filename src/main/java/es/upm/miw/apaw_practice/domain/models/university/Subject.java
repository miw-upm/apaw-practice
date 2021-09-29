package es.upm.miw.apaw_practice.domain.models.university;

import java.util.List;

public class Subject {

    private String name;
    private Integer code;
    private Integer credits;
    private Classroom classroom;
    private List<Student> students;

    public Subject() {
        //empty for framework
    }

    public Subject(String name, Integer code, Integer credits, Classroom classroom, List<Student> students) {
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.classroom = classroom;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
