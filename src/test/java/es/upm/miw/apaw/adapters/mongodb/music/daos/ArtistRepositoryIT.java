package es.upm.miw.apaw.adapters.mongodb.music.daos;

import es.upm.miw.apaw.adapters.mongodb.music.entities.ArtistEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ArtistRepositoryIT {

    @Autowired private ArtistRepository artistRepository;
    @Autowired private MusicSeeder musicSeeder;

    @BeforeEach
    void resetDb() {
        musicSeeder.deleteAll();
        musicSeeder.seedDatabase();
    }

    @Test
    void testFindByIdOk() {
        Optional<ArtistEntity> opt = this.artistRepository.findById("Tame Impala");
        assertThat(opt).isPresent();

        ArtistEntity entity = opt.get();
        assertThat(entity.getName()).isEqualTo("Tame Impala");
        assertThat(entity.getMonthlyListeners()).isGreaterThan(0);
        assertThat(entity.getUserId()).isNotBlank();
    }

    @Test
    void testFindByIdNotFound() {
        assertThat(this.artistRepository.findById("Unknown Artist")).isNotPresent();
    }
}
