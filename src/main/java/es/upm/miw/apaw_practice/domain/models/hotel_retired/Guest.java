package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDateTime;
import java.util.Objects;

public class Guest {

    private String nif;
    private String fullName;
    private LocalDateTime birthDay;

    public Guest() {
        // empty for framework
    }

    public Guest(String nif, String fullName, LocalDateTime birthDay) {
        this.nif = nif;
        this.fullName = fullName;
        this.birthDay = birthDay;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        return Objects.equals(nif, guest.nif) && Objects.equals(fullName, guest.fullName) && Objects.equals(birthDay, guest.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, fullName, birthDay);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "nif='" + nif + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
