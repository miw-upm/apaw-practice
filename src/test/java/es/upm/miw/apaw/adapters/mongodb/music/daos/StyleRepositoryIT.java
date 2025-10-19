package es.upm.miw.apaw.adapters.mongodb.music.daos;

import es.upm.miw.apaw.adapters.mongodb.music.entities.StyleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class StyleRepositoryIT {

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private MusicSeeder musicSeeder;

    @BeforeEach
    void setUp() {
        this.musicSeeder.deleteAll();
        this.musicSeeder.seedDatabase();
    }

    @Test
    void testFindAndSave() {
        String genre = "ELECTRONIC";
        StyleEntity entity = this.styleRepository.findById(genre).orElseThrow();

        Integer oldIndex = entity.getPopularityIndex();
        String oldMood = entity.getMood();

        entity.setPopularityIndex((oldIndex == null ? 50 : Math.min(100, oldIndex + 5)));
        entity.setMood(oldMood == null ? "ENERGETIC" : oldMood + "!");
        this.styleRepository.save(entity);

        StyleEntity after = this.styleRepository.findById(genre).orElseThrow();
        assertThat(after.getPopularityIndex()).isNotEqualTo(oldIndex);
        assertThat(after.getMood()).isNotEqualTo(oldMood);
    }
}
