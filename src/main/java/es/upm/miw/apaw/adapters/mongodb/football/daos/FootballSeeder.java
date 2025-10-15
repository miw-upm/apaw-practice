package es.upm.miw.apaw.adapters.mongodb.football.daos;


import es.upm.miw.apaw.adapters.mongodb.football.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class FootballSeeder {

    private final FootballClubRepository clubRepository;
    private final FootballPlayerRepository playerRepository;
    private final StadiumRepository stadiumRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public FootballSeeder(FootballClubRepository clubRepository,
                          FootballPlayerRepository playerRepository,
                          StadiumRepository stadiumRepository,
                          MatchRepository matchRepository) {
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
        this.stadiumRepository = stadiumRepository;
        this.matchRepository = matchRepository;
    }

    public void seedDatabase() {
        log.warn("------- Football Initial Seeder -----------");

        StadiumEntity[] stadiums = {
                StadiumEntity.builder()
                        .stadiumId(UUID.randomUUID())
                        .officialName("Salamanca Stadium")
                        .capacity(40000)
                        .roof(true)
                        .build(),
                StadiumEntity.builder()
                        .stadiumId(UUID.randomUUID())
                        .officialName("Madrid Arena")
                        .capacity(65000)
                        .roof(false)
                        .build()
        };
        this.stadiumRepository.saveAll(Arrays.asList(stadiums));

        FootballPlayerEntity[] players = {
                FootballPlayerEntity.builder()
                        .playerId(1L)
                        .nickname("Rafa")
                        .birthDate(LocalDate.of(1995, 3, 10))
                        .goalsScored(12)
                        .build(),
                FootballPlayerEntity.builder()
                        .playerId(2L)
                        .nickname("Luis")
                        .birthDate(LocalDate.of(1998, 7, 25))
                        .goalsScored(9)
                        .build(),
                FootballPlayerEntity.builder()
                        .playerId(3L)
                        .nickname("Carlos")
                        .birthDate(LocalDate.of(1999, 1, 15))
                        .goalsScored(5)
                        .build()
        };
        this.playerRepository.saveAll(Arrays.asList(players));

        FootballClubEntity[] clubs = {
                FootballClubEntity.builder()
                        .clubId(1L)
                        .name("Salamanca FC")
                        .budget(new BigDecimal("4500000"))
                        .founded(LocalDate.of(1985, 6, 12))
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .players(List.of(players[0], players[1]))
                        .stadium(stadiums[0])
                        .build(),
                FootballClubEntity.builder()
                        .clubId(2L)
                        .name("Madrid United")
                        .budget(new BigDecimal("6200000"))
                        .founded(LocalDate.of(1972, 9, 18))
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .players(List.of(players[2]))
                        .stadium(stadiums[1])
                        .build()
        };
        this.clubRepository.saveAll(Arrays.asList(clubs));

        MatchEntity[] matches = {
                MatchEntity.builder()
                        .matchId(1L)
                        .dateTime(LocalDateTime.now().plusDays(3))
                        .homeGoals(2)
                        .awayGoals(1)
                        .clubs(List.of(clubs[0], clubs[1]))
                        .build(),
                MatchEntity.builder()
                        .matchId(2L)
                        .dateTime(LocalDateTime.now().plusDays(7))
                        .homeGoals(1)
                        .awayGoals(1)
                        .clubs(List.of(clubs[1], clubs[0]))
                        .build()
        };
        this.matchRepository.saveAll(Arrays.asList(matches));

        log.warn("-- Football Seeder Done --");
    }

    public void deleteAll() {
        this.matchRepository.deleteAll();
        this.clubRepository.deleteAll();
        this.playerRepository.deleteAll();
        this.stadiumRepository.deleteAll();
    }
}
