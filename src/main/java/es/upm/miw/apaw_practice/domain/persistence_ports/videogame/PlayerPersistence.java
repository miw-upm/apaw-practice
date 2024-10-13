package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PlayerPersistence {
    Stream<Player> readAll();
    Player create(Player player);
    Player update(String playerName, Player player);
    boolean existsPlayer(String playerName);
}
