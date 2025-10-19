package es.upm.miw.apaw.adapters.mongodb.music.persistence;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.daos.StyleRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.StyleEntity;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Style;
import es.upm.miw.apaw.domain.persistenceports.music.StylePersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class StylePersistenceMongodbIT {

    @Autowired
    private StylePersistence stylePersistence;

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
    void testPatchOk() {
        String genre = "ELECTRONIC";
        StyleEntity before = this.styleRepository.findById(genre).orElseThrow();

        Style patch = Style.builder()
                .popularityIndex(85)     // dentro de 0..100
                .mood("HYPE")
                .build();

        this.stylePersistence.patch(genre, patch);

        StyleEntity after = this.styleRepository.findById(genre).orElseThrow();
        assertThat(after.getPopularityIndex()).isEqualTo(85);
        assertThat(after.getMood()).isEqualTo("HYPE");
    }

    @Test
    void testPatchNotFound() {
        Style patch = Style.builder().popularityIndex(10).build();
        assertThatThrownBy(() -> this.stylePersistence.patch("NO-SUCH-GENRE", patch))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Style not found");
    }

    @Test
    void testPatchBadRequest_OutOfRange() {
        String genre = "ELECTRONIC";
        Style patch = Style.builder().popularityIndex(101).build();
        assertThatThrownBy(() -> this.stylePersistence.patch(genre, patch))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("popularityIndex");
    }

    @Test
    void testPatchBadRequest_BlankMood() {
        String genre = "ELECTRONIC";
        Style patch = Style.builder().mood("   ").build();
        assertThatThrownBy(() -> this.stylePersistence.patch(genre, patch))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("mood");
    }
}