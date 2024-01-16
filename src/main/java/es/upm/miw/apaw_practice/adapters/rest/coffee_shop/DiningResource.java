package es.upm.miw.apaw_practice.adapters.rest.coffee_shop;
import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.coffee_shop.Dining;
import es.upm.miw.apaw_practice.domain.services.coffee_shop.DiningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(DiningResource.DINING)
public class DiningResource {
    static final String DINING = "/coffee-shop/dining";
    static final String NAME = "/{name}";
    private final DiningService diningService;

    @Autowired
    public DiningResource(DiningService diningService) {
        this.diningService = diningService;
    }

    @PostMapping
    public Dining create(@RequestBody Dining dining) {
        return this.diningService.Create(dining);
    }

    @GetMapping(NAME)
    public String getLocationByClientName(@RequestParam String q) {
        String clientName = new LexicalAnalyzer().extractWithAssure(q, "name");
        return this.diningService.getLocationsByClientName(clientName);
    }

}
