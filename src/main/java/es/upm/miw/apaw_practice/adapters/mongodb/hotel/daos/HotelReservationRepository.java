package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelReservationRepository extends MongoRepository<HotelReservationEntity, String> {
}
