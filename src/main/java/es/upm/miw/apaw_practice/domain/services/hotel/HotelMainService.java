package es.upm.miw.apaw_practice.domain.services.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelMainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class HotelMainService {

    private final HotelMainPersistence hotelMainPersistence;

    @Autowired
    public HotelMainService (HotelMainPersistence hotelMainPersistence) {
        this.hotelMainPersistence = hotelMainPersistence;
    }

    public HotelMain findByName(String name) { return this.hotelMainPersistence.findByName(name); }

    public void delete(String name) { this.hotelMainPersistence.delete(name); }

    public HotelMain updateRoom(String name, String number, HotelRoom room) { return this.hotelMainPersistence.updateRoom(name, number, room); }

    public Stream<String> findNonRepeatedRoomNumberByType(String type) { return this.hotelMainPersistence.findNonRepeatedRoomNumberByType(type); }
}
