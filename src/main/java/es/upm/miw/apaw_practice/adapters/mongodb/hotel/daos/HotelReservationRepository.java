package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelReservationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HotelReservationRepository extends MongoRepository<HotelReservationEntity, String> {

    Optional<HotelReservationEntity> findByReservationNumber(String reservationNumber);

}
