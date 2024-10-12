package es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface HotelPersistence {

    Stream<Hotel> readAll();

    Hotel create(Hotel hotel);

    Hotel update(String cif, Hotel hotel);

    Hotel read(String cif);

    boolean existCIF(String cif);

    void delete(String cif);
}
