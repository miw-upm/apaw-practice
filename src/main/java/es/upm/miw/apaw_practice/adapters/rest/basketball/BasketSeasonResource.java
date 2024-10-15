package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketSeason;
import es.upm.miw.apaw_practice.domain.services.basketball.BasketSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BasketSeasonResource.BASKETSEASONS)
public class BasketSeasonResource {
    static final String BASKETSEASONS = "/basket/seasons";

    private final BasketSeasonService basketSeasonService;

    @Autowired
    public BasketSeasonResource(BasketSeasonService basketSeasonService) {
        this.basketSeasonService = basketSeasonService;
    }

    @PostMapping
    public BasketSeason create(@RequestBody BasketSeason basketSeason) {
        basketSeason.doDefault();
        return this.basketSeasonService.create(basketSeason);
    }

}
