package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public class StudentEntity {

    @Id
    private String id;
    private String dni;
    private String fullName;
    private Boolean internationalStudent;
    private List<SubjectEntity> subjects;

    public StudentEntity(){
        //empty for framework
    }

    public StudentEntity(Student student) {
        this(student, null);
    }

    public StudentEntity(Student student, List<SubjectEntity> subjects) {
        BeanUtils.copyProperties(student, this);
        this.subjects = subjects;
        this.id = UUID.randomUUID().toString();
    }

    public Student toStudent() {
        Student student = new Student();
        BeanUtils.copyProperties(this, student);
        return student;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
