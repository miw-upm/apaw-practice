package es.upm.miw.apaw.adapters.resources.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import es.upm.miw.apaw.domain.services.theater.TheaterPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.stream.Stream;

@RestController
@RequestMapping(TheaterPerformanceResource.PERFORMANCES)
public class TheaterPerformanceResource {

    public static final String PERFORMANCES = "/theater/performances";
    public static final String SEARCH = "/search";

    private final TheaterPerformanceService theaterPerformanceService;

    @Autowired
    public TheaterPerformanceResource(TheaterPerformanceService theaterPerformanceService) {
        this.theaterPerformanceService = theaterPerformanceService;
    }

    @GetMapping(SEARCH)
    public Stream<TheaterPerformance> findByMinDate(@RequestParam LocalDate minDate) {
        return this.theaterPerformanceService.findByMinDate(minDate);
    }
}
