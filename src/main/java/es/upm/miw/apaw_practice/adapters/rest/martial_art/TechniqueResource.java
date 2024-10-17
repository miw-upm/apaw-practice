package es.upm.miw.apaw_practice.adapters.rest.martial_art;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.services.martial_art.TechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TechniqueResource.TECHNIQUE)
public class TechniqueResource {
    static final String TECHNIQUE = "/martial_art/technique";
    static final String NAME = "/{name}";
    private final TechniqueService techniqueService;

    @Autowired
    public TechniqueResource(TechniqueService techniqueService) {
        this.techniqueService = techniqueService;
    }

    @PostMapping
    public Technique create(@RequestBody Technique technique) {
        return this.techniqueService.create(technique);
    }

}
