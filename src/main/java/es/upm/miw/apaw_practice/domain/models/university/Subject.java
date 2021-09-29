package es.upm.miw.apaw_practice.domain.models.university;

import java.util.List;

public class Subject {

    private String subjectName;
    private Integer subjectCode;
    private Integer credits;
    private Classroom classroom;
    private List<Student> students;

    public Subject() {
        //empty for framework
    }

    public Subject(String subjectName, Integer subjectCode, Integer credits, Classroom classroom, List<Student> students) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.credits = credits;
        this.classroom = classroom;
        this.students = students;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(Integer subjectCode) {
        this.subjectCode = subjectCode;
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
