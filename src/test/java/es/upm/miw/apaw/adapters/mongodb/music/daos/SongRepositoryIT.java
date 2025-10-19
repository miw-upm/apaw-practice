package es.upm.miw.apaw.adapters.mongodb.music.daos;

import es.upm.miw.apaw.adapters.mongodb.music.entities.SongEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SongRepositoryIT {

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
    void testSaveAndFind() {
        String isrc = "FRX123ABC9999";
        SongEntity entity = new SongEntity();
        entity.setIsrc(isrc);
        entity.setTitle("Brand New Song");
        entity.setDurationSeconds(215);
        entity.setStyleGenre("electro");

        this.songRepository.save(entity);

        assertThat(this.songRepository.findById(isrc)).isPresent();
    }
}
