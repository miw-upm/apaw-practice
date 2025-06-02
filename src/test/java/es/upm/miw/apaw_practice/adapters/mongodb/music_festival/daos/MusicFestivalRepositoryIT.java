package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.daos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_festival.entities.MusicFestivalEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class MusicFestivalRepositoryIT {
    @Autowired
    private MusicFestivalRepository musicFestivalRepository;

    @Test
    void testFindByName() {
        String name = "SummerBeat";
        Optional<MusicFestivalEntity> festivalOptional = this.musicFestivalRepository.findByName(name);
        assertTrue(festivalOptional.isPresent());
        MusicFestivalEntity festival = festivalOptional.get();
        assertAll(
                () -> assertNotNull(festival.getId()),
                () -> assertEquals(name, festival.getName()),
                () -> assertEquals(LocalDateTime.of(2025, 6, 1, 9, 0), festival.getCreationDate()),
                () -> assertEquals(new BigDecimal("180000"), festival.getBudget()),
                () -> assertEquals(2, festival.getConcerts().size()),
                () -> assertEquals(festival.hashCode(), Objects.hashCode(name)),
                () -> assertTrue(festival.toString().contains(name))
        );
    }
}