package es.upm.miw.apaw.adapters.mongodb.music.daos;

import es.upm.miw.apaw.adapters.mongodb.music.entities.ArtistEntity;
import es.upm.miw.apaw.adapters.mongodb.music.entities.PlaylistEntity;
import es.upm.miw.apaw.adapters.mongodb.music.entities.SongEntity;
import es.upm.miw.apaw.adapters.mongodb.music.entities.StyleEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class MusicSeeder {

    private final ArtistRepository artistRepository;
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final StyleRepository styleRepository;

    @Autowired
    public MusicSeeder(ArtistRepository artistRepository,
                       PlaylistRepository playlistRepository,
                       SongRepository songRepository,
                       StyleRepository styleRepository) {
        this.artistRepository = artistRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
    }

    public void seedDatabase() {
        log.warn("------- Music Initial Load -----------");

        ArtistEntity[] artists = {
                ArtistEntity.builder()
                        .name("Daft Punk")
                        .activeSince(LocalDate.of(1993, 1, 1))
                        .monthlyListeners(10_000_000L)
                        .userId("11111111-1111-1111-1111-111111111111")
                        .build(),
                ArtistEntity.builder()
                        .name("Tame Impala")
                        .activeSince(LocalDate.of(2007, 1, 1))
                        .monthlyListeners(6_500_000L)
                        .userId("22222222-2222-2222-2222-222222222222")
                        .build()
        };
        this.artistRepository.saveAll(Arrays.asList(artists));

        StyleEntity[] styles = {
                StyleEntity.builder()
                        .genre("ELECTRONIC")
                        .popularityIndex(90)
                        .mood("ENERGETIC")
                        .build(),
                StyleEntity.builder()
                        .genre("PSYCH")
                        .popularityIndex(75)
                        .mood("DREAMY")
                        .build()
        };
        this.styleRepository.saveAll(Arrays.asList(styles));

        SongEntity[] songs = {
                SongEntity.builder()
                        .isrc("FRX123ABC0001")
                        .title("Around the World")
                        .durationSeconds(420)
                        .artistName("Daft Punk")
                        .styleGenre("ELECTRONIC")
                        .build(),
                SongEntity.builder()
                        .isrc("FRX123ABC0002")
                        .title("Digital Love")
                        .durationSeconds(300)
                        .artistName("Daft Punk")
                        .styleGenre("ELECTRONIC")
                        .build(),
                SongEntity.builder()
                        .isrc("AUX99ZZZ00001")
                        .title("The Less I Know The Better")
                        .durationSeconds(216)
                        .artistName("Tame Impala")
                        .styleGenre("PSYCH")
                        .build()
        };
        this.songRepository.saveAll(Arrays.asList(songs));

        PlaylistEntity[] playlists = {
                PlaylistEntity.builder()
                        .code("PL-001")
                        .label("Classics")
                        .opened(true)
                        .songIsrcs(List.of("FRX123ABC0001", "AUX99ZZZ00001"))
                        .build(),
                PlaylistEntity.builder()
                        .code("PL-002")
                        .label("Electro Vibes")
                        .opened(true)
                        .songIsrcs(List.of("FRX123ABC0001", "FRX123ABC0002"))
                        .build()
        };
        this.playlistRepository.saveAll(Arrays.asList(playlists));

        log.warn("        ------- music");
    }

    public void deleteAll() {
        this.playlistRepository.deleteAll();
        this.songRepository.deleteAll();
        this.styleRepository.deleteAll();
        this.artistRepository.deleteAll();
    }
}
