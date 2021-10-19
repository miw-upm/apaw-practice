package es.upm.miw.apaw_practice.domain.services.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.PlayerSeriesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PlayerSeriesService {

    private final PlayerSeriesPersistence playerSeriesPersistence;

    @Autowired
    public PlayerSeriesService(PlayerSeriesPersistence playerSeriesPersistence) {
        this.playerSeriesPersistence = playerSeriesPersistence;
    }

    public Stream<Player> readAll() {
        return this.playerSeriesPersistence.readAll();
    }
}
