package es.upm.miw.apaw.adapters.resources.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Dojo;
import es.upm.miw.apaw.domain.services.martialartsgym.DojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DojoResource.DOJOS)
public class DojoResource {

    public static final String DOJOS = "/dojos";

    private final DojoService dojoService;

    @Autowired
    public DojoResource(DojoService dojoService) {
        this.dojoService = dojoService;
    }

    @PostMapping
    public Dojo create(@RequestBody Dojo dojo) {
        return this.dojoService.create(dojo);
    }
}
