package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.daos.SongRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.SongEntity;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.music.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class SongServiceIT {

    @Autowired
    private SongService songService;

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
                .title("Service Created")
                .durationSeconds(210)
                .build();

        this.songService.create(song);

        SongEntity saved = this.songRepository.findById(isrc).orElseThrow();
        assertThat(saved.getTitle()).isEqualTo("Service Created");
        assertThat(saved.getDurationSeconds()).isEqualTo(210);
    }

    @Test
    void testCreateConflict() {
        String isrc = "FRX123ABC9999";
        this.songService.create(Song.builder().isrc(isrc).title("First").durationSeconds(200).build());

        assertThatThrownBy(() ->
                this.songService.create(Song.builder().isrc(isrc).title("Dup").durationSeconds(180).build())
        )
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Song already exists: " + isrc);
    }
}
