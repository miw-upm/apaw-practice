package es.upm.miw.apaw_practice.domain.persistence_ports.tv_series;

import es.upm.miw.apaw_practice.domain.models.tv_series.Player;

import java.util.stream.Stream;

public interface PlayerSeriesPersistence {

    Stream<Player> readAll();
}
