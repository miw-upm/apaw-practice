package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.FighterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class FighterRepositoryIT {

    @Autowired
    private FighterRepository fighterRepository;

    @Test
    void testFindByNickname_ok() {
        Optional<FighterEntity> opt = this.fighterRepository.findByNickname("Spider");
        assertTrue(opt.isPresent());
        FighterEntity fighter = opt.get();
        assertThat(fighter.getNickname()).isEqualTo("Spider");
        assertThat(fighter.getName()).isEqualTo("Anderson");
        assertThat(fighter.getLastName()).isEqualTo("Silva");
        assertThat(fighter.getCountry()).isEqualTo("Brazil");
        assertThat(fighter.getWins()).isEqualTo(34);
        assertThat(fighter.getLosses()).isEqualTo(11);
    }

    @Test
    void testFindByNickname_notFound() {
        assertThat(this.fighterRepository.findByNickname("no-existe")).isEmpty();
    }
}
