package es.upm.miw.apaw_practice.domain.models.hotel;
import java.time.LocalDate;

public class PatchReservationRequest {
    private String roomNumber;
    private LocalDate reservationDate;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}
