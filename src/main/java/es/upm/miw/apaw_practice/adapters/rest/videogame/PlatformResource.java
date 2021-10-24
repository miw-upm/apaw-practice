package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.PlatformMemoryUpdating;
import es.upm.miw.apaw_practice.domain.services.videogame.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PlatformResource.PLATFORMS)
public class PlatformResource {

    static final String PLATFORMS = "/videogame/platforms";

    static final String CONSOLE_NAME_ID = "/{name}";

    private final PlatformService platformService;

    @Autowired
    public PlatformResource(PlatformService platformService) { this.platformService = platformService; }

    @PostMapping
    public Platform create(@RequestBody Platform platform) {
        //platform.doDefault();
        return this.platformService.create(platform);
    }

    @PatchMapping(CONSOLE_NAME_ID)
    public void updateMemory(@RequestBody List<PlatformMemoryUpdating> platformMemoryUpdatingList) {
        this.platformService.updateMemory(platformMemoryUpdatingList.stream());
    }
}
