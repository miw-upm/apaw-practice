package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.CustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.GameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.MembershipRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.PlaySessionRepository;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.CustomerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.MembershipEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlaySessionEntity;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Game;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.PlaySession;
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

        MembershipEntity[] memberships = {
                new MembershipEntity(new Membership(0, "Bronze", 1, new BigDecimal("2.0")) ),
                new MembershipEntity(new Membership(1, "Silver", 3, new BigDecimal("2.5"))),
                new MembershipEntity(new Membership(2, "Gold", 6, new BigDecimal("3.0"))),
                new MembershipEntity(new Membership(3, "Platinum", 12, new BigDecimal("3.5"))),
        };
        this.membershipRepository.saveAll(Arrays.asList(memberships));

        CustomerEntity[] customers = {
                new CustomerEntity(new Customer("joralvmel@gmail.com", "Jorge", LocalDate.of(1997, 9, 23), true, memberships[2].toMembership())),
                new CustomerEntity(new Customer("ander@gmail.com", "Ander", LocalDate.of(2004, 1, 24), false, memberships[0].toMembership())),
                new CustomerEntity(new Customer("giselle@hotmail.com", "Giselle", LocalDate.of(1997, 5, 16), true, memberships[1].toMembership())),
                new CustomerEntity(new Customer("cesar@yahoo.com", "Cesar", LocalDate.of(1998, 2, 6), true, memberships[3].toMembership())),
                new CustomerEntity(new Customer("munira@outlook.com", "Munira", LocalDate.of(1964, 1, 19), false, memberships[0].toMembership()))
        };
        this.customerRepository.saveAll(Arrays.asList(customers));

        GameEntity[] games = {
                new GameEntity(new Game("CATAN", 4, "Strategy", 4)),
                new GameEntity(new Game("Gloomhaven", 4, "Strategy", 1)),
                new GameEntity(new Game("Exploding Kittens", 5, "Party", 6)),
                new GameEntity(new Game("Zombicide", 6, "Co-op", 2)),
                new GameEntity(new Game("Azul", 4, "Family", 3)),
                new GameEntity(new Game("Warewolf", 24, "Party", 2)),
        };
        this.gameRepository.saveAll(Arrays.asList(games));

        PlaySessionEntity[] playSessions = {
                new PlaySessionEntity(new PlaySession(0, 5, LocalDateTime.of(2024, 9, 15, 13, 10), List.of(games[0].toGame(), games[1].toGame()))),
                new PlaySessionEntity(new PlaySession(1, 2, LocalDateTime.of(2024, 9, 18, 19, 15), List.of(games[2].toGame()))),
                new PlaySessionEntity(new PlaySession(2, 4, LocalDateTime.of(2024, 9, 25, 15, 52), List.of(games[4].toGame()))),
                new PlaySessionEntity(new PlaySession(3, 8, LocalDateTime.of(2024, 10, 5, 21, 2), List.of(games[1].toGame(), games[2].toGame()))),
                new PlaySessionEntity(new PlaySession(4, 4, LocalDateTime.of(2024, 10, 5, 22, 0), List.of(games[1].toGame(), games[3].toGame(), games[4].toGame())))
        };
        this.playSessionRepository.saveAll(Arrays.asList(playSessions));
    }

    public void deleteAll() {
        this.customerRepository.deleteAll();
        this.gameRepository.deleteAll();
        this.playSessionRepository.deleteAll();
    }
}
