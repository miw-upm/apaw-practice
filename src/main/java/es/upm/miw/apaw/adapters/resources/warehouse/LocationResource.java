package es.upm.miw.apaw.adapters.resources.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.services.warehouse.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(LocationResource.LOCATIONS)
public class LocationResource {

    static final String LOCATIONS = "/warehouse/locations";

    private final LocationService locationService;

    @Autowired
    public LocationResource(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public Stream<Location> readAll() {
        return this.locationService.readAll();
    }

}
