package es.upm.miw.apaw_practice.adapters.rest.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.services.boardgame_cafe.PlaySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PlaySessionResource.PLAY_SESSION)
public class PlaySessionResource {

    static final String PLAY_SESSION = "/boardgame-cafe/play-session";

    private final PlaySessionService playSessionService;

    @Autowired
    public PlaySessionResource(PlaySessionService playSessionService) {
        this.playSessionService = playSessionService;
    }
}
