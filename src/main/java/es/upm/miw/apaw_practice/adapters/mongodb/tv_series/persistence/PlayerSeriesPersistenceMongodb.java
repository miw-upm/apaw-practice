package es.upm.miw.apaw_practice.adapters.mongodb.tv_series.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.daos.PlayerSeriesRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tv_series.entities.PlayerSeriesEntity;
import es.upm.miw.apaw_practice.domain.models.tv_series.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.tv_series.PlayerSeriesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("playerSeriesPersistence")
public class PlayerSeriesPersistenceMongodb implements PlayerSeriesPersistence {

    private final PlayerSeriesRepository playerSeriesRepository;

    @Autowired
    public PlayerSeriesPersistenceMongodb(PlayerSeriesRepository playerSeriesRepository) {
        this.playerSeriesRepository = playerSeriesRepository;
    }

    @Override
    public Stream<Player> readAll() {
        return playerSeriesRepository.findAll().stream()
                .map(PlayerSeriesEntity::toPlayer);
    }
}
