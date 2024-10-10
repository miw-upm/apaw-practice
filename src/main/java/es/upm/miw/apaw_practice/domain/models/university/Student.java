package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;
import java.util.List;

public class Student {
    private String email;
    private String firstName;
    private String placeOfBirth;
    private LocalDate enrollmentDate;
    private List<Degree> enrolledDegrees;

    public Student() {
        //empty for framework
    }

    public Student(String email, String firstName, String placeOfBirth, LocalDate enrollmentDate, List<Degree> enrolledDegrees) {
        this.email = email;
        this.firstName = firstName;
        this.placeOfBirth = placeOfBirth;
        this.enrollmentDate = enrollmentDate;
        this.enrolledDegrees = enrolledDegrees;
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

    public List<Degree> getEnrolledDegrees() {
        return enrolledDegrees;
    }

    public void setEnrolledDegrees(List<Degree> enrolledDegrees) {
        this.enrolledDegrees = enrolledDegrees;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (email.equals(((Student) obj).email));
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", enrolledDegrees=" + enrolledDegrees +
                '}';
    }
}
