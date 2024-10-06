package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.CustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.GameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.MembershipRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.PlaySessionRepository;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.CustomerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.MembershipEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlaySessionEntity;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BoardgameCafeSeederService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private PlaySessionRepository playSessionRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Boardgame Cafe Initial Load -----------");

        CustomerEntity[] customers = {
                new CustomerEntity("joralvmel@gmail.com", "Jorge", LocalDate.of(1997, 9, 23), true),
                new CustomerEntity("ander@gmail.com", "Ander", LocalDate.of(2004, 1, 24), false),
                new CustomerEntity("giselle@hotmail.com", "Giselle", LocalDate.of(1997, 5, 16), true),
                new CustomerEntity("cesar@yahoo.com", "Cesar", LocalDate.of(1998, 2, 6), true),
                new CustomerEntity("munira@outlook.com", "Munira", LocalDate.of(1964, 1, 19), false)
        };
        this.customerRepository.saveAll(Arrays.asList(customers));

        GameEntity[] games = {
                new GameEntity("CATAN", 4, "Strategy", 4),
                new GameEntity("Gloomhaven", 4, "Strategy", 1),
                new GameEntity("Exploding Kittens", 5, "Party", 6),
                new GameEntity("Zombicide", 6, "Co-op", 2),
                new GameEntity("Azul", 4, "Family", 3),
                new GameEntity("Warewolf", 24, "Party", 2),
        };
        this.gameRepository.saveAll(Arrays.asList(games));

        MembershipEntity[] memberships = {
                new MembershipEntity(0, "Gold", 1, new BigDecimal("2.0")),
                new MembershipEntity(1, "Silver", 3, new BigDecimal("2.5")),
                new MembershipEntity(2, "Bronze", 6, new BigDecimal("3.0")),
                new MembershipEntity(3, "Platinum", 12, new BigDecimal("3.5")),
        };
        this.membershipRepository.saveAll(Arrays.asList(memberships));

        PlaySessionEntity[] playSessions = {
                new PlaySessionEntity(0, 5, LocalDateTime.of(2024, 9, 15, 13, 10), List.of(games[0], games[1])),
                new PlaySessionEntity(1, 2, LocalDateTime.of(2024, 9, 18, 19, 15), List.of(games[2])),
                new PlaySessionEntity(2, 4, LocalDateTime.of(2024, 9, 25, 15, 52), List.of(games[4])),
                new PlaySessionEntity(3, 8, LocalDateTime.of(2024, 10, 5, 21, 2), List.of(games[1], games[2])),
                new PlaySessionEntity(4, 4, LocalDateTime.of(2024, 10, 5, 22, 0), List.of(games[1], games[3], games[4]))
        };
        this.playSessionRepository.saveAll(Arrays.asList(playSessions));
    }

    public void deleteAll() {
        this.customerRepository.deleteAll();
        this.gameRepository.deleteAll();
        this.membershipRepository.deleteAll();
        this.playSessionRepository.deleteAll();
    }
}
