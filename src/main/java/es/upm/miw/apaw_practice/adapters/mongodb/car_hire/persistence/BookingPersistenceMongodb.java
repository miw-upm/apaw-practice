package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos.BookingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.BookingEntity;
import es.upm.miw.apaw_practice.domain.models.car_hire.Booking;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.BookingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

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

    @Override
    public Stream<Booking> readAll() {
        return this.bookingRepository.findAll().stream()
                .map(BookingEntity::toBooking);
    }

    @Override
    public Stream<Booking> readByRenterName(String name) {
        return this.readAll()
                .filter(booking ->
                        booking.getRenter().getName().equals(name));
    }
}
