package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Guest {

    private String nif;
    private String fullName;
    private LocalDateTime birthDay;
    private List<Booking> bookings;

    public Guest() {
        // empty for framework
    }

    public Guest(String nif, String fullName, LocalDateTime birthDay) {
        this.nif = nif;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.bookings = new ArrayList<>();
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(nif, guest.nif) && Objects.equals(fullName, guest.fullName) && Objects.equals(birthDay, guest.birthDay) && Objects.equals(bookings, guest.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, fullName, birthDay, bookings);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "nif='" + nif + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", bookings=" + bookings +
                '}';
    }
}
