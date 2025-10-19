package es.upm.miw.apaw.adapters.resources.airport;

import es.upm.miw.apaw.domain.models.airport.Airline;
import es.upm.miw.apaw.domain.services.airport.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(AirlineResource.AIRLINES)
public class AirlineResource {
    public static final String AIRLINES = "airport/airlines";
    public static final String NAME = "/{name}";
    public static final String PLANE_MODEL = "/{planeModel}";

    private final AirlineService airlineService;

    @Autowired
    public AirlineResource(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @DeleteMapping(NAME)
    public void delete(@PathVariable String name) {
        this.airlineService.delete(name);
    }

    @GetMapping(PLANE_MODEL)
    public Stream<String> readByPlaneModel(@PathVariable String planeModel) {
        return this.airlineService.readByPlaneModel(planeModel);
    }
}
