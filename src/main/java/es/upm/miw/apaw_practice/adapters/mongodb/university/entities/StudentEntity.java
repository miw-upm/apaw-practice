package es.upm.miw.apaw_practice.adapters.mongodb.university.entities;

import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class StudentEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String firstName;
    private String placeOfBirth;
    private LocalDate enrollmentDate;
    @DBRef
    private List<DegreeEntity> degreesEnrolled;

    public StudentEntity() {
        //empty for framework
    }

    public StudentEntity(Student student) {
        this.fromStudent(student);
        this.id = UUID.randomUUID().toString();
    }

    public void fromStudent(Student student) {
        BeanUtils.copyProperties(student, this);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public List<DegreeEntity> getDegreesEnrolled() {
        return degreesEnrolled;
    }

    public void setDegreesEnrolled(List<DegreeEntity> degreesEnrolled) {
        this.degreesEnrolled = degreesEnrolled;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", degreesEnrolled=" + degreesEnrolled +
                '}';
    }
}
