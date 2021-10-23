package es.upm.miw.apaw_practice.adapters.rest.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Player;
import es.upm.miw.apaw_practice.domain.services.tv_series.PlayerSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(PlayerSeriesResource.PLAYER_SERIES)
public class PlayerSeriesResource {
    static final String PLAYER_SERIES = "/player_series";

    private final PlayerSeriesService playerSeriesService;

    @Autowired
    public PlayerSeriesResource(PlayerSeriesService playerSeriesService) {
        this.playerSeriesService = playerSeriesService;
    }

    @GetMapping()
    public Stream<Player> findAll() {
        return this.playerSeriesService.findAll();
    }
}
