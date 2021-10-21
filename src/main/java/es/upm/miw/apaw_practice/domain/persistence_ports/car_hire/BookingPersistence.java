package es.upm.miw.apaw_practice.domain.persistence_ports.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Booking;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BookingPersistence {

    void delete(String bookingNumber);

    Stream<Booking> readAll();

    Stream<Booking> readByRenterName(String name);

    boolean assertExistRenterName(String name);

}
