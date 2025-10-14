package es.upm.miw.apaw.adapters.mongodb.football.daos;

import es.upm.miw.apaw.adapters.mongodb.football.entities.FootballClubEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class FootballClubRepositoryIT {

    @Autowired
    private FootballClubRepository footballClubRepository;

    @Test
    void testFindByName_ok() {
        Optional<FootballClubEntity> opt = this.footballClubRepository.findByName("Salamanca FC");
        assertTrue(opt.isPresent());
        FootballClubEntity club = opt.get();
        assertThat(club.getName()).isEqualTo("Salamanca FC");
        assertThat(club.getBudget()).isEqualTo(new BigDecimal("4500000"));
        assertThat(club.getFounded()).isEqualTo(LocalDate.of(1985, 6, 12));
        assertThat(club.getPlayers().size()).isEqualTo(2);
        assertThat(club.getStadium().getOfficialName()).isEqualTo("Salamanca Stadium");
    }

    @Test
    void testFindByName_notFound() {
        assertThat(this.footballClubRepository.findByName("no existe")).isEmpty();
    }

    @Test
    void testExistsByClubId_ok() {
        assertThat(this.footballClubRepository.existsByClubId(1L)).isTrue();
    }

    @Test
    void testExistsByClubId_notFound() {
        assertThat(this.footballClubRepository.existsByClubId(999L)).isFalse();
    }
}
