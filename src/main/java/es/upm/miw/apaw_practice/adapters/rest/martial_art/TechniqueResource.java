package es.upm.miw.apaw_practice.adapters.rest.martial_art;
import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import es.upm.miw.apaw_practice.domain.services.martial_art.TechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(TechniqueResource.TECHNIQUE)
public class TechniqueResource {
    static final String TECHNIQUE = "/martial_art/technique";
    static final String SEARCH = "/search";
    static final String NAME = "/{name}";
    static final String PHONE_NO_REPEAT = "/phone-no-repeat";
    static final String TOTAL_SUM_TECHNIQUE_NO_REPEAT = "/total-sum-technique-no-repeat";
    private final TechniqueService techniqueService;

    @Autowired
    public TechniqueResource(TechniqueService techniqueService) {
        this.techniqueService = techniqueService;
    }

    @PostMapping
    public Technique create(@RequestBody Technique technique) {
        return this.techniqueService.create(technique);
    }

    // Nuevo endpoint para obtener los números de teléfono de instructores únicos según la popularidad
    @GetMapping(SEARCH + PHONE_NO_REPEAT)
    public Stream<Integer> findNonDuplicatedInstructorPhonesByPopularity(@RequestParam String q) {
        int popularity = Integer.parseInt(new LexicalAnalyzer().extractWithAssure(q, "popularity").trim());
        return this.techniqueService.findNonDuplicatedInstructorPhonesByPopularity(popularity);
    }

    @GetMapping(SEARCH + TOTAL_SUM_TECHNIQUE_NO_REPEAT)
    public Integer findTotalDurationNoRepeatByDescription(@RequestParam String q) {
        String description = new LexicalAnalyzer().extractWithAssure(q, "description").trim();
        return this.techniqueService.findTotalDurationNoRepeatByDescription(description);
    }

}
