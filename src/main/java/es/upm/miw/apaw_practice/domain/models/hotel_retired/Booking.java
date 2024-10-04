package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {

    private int id;
    private Boolean confirmed;
    private LocalDate dateIn;
    private LocalDate dateOut;

    public Booking() {
        // empty for framework
    }

    public Booking(int id, Boolean confirmed,  LocalDate dateIn, LocalDate dateOut) {
        this.id = id;
        this.confirmed = confirmed;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(confirmed, booking.confirmed) && Objects.equals(dateIn, booking.dateIn) && Objects.equals(dateOut, booking.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, confirmed, dateIn, dateOut);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", confirmed=" + confirmed +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                '}';
    }
}
