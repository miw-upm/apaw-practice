package es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BookingPersistence {

    Stream<Booking> readAll();

    Booking create(Booking booking);

    Booking update(String id, Booking booking);

    Booking read(String id);

    boolean existsBooking(String id);
}