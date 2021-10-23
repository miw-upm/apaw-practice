package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Platform;
import es.upm.miw.apaw_practice.domain.models.videogame.VideoGameCompany;
import es.upm.miw.apaw_practice.domain.services.videogame.VideoGameCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VideoGameCompanyResource.COMPANIES)
public class VideoGameCompanyResource {

    static final String COMPANIES = "/videogame/companies";

    static final String NAME = "/{name}";
    static final String PLATFORMS = "/platforms";

    private final VideoGameCompanyService videoGameCompanyService;

    @Autowired
    public VideoGameCompanyResource(VideoGameCompanyService videoGameCompanyService) {
        this.videoGameCompanyService = videoGameCompanyService;
    }

    @PutMapping(NAME + PLATFORMS)
    public VideoGameCompany updatePlatform(@PathVariable String name, @RequestBody List<Platform> platformList) {
        return this.videoGameCompanyService.updatePlatform(name, platformList);
    }
}
