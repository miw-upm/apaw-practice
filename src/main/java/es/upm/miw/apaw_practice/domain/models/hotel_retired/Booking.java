package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Booking {

    private Boolean confirmed;
    private LocalDateTime creationDate;
    private Date dateIn;
    private Date dateOut;

    public Booking() {
        // empty for framework
    }

    public Booking(Boolean confirmed, LocalDateTime creationDate, Date dateIn, Date dateOut) {
        this.confirmed = confirmed;
        this.creationDate = creationDate;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(confirmed, booking.confirmed) && Objects.equals(creationDate, booking.creationDate) && Objects.equals(dateIn, booking.dateIn) && Objects.equals(dateOut, booking.dateOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmed, creationDate, dateIn, dateOut);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "confirmed=" + confirmed +
                ", creationDate=" + creationDate +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                '}';
    }
}
