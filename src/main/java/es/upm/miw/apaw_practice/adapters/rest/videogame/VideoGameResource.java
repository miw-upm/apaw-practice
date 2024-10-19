package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.services.videogame.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VideoGameResource.VIDEOGAMES)
public class VideoGameResource {
    public static final String VIDEOGAMES = "/videogames";

    static final String VIDEOGAME_ALIAS = "/{videoGameAlias}";
    static final String SEARCH = "/search";
    static final String PLAYERNAMES_BY_VIDEOGAMEALIAS = "playernames-by-videogamealias";

    private final VideoGameService videoGameService;

    @Autowired
    public VideoGameResource(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }

    @PostMapping
    public VideoGame create(@RequestBody VideoGame videoGame) {
        return videoGameService.create(videoGame);
    }

    @PutMapping(VIDEOGAME_ALIAS)
    public VideoGame update(@PathVariable String videoGameAlias, @RequestBody VideoGame videoGame) {
        this.videoGameService.assertVideoGameAliasNotExist(videoGameAlias);
        return videoGameService.update(videoGameAlias, videoGame);
    }

    @GetMapping(SEARCH + PLAYERNAMES_BY_VIDEOGAMEALIAS)
    public List<String> findPlayerNamesByVideoGameAlias(@RequestParam String l) {
        String videoGameAlias = new LexicalAnalyzer().extractWithAssure(l,"videoGameAlias").trim();
        return this.videoGameService.findPlayerNamesByVideoGameAlias(videoGameAlias).toList();
    }
}