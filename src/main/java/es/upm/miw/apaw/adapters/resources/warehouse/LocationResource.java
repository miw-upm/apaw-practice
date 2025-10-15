package es.upm.miw.apaw.adapters.resources.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.Location;
import es.upm.miw.apaw.domain.services.warehouse.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping(LocationResource.LOCATIONS)
public class LocationResource {

    public static final String LOCATIONS = "/warehouse/locations";
    public static final String POSITION = "/{position}";
    public static final String AVAILABILITY = POSITION + "/availability";

    private final LocationService locationService;

    @Autowired
    public LocationResource(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public Stream<Location> readAll() {
        return this.locationService.readAll();
    }

    @GetMapping(POSITION)
    public Location readByPosition(@PathVariable String position) {
        return this.locationService.readByPosition(position);
    }

    @PatchMapping(AVAILABILITY)
    public Location updateAvailability(@PathVariable String position,
                                       @RequestBody Map<String, Boolean> availability) {
        return this.locationService.updateAvailability(position, availability.get("availability"));
    }

}