package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.shop.Tag;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.services.videogame.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
@RequestMapping(VideoGameResource.GAMES)
public class VideoGameResource {

    static final String GAMES = "/videogame/games";

    static final String TITLE = "/{title}";

    private final VideoGameService videoGameService;

    @Autowired
    public VideoGameResource(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @GetMapping(TITLE)
    public VideoGame read(@PathVariable String title) {
        return VideoGame.ofPlatformConsoleName(this.videoGameService.read(title));
    }
}
