package es.upm.miw.apaw.adapters.mongodb.football.daos;

import es.upm.miw.apaw.adapters.mongodb.football.entities.StadiumEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class StadiumRepositoryIT {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Test
    void testFindByOfficialName_ok() {
        Optional<StadiumEntity> opt = this.stadiumRepository.findByOfficialNameIgnoreCase("Salamanca Stadium");
        assertTrue(opt.isPresent());
        StadiumEntity stadium = opt.get();
        assertThat(stadium.getOfficialName()).isEqualTo("Salamanca Stadium");
        assertThat(stadium.getCapacity()).isEqualTo(40000);
        assertThat(stadium.getRoof()).isTrue();
    }

    @Test
    void testFindByOfficialName_notFound() {
        assertThat(this.stadiumRepository.findByOfficialNameIgnoreCase("no existe")).isEmpty();
    }

    @Test
    void testExistsByStadiumId_ok() {
        UUID stadiumId = this.stadiumRepository.findAll().get(0).getStadiumId();
        assertThat(this.stadiumRepository.existsById(stadiumId)).isTrue();
    }
    @Test
    void testExistsByStadiumId_notFound() {
        UUID randomId = UUID.randomUUID();
        assertThat(this.stadiumRepository.existsById(randomId)).isFalse();
    }
}
