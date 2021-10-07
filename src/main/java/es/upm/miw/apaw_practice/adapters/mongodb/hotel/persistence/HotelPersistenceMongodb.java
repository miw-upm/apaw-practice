package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("hotelPersistence")
public class HotelPersistenceMongodb implements HotelPersistence {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelPersistenceMongodb(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
 /*
    @Override
    public void updateRoomPrice(String id, Integer numberRoom, BigDecimal price) {
     this.hotelRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel id:" + id))
                .getRooms().stream()
                .filter(roomEntity -> roomEntity.getNumberRoom().equals(numberRoom))
                .map(roomEntity -> {
                    roomEntity.setPriceRoom(price);
                    return roomEntity;
                })
                .forEach(this.roomRepository::save);
      */


}
