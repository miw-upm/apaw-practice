package es.upm.miw.apaw_practice.adapters.rest.theme_park;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.theme_park.Ride;
import es.upm.miw.apaw_practice.domain.services.theme_park.RideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;


@RestController
@RequestMapping(RideResource.RIDES)
public class RideResource {

    static final String RIDES = "/theme-park/rides";
    static final String SEARCH = "/search";

    private final RideService rideService;

    @Autowired
    public RideResource(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping(SEARCH)
    public Stream<Ride> findByThemeAndMaxCapacityLessThan(@RequestParam String q) {
        String theme = new LexicalAnalyzer().extractWithAssure(q, "theme");
        Integer maxCapacity = Integer.valueOf(new LexicalAnalyzer().extractWithAssure(q, "maxCapacity"));
        return this.rideService.findByThemeAndMaxCapacityLessThan(theme, maxCapacity);
    }

}
