package es.upm.miw.apaw_practice.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.PlayerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.VideoGamerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("playerPersistence")
public class PlayerPersistenceMongodb implements PlayerPersistence {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerPersistenceMongodb(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Stream<Player> readAll() {
        return this.playerRepository.findAll().stream()
                .map(PlayerEntity::toPlayer);
    }

    @Override
    public Player readyByPlayerName(String playerName) {
        return this.playerRepository.findByPlayerName(playerName)
                .orElseThrow(() -> new NotFoundException("Player playerName: " + playerName))
                .toPlayer();
    }

    @Override
    public void delete(String playerName) {
        this.playerRepository.deleteByPlayerName(playerName);
    }

    @Override
    public Stream<String> findVideoGameAliasByPlayerName(String playerName) {
        return this.playerRepository.findAll().stream()
                .filter(playerEntity ->
                        playerEntity.getPlayerName().equals(playerName))
                .flatMap(playerEntity ->
                playerEntity.getConsoleEntity().getVideoGameEntities().stream())
                .map(VideoGamerEntity::getVideoGameAlias)
                .distinct();
    }
}
