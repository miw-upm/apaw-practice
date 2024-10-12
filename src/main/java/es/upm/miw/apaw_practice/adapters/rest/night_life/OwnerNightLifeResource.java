package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import es.upm.miw.apaw_practice.domain.services.night_life.OwnerNightLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(OwnerNightLifeResource.OWNERS)
public class OwnerNightLifeResource {
    static final String OWNERS = "/night-life/owners";
    private final OwnerNightLifeService ownerService;
    static final String NAME_ID = "/{name}";
    static final String TOTAL_PRICE = "/total-price";
    @Autowired
    public OwnerNightLifeResource(OwnerNightLifeService ownerService) {
        this.ownerService = ownerService;
    }
    @PostMapping
    public Owner create(@RequestBody Owner owner) {
        return this.ownerService.create(owner);
    }
    @GetMapping(NAME_ID)
    public Owner readByName(@PathVariable String name) {
        return this.ownerService.readByName(name);
    }

    @GetMapping(NAME_ID + TOTAL_PRICE)
    public BigDecimal getTotalPriceByOwner(@PathVariable String name) {
        return this.ownerService.calculateTotalPriceByOwner(name);
    }

}
