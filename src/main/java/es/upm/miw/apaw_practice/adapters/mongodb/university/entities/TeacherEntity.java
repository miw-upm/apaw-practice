package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class TeacherEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nationalId;
    private LocalDate birthDate;
    private String lastName;
    @DBRef
    private UniversityEntity universityEntity;

    public TeacherEntity() {
        //empty for framework
    }

    public TeacherEntity(Teacher teacher) {
        this.fromTeacher(teacher);
        this.id = UUID.randomUUID().toString();
    }

    public void fromTeacher(Teacher teacher) {
        BeanUtils.copyProperties(teacher, this);
    }

    public Teacher toTeacher() {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(this, teacher);
        return teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UniversityEntity getUniversityEntity() {
        return universityEntity;
    }

    public void setUniversityEntity(UniversityEntity universityEntity) {
        this.universityEntity = universityEntity;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id='" + id + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", birthDate=" + birthDate +
                ", lastName='" + lastName + '\'' +
                ", universityEntity=" + universityEntity +
                '}';
    }
}
