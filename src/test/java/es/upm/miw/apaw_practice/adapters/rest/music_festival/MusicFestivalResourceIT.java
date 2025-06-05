package es.upm.miw.apaw_practice.adapters.rest.music_festival;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_festival.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
class MusicFestivalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateBudgets() {
        List<MusicFestivalBudgetUpdating> list = Arrays.asList(
                new MusicFestivalBudgetUpdating("SpringFest", BigDecimal.valueOf(210000)),
                new MusicFestivalBudgetUpdating("AutumnRock", BigDecimal.valueOf(160000))
        );
        this.webTestClient
                .patch()
                .uri(MusicFestivalResource.FESTIVALS)
                .body(BodyInserters.fromValue(list))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateBudgetsNotFound() {
        List<MusicFestivalBudgetUpdating> list = List.of(
                new MusicFestivalBudgetUpdating("UnknownFest", BigDecimal.ONE)
        );
        this.webTestClient
                .patch()
                .uri(MusicFestivalResource.FESTIVALS)
                .body(BodyInserters.fromValue(list))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
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
        this.webTestClient
                .put()
                .uri(MusicFestivalResource.FESTIVALS + MusicFestivalResource.NAME_ID + MusicFestivalResource.CONCERTS, "SpringFest")
                .body(BodyInserters.fromValue(concerts))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateConcertsDuplicateConcert() {
        Concert concert1 = new Concert();
        Concert concert2 = new Concert();
        LocalDate date = LocalDate.of(2025, 12, 2);
        concert1.setDate(date);
        concert1.setTicketPrice(BigDecimal.valueOf(40.0));
        concert1.setSoldOut(false);
        concert2.setDate(date);
        concert2.setTicketPrice(BigDecimal.valueOf(45.0));
        concert2.setSoldOut(true);
        Stage stage1 = new Stage();
        stage1.setName("MainStage");
        Stage stage2 = new Stage();
        stage2.setName("MainStage");
        concert1.setStage(stage1);
        concert2.setStage(stage2);
        ConcertArtist artistA = new ConcertArtist("RockPower", "Chilean", 4.6);
        ConcertArtist artistB = new ConcertArtist("PopStar", "American", 4.0);
        concert1.setArtists(List.of(artistA));
        concert2.setArtists(List.of(artistB));
        List<Concert> concerts = Arrays.asList(concert1, concert2);
        this.webTestClient
                .put()
                .uri(MusicFestivalResource.FESTIVALS + MusicFestivalResource.NAME_ID + MusicFestivalResource.CONCERTS, "AutumnRock")
                .body(BodyInserters.fromValue(concerts))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
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
        this.webTestClient
                .put()
                .uri(MusicFestivalResource.FESTIVALS + MusicFestivalResource.NAME_ID + MusicFestivalResource.CONCERTS, "SpringFest")
                .body(BodyInserters.fromValue(concerts))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testUpdateConcertsNotFound() {
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 12, 31));
        concert.setTicketPrice(BigDecimal.valueOf(100.0));
        concert.setSoldOut(false);
        Stage stage = new Stage();
        stage.setName("MainStage");
        concert.setStage(stage);
        ConcertArtist artist = new ConcertArtist("The Fabulous", "Mexican", 4.5);
        concert.setArtists(List.of(artist));
        List<Concert> concerts = List.of(concert);
        this.webTestClient
                .put()
                .uri(MusicFestivalResource.FESTIVALS + MusicFestivalResource.NAME_ID + MusicFestivalResource.CONCERTS, "UnknownFest")
                .body(BodyInserters.fromValue(concerts))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testUpdateConcertsStageMissing() {
        Concert concert = new Concert();
        concert.setDate(LocalDate.of(2025, 8, 20));
        concert.setTicketPrice(BigDecimal.valueOf(50.0));
        concert.setSoldOut(false);
        ConcertArtist artist = new ConcertArtist("DJ Moon", "Spanish", 4.2);
        concert.setArtists(List.of(artist));
        List<Concert> concerts = List.of(concert);
        this.webTestClient
                .put()
                .uri(MusicFestivalResource.FESTIVALS + MusicFestivalResource.NAME_ID + MusicFestivalResource.CONCERTS, "SummerBeat")
                .body(BodyInserters.fromValue(concerts))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
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

        List<Concert> concerts = Arrays.asList(concert1, concert2);
        this.webTestClient
                .put()
                .uri(MusicFestivalResource.FESTIVALS + MusicFestivalResource.NAME_ID + MusicFestivalResource.CONCERTS, "MultiGenreFest")
                .body(BodyInserters.fromValue(concerts))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MusicFestival.class)
                .value(festival -> {
                    assertEquals(2, festival.getConcerts().size());
                    List<String> stageNames = festival.getConcerts().stream()
                            .map(c -> c.getStage().getName())
                            .toList();
                    assertTrue(stageNames.contains("SecondStage"));
                    assertTrue(stageNames.contains("ArenaStage"));
                });
    }
}