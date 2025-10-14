package es.upm.miw.apaw.adapters.mongodb.football.daos;

import es.upm.miw.apaw.adapters.mongodb.football.entities.MatchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class MatchRepositoryIT {

    @Autowired
    private MatchRepository matchRepository;

    @Test
    void testFindByMatchId_ok() {
        Optional<MatchEntity> opt = this.matchRepository.findByMatchId(1L);
        assertTrue(opt.isPresent());
        MatchEntity match = opt.get();
        assertThat(match.getHomeGoals()).isEqualTo(2);
        assertThat(match.getAwayGoals()).isEqualTo(1);
        assertThat(match.getClubs().size()).isEqualTo(2);
    }

    @Test
    void testFindByMatchId_notFound() {
        assertThat(this.matchRepository.findById(999L)).isEmpty();
    }

    @Test
    void testExistsByMatchId_ok() {
        assertThat(this.matchRepository.existsByMatchId(1L)).isTrue();
    }

    @Test
    void testExistsByMatchId_notFound() {
        assertThat(this.matchRepository.existsByMatchId(999L)).isFalse();
    }
}
