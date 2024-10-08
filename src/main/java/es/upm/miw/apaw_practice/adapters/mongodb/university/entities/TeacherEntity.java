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
    private UniversityEntity workplace;

    public TeacherEntity() {
        //empty for framework
    }

    public TeacherEntity(String nationalId, LocalDate birthDate, String lastName, UniversityEntity workplace) {
        this.id = UUID.randomUUID().toString();
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.lastName = lastName;
        this.workplace = workplace;
    }

    public TeacherEntity(Teacher teacher) {
        this.fromTeacher(teacher);
        this.id = UUID.randomUUID().toString();
    }

    public void fromTeacher(Teacher teacher) {
        BeanUtils.copyProperties(teacher, this);
        this.workplace = new UniversityEntity(teacher.getWorkplace());
    }

    public Teacher toTeacher() {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(this, teacher);
        if (workplace != null) {
            teacher.setWorkplace(workplace.toUniversity());
        }
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

    public UniversityEntity getWorkplace() {
        return workplace;
    }

    public void setWorkplace(UniversityEntity workplace) {
        this.workplace = workplace;
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id='" + id + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", birthDate=" + birthDate +
                ", lastName='" + lastName + '\'' +
                ", workplace=" + workplace +
                '}';
    }
}
