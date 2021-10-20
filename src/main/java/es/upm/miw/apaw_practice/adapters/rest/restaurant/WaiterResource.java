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
    static final String SECTION = "/section";

    private final WaiterService waiterService;

    @Autowired
    public WaiterResource(WaiterService waiterService){
        this.waiterService = waiterService;
    }

    @PostMapping
    public Waiter create(@RequestBody Waiter waiter){
        return this.waiterService.create(waiter);
    }

    @GetMapping(SECTION)
    public Stream<Waiter> findSection(){
        return this.waiterService.findSection();
    }

    @GetMapping(SEARCH)
    public Stream<Waiter> findByNumberTable(@RequestParam String q){
        String number = new LexicalAnalyzer().extractWithAssure(q, "number");
        return this.waiterService.findByNumberTable(Integer.parseInt(number));
    }

}
