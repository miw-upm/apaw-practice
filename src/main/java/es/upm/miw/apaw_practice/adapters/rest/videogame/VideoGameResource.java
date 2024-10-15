package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.VideoGame;
import es.upm.miw.apaw_practice.domain.services.videogame.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
