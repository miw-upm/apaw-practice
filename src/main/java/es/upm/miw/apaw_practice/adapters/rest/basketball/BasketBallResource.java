package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.domain.models.basketball.BasketBall;
import es.upm.miw.apaw_practice.domain.services.basketball.BasketBallService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BasketBallResource.BALLS)
public class BasketBallResource {

    static final String ID_ID = "/{id}";
    static final String BALLS = "/basket/balls";

    private final BasketBallService basketBallService;

    public BasketBallResource(BasketBallService basketBallService) {
        this.basketBallService = basketBallService;
    }

    @PutMapping(ID_ID)
    public BasketBall update(@PathVariable Integer id, @RequestBody BasketBall basketBall) {
        return this.basketBallService.update(id,basketBall);
    }
}
