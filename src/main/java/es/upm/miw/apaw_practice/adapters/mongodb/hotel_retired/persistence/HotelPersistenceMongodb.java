package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.HotelRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.RoomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.HotelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.RoomEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.HotelPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("hotelPersistence")
public class HotelPersistenceMongodb implements HotelPersistence {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public HotelPersistenceMongodb(
            HotelRepository hotelRepository,
            RoomRepository roomRepository
    ) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Stream<Hotel> readAll() {
        return this.hotelRepository
                .findAll().stream()
                .map(HotelEntity::toHotel);
    }

    @Override
    public Hotel create(Hotel hotel) {
        List<RoomEntity> roomEntities = this.roomRepository
                .findAll().stream()
                .toList();
        return this.hotelRepository
                .save(new HotelEntity(hotel.getCif(), hotel.getHotelName(), hotel.getAddress(), roomEntities))
                .toHotel();
    }

    @Override
    public Hotel update(String cif, Hotel hotel) {
        HotelEntity hotelEntity = this.hotelRepository
                .findByCif(cif)
                .orElseThrow(() -> new NotFoundException("Hotel cif: " + cif));
        hotelEntity.fromHotel(hotel);
        return this.hotelRepository
                .save(hotelEntity)
                .toHotel();
    }

    @Override
    public Hotel read(String cif) {
        return this.hotelRepository
                .findByCif(cif)
                .orElseThrow(() -> new NotFoundException("Hotel CIF: " + cif))
                .toHotel();
    }

    @Override
    public boolean existCIF(String cif) {
        return this.hotelRepository
                .findByCif(cif)
                .isPresent();
    }
}
