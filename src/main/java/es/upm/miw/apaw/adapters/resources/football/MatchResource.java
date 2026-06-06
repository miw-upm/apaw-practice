package es.upm.miw.apaw.adapters.resources.football;

import es.upm.miw.apaw.domain.models.football.Match;
import es.upm.miw.apaw.domain.services.football.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MatchResource.MATCHES)
public class MatchResource {

    public static final String MATCHES = "/football/matches";
    public static final String MATCH_ID = "/{matchId}";

    private final MatchService matchService;

    @Autowired
    public MatchResource(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<Match> readAll() {
        return this.matchService.readAll();
    }

    @GetMapping(MATCH_ID)
    public Match readByMatchId(@PathVariable Long matchId) {
        return this.matchService.readByMatchId(matchId);
    }
}