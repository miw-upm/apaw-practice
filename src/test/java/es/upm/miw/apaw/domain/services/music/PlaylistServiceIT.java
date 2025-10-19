package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.daos.PlaylistRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
public class PlaylistServiceIT {
    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicSeeder musicSeeder;

    @BeforeEach
    void resetDb() {
        musicSeeder.deleteAll();
        musicSeeder.seedDatabase();
    }

    @Test
    void testDeleteOk() {
        assertThat(playlistRepository.existsById("PL-001")).isTrue();
        playlistService.delete("PL-001");
        assertThat(playlistRepository.existsById("PL-001")).isFalse();
    }

    @Test
    void testDeleteNotFound() {
        assertThatThrownBy(() -> playlistService.delete("PL-999"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Playlist not found: PL-999");
    }
}
