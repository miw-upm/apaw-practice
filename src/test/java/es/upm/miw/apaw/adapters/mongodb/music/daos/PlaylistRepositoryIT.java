package es.upm.miw.apaw.adapters.mongodb.music.daos;

import es.upm.miw.apaw.adapters.mongodb.music.entities.PlaylistEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class PlaylistRepositoryIT {
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
    void testFindByIdAndDelete() {
        String code = "PL-001";

        Optional<PlaylistEntity> playlist = this.playlistRepository.findById(code);
        assertThat(playlist).isPresent();
        assertThat(playlist.get().getCode()).isEqualTo("PL-001");
        assertThat(playlist.get().getLabel()).isEqualTo("Classics");
        assertThat(playlist.get().getSongIsrcs())
                .containsExactlyInAnyOrder("FRX123ABC0001", "AUX99ZZZ00001");
        this.playlistRepository.deleteById(code);
        assertThat(this.playlistRepository.findById(code)).isEmpty();
    }

    @Test
    void testFindSave() {
        String code = "PL-001"; // ajusta si tu seeder usa otro
        PlaylistEntity entity = this.playlistRepository.findById(code).orElseThrow();

        String oldLabel = entity.getLabel();
        Boolean oldOpened = entity.getOpened();

        entity.setLabel(oldLabel + " RepoUpdated");
        entity.setOpened(oldOpened == null ? Boolean.TRUE : !oldOpened);

        this.playlistRepository.save(entity);

        PlaylistEntity after = this.playlistRepository.findById(code).orElseThrow();
        assertThat(after.getLabel()).isNotEqualTo(oldLabel);
        assertThat(after.getOpened()).isNotEqualTo(oldOpened);
    }
}
