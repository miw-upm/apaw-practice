package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.services.videogame.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VideoGameResource.VIDEOGAMES)
public class VideoGameResource {
    static final String VIDEOGAMES = "/videogames";
    public static final String VIDEOGAME_ALIAS = "/{videoGameAlias}";

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
}
