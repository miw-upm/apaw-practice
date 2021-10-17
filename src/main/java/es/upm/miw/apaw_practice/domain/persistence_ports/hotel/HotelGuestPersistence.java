package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelGuestPersistence {
    HotelGuest create(HotelGuest hotelGuest);

    HotelGuest readByDni(String dni);

    void delete(String dni);

}
