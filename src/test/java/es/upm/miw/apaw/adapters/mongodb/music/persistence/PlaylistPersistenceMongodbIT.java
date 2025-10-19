package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.daos.PlaylistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.PlaylistEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Playlist;
import es.upm.miw.apaw.domain.persistenceports.music.PlaylistPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class PlaylistPersistenceMongodbIT {
    @Autowired
    private PlaylistPersistence playlistPersistence;

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
    void testDeleteExistingPlaylist() {
        assertThat(playlistRepository.existsById("PL-001")).isTrue();
        playlistPersistence.delete("PL-001");
        assertThat(playlistRepository.existsById("PL-001")).isFalse();
    }

    @Test
    void testDeleteNotFound() {
        assertThatThrownBy(() -> playlistPersistence.delete("PL-404"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Playlist not found: PL-404");
    }

    @Test
    void testUpdateOk() {
        // Usa un code existente del seeder (ajusta si es distinto)
        String code = "PL-001";

        // Estado antes de actualizar
        PlaylistEntity before = this.playlistRepository.findById(code).orElseThrow();
        String oldLabel = before.getLabel();
        Boolean oldOpened = before.getOpened();

        // === payload: simula el JSON del PUT ===
        String newLabel = oldLabel + " (UPDATED)";
        Boolean newOpened = (oldOpened == null) ? Boolean.TRUE : !oldOpened;

        Playlist payload = Playlist.builder()
                .label(newLabel)
                .opened(newOpened)
                .build();
        this.playlistPersistence.update(code, payload);

        PlaylistEntity after = this.playlistRepository.findById(code).orElseThrow();
        assertThat(after.getLabel()).isEqualTo(newLabel);
        assertThat(after.getOpened()).isEqualTo(newOpened);

        assertThat(after.getSongIsrcs()).isEqualTo(before.getSongIsrcs());

        assertThat(after.getLabel()).isNotEqualTo(oldLabel);
        assertThat(after.getOpened()).isNotEqualTo(oldOpened);
    }

    @Test
    void testUpdateNotFound() {
        Playlist payload = Playlist.builder()
                .label("No Exists")
                .opened(Boolean.FALSE)
                .build();

        assertThatThrownBy(() -> this.playlistPersistence.update("PL-404", payload))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Playlist not found");
    }
}
