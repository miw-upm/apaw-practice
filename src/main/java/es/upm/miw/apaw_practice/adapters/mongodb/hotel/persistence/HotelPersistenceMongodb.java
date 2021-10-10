package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository("hotelPersistence")
public class HotelPersistenceMongodb implements HotelPersistence {
    private final HotelRepository hotelRepository;

    @Autowired
    public HotelPersistenceMongodb(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void updateRoomPrice(String id, Integer numberRoom, BigDecimal price) {
        HotelEntity hotelEntity = this.hotelRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel id:" + id));

        hotelEntity.getRoomEntities().stream()
                .filter(roomEntity -> roomEntity.getNumber().equals(numberRoom))
                .forEach(room ->
                    room.setPrice(price)
                );
        this.hotelRepository.save(hotelEntity);
    }

    @Override
    public Hotel read(String id) {
        return this.hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel with id: " + id))
                .toHotel();
    }
}
