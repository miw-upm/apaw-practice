package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelRoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRoomRepository extends MongoRepository<HotelRoomEntity, String> {
}
