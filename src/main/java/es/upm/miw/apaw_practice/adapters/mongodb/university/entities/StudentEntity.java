package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class StudentEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String lastName;
    private Boolean internationalStudent;
    private List<SubjectEntity> subjects;

    public StudentEntity(){
        //empty for framework
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
