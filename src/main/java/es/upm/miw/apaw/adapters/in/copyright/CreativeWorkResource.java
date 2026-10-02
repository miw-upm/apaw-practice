package es.upm.miw.apaw.adapters.in.copyright;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkCreation;
import es.upm.miw.apaw.domain.services.copyright.CreativeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CreativeWorkResource.CREATIVE_WORKS)
public class CreativeWorkResource {
    public static final String CREATIVE_WORKS = "/copyright/creative-works";

    private final CreativeWorkService creativeWorkService;

    @Autowired
    public CreativeWorkResource(CreativeWorkService creativeWorkService) {
        this.creativeWorkService = creativeWorkService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CreativeWork create(@RequestBody CreativeWorkCreation creation) {
        return this.creativeWorkService.create(creation);
    }
}
