package es.upm.miw.apaw.adapters.mongodb.football.persistence;

import es.upm.miw.apaw.adapters.mongodb.football.daos.FootballPlayerRepository;
import es.upm.miw.apaw.adapters.mongodb.football.entities.FootballPlayerEntity;
import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import es.upm.miw.apaw.domain.persistenceports.football.FootballPlayerPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("footballPlayerPersistence")
public class FootballPlayerPersistenceMongodb implements FootballPlayerPersistence {

    private final FootballPlayerRepository playerRepository;

    public FootballPlayerPersistenceMongodb(FootballPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<FootballPlayer> findByNickname(String nickname) {
        return this.playerRepository.findByNickname(nickname)
                .map(FootballPlayerEntity::toFootballPlayer);
    }

    @Override
    public List<FootballPlayer> readAll() {
        return this.playerRepository.findAll().stream()
                .map(FootballPlayerEntity::toFootballPlayer)
                .toList();
    }
}
