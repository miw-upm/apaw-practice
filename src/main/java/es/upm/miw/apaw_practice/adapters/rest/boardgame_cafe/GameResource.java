package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.services.boardgame_cafe.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(GameResource.GAME)
public class GameResource {
    static final String GAME = "/boardgame-cafe/game";
    static final String GAMENAME_ID = "/{gameName}";

    private final GameService gameService;

    @Autowired
    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public Stream<Game> readAllGames() {
        return this.gameService.readAllGames();
    }

    @DeleteMapping(GAMENAME_ID)
    public void deleteGame(@PathVariable String gameName) {
        this.gameService.deleteGame(gameName);
    }
}