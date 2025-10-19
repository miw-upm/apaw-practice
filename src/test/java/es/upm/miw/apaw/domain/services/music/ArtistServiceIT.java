package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.ArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.entities.ArtistEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class ArtistServiceIT {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MusicSeeder musicSeeder;

    @BeforeEach
    void resetDb() {
        musicSeeder.deleteAll();
        musicSeeder.seedDatabase();
    }

    @Test
    void testReadByNameOk() {
        String name = "Tame Impala";

        Optional<ArtistEntity> optional = this.artistRepository.findById(name);
        assertThat(optional).isPresent();

        Artist artist = this.artistService.readByName(name);

        assertThat(artist.getName()).isEqualTo(name);
        assertThat(artist.getUser()).isNotNull(); // user viene del micro externo
        assertThat(artist.getMonthlyListeners()).isGreaterThan(0);
    }

    @Test
    void testReadByNameNotFound() {
        assertThatThrownBy(() -> this.artistService.readByName("Unknown Band"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Artist not found");
    }
}
