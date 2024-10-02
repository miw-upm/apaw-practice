package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDateTime;
import java.util.Objects;

public class Guest {

    private String nif;
    private String name;
    private String surname;
    private LocalDateTime birthDay;

    public Guest() {
        // empty for framework
    }

    public Guest(String nif, String name, String surname, LocalDateTime birthDay) {
        this.nif = nif;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(nif, guest.nif) && Objects.equals(name, guest.name) && Objects.equals(surname, guest.surname) && Objects.equals(birthDay, guest.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, name, surname, birthDay);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "nif='" + nif + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
