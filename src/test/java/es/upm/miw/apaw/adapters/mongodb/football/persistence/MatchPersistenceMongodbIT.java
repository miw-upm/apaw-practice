package es.upm.miw.apaw.adapters.mongodb.football.persistence;


import es.upm.miw.apaw.adapters.mongodb.football.daos.FootballSeeder;
import es.upm.miw.apaw.domain.models.football.Match;
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
class MatchPersistenceMongodbIT {

    @Autowired
    private MatchPersistenceMongodb matchPersistence;
    @Autowired
    private FootballSeeder footballSeeder;

    @BeforeEach
    void resetDb() {
        footballSeeder.deleteAll();
        footballSeeder.seedDatabase();
    }

    @Test
    void testFindByMatchId_ok() {
        Optional<Match> match = this.matchPersistence.findByMatchId(1L);
        assertThat(match).isPresent();
        assertThat(match.get().getHomeGoals()).isEqualTo(2);
        assertThat(match.get().getAwayGoals()).isEqualTo(1);
        assertThat(match.get().getClubs()).hasSize(2);
    }

    @Test
    void testFindByMatchId_notFound() {
        Optional<Match> match = this.matchPersistence.findByMatchId(99L);
        assertThat(match).isEmpty();
    }

    @Test
    void testReadAll_ok() {
        List<Match> matches = this.matchPersistence.readAll();
        assertThat(matches).hasSize(2);
    }
}

