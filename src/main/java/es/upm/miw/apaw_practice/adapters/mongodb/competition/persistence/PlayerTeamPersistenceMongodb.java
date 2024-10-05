package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.PlayerTeamRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.PlayerTeamEntity;
import es.upm.miw.apaw_practice.domain.models.competition.PlayerTeam;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.PlayerTeamPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("playerTeamPersistence")
public class PlayerTeamPersistenceMongodb implements PlayerTeamPersistence {

    private final PlayerTeamRepository playerTeamRepository;

    @Autowired
    public PlayerTeamPersistenceMongodb(PlayerTeamRepository playerTeamRepository) {
        this.playerTeamRepository = playerTeamRepository;
    }

    @Override
    public void delete(String id) {
        this.playerTeamRepository.deleteById(id);
    }

    @Override
    public Stream<PlayerTeam> readAll() {
        return this.playerTeamRepository.findAll().stream()
                .map(PlayerTeamEntity::toPlayerTeam);
    }
}
