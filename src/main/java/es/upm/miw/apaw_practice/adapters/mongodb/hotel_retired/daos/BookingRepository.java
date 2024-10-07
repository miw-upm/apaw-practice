package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.BookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<BookingEntity, String > {
}
