package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketMatch;
import es.upm.miw.apaw_practice.domain.services.basketball.BasketMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BasketMatchResource.MATCHES)
public class BasketMatchResource {

    static final String ID_ID = "/{matchId}";
    static final String MATCHES = "/matches";

    private final BasketMatchService basketMatchService;

    @Autowired
    public BasketMatchResource(BasketMatchService basketMatchService) {
        this.basketMatchService = basketMatchService;
    }

    @DeleteMapping(ID_ID)
    public void delete(@PathVariable Integer matchId) {
        this.basketMatchService.delete(matchId);
    }

    @PatchMapping(ID_ID)
    public BasketMatch updateAddress(@PathVariable Integer matchId, @RequestBody String address) {
        return this.basketMatchService.updateAddress(matchId,address);
    }
}
