package es.upm.miw.apaw.domain.services.music;

import es.upm.miw.apaw.adapters.mongodb.music.daos.MusicSeeder;
import es.upm.miw.apaw.adapters.mongodb.music.daos.StyleRepository;
import es.upm.miw.apaw.adapters.mongodb.music.entities.StyleEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.music.Style;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class StyleServiceIT {

    @Autowired
    private StyleService styleService;

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

        Style patch = Style.builder()
                .popularityIndex(60)
                .mood("FOCUSED")
                .build();

        this.styleService.patch(genre, patch);

        StyleEntity after = this.styleRepository.findById(genre).orElseThrow();
        assertThat(after.getPopularityIndex()).isEqualTo(60);
        assertThat(after.getMood()).isEqualTo("FOCUSED");
        assertThat(after.getGenre()).isEqualTo(genre);
    }

    @Test
    void testPatchNotFound() {
        Style patch = Style.builder().mood("CALM").build();
        assertThatThrownBy(() -> this.styleService.patch("NO-SUCH-GENRE", patch))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Style not found");
    }
}
