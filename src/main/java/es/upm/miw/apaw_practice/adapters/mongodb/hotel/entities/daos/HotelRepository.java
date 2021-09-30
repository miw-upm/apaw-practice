package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<HotelEntity, String> {
}
