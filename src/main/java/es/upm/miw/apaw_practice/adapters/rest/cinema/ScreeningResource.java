package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.services.cinema.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema/screenings")
public class ScreeningResource {

    private final ScreeningService screeningService;

    @Autowired
    public ScreeningResource(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public List<Screening> getAll() {
        return screeningService.findAll();
    }

    @GetMapping("/{screeningTime}")
    public Screening getByScreeningTime(@PathVariable String screeningTime) {
        return screeningService.findByScreeningTime(screeningTime);
    }

    @PostMapping
    public Screening create(@RequestBody Screening screening) {
        return screeningService.create(screening);
    }
}