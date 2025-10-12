package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.MartialArtEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class MartialArtRepositoryIT {

    @Autowired
    private MartialArtRepository martialArtRepository;

    @Test
    void testFindByDiscipline_ok() {
        Optional<MartialArtEntity> opt = this.martialArtRepository.findByDiscipline("BJJ");
        assertTrue(opt.isPresent());
        MartialArtEntity martialArt = opt.get();
        assertThat(martialArt.getDiscipline()).isEqualTo("BJJ");
        assertThat(martialArt.getOrigin()).isEqualTo("Brazil");
        assertThat(martialArt.getDescription()).isEqualTo("Brazilian Jiu-Jitsu");
        assertThat(martialArt.getStriking()).isFalse();
        assertThat(martialArt.getGrappling()).isTrue();
    }

    @Test
    void testFindByDiscipline_notFound() {
        assertThat(this.martialArtRepository.findByDiscipline("no-existe")).isEmpty();
    }
}
