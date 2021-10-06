package es.upm.miw.apaw_practice.adapters.rest.football;

import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import es.upm.miw.apaw_practice.domain.services.football.FootballPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(FootballPlayerResource.PLAYERS)
public class FootballPlayerResource {

    static final String PLAYERS = "/football/players";

    private final FootballPlayerService footballPlayerService;

    @Autowired
    public FootballPlayerResource(FootballPlayerService footballPlayerService) {
        this.footballPlayerService = footballPlayerService;
    }

    @GetMapping
    public Stream<FootballPlayer> readAllPlayers() {
        return this.footballPlayerService.readAll();
    }
}
