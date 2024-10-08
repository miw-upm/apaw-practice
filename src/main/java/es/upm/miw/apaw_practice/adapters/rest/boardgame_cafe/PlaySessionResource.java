package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
import es.upm.miw.apaw_practice.domain.services.boardgame_cafe.PlaySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PlaySessionResource.PLAY_SESSION)
public class PlaySessionResource {
    static final String PLAY_SESSION = "/boardgame-cafe/play-session";

    static final String PLAYSESSIONID_ID = "/{playSessionId}";
    static final String GAMES = "/games";

    private final PlaySessionService playSessionService;

    @Autowired
    public PlaySessionResource(PlaySessionService playSessionService) {
        this.playSessionService = playSessionService;
    }

    @PutMapping(PLAYSESSIONID_ID + GAMES)
    public PlaySession updatePlaySessionGames (@PathVariable Integer playSessionId , @RequestBody List<Game> gameList) {
        return this.playSessionService.updatePlaySessionGames(playSessionId, gameList);
    }
}
