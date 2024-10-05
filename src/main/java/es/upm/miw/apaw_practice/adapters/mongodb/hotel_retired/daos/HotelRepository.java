package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.HotelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<HotelEntity, String> {
}