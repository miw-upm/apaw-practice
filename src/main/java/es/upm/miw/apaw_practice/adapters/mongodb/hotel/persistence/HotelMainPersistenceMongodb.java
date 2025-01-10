package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelMainRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelRoomEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.shopping_center.entities.ShopEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelMainPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

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
    public HotelMain updateRoom(String name, String number, HotelRoom room){
        HotelMainEntity hotelEntity = this.hotelMainRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(" HotelMain name: " + name));
        HotelRoomEntity hotelRoomEntity = hotelEntity.getRooms().stream()
                .filter(roomToFind -> room.getNumber().equals(number))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Room number not found: " + number));
        hotelRoomEntity.setPrice(room.getPrice());
        hotelRoomEntity.setType(room.getType());
        hotelRoomEntity.setReserved(room.isReserved());
        return hotelMainRepository.save(hotelEntity).toHotel();
    }

}

