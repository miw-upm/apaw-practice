package es.upm.miw.apaw_practice.adapters.mongodb.basketball;

import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketBallRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketMatchRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketPlayerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.daos.BasketSeasonRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketBallEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketMatchEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketPlayerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.basketball.entities.BasketSeasonEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BasketballSeederService {

    @Autowired
    private BasketPlayerRepository basketPlayerRepository;
    @Autowired
    private BasketMatchRepository basketMatchRepository;
    @Autowired
    private BasketBallRepository basketBallRepository;
    @Autowired
    private BasketSeasonRepository basketSeasonRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Basketball Initial Load -----------");

        BasketPlayerEntity[] players = {
                new BasketPlayerEntity("12345678A", "Lebron", 7, 30),
                new BasketPlayerEntity("23456789B", "Stephen", 10, 20),
                new BasketPlayerEntity("34567890C", "Kobe", 23, 50),
                new BasketPlayerEntity("45678901D", "Michael", 12, 15),
                new BasketPlayerEntity("56789012E", "Jordan", 3, 40)
        };
        this.basketPlayerRepository.saveAll(Arrays.asList(players));

        BasketMatchEntity[] matches = {
                new BasketMatchEntity(1,LocalDateTime.of(2023, 10, 12, 18, 0), "Stadium A", List.of(players[0], players[1])),
                new BasketMatchEntity(2,LocalDateTime.of(2023, 10, 13, 19, 0), "Stadium B", List.of(players[2], players[3])),
                new BasketMatchEntity(3,LocalDateTime.of(2023, 10, 14, 20, 0), "Stadium C", List.of(players[0], players[3], players[4])),
                new BasketMatchEntity(5,LocalDateTime.of(2023, 10, 14, 20, 0), "Stadium D", List.of(players[0], players[1], players[2]))
        };
        this.basketMatchRepository.saveAll(Arrays.asList(matches));

        BasketBallEntity[] basketballs = {
                new BasketBallEntity(1, "Nike", new BigDecimal("50.0"), matches[0]),
                new BasketBallEntity(2, "Adidas", new BigDecimal("60.0"), matches[1]),
                new BasketBallEntity(3, "Spalding", new BigDecimal("70.0"), matches[2])
        };
        this.basketBallRepository.saveAll(Arrays.asList(basketballs));

        BasketSeasonEntity[] seasons = {
                new BasketSeasonEntity(1, 2022, 2023, "NBA", List.of(matches[0], matches[1])),
                new BasketSeasonEntity(2, 2023, 2024, "Euroleague", List.of(matches[2])),
                new BasketSeasonEntity(3, 2021, 2022, "ACB", List.of(matches[0], matches[1], matches[2]))
        };
        this.basketSeasonRepository.saveAll(Arrays.asList(seasons));
    }

    public void deleteAll() {
        this.basketSeasonRepository.deleteAll();
        this.basketBallRepository.deleteAll();
        this.basketMatchRepository.deleteAll();
        this.basketPlayerRepository.deleteAll();
    }
}
