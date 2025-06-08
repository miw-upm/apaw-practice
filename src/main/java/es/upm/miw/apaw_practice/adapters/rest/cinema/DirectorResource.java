package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.services.cinema.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinema/directors")
public class DirectorResource {

    private final DirectorService directorService;

    @Autowired
    public DirectorResource(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<Director> getAll() {
        return directorService.findAll();
    }

    @GetMapping("/{dni}")
    public Director getByDni(@PathVariable String dni) {
        return directorService.findByDni(dni);
    }

    @PostMapping
    public Director create(@RequestBody Director director) {
        return directorService.create(director);
    }
}