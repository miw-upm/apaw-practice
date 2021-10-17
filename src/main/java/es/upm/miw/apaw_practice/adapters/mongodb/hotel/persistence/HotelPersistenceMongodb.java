package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelGuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.models.hotel.Room;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public void update(String id, Hotel hotel) {
        HotelEntity hotelEntity = this.hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Hotel id:" + id));
        hotelEntity.setDirection(hotel.getDirection());
        hotelEntity.setNumberStars(hotel.getNumberStars());
        this.hotelRepository.save(hotelEntity);
    }

    @Override
    public List<Hotel> findHotelNameListByGuestName(String name) {
            return this.hotelRepository.findAll().stream()
                    .map(HotelEntity::toHotel)
                    .filter(hotel -> filterHotelByGuestName(hotel, name))
                    .map(Hotel::ofName)
                    .collect(Collectors.toList());
    }

    private boolean filterHotelByGuestName(Hotel hotel, String name) {
        for(Room room: hotel.getRooms()){
            for(HotelGuest hotelGuest : room.getHotelGuests()){
                if(hotelGuest.getName().equals(name))
                    return true;
            }
        }
        return false;
    }
}
