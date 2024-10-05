package es.upm.miw.apaw_practice.adapters.mongodb.competition;

import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.CompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.OrganizationRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.PlayerTeamRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.daos.TeamCompetitionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.CompetitionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.OrganizationEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.PlayerTeamEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.competition.entities.TeamCompetitionEntity;
import es.upm.miw.apaw_practice.domain.models.competition.Organization;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class CompetitionSeederService {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private PlayerTeamRepository playerTeamRepository;
    @Autowired
    private TeamCompetitionRepository teamCompetitionRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Competition Initial Load -----------");
        PlayerTeamEntity[] playerTeams = {
                new PlayerTeamEntity(84.32, 180.79, new BigDecimal("1.23")),
                new PlayerTeamEntity(76.34, 156.76, new BigDecimal("6.83")),
                new PlayerTeamEntity(90.93, 195.21, new BigDecimal("4.22")),
                new PlayerTeamEntity(76.09, 175.33, new BigDecimal("3.95")),
                new PlayerTeamEntity(79.43, 154.99, new BigDecimal("2.08")),
                new PlayerTeamEntity(86.89, 185.24, new BigDecimal("10.10"))
        };
        this.playerTeamRepository.saveAll(Arrays.asList(playerTeams));
        TeamCompetitionEntity[] teamCompetitions = {
                new TeamCompetitionEntity("Atlético de Madrid", 37, "Diego Pablo Simeone", List.of(playerTeams[0], playerTeams[1], playerTeams[2])),
                new TeamCompetitionEntity("Club Deportivo Sigüenza", 3, "Diego Delgado Pérez", List.of(playerTeams[4], playerTeams[5])),
                new TeamCompetitionEntity("Cabeza de mula FC", 1, "Pablo Carabaña Lozano", List.of(playerTeams[3]))
        };
        this.teamCompetitionRepository.saveAll(Arrays.asList(teamCompetitions));
        OrganizationEntity[] organizations = {
                new OrganizationEntity(new Organization("UEFA", LocalDateTime.of(1867, 8, 12, 12, 32), true)),
                new OrganizationEntity(new Organization("FEMAFUSA", LocalDateTime.of(1935, 4, 10, 22, 45), true)),
        };
        this.organizationRepository.saveAll(Arrays.asList(organizations));
        CompetitionEntity[] competitions = {
                new CompetitionEntity("Champions League", LocalDate.of(2024, 9, 15), LocalDate.of(2025, 6, 23), List.of(teamCompetitions[0]), organizations[0]),
                new CompetitionEntity("Europa League", LocalDate.of(2024, 9, 19), LocalDate.of(2025, 6, 12), List.of(teamCompetitions[1], teamCompetitions[2]), organizations[1]),
                new CompetitionEntity("El chirincirco", LocalDate.of(2024, 1, 9), LocalDate.of(2025, 1, 24), List.of(teamCompetitions[1], teamCompetitions[0]), organizations[1]),
        };
        this.competitionRepository.saveAll(Arrays.asList(competitions));
    }

    public void deleteAll() {
        this.playerTeamRepository.deleteAll();
        this.teamCompetitionRepository.deleteAll();
        this.organizationRepository.deleteAll();
        this.competitionRepository.deleteAll();
    }
}
