package es.upm.miw.apaw_practice.domain.services.videogame;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.videogame.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.videogame.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PlayerService {

    private final PlayerPersistence playerPersistence;

    @Autowired
    public PlayerService(PlayerPersistence playerPersistence) {
        this.playerPersistence = playerPersistence;
    }

    public Player read(String playerName){
        return this.playerPersistence.readyByPlayerName(playerName);
    }

    public void delete(String playerName){
        this.playerPersistence.delete(playerName);
    }

    public Stream<String> findVideoGameAliasByPlayerName(String playerName){
        return this.playerPersistence.findVideoGameAliasByPlayerName(playerName);
    }

    public Player create(Player player){
        this.assertPlayerNotExist(player.getPlayerName());
        return this.playerPersistence.create(player);
    }

    public void assertPlayerNotExist(String playerName){
        if(this.playerPersistence.existsPlayer(playerName)){
            throw new ConflictException("Player name exists: " + playerName);
        }
    }
}