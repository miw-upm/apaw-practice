package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorPersistence {
    List<Director> readEmails();

    List<Hotel> getHotelsByDirector(String dni);

    List<HotelGuest> findHotelGuestDistinctDni(String dni);
}
