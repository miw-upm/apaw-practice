package es.upm.miw.apaw_practice.domain.persistence_ports.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Player;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PlayerSeriesPersistence {

    Stream<Player> readAll();
}
