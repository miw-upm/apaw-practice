package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.RoomEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<RoomEntity, String> {
}
