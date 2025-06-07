package es.upm.miw.apaw_practice.domain.services.music_festival;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.BadRequestException;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.music_festival.*;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_festival.MusicFestivalPersistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class MusicFestivalServiceIT {

    @Autowired
    private MusicFestivalService musicFestivalService;

    @Autowired
    private MusicFestivalPersistence musicFestivalPersistence;

    @Test
    void testUpdateBudgets() {
        List<MusicFestivalBudgetUpdating> updatingList = List.of(
                new MusicFestivalBudgetUpdating("SpringFest", BigDecimal.valueOf(210000)),
                new MusicFestivalBudgetUpdating("AutumnRock", BigDecimal.valueOf(160000))
        );
        this.musicFestivalService.updateBudgets(updatingList.stream());
        assertTrue(updatingList.get(0).toString().contains("SpringFest"));
        assertEquals(BigDecimal.valueOf(210000), this.musicFestivalPersistence.readByName("SpringFest").getBudget());
        assertEquals(BigDecimal.valueOf(160000), this.musicFestivalPersistence.readByName("AutumnRock").getBudget());
        updatingList = List.of(
                new MusicFestivalBudgetUpdating("SpringFest", BigDecimal.valueOf(200000)),
                new MusicFestivalBudgetUpdating("AutumnRock", BigDecimal.valueOf(150000))
        );
        this.musicFestivalService.updateBudgets(updatingList.stream());
    }

    @ParameterizedTest(name = "update concerts artists missing when {1}")
    @MethodSource("artistsProvider")
    void testUpdateConcertsArtistsMissing(List<ConcertArtist> artists, String title) {
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 5, 15));
        concert.setTicketPrice(BigDecimal.valueOf(30.0));
        concert.setSoldOut(false);
        Stage stage = new Stage();
        stage.setName("SpringFest");
        concert.setStage(stage);
        concert.setArtists(artists);
        List<Concert> concerts = List.of(concert);
        assertThrows(BadRequestException.class, () -> this.musicFestivalService.updateConcerts("SummerBeat", concerts));
    }

    @Test
    void testUpdateConcertsDuplicateArtists() {
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 5, 15));
        concert.setTicketPrice(BigDecimal.valueOf(30.0));
        concert.setSoldOut(false);
        Stage stage = new Stage();
        stage.setName("SpringFest");
        concert.setStage(stage);
        ConcertArtist artist1 = new ConcertArtist("DJ Moon", "Spanish", 4.2);
        ConcertArtist artist2 = new ConcertArtist("DJ Moon", "Spanish", 4.2);
        concert.setArtists(Arrays.asList(artist1, artist2));
        List<Concert> concerts = List.of(concert);
        assertThrows(ConflictException.class, () -> this.musicFestivalService.updateConcerts("SpringFest", concerts));
    }

    @Test
    void testUpdateConcertsGlobalConflict() {
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 6, 15));
        concert.setTicketPrice(BigDecimal.valueOf(70.0));
        concert.setSoldOut(false);
        Stage stage = new Stage();
        stage.setName("MainStage");
        concert.setStage(stage);
        ConcertArtist artist = new ConcertArtist("The Fabulous", "Mexican", 4.5);
        concert.setArtists(List.of(artist));
        List<Concert> concerts = List.of(concert);
        assertThrows(ConflictException.class, () -> this.musicFestivalService.updateConcerts("SpringFest", concerts));
    }

    @ParameterizedTest(name = "update concerts but missing when {1}")
    @MethodSource("concertsProvider")
    void testUpdateConcertsMissing(List<Concert> concerts, String title) {
        assertThrows(BadRequestException.class, () -> this.musicFestivalService.updateConcerts("SummerBeat", concerts));
    }

    @Test
    void testUpdateConcertsOrphanArtist() {
        List<Concert> summerConcerts = List.of(
                createConcert(LocalDate.of(2025, 6, 15), BigDecimal.valueOf(55.00), false,
                        "MainStage",
                        List.of(new ConcertArtist("The Fabulous", "Mexican", 4.5),
                                new ConcertArtist("FolkSingers", "Colombian", 4.1),
                                new ConcertArtist("IndieWave", "English", 4.3))),
                createConcert(LocalDate.of(2025, 6, 16), BigDecimal.valueOf(58.50), false,
                        "SummerStage",
                        List.of(new ConcertArtist("DJ Moon", "Spanish", 4.2),
                                new ConcertArtist("PopStar", "American", 4.0)))
        );
        assertThrows(BadRequestException.class,
                () -> this.musicFestivalService.updateConcerts("SummerBeat", summerConcerts));
    }

    @ParameterizedTest(name = "update concerts stage missing when {1}")
    @MethodSource("stageProvider")
    void testUpdateConcertsStageMissing(Stage stage, String title) {
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 8, 20));
        concert.setTicketPrice(BigDecimal.valueOf(50.0));
        concert.setSoldOut(false);
        concert.setStage(stage);

        ConcertArtist artist = new ConcertArtist("DJ Moon", "Spanish", 4.2);
        concert.setArtists(List.of(artist));
        List<Concert> concerts = List.of(concert);
        assertThrows(BadRequestException.class, () -> this.musicFestivalService.updateConcerts("SummerBeat", concerts));
    }

    @Test
    void testUpdateConcertsStageOpenTimeAfterDate() {
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 7, 22));
        concert.setTicketPrice(BigDecimal.valueOf(50.0));
        concert.setSoldOut(false);
        Stage stage = new Stage();
        stage.setName("SideStage");
        concert.setStage(stage);
        ConcertArtist artist = new ConcertArtist("The Fabulous", "Mexican", 4.5);
        concert.setArtists(List.of(artist));
        List<Concert> concerts = List.of(concert);
        assertThrows(BadRequestException.class,
                () -> this.musicFestivalService.updateConcerts("SpringFest", concerts));
    }

    @Test
    void testUpdateConcertsSuccess() {
        Concert concert1 = new Concert();
        concert1.setDate(LocalDate.of(2025, 6, 15));
        concert1.setTicketPrice(BigDecimal.valueOf(55.5));
        concert1.setSoldOut(false);
        Stage stage1 = new Stage();
        stage1.setName("SecondStage");
        concert1.setStage(stage1);
        ConcertArtist artistA1 = new ConcertArtist("DJ Moon", "Spanish", 4.2);
        ConcertArtist artistA2 = new ConcertArtist("PopStar", "American", 4.0);
        concert1.setArtists(Arrays.asList(artistA1, artistA2));

        Concert concert2 = new Concert();
        concert2.setDate(LocalDate.of(2025, 7, 24));
        concert2.setTicketPrice(BigDecimal.valueOf(60.0));
        concert2.setSoldOut(true);
        Stage stage2 = new Stage();
        stage2.setName("ArenaStage");
        concert2.setStage(stage2);
        ConcertArtist artistB1 = new ConcertArtist("RockPower", "Chilean", 4.6);
        ConcertArtist artistB2 = new ConcertArtist("IndieWave", "English", 4.3);
        concert2.setArtists(Arrays.asList(artistB1, artistB2));

        List<Concert> newConcerts = Arrays.asList(concert1, concert2);
        MusicFestival updatedFestival = this.musicFestivalService.updateConcerts("MultiGenreFest", newConcerts);
        assertEquals(2, updatedFestival.getConcerts().size());

        List<String> stageNames = updatedFestival.getConcerts().stream()
                .map(c -> c.getStage().getName())
                .toList();
        assertTrue(stageNames.contains("ArenaStage"));
        assertTrue(stageNames.contains("SecondStage"));

        MusicFestival persistedFestival = this.musicFestivalPersistence.readByName("MultiGenreFest");
        assertEquals(2, persistedFestival.getConcerts().size());
        assertTrue(persistedFestival.getConcerts().stream()
                .anyMatch(c -> "ArenaStage".equals(c.getStage().getName()) && LocalDate.of(2025, 7, 24).equals(c.getDate())));
        assertTrue(persistedFestival.getConcerts().stream()
                .anyMatch(c -> "SecondStage".equals(c.getStage().getName()) && LocalDate.of(2025, 6, 15).equals(c.getDate())));
    }

    private Concert createConcert(LocalDate date, BigDecimal ticketPrice, boolean soldOut,
                                  String stageName, List<ConcertArtist> artists) {
        Concert concert = new Concert();
        concert.setDate(date);
        concert.setTicketPrice(ticketPrice);
        concert.setSoldOut(soldOut);
        Stage stage = new Stage();
        stage.setName(stageName);
        concert.setStage(stage);
        concert.setArtists(artists);
        return concert;
    }

    private static Stream<Arguments> artistsProvider() {
        return Stream.of(
                Arguments.of(null, "artists is null"),
                Arguments.of(List.of(),"artists is empty"));
    }

    private static Stream<Arguments> concertsProvider() {
        return Stream.of(
                Arguments.of(null, "list is null"),
                Arguments.of(List.of(),"list empty"));
    }

    private static Stream<Arguments> stageProvider() {
        Stage stage = new Stage();
        stage.setName("");
        return Stream.of(
                Arguments.of(null, "stage is null"),
                Arguments.of(new Stage(),"stage without name"),
                Arguments.of(stage,"stage with name empty"));
    }
}