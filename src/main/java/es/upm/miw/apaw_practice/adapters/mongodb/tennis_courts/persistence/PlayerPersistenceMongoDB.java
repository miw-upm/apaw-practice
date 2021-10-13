package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities.PlayerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.tennis_courts.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.tennis_courts.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("playerPersistence")
public class PlayerPersistenceMongoDB implements PlayerPersistence {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerPersistenceMongoDB(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public void create(Player player) {
        this.playerRepository
                .save(new PlayerEntity(player));
    }

    @Override
    public Player read(String dni){
        return this.playerRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Player DNI: " + dni))
                .toPlayer();
    }
}
