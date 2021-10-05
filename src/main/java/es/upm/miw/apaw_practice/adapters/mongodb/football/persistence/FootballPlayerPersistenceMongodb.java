package es.upm.miw.apaw_practice.adapters.mongodb.football.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.FootballPlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.FootballPlayerEntity;
import es.upm.miw.apaw_practice.domain.models.football.FootballPlayer;
import es.upm.miw.apaw_practice.domain.persistence_ports.football.FootballPlayerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("footballPlayerPersistence")
public class FootballPlayerPersistenceMongodb implements FootballPlayerPersistence {
    private final FootballPlayerRepository footballPlayerRepository;

    @Autowired
    public FootballPlayerPersistenceMongodb(FootballPlayerRepository footballPlayerRepository) {
        this.footballPlayerRepository = footballPlayerRepository;
    }
    
    @Override
    public Stream<FootballPlayer> readAll() {
        return this.footballPlayerRepository.findAll().stream()
                .map(FootballPlayerEntity::toFootballPlayer);
    }
}
