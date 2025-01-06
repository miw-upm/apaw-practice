package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;


import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.UUID;

public class HotelReservationEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String reservationNumber;
    private String roomNumber;
    private LocalDate reservationDate;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public HotelReservationEntity(){}

    public HotelReservationEntity(String reservationNumber, String roomNumber, LocalDate reservationDate){
        this.reservationNumber = reservationNumber;
        this.roomNumber = roomNumber;
        this.reservationDate = reservationDate;
        this.id = UUID.randomUUID().toString();
    }

    public String getReservationNumber() { return this.reservationNumber; }

    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber;}

    public String getRoomNumber() { return this.roomNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public LocalDate getReservationDate() { return this.reservationDate; }

    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }


    public HotelReservation toReservation() {
        HotelReservation reservation = new HotelReservation();
        BeanUtils.copyProperties(this, reservation, "id");
        reservation.setId(this.id);
        return reservation;
    }

    @Override
    public String toString() {
        return "HotelReservation{" +
                "reservationNumber='" + reservationNumber + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", reservationDate=" + reservationDate +
                '}';
    }

}
