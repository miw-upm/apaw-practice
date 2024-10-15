package es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface GuestPersistence {

    Stream<Guest> readAll();

    Guest create(Guest guest);

    Guest update(String nif, Guest guest);

    Guest read(String nif);

    boolean existsNIF(String nif);

    void delete(String nif);
}
