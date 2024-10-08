package es.upm.miw.apaw_practice.adapters.rest.night_life;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import es.upm.miw.apaw_practice.domain.services.night_life.OwnerNightLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(OwnerNightLifeResource.OWNERS)
public class OwnerNightLifeResource {
    static final String OWNERS = "/night-life/owners";
    private final OwnerNightLifeService ownerService;
    @Autowired
    public OwnerNightLifeResource(OwnerNightLifeService ownerService) {
        this.ownerService = ownerService;
    }
    @PostMapping
    public Owner create(@RequestBody Owner owner) {
        return this.ownerService.create(owner);
    }
}
