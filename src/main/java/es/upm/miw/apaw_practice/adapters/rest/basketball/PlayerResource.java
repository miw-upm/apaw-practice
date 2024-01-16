package es.upm.miw.apaw_practice.adapters.rest.basketball;

import es.upm.miw.apaw_practice.domain.services.basketball.PlayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PlayerResource.PLAYER)
public class PlayerResource {
    static final String PLAYER = "/basketball/player";
    private final PlayerService playerService;
    static final String EMAIL_ID = "/{email}";
    static final String BASKET_ID = "/{id}";

    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(BASKET_ID)
    public String readByBasketId(@PathVariable String basket_id) {
        return this.playerService.read(basket_id);
    }

    @DeleteMapping(EMAIL_ID)
    public void delete(@PathVariable String email) {
        this.playerService.delete(email);
    }
}
