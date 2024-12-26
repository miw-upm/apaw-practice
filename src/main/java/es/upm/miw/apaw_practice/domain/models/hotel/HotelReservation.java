package es.upm.miw.apaw_practice.domain.models.hotel;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class HotelReservation {
    private String reservationNumber;
    private String roomNumber;
    private LocalDate reservationDate;

    public HotelReservation(){}

    public HotelReservation(String reservationNumber, String roomNumber, LocalDate reservationDate){
        this.reservationNumber = reservationNumber;
        this.roomNumber = roomNumber;
        this.reservationDate = reservationDate;
    }

    public String getReservationNumber() { return reservationNumber; }

    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber;}

    public String getRoomNumber() { return roomNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public LocalDate getReservationDate() { return reservationDate; }

    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

    public HotelReservationEntity toReservationEntity() {
        HotelReservationEntity reservationEntity = new HotelReservationEntity();
        BeanUtils.copyProperties(this, reservationEntity);
        return reservationEntity;
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
