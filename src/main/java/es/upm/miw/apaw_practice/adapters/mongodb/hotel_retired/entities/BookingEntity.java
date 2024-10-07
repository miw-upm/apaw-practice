package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class BookingEntity {
    @Id
    private String id;
    private boolean confirmed;
    private LocalDate dateIn;
    private LocalDate dateOut;
    @DBRef
    private GuestEntity guestEntity;

    public BookingEntity() {
        // empty for framework
    }

    public BookingEntity(boolean confirmed, LocalDate dateIn, LocalDate dateOut, GuestEntity guestEntity) {
        this.id = UUID.randomUUID().toString();
        this.confirmed = confirmed;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.guestEntity = guestEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
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

    public GuestEntity getGuestEntity() {
        return guestEntity;
    }

    public void setGuestEntity(GuestEntity guestEntity) {
        this.guestEntity = guestEntity;
    }

    public void fromBooking(Booking booking) {
        BeanUtils.copyProperties(booking, this);
    }

    public Booking toBooking() {
        Booking booking = new Booking();
        BeanUtils.copyProperties(this, booking);
        booking.setGuest(this.guestEntity.toGuest());
        return booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return confirmed == that.confirmed && Objects.equals(id, that.id) && Objects.equals(dateIn, that.dateIn) && Objects.equals(dateOut, that.dateOut) && Objects.equals(guestEntity, that.guestEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, confirmed, dateIn, dateOut, guestEntity);
    }

    @Override
    public String toString() {
        return "BookingEntity{" +
                "id='" + id + '\'' +
                ", confirmed=" + confirmed +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", guestEntity=" + guestEntity +
                '}';
    }
}
