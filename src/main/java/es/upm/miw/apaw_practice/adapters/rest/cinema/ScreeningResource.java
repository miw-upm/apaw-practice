package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Screening;
import es.upm.miw.apaw_practice.domain.services.cinema.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ScreeningResource.SCREENINGS)
public class ScreeningResource {
    public static final String SCREENINGS = "/cinema/screenings";

    private final ScreeningService screeningService;

    @Autowired
    public ScreeningResource(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public List<Screening> findAll() {
        return screeningService.findAll();
    }

    @GetMapping("/{id}")
    public Screening findById(@PathVariable String id) {
        return screeningService.findById(id);
    }

    @PostMapping
    public Screening create(@RequestBody Screening screening) {
        return screeningService.create(screening);
    }

    @PutMapping("/{id}")
    public Screening update(@PathVariable String id, @RequestBody Screening screening) {
        return screeningService.update(id, screening);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        screeningService.delete(id);
    }
}