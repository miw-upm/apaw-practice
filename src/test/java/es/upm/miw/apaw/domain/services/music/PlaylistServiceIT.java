package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.daos.PlaylistRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.PlaylistEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Playlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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
        this.musicSeeder.deleteAll();
        this.musicSeeder.seedDatabase();
    }

    @Test
    void testDeleteOk() {
        assertThat(this.playlistRepository.existsById("PL-001")).isTrue();
        this.playlistService.delete("PL-001");
        assertThat(this.playlistRepository.existsById("PL-001")).isFalse();
    }

    @Test
    void testDeleteNotFound() {
        assertThatThrownBy(() -> this.playlistService.delete("PL-999"))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Playlist not found: PL-999");
    }

    @Test
    void testUpdateOk() {
        String code = "PL-001";

        PlaylistEntity before = this.playlistRepository.findById(code).orElseThrow();
        String oldLabel = before.getLabel();
        Boolean oldOpened = before.getOpened();

        String newLabel = oldLabel + " (UPDATED)";
        Boolean newOpened = (oldOpened == null) ? Boolean.TRUE : !oldOpened;

        Playlist payload = Playlist.builder()
                .label(newLabel)
                .opened(newOpened)
                .build();

        this.playlistService.update(code, payload);

        PlaylistEntity after = this.playlistRepository.findById(code).orElseThrow();
        assertThat(after.getLabel()).isEqualTo(newLabel);
        assertThat(after.getOpened()).isEqualTo(newOpened);
        assertThat(after.getSongIsrcs()).isEqualTo(before.getSongIsrcs()); // relación intacta
        assertThat(after.getLabel()).isNotEqualTo(oldLabel);
        assertThat(after.getOpened()).isNotEqualTo(oldOpened);
    }

    @Test
    void testUpdateNotFound() {
        Playlist payload = Playlist.builder()
                .label("Does Not Exist")
                .opened(Boolean.FALSE)
                .build();

        assertThatThrownBy(() -> this.playlistService.update("PL-404", payload))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Playlist not found: PL-404");
    }

    @Test
    void testFindArtistNamesByLabelOk() {
        List<String> result = this.playlistService.findArtistNamesByLabel("Classics").toList();

        assertThat(result).isNotEmpty();
        assertThat(result).contains("Daft Punk", "Tame Impala");
        assertThat(result).doesNotHaveDuplicates();
    }

    @Test
    void testFindArtistNamesByLabelEmpty() {
        List<String> result = this.playlistService.findArtistNamesByLabel("NOPE").toList();
        assertThat(result).isEmpty();
    }
}
