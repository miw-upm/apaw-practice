package es.upm.miw.apaw_practice.domain.persistence_ports.hotel;


import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface HotelMainPersistence {

    HotelMain findByName(String name);

    void delete(String name);

    HotelMain updateRoom(String name, String roomNumber, HotelRoom room);

    Stream<String> findNonRepeatedRoomNumberByType(String type);
}
