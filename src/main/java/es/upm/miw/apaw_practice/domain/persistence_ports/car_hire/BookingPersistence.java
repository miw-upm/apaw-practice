package es.upm.miw.apaw_practice.domain.persistence_ports.car_hire;

import org.springframework.stereotype.Repository;

@Repository
public interface BookingPersistence {

    void delete(String bookingNumber);

}
