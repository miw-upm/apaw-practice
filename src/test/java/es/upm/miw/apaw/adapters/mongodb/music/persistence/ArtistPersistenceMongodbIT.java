package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.ArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.domain.models.music.Artist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ArtistPersistenceMongodbIT {

    @Autowired
    private ArtistPersistenceMongodb artistPersistenceMongodb;

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
        Stream<Artist> result = this.artistPersistenceMongodb.readByName("Tame Impala");
        List<Artist> artists = result.collect(Collectors.toList());

        assertThat(artists).isNotEmpty();
        Artist artist = artists.get(0);
        assertThat(artist.getName()).isEqualTo("Tame Impala");
        assertThat(artist.getMonthlyListeners()).isGreaterThan(0);
        assertThat(artist.getUser()).isNotNull();
    }

    @Test
    void testReadByNameEmpty() {
        Stream<Artist> result = this.artistPersistenceMongodb.readByName("Nonexistent Artist");
        assertThat(result).isEmpty();
    }

    @Test
    void testSeedReload() {
        musicSeeder.deleteAll();
        musicSeeder.seedDatabase();

        Stream<Artist> result = this.artistPersistenceMongodb.readByName("Daft Punk");
        assertThat(result).isNotEmpty();
    }
}
