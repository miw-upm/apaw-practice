package es.upm.miw.apaw_practice.domain.services.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Booking;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_hire.BookingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

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

    public Stream<Booking> readAll() {
        return this.bookingPersistence.readAll();
    }

    public Stream<Booking> readByRenterName(String name) {
        return this.bookingPersistence.readByRenterName(name);
    }

    public Stream<Vehicle> getVehiclesVinNumberByRentersName(String name) {
        Stream<Booking> bookings = this.readByRenterName(name);
        return bookings.flatMap(vehicle ->
                vehicle.getVehicleList().stream());
    }
}
