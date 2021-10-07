package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HotelGuestRepository extends MongoRepository<HotelGuestEntity, String> {
    Optional<HotelGuestEntity> findByDni(String dni);

    Optional<HotelGuestEntity> findByName(String name);

}
