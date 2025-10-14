package es.upm.miw.apaw.adapters.mongodb.football.daos;

import es.upm.miw.apaw.adapters.mongodb.football.entities.StadiumEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class StadiumRepositoryIT {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Test
    void testFindByOfficialName_ok() {
        Optional<StadiumEntity> opt = this.stadiumRepository.findByOfficialName("Salamanca Stadium");
        assertTrue(opt.isPresent());
        StadiumEntity stadium = opt.get();
        assertThat(stadium.getOfficialName()).isEqualTo("Salamanca Stadium");
        assertThat(stadium.getCapacity()).isEqualTo(40000);
        assertThat(stadium.getRoof()).isTrue();
    }

    @Test
    void testFindByOfficialName_notFound() {
        assertThat(this.stadiumRepository.findByOfficialName("no existe")).isEmpty();
    }

    @Test
    void testExistsByStadiumId_ok() {
        assertThat(this.stadiumRepository.existsByStadiumId(1L)).isTrue();
    }

    @Test
    void testExistsByStadiumId_notFound() {
        assertThat(this.stadiumRepository.existsByStadiumId(999L)).isFalse();
    }
}
