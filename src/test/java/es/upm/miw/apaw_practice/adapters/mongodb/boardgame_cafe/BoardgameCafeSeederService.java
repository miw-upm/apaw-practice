package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.CustomerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.GameRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.MembershipRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.PlayServiceRepository;

import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.CustomerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.GameEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.MembershipEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.PlayServiceEntity;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class BoardgameCafeSeederService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private PlayServiceRepository playServiceRepository;

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
                new GameEntity("Zombicide", 6, "Thematic", 2),
                new GameEntity("Azul", 4, "Family", 3),
                new GameEntity("Warewolf", 24, "Party", 2),
        };
        this.gameRepository.saveAll(Arrays.asList(games));

        MembershipEntity[] memberships = {
                new MembershipEntity(1, "Gold", LocalDate.of(2024, 1, 1), LocalDate.of(2023, 1, 1)),
                new MembershipEntity(2, "Silver", LocalDate.of(2024, 1, 1), LocalDate.of(2023, 1, 1)),
                new MembershipEntity(3, "Bronze", LocalDate.of(2024, 1, 1), LocalDate.of(2023, 1, 1)),
        };
        this.membershipRepository.saveAll(Arrays.asList(memberships));

        PlayServiceEntity[] playServices = {
                new PlayServiceEntity(1, new BigDecimal("10.00"), LocalDateTime.of(2024, 9, 15, 18, 0), List.of(games[0], games[1])),
                new PlayServiceEntity(2, new BigDecimal("15.00"), LocalDateTime.of(2024, 9, 18, 19, 0), List.of(games[2])),
                new PlayServiceEntity(3, new BigDecimal("20.00"), LocalDateTime.of(2024, 9, 25, 20, 0), List.of(games[4])),
                new PlayServiceEntity(4, new BigDecimal("25.00"), LocalDateTime.of(2024, 10, 2, 21, 0), List.of(games[0], games[2])),
                new PlayServiceEntity(5, new BigDecimal("30.00"), LocalDateTime.of(2024, 10, 5, 22, 0), List.of(games[1], games[3], games[4]))
        };
        this.playServiceRepository.saveAll(Arrays.asList(playServices));
    }
}
