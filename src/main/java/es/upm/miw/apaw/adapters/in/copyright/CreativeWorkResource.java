package es.upm.miw.apaw.adapters.in.copyright;

import es.upm.miw.apaw.domain.model.copyright.CreativeWork;
import es.upm.miw.apaw.domain.model.copyright.CreativeWorkCreation;
import es.upm.miw.apaw.domain.services.copyright.CreativeWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

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
    @ResponseStatus(HttpStatus.CREATED)
    public CreativeWork create(@Valid @RequestBody CreativeWorkCreation creation) {
        return this.creativeWorkService.create(creation);
    }

    @GetMapping("/claim-summaries")
    public java.util.List<es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary> generateClaimSummaries() {
        return this.creativeWorkService.generateClaimSummaries();
    }

    @GetMapping("/search")
    public java.util.List<CreativeWork> find(es.upm.miw.apaw.domain.model.copyright.CreativeWorkFindCriteria criteria) {
        return this.creativeWorkService.find(criteria);
    }
}
