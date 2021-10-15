package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelGuestPersistence {
    HotelGuest create(HotelGuest hotelGuest);

    HotelGuest readByDni(String dni);

    void delete(String dni);
}
