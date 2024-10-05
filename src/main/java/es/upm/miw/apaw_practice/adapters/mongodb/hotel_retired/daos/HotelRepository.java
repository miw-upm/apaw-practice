package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<HotelRepository, String> {
}