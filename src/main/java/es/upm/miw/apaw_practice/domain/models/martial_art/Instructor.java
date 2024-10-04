package es.upm.miw.apaw_practice.domain.models.martial_art;

import java.time.LocalDate;

public class Instructor {
    private String id;
    private String fullName;
    private Integer phoneNumber;
    private LocalDate birthDate;

    public Instructor() {
        // empty for framework
    }

    public Instructor(String id, String fullName, Integer phoneNumber, LocalDate birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", birthDate=" + birthDate +
                '}';
    }
}
