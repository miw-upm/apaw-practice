package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.BookingRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.BookingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bookingPersistence")
public class BookingPersistenceMongodb implements BookingPersistence {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingPersistenceMongodb(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void delete(String bookingNumber) {
        this.bookingRepository.deleteByBookingNumber(bookingNumber);
    }
}
