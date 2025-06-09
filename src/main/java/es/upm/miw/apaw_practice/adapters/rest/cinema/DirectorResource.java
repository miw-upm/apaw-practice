package es.upm.miw.apaw_practice.adapters.rest.cinema;

import es.upm.miw.apaw_practice.domain.models.cinema.Director;
import es.upm.miw.apaw_practice.domain.services.cinema.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DirectorResource.DIRECTORS)
public class DirectorResource {
    public static final String DIRECTORS = "/cinema/directors";

    private final DirectorService directorService;

    @Autowired
    public DirectorResource(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public List<Director> findAll() {
        return directorService.findAll();
    }

    @GetMapping("/{dni}")
    public Director findByDni(@PathVariable String dni) {
        return directorService.findByDni(dni);
    }

    @PostMapping
    public Director create(@RequestBody Director director) {
        return directorService.create(director);
    }

    @PutMapping("/{dni}")
    public Director update(@PathVariable String dni, @RequestBody Director director) {
        return directorService.update(dni, director);
    }

    @DeleteMapping("/{dni}")
    public void delete(@PathVariable String dni) {
        directorService.delete(dni);
    }
}