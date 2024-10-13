package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketPlayer;
import es.upm.miw.apaw_practice.domain.services.basketball.BasketPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BasketPlayerResource.BASKETPLAYERS)
public class BasketPlayerResource {

    static final String BASKETPLAYERS = "/basket/players";

    static final String SEARCH = "/search";

    private final BasketPlayerService basketPlayerService;

    @Autowired
    public BasketPlayerResource(BasketPlayerService basketPlayerService) {
        this.basketPlayerService = basketPlayerService;
    }

    @GetMapping(SEARCH)
    public BasketPlayer findByDni(@RequestParam String dni){
        return this.basketPlayerService.findByDni(dni);
    }
}
