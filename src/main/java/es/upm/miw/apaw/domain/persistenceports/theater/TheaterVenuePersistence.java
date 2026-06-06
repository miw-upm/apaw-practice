package es.upm.miw.apaw.domain.persistenceports.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TheaterVenuePersistence {

    TheaterVenue create(TheaterVenue theaterVenue);

    TheaterVenue read(String venueCode);

    TheaterVenue update(String venueCode, TheaterVenue theaterVenue);

    Stream<TheaterVenue> readAll();

    boolean existsByVenueCode(String venueCode);

    Stream<TheaterHall> findHallsByVenueCode(String venueCode);
}
