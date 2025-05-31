package es.upm.miw.apaw_practice.domain.services.hotel;


import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelReservationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class HotelReservationService {
    private final HotelReservationPersistence hotelReservationPersistence;

    @Autowired
    public HotelReservationService (HotelReservationPersistence hotelReservationPersistence) {
        this.hotelReservationPersistence = hotelReservationPersistence;
    }

    public HotelReservation patchReservation(String reservationNumber, HotelReservation reservation){
        return this.hotelReservationPersistence.patchReservation(reservationNumber, reservation); }

    public BigDecimal findSumTotalPriceByReservationDate(LocalDate date){
        return this.hotelReservationPersistence.findSumTotalPriceByReservationDate(date);
    }
}
