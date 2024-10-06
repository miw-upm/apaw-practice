package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {

    private String id;
    private Boolean confirmed;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private Guest guest;

    public Booking() {
        // empty for framework
    }

    public Booking(String id, Boolean confirmed,  LocalDate dateIn, LocalDate dateOut, Guest guest) {
        this.id = id;
        this.confirmed = confirmed;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.guest = guest;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDateIn() {
        return dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    public LocalDate getDateOut() {
        return dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(confirmed, booking.confirmed) && Objects.equals(dateIn, booking.dateIn) && Objects.equals(dateOut, booking.dateOut) && Objects.equals(guest, booking.guest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, confirmed, dateIn, dateOut, guest);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", confirmed=" + confirmed +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", guest=" + guest +
                '}';
    }
}
