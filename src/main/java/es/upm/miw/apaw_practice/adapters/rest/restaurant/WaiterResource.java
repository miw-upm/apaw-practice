package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import es.upm.miw.apaw_practice.domain.services.restaurant.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(WaiterResource.WAITERS)
public class WaiterResource {

    static final String WAITERS = "/restaurant/waiters";
    static final String SEARCH = "/search";
    static final String ID_SECTION = "/{section}";
    static final String CATEGORY = "/category";

    private final WaiterService waiterService;

    @Autowired
    public WaiterResource(WaiterService waiterService){
        this.waiterService = waiterService;
    }

    @PostMapping
    public Waiter create(@RequestBody Waiter waiter){
        return this.waiterService.create(waiter);
    }

    @GetMapping(SEARCH)
    public Stream<Waiter> findBySectionAndCategory(@RequestParam String q){
        String section = new LexicalAnalyzer().extractWithAssure(q, "section");
        String category = new LexicalAnalyzer().extractWithAssure(q, "category");
        return this.waiterService.findBySectionAndCategory(section,category);
    }

    @GetMapping(TableResource.ID_NUMBER)
    public Stream<Waiter> findByNumberTable(@PathVariable Integer number){
        return this.waiterService.findByNumberTable(number);
    }

}
