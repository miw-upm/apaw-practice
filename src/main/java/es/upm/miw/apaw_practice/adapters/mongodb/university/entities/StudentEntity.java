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
    private List<DegreeEntity> enrolledDegrees;

    public StudentEntity() {
        //empty for framework
    }

    public StudentEntity(String email, String firstName, String placeOfBirth, LocalDate enrollmentDate, List<DegreeEntity> enrolledDegrees) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.firstName = firstName;
        this.placeOfBirth = placeOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.enrolledDegrees = enrolledDegrees;
    }

    public StudentEntity(Student student) {
        this.fromStudent(student);
        this.id = UUID.randomUUID().toString();
    }

    public void fromStudent(Student student) {
        BeanUtils.copyProperties(student, this);
        this.enrolledDegrees = student.getEnrolledDegrees().stream().map(DegreeEntity::new).toList();
    }

    public Student toStudent() {
        Student student = new Student();
        BeanUtils.copyProperties(this, student);
        student.setEnrolledDegrees(this.enrolledDegrees.stream().map(DegreeEntity::toDegree).toList());
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

    public List<DegreeEntity> getEnrolledDegrees() {
        return enrolledDegrees;
    }

    public void setEnrolledDegrees(List<DegreeEntity> enrolledDegrees) {
        this.enrolledDegrees = enrolledDegrees;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", degreesEnrolled=" + enrolledDegrees +
                '}';
    }
}
