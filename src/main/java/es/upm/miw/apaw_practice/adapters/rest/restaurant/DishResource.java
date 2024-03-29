package es.upm.miw.apaw_practice.adapters.rest.restaurant;


import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.restaurant.Dish;
import es.upm.miw.apaw_practice.domain.services.restaurant.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(DishResource.DISHES)
public class DishResource {

    static final String DISHES = "/restaurant/dishes";
    static final String TITLE_ID = "/{title}";
    static final String PRICE = "/price";
    static final String SEARCH = "/search";

    private final DishService dishService;

    @Autowired
    public DishResource(DishService dishService) {
        this.dishService = dishService;
    }

    @PutMapping(TITLE_ID + PRICE)
    public Dish updatePrice(@PathVariable String title, @RequestBody BigDecimal price) {
        return this.dishService.updatePrice(title, price);
    }

    @PatchMapping()
    public void increasePrices(@RequestBody Float increment) {
        this.dishService.increasePrices(increment);
    }

    @GetMapping(SEARCH)
    public BigDecimal findSumOfPriceByAvailableAndLastModificationThisMonth(@RequestParam String q) {
        String available = new LexicalAnalyzer().extractWithAssure(q, "available");
        return this.dishService.findSumOfPriceByAvailableAndLastModificationThisMonth(Boolean.parseBoolean(available));
    }

}
