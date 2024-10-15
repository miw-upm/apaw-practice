package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.services.videogame.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PlayerResource {
    public static final String PLAYERS = "/players";

    static final String PLAYER_NAME = "/{playerName}";

    private final PlayerService playerService;

    @Autowired
    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @DeleteMapping(PLAYER_NAME)
    public void delete(@PathVariable String playerName) {
        this.playerService.delete(playerName);
    }
}
