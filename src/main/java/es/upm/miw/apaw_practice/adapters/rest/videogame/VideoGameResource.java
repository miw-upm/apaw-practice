package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.services.videogame.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VideoGameResource.GAMES)
public class VideoGameResource {

    static final String GAMES = "/videogame/games";

    private final VideoGameService videoGameService;

    @Autowired
    public VideoGameResource(VideoGameService videoGameService) {
        this.videoGameService = videoGameService;
    }
}
