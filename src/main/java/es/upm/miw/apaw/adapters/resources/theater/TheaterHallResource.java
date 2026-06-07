package es.upm.miw.apaw.adapters.resources.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.services.theater.TheaterHallService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(TheaterHallResource.HALLS)
public class TheaterHallResource {

    public static final String HALLS = "/theater/halls";
    public static final String HALL_CODE = "/{hallCode}";
    public static final String SEARCH = "/search";

    private final TheaterHallService theaterHallService;

    @Autowired
    public TheaterHallResource(TheaterHallService theaterHallService) {
        this.theaterHallService = theaterHallService;
    }

    @PutMapping(HALL_CODE)
    public TheaterHall update(@PathVariable String hallCode, @Valid @RequestBody TheaterHall theaterHall) {
        return this.theaterHallService.update(hallCode, theaterHall);
    }

    @GetMapping(SEARCH)
    public Stream<TheaterHall> findByMinCapacity(@RequestParam Integer minCapacity) {
        return this.theaterHallService.findByMinCapacity(minCapacity);
    }
}
