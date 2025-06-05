package es.upm.miw.apaw_practice.adapters.mongodb.music_festival.persistence;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_festival.Concert;
import es.upm.miw.apaw_practice.domain.models.music_festival.ConcertArtist;
import es.upm.miw.apaw_practice.domain.models.music_festival.MusicFestival;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import es.upm.miw.apaw_practice.domain.models.music_festival.Stage;
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
        assertFalse(festival.getConcerts().isEmpty());
        var concert = festival.getConcerts().get(0);
        var concertDummy = new Concert(null, null, false);
        assertAll(
                () -> assertEquals(BigDecimal.valueOf(60.5), concert.getTicketPrice()),
                () -> assertFalse(concert.isSoldOut()),
                () -> assertEquals(LocalDate.of(2025, 5, 15), concert.getDate()),
                () -> assertTrue(concert.toString().contains(String.valueOf(concertDummy.isSoldOut()))),
                () -> assertEquals("MainStage", concert.getStage().getName()),
                () -> assertEquals(3, concert.getArtists().size()),
                () -> assertEquals("The Fabulous", concert.getArtists().get(0).getName())
        );
    }

    @Test
    void testReadByNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.musicFestivalPersistence.readByName("UnknownFestival"));
    }


    @Test
    void testUpdateConcertsAdd() {
        MusicFestival festival = this.musicFestivalPersistence.readByName("SpringFest");
        assertEquals(1, festival.getConcerts().size());
        Concert originalConcert = festival.getConcerts().get(0);

        Concert newConcert = new Concert();
        newConcert.setDate(LocalDate.of(2025, 6, 15));
        newConcert.setTicketPrice(BigDecimal.valueOf(59.99));
        newConcert.setSoldOut(true);
        Stage newStage = new Stage();
        newStage.setName("SecondStage");
        newConcert.setStage(newStage);
        ConcertArtist artist1 = new ConcertArtist("RockPower", "Chilean", 4.6);
        ConcertArtist artist2 = new ConcertArtist("PopStar", "American", 4.0);
        newConcert.setArtists(List.of(artist1, artist2));

        festival.setConcerts(Arrays.asList(originalConcert, newConcert));
        MusicFestival updatedFestival = this.musicFestivalPersistence.update(festival);
        assertEquals(2, updatedFestival.getConcerts().size());

        assertTrue(updatedFestival.getConcerts().stream().anyMatch(c ->
                "MainStage".equals(c.getStage().getName()) && LocalDate.of(2025, 5, 15).equals(c.getDate())));
        assertTrue(updatedFestival.getConcerts().stream().anyMatch(c ->
                "SecondStage".equals(c.getStage().getName()) && LocalDate.of(2025, 6, 15).equals(c.getDate())));

        MusicFestival persistedFestival = this.musicFestivalPersistence.readByName("SpringFest");
        assertEquals(2, persistedFestival.getConcerts().size());

        ConcertArtist popStar = updatedFestival.getConcerts().stream()
                .flatMap(c -> c.getArtists().stream())
                .filter(artist -> "PopStar".equals(artist.getName()))
                .findFirst()
                .orElseThrow();
        assertEquals("American", popStar.getNationality());

        assertTrue(updatedFestival.getConcerts().stream().anyMatch(c ->
                "SecondStage".equals(c.getStage().getName()) &&
                        c.isSoldOut() &&
                        BigDecimal.valueOf(59.99).equals(c.getTicketPrice())));
    }

    @Test
    void testUpdateConcertsNotFound() {
        MusicFestival unknownFestival = new MusicFestival("UnknownFest", null, null);
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 10, 10));
        concert.setTicketPrice(BigDecimal.TEN);
        concert.setSoldOut(false);
        Stage stage = new Stage();
        stage.setName("MainStage");
        concert.setStage(stage);
        ConcertArtist artist = new ConcertArtist("The Fabulous", "Mexican", 4.5);
        concert.setArtists(List.of(artist));
        unknownFestival.setConcerts(List.of(concert));
        assertThrows(NotFoundException.class, () -> this.musicFestivalPersistence.update(unknownFestival));
    }

    @Test
    void testUpdateConcertsStageNotFound() {
        MusicFestival festival = this.musicFestivalPersistence.readByName("AutumnRock");
        Concert modifiedConcert = festival.getConcerts().get(0);
        Stage unknownStage = new Stage();
        unknownStage.setName("NonExistentStage");
        modifiedConcert.setStage(unknownStage);
        festival.setConcerts(List.of(modifiedConcert));
        assertThrows(NotFoundException.class, () -> this.musicFestivalPersistence.update(festival));
    }
}