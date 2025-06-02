package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.Concert;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class MusicFestivalPersistenceMongodbIT {

    @Autowired
    private MusicFestivalPersistenceMongodb musicFestivalPersistence;

    @Test
    void testReadByName() {
        String name = "SummerBeat";
        MusicFestival festival = this.musicFestivalPersistence.readByName(name);
        var festivalDummy = new MusicFestival(name, null, null);
        assertEquals(name, festival.getName());
        assertEquals(LocalDateTime.of(2025, 6, 1, 9, 0), festival.getCreationDate());
        assertTrue(festival.toString().contains(festivalDummy.getName()));
        assertEquals(new BigDecimal("180000"), festival.getBudget());
        assertEquals(2, festival.getConcerts().size());
    }

    @Test
    void testReadWithConcertComposition() {
        MusicFestival festival = this.musicFestivalPersistence.readByName("SpringFest");
        assertEquals(1, festival.getConcerts().size());
        var concert = festival.getConcerts().get(0);
        var concertDummy = new Concert(concert.getCode(), null, null, false);
        assertAll(
                () -> assertEquals("CON001", concert.getCode()),
                () -> assertEquals(BigDecimal.valueOf(60.5), concert.getTicketPrice()),
                () -> assertFalse(concert.isSoldOut()),
                () -> assertEquals(LocalDate.of(2025, 5, 15), concert.getDate()),
                () -> assertTrue(concert.toString().contains(concertDummy.getCode())),
                () -> assertEquals("MainStage", concert.getStage().getName()),
                () -> assertEquals(3, concert.getArtists().size()),
                () -> assertEquals("The Fabulous", concert.getArtists().get(0).getName())
        );
    }

    @Test
    void testReadByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.musicFestivalPersistence.readByName("UnknownFestival"));
    }
}