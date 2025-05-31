package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface HotelReservationPersistence {

    HotelReservation patchReservation(String reservationNumber, HotelReservation reservation);

    BigDecimal findSumTotalPriceByReservationDate(LocalDate date);
}
