package es.upm.miw.apaw_practice.adapters.rest.car_workshop;


import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.services.car_workshop.TyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(TyreResource.TYRES)
public class TyreResource {

    static final String TYRES = "/car-workshop/tyres";
    static final String MANUFACTURER = "/{manufacturer}";
    static final String SEARCH = "/search";

    private final TyreService tyreService;

    @Autowired
    public TyreResource(TyreService tyreService) {
        this.tyreService = tyreService;
    }

    @DeleteMapping(TyreResource.MANUFACTURER)
    public void deleteManufacturer(@PathVariable String manufacturer) {
        this.tyreService.deleteManufacturer(manufacturer);
    }

    @GetMapping(SEARCH)
    public Stream<String> findModelByOwnerNameAndRevision(@RequestParam String q) {
        //q=ownerName:name;revision:true
        String ownerName = new LexicalAnalyzer().extractWithAssure(q,"ownerName");
        Boolean revision = Boolean.parseBoolean(new LexicalAnalyzer().extractWithAssure(q, "revision"));
        return this.tyreService.findModelByOwnerNameAndRevision(ownerName, revision);
    }
}
