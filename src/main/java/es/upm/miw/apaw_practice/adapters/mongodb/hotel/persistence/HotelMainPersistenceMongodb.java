package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelMainRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelRoomEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelMainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("hotelMainPersistence")
public class HotelMainPersistenceMongodb implements HotelMainPersistence{

    private final HotelMainRepository hotelMainRepository;

    @Autowired
    public HotelMainPersistenceMongodb(HotelMainRepository hotelMainRepository) {
        this.hotelMainRepository = hotelMainRepository;
    }

    @Override
    public HotelMain findByName(String name) {
        return this.hotelMainRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(" HotelMain name: " + name))
                .toHotel();
    }

    @Override
    public void delete(String name) {
        this.hotelMainRepository.deleteByName(name);
    }

    @Override
    public void updateRoom(String name, String roomNumber, HotelRoom room){
        HotelMain hotel = findByName(name);
        HotelRoom hotelRoom = hotel.getRooms().stream()
                .filter(roomToFind -> room.getNumber().equals(roomNumber))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Room number not found: " + roomNumber));
        hotelRoom.setNumber(room.getNumber());
        hotelRoom.setPrice(room.getPrice());
        hotelRoom.setType(room.getType());
        hotelRoom.setReserved(room.isReserved());
        //HotelMainEntity hotelEntity = hotel.toHotelEntity();
        //hotelMainRepository.save(hotelEntity);
    }

}

