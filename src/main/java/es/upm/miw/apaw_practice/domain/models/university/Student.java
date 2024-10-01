package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;
import java.util.List;

public class Student {
    String email;
    String firstName;
    String placeOfBirth;
    LocalDate enrollmentDate;
    List<Degree> degreesStudied;

    public Student() {
        //empty for framework
    }

    public Student(String email, String firstName, String placeOfBirth, LocalDate enrollmentDate, List<Degree> degreesStudied) {
        this.email = email;
        this.firstName = firstName;
        this.placeOfBirth = placeOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.degreesStudied = degreesStudied;
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

    public List<Degree> getDegreesStudied() {
        return degreesStudied;
    }

    public void setDegreesStudied(List<Degree> degreesStudied) {
        this.degreesStudied = degreesStudied;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", degreesStudied=" + degreesStudied +
                '}';
    }
}
