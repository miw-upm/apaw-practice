package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.BookingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingPersistence bookingPersistence;

    @Autowired
    public BookingService(BookingPersistence bookingPersistence) {
        this.bookingPersistence = bookingPersistence;
    }

    public Booking create(Booking booking) {
        return this.bookingPersistence.create(booking);
    }
}
