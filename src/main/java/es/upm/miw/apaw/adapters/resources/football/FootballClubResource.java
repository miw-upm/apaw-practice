package es.upm.miw.apaw.adapters.resources.football;

import es.upm.miw.apaw.domain.models.football.FootballClub;
import es.upm.miw.apaw.domain.services.football.FootballClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(FootballClubResource.FOOTBALL_CLUBS)
public class FootballClubResource {

    public static final String FOOTBALL_CLUBS = "/football/clubs";
    public static final String NAME_ID = "/{name}";

    private final FootballClubService footballClubService;

    @Autowired
    public FootballClubResource(FootballClubService footballClubService) {
        this.footballClubService = footballClubService;
    }

    @GetMapping(NAME_ID)
    public FootballClub readByName(@PathVariable String name) {
        return this.footballClubService.readByName(name);
    }
}
