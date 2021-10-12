package es.upm.miw.apaw_practice.domain.services.car_hire;

import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.BookingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingPersistence bookingPersistence;

    @Autowired
    public BookingService(BookingPersistence bookingPersistence) {
        this.bookingPersistence = bookingPersistence;
    }

    public void delete(String bookingNumber) {
        this.bookingPersistence.delete(bookingNumber);
    }
}
