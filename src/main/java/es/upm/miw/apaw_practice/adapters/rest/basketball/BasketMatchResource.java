package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.domain.services.basketball.BasketMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
