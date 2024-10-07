package es.upm.miw.apaw_practice.adapters.mongodb.competition.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.PlayerTeamRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.TeamCompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.PlayerTeamEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.TeamCompetitionEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.competition.TeamCompetition;
import es.upm.miw.apaw_practice.domain.persistence_ports.competition.TeamCompetitionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("teamCompetitionPersistence")
public class TeamCompetitionPersistenceMongodb implements TeamCompetitionPersistence {
    private final TeamCompetitionRepository teamCompetitionRepository;
    private final PlayerTeamRepository playerTeamRepository;

    @Autowired
    public TeamCompetitionPersistenceMongodb(TeamCompetitionRepository teamCompetitionRepository, PlayerTeamRepository playerTeamRepository) {
        this.teamCompetitionRepository = teamCompetitionRepository;
        this.playerTeamRepository = playerTeamRepository;
    }

    @Override
    public Stream<TeamCompetition> readAll() {
        return this.teamCompetitionRepository.findAll().stream()
                .map(TeamCompetitionEntity::toTeamCompetition);
    }

    @Override
    public TeamCompetition readById(String id) {
        return this.teamCompetitionRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Team competition with id " + id + " not found"))
                .toTeamCompetition();
    }

    @Override
    public TeamCompetition update(TeamCompetition teamCompetition) {
        TeamCompetitionEntity teamCompetitionEntity = this.teamCompetitionRepository
                .findByNameTeamCompetition(teamCompetition.getNameTeamCompetition())
                .orElseThrow(() -> new NotFoundException("Team competition with name " + teamCompetition.getNameTeamCompetition() + " not found"));
        List<PlayerTeamEntity> playerTeamEntities = teamCompetition.getPlayerTeams().stream()
                .map(playerTeam -> new PlayerTeamEntity(
                                playerTeam.getWeight(),
                                playerTeam.getHeight(),
                                playerTeam.getSalary()
                        )
                ).collect(Collectors.toList());
        playerTeamRepository.saveAll(playerTeamEntities);
        teamCompetitionEntity.setPlayerTeamsEntity(playerTeamEntities);
        return this.teamCompetitionRepository.save(teamCompetitionEntity).toTeamCompetition();
    }
}
