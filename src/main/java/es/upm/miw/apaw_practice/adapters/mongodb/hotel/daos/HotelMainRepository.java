package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelMainRepository extends MongoRepository<HotelMainEntity, String> {
}
