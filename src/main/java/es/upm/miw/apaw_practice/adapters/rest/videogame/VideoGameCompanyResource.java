package es.upm.miw.apaw_practice.adapters.rest.videogame;

import es.upm.miw.apaw_practice.domain.services.videogame.VideoGameCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VideoGameCompanyResource.COMPANIES)
public class VideoGameCompanyResource {

    static final String COMPANIES = "/videogame/companies";

    private final VideoGameCompanyService videoGameCompanyService;

    @Autowired
    public VideoGameCompanyResource(VideoGameCompanyService videoGameCompanyService) {
        this.videoGameCompanyService = videoGameCompanyService;
    }
}
