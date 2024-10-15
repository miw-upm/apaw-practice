package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelMainPersistence {

    HotelMain findByName(String name);

}
