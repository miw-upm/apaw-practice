package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.BookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<BookingEntity, String> {

    void deleteByBookingNumber(String bookingNumber);

}
