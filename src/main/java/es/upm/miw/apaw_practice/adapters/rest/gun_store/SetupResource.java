package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Setup;
import es.upm.miw.apaw_practice.domain.services.gun_store.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SetupResource.SETUPS)
public class SetupResource {
    static final String SETUPS = "/gunstore/setups";

    static final String ID = "/{id}";

    static final String SEARCH = "/search";

    private final SetupService setupService;

    @Autowired
    public SetupResource(SetupService setupService) {
        this.setupService = setupService;
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Integer id){
        this.setupService.delete(id);
    }

    @PostMapping
    public Setup create(@RequestBody Setup setup) {
        return this.setupService.create(setup);
    }

    @GetMapping(SEARCH)
    public Setup findMostExpensiveByCaliberAndCategory(@RequestParam String q) {
        if (q.isEmpty()) {
            throw new BadRequestException("Query string is missing");
        }
        String caliber = new LexicalAnalyzer().extractWithAssure(q, "caliber");
        String category = new LexicalAnalyzer().extractWithAssure(q, "category");
        return this.setupService.findMostExpensiveByCaliberAndCategory(caliber, category);

    }



}
