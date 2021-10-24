package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.domain.models.football.MatchWeatherDto;
import es.upm.miw.apaw_practice.domain.services.football.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MatchResource.MATCHES)
public class MatchResource {
    static final String MATCHES = "/football/matches";

    static final String ROUND_ID = "/{round}";

    private final MatchService matchservice;

    @Autowired
    public MatchResource(MatchService matchservice) {
        this.matchservice = matchservice;
    }

    @DeleteMapping(ROUND_ID)
    public void delete(@PathVariable Integer round) {
        this.matchservice.delete(round);
    }

    @PatchMapping
    public void updateWeather(@RequestBody MatchWeatherDto matchWeatherDto) {
        this.matchservice.updateWeather(matchWeatherDto);
    }
}
