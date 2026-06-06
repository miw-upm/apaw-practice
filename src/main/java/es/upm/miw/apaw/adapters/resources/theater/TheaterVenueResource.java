package es.upm.miw.apaw.adapters.resources.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import es.upm.miw.apaw.domain.services.theater.TheaterVenueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TheaterVenueResource.VENUES)
public class TheaterVenueResource {

    public static final String VENUES = "/theater/venues";
    public static final String VENUE_CODE = "/{venueCode}";

    private final TheaterVenueService theaterVenueService;

    @Autowired
    public TheaterVenueResource(TheaterVenueService theaterVenueService) {
        this.theaterVenueService = theaterVenueService;
    }

    @GetMapping(VENUE_CODE)
    public TheaterVenue read(@Valid @PathVariable String venueCode) {
        return this.theaterVenueService.read(venueCode);
    }
}
