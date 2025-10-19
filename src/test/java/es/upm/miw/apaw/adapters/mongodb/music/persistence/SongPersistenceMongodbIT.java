package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.daos.SongRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.SongEntity;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.music.Song;
import es.upm.miw.apaw.domain.models.music.Style;
import es.upm.miw.apaw.domain.persistenceports.music.SongPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class SongPersistenceMongodbIT {

    @Autowired
    private SongPersistence songPersistence;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private MusicSeeder musicSeeder;

    @BeforeEach
    void setUp() {
        this.musicSeeder.deleteAll();
        this.musicSeeder.seedDatabase();
    }

    @Test
    void testCreateOk() {
        String isrc = "FRX123ABC9999";
        Song song = Song.builder()
                .isrc(isrc)
                .title("Brand New Song")
                .durationSeconds(215)
                .style(Style.builder().genre("electro").popularityIndex(70).mood("energetic").build())
                .build();

        this.songPersistence.create(song);

        SongEntity saved = this.songRepository.findById(isrc).orElseThrow();
        assertThat(saved.getTitle()).isEqualTo("Brand New Song");
        assertThat(saved.getDurationSeconds()).isEqualTo(215);
        assertThat(saved.getStyleGenre()).isEqualTo("electro");
    }

    @Test
    void testCreateConflict() {
        String isrc = "FRX123ABC9999";
        this.songPersistence.create(Song.builder()
                .isrc(isrc).title("First").durationSeconds(200).build());
        assertThatThrownBy(() ->
                this.songPersistence.create(Song.builder()
                        .isrc(isrc).title("Duplicate").durationSeconds(180).build())
        )
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Song already exists: " + isrc);
    }
}
