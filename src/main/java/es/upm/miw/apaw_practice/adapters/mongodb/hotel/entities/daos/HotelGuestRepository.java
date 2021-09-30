package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelGuestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelGuestRepository extends MongoRepository<HotelGuestEntity, String> {
}
