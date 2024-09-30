package es.upm.miw.apaw_practice.domain.models.university;

import java.time.LocalDate;

public class Teacher {
    private String nationalId;
    private LocalDate birthDate;
    private String lastName;
    private University workPlace;

    public Teacher() {
        //empty for framework
    }

    public Teacher(University workPlace, String lastName, LocalDate birthDate, String nationalId) {
        this.workPlace = workPlace;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationalId = nationalId;
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

    public University getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(University workPlace) {
        this.workPlace = workPlace;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "nationalId='" + nationalId + '\'' +
                ", birthDate=" + birthDate +
                ", lastName='" + lastName + '\'' +
                ", workPlace=" + workPlace +
                '}';
    }
}
