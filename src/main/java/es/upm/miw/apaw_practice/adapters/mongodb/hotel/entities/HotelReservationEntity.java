package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDate;
import java.util.UUID;

public class HotelReservationEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String reservationNumber;
    private String roomNumber;
    private LocalDate reservationDate;
    @DBRef
    private HotelClientEntity client;

    public HotelReservationEntity(){

    }

    public HotelReservationEntity(String reservationNumber, String roomNumber, LocalDate reservationDate, HotelClientEntity client){
        this.reservationNumber = reservationNumber;
        this.roomNumber = roomNumber;
        this.reservationDate = reservationDate;
        this.client = client;
        this.id = UUID.randomUUID().toString();
    }

    public String getReservationNumber() { return this.reservationNumber; }

    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber;}

    public String getRoomNumber() { return this.roomNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public LocalDate getReservationDate() { return this.reservationDate; }

    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }

    public HotelClientEntity getClient() { return this.client; }

    public void setClient(final HotelClientEntity client) { this.client = client; }

    public HotelReservation toReservation() {
        HotelReservation reservation = new HotelReservation();
        HotelClient client = this.client.toClient();
        BeanUtils.copyProperties(this, reservation, "client");
        reservation.setClient(client);
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
