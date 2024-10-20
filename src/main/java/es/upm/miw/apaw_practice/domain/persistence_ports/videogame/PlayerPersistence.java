package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PlayerPersistence {
    Player readyByPlayerName(String playerName);
    void delete(String playerName);
    Stream<Player> readAll();
    Stream<String>findVideoGameAliasByPlayerName(String playerName);
    boolean existsPlayer(String playerName);
    Player create(Player player);
    Player update(String playerName, Player player);
}
