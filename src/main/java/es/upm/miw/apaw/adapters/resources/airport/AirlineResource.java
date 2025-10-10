package es.upm.miw.apaw.adapters.resources.airport;

import es.upm.miw.apaw.domain.services.airport.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AirlineResource.AIRLINES)
public class AirlineResource {
    public static final String AIRLINES = "airport/airlines";
    public static final String NAME = "/{name}";

    private final AirlineService airlineService;

    @Autowired
    public AirlineResource(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @DeleteMapping(NAME)
    public void delete(@PathVariable String name) {
        this.airlineService.delete(name);
    }
}
