package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import java.util.List;

public class StudentEntity {

    private String name;
    private String lastName;
    private Boolean internationalStudent;
    private List<SubjectEntity> subjects;

    public StudentEntity(){
        //empty for framework
    }

    public StudentEntity(String name, String lastName, Boolean internationalStudent, List<SubjectEntity> subjects) {
        this.name = name;
        this.lastName = lastName;
        this.internationalStudent = internationalStudent;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getInternationalStudent() {
        return internationalStudent;
    }

    public void setInternationalStudent(Boolean internationalStudent) {
        this.internationalStudent = internationalStudent;
    }

    public List<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        this.subjects = subjects;
    }
}
