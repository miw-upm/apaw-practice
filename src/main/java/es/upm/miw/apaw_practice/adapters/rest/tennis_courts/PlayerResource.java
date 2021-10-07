package es.upm.miw.apaw_practice.adapters.rest.tennis_courts;

import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.services.tennis_courts.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerResource {

    public static final String PLAYERS = "/players";

    private final PlayerService playerService;

    @Autowired
    public PlayerResource(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping
    public void create(@RequestBody Player player){
        this.playerService.create(player);
    }

    
}
