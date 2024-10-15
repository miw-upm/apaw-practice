package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Studio;
import es.upm.miw.apaw_practice.domain.services.movies.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(StudioResource.STUDIOS)
public class StudioResource {

    static final String STUDIOS = "/movies/studios";

    private final StudioService studioService;

    @Autowired
    public StudioResource(StudioService studioService) {
        this.studioService = studioService;
    }

    @GetMapping("/{name}")
    public Studio getStudioByName(@PathVariable String name) {
        return this.studioService.findByName(name);
    }
}