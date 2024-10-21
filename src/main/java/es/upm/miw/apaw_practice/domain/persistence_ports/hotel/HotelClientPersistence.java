package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelReservation;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelClientPersistence {

    HotelClient create(HotelClient client);

    boolean existDNI(String dni);

    HotelReservation getReservation(String rNumber);


}