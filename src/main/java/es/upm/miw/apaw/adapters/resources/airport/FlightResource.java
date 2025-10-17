package es.upm.miw.apaw.adapters.resources.airport;

import es.upm.miw.apaw.domain.models.airport.Flight;
import es.upm.miw.apaw.domain.services.airport.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping(FlightResource.FLIGHTS)
public class FlightResource {
    public static final String FLIGHTS = "airport/flights";
    public static final String ID = "/{id}";

    private final FlightService flightService;

    @Autowired
    public FlightResource(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping(ID)
    public Flight read(@PathVariable UUID id) {
        return flightService.read(id);
    }
}
