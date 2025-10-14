package es.upm.miw.apaw.adapters.mongodb.football.daos;

import es.upm.miw.apaw.adapters.mongodb.football.entities.FootballPlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class FootballPlayerRepositoryIT {

    @Autowired
    private FootballPlayerRepository footballPlayerRepository;

    @Test
    void testFindByNickname_ok() {
        Optional<FootballPlayerEntity> opt = this.footballPlayerRepository.findByNickname("Rafa");
        assertTrue(opt.isPresent());
        FootballPlayerEntity player = opt.get();
        assertThat(player.getNickname()).isEqualTo("Rafa");
        assertThat(player.getBirthDate()).isEqualTo(LocalDate.of(1995, 3, 10));
        assertThat(player.getGoalsScored()).isEqualTo(12);
    }

    @Test
    void testFindByNickname_notFound() {
        assertThat(this.footballPlayerRepository.findByNickname("no existe")).isEmpty();
    }

    @Test
    void testExistsByPlayerId_ok() {
        assertThat(this.footballPlayerRepository.existsByPlayerId(1L)).isTrue();
    }

    @Test
    void testExistsByPlayerId_notFound() {
        assertThat(this.footballPlayerRepository.existsByPlayerId(999L)).isFalse();
    }
}
