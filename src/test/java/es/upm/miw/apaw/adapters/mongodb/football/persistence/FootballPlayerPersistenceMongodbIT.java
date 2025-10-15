package es.upm.miw.apaw.adapters.mongodb.football.persistence;


import es.upm.miw.apaw.adapters.mongodb.football.daos.FootballSeeder;
import es.upm.miw.apaw.domain.models.football.FootballPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class FootballPlayerPersistenceMongodbIT {

    @Autowired
    private FootballPlayerPersistenceMongodb playerPersistence;
    @Autowired
    private FootballSeeder footballSeeder;

    @BeforeEach
    void resetDb() {
        footballSeeder.deleteAll();
        footballSeeder.seedDatabase();
    }

    @Test
    void testFindByNickname_ok() {
        Optional<FootballPlayer> player = this.playerPersistence.findByNickname("Rafa");
        assertThat(player).isPresent();
        assertThat(player.get().getNickname()).isEqualTo("Rafa");
        assertThat(player.get().getGoalsScored()).isEqualTo(12);
    }

    @Test
    void testFindByNickname_notFound() {
        Optional<FootballPlayer> player = this.playerPersistence.findByNickname("Unknown");
        assertThat(player).isEmpty();
    }

    @Test
    void testReadAll_ok() {
        List<FootballPlayer> players = this.playerPersistence.readAll();
        assertThat(players).hasSize(3);
        assertThat(players).extracting(FootballPlayer::getNickname)
                .containsExactlyInAnyOrder("Rafa", "Luis", "Carlos");
    }
}

