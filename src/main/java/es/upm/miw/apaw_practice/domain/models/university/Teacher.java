package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;

public class Teacher {
    private String nationalId;
    private LocalDate birthDate;
    private String lastName;
    private University university;

    public Teacher() {
        //empty for framework
    }

    public Teacher(String nationalId, LocalDate birthDate, String lastName, University university) {
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.lastName = lastName;
        this.university = university;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (nationalId.equals(((Teacher) obj).nationalId));
    }

    @Override
    public int hashCode() {
        return nationalId.hashCode();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "nationalId='" + nationalId + '\'' +
                ", birthDate=" + birthDate +
                ", lastName='" + lastName + '\'' +
                ", workPlace=" + university +
                '}';
    }
}
