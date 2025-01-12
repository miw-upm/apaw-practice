package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HotelReservationPersistence {

    HotelReservation patchReservation(String reservationNumber, String roomNumber, LocalDate reservationDate, HotelClient client);

}
