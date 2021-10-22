package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.services.videogame.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PlatformResource.PLATFORMS)
public class PlatformResource {

    static final String PLATFORMS = "/videogame/platforms";

    private final PlatformService platformService;

    @Autowired
    public PlatformResource(PlatformService platformService) { this.platformService = platformService; }

    @PostMapping
    public Platform create(@RequestBody Platform platform) {
        platform.doDefault();
        return this.platformService.create(platform);
    }
}
