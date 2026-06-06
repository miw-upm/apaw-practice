package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterVenuePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterVenueService {

    private final TheaterVenuePersistence theaterVenuePersistence;

    @Autowired
    public TheaterVenueService(TheaterVenuePersistence theaterVenuePersistence) {
        this.theaterVenuePersistence = theaterVenuePersistence;
    }

    public TheaterVenue read(String venueCode) {
        return this.theaterVenuePersistence.read(venueCode);
    }
}
