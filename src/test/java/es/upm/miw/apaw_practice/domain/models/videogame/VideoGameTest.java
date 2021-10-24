package es.upm.miw.apaw_practice.domain.models.videogame;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class VideoGameTest {

    @Test
    void testBuilder() {

        List<Platform> platforms = List.of(
                new Platform("ds", "xl","1tb"),
                new Platform("3ds", "xl","1.5tb")
        );

        VideoGame videoGame = VideoGame.builder()
                .title("Super Mario")
                        .platforms(platforms)
                                .rating("e")
                                        .releaseDate(LocalDate.of(1985, 9, 13))
                                                .build();

        assertEquals("Super Mario", videoGame.getTitle());
        assertEquals("ds", videoGame.getPlatforms().get(0).getConsoleName());
        assertEquals("e", videoGame.getRating());
    }
}
