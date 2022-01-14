package es.upm.miw.apaw_practice.domain.models.airport;

import java.time.LocalDate;

public class Passenger {
    private String passportId;
    private String name;
    private String surname;
    private LocalDate birthDate;

    public Passenger() {
        // empty for framework
    }

    public Passenger(String passportId, String name, String surname, LocalDate birthDate) {
        this.passportId = passportId;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passportId='" + this.passportId + '\'' +
                ", name='" + this.name + '\'' +
                ", surname='" + this.surname + '\'' +
                ", birthDate=" + this.birthDate +
                '}';
    }
}
