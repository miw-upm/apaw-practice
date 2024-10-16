package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.services.videogame.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PlayerResource.PLAYERS)
public class PlayerResource {
    public static final String PLAYERS = "/players";

    static final String PLAYER_NAME = "/{playerName}";
    static final String SEARCH = "/search";
    static final String VIDEOGAMEALIAS_BY_PLAYERNAMES = "videogamealias-by-playernames";

    private final PlayerService playerService;

    @Autowired
    public PlayerResource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @DeleteMapping(PLAYER_NAME)
    public void delete(@PathVariable String playerName) {
        this.playerService.delete(playerName);
    }

    @GetMapping(SEARCH + VIDEOGAMEALIAS_BY_PLAYERNAMES)
    public List<String> findVideoGameAliasByPlayerName(@RequestParam String l) {
        String playerName = new LexicalAnalyzer().extractWithAssure(l, "playerName").trim();
        return this.playerService.findVideoGameAliasByPlayerName(playerName).toList();
    }
}