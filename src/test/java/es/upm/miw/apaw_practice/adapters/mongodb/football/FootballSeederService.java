package es.upm.miw.apaw_practice.adapters.mongodb.football;

import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.FootballPlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.MatchRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.PrincipalRefereeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.daos.StadiumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.FootballPlayerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.MatchEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.PrincipalRefereeEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.football.entities.StadiumEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FootballSeederService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private FootballPlayerRepository playerRepository;
    @Autowired
    private PrincipalRefereeRepository principalRefereeRepository;
    @Autowired
    private StadiumRepository stadiumRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Football Initial Load -----------");
        FootballPlayerEntity[] players = {
                new FootballPlayerEntity(Boolean.TRUE, 10, 24, "Cristiano"),
                new FootballPlayerEntity(Boolean.FALSE, 20, 22, "Ramos"),
                new FootballPlayerEntity(Boolean.FALSE, 0, 19, "Messi"),
                new FootballPlayerEntity(Boolean.TRUE, 2, 24, "Yuri"),
                new FootballPlayerEntity(Boolean.FALSE, 7, 35, "Courtois"),
                new FootballPlayerEntity(Boolean.FALSE, 8, 40, "Benzema")
        };
        this.playerRepository.saveAll(Arrays.asList(players));

        PrincipalRefereeEntity[] principalReferees = {
                new PrincipalRefereeEntity("Undiano", "Madrid", 34),
                new PrincipalRefereeEntity("Sergio", "Barcelona", 32),
                new PrincipalRefereeEntity("Jose", "Sevilla", 27)
        };
        this.principalRefereeRepository.saveAll(Arrays.asList(principalReferees));

        MatchEntity[] matches = {
                new MatchEntity(LocalDateTime.of(2021, 10, 20, 21, 0), "cloudy", 3, principalReferees[0], List.of(players[0], players[1], players[2])),
                new MatchEntity(LocalDateTime.of(2022, 1, 1, 18, 30), "rainy", 4, principalReferees[1], List.of(players[3], players[4], players[5])),
                new MatchEntity(LocalDateTime.of(2022, 6, 6, 22, 30), "clear", 5, principalReferees[2], List.of(players[0], players[3], players[4])),
                new MatchEntity(LocalDateTime.of(2022, 12, 9, 22, 45), "rainy", 6, principalReferees[0], List.of(players[1], players[2], players[5]))
        };
        this.matchRepository.saveAll(Arrays.asList(matches));

        StadiumEntity[] stadiums = {
                new StadiumEntity("Madrid", "Bernabeu", "Real Madrid", List.of(matches[0], matches[1], matches[2])),
                new StadiumEntity("Barcelona", "Camp Nou", "Futbol Club Barcelona", List.of(matches[3]))
        };
        this.stadiumRepository.saveAll(Arrays.asList(stadiums));
    }

    public void deleteAll() {
        this.matchRepository.deleteAll();
        this.playerRepository.deleteAll();
        this.principalRefereeRepository.deleteAll();
        this.stadiumRepository.deleteAll();
    }
}
