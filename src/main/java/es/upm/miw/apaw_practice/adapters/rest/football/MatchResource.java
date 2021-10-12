package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.domain.services.football.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MatchResource.TAGS)
public class MatchResource {
    static final String TAGS = "/football/matches";

    static final String ROUND_ID = "/{round}";

    private final MatchService matchservice;

    @Autowired
    public MatchResource(MatchService matchservice) {
        this.matchservice = matchservice;
    }

    @DeleteMapping(ROUND_ID)
    public void delete(@PathVariable Integer round)
    {
        this.matchservice.delete(round);
    }
}
