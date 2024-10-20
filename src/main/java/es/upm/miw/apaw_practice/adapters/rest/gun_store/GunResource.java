package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Gun;
import es.upm.miw.apaw_practice.domain.services.gun_store.GunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(GunResource.GUNS)
public class GunResource {
    static final String GUNS = "/gun_store/guns";
    static final String NAME = "/{name}";
    static final String ID = "/{id}";
    static final String SEARCH = "/search";

    private final GunService gunService;

    @Autowired
    public GunResource(GunService gunService) {
        this.gunService = gunService;
    }

    @GetMapping(NAME)
    public Gun getGunByName(@PathVariable String name) {
        return gunService.read(name);
    }

    @PutMapping(ID)
    public Gun updateGUN(@PathVariable Integer id, @RequestBody Gun gun) {
        return gunService.update(id, gun);
    }

    @GetMapping(SEARCH)
    public BigDecimal getAccesoryPriceSumByGunName(@RequestParam String q) {
        if (q.isEmpty()) {
            throw new BadRequestException("Query string is missing");
        }
        String name = new LexicalAnalyzer().extractWithAssure(q, "name");
        return gunService.getAccesoryPriceSumByGunName(name);
    }


}
