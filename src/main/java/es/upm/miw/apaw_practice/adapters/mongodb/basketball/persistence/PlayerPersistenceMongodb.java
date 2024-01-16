package es.upm.miw.apaw_practice.adapters.mongodb.basketball.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.PlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.PlayerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.basketball.Player;
import es.upm.miw.apaw_practice.domain.persistence_ports.basketball.PlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("playerPersistence")
public class PlayerPersistenceMongodb implements PlayerPersistence {
    private final PlayerRepository playerRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public PlayerPersistenceMongodb(PlayerRepository playerRepository, BasketRepository basketRepository) {
        this.playerRepository = playerRepository;
        this.basketRepository = basketRepository;
    }
    @Override
    public Player readByEmail(String email) {
        return this.playerRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Player email: " + email))
                .toPlayer();
    }

    @Override
    public String readByBasketId(String basket_id) {
        PlayerEntity player = this.basketRepository.findByIdentifier(basket_id)
                .orElseThrow(() -> new NotFoundException("Basket id: " + basket_id))
                .getPlayer();
        return player.getPosition();
    }

    @Override
    public void delete(String email) {
        this.playerRepository.deleteByEmail(email);
    }

}
