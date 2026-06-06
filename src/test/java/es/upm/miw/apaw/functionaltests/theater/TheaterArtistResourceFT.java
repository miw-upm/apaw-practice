package es.upm.miw.apaw.functionaltests.theater;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.resources.theater.TheaterArtistResource;
import es.upm.miw.apaw.adapters.resources.theater.TheaterArtistResource.PatchActiveDto;
import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TheaterArtistResourceFT extends BaseTheaterTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("TART99")
                .artistFullName("Test Artist")
                .artistBirthDate(LocalDate.of(1990, 1, 1))
                .artistFee(new BigDecimal("500.00"))
                .artistActive(true)
                .build();

        webTestClient.post()
                .uri(TheaterArtistResource.ARTISTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(artist)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TheaterArtist.class)
                .value(created -> {
                    assert created != null;
                    assert created.getArtistCode().equals("TART99");
                    assert created.getArtistFullName().equals("Test Artist");
                    assert created.getArtistBirthDate().equals(LocalDate.of(1990, 1, 1));
                    assert created.getArtistFee().equals(new BigDecimal("500.00"));
                    assert created.getArtistActive();
                });
    }

    @Test
    void testCreate_Conflict() {
        TheaterArtist artist = TheaterArtist.builder()
                .artistCode("TART01")
                .artistFullName("Duplicate Artist")
                .artistBirthDate(LocalDate.of(1990, 1, 1))
                .artistFee(new BigDecimal("500.00"))
                .artistActive(true)
                .build();

        webTestClient.post()
                .uri(TheaterArtistResource.ARTISTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(artist)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void testCreate_BadRequest() {
        TheaterArtist artist = TheaterArtist.builder()
                .artistCode(null)
                .artistFullName("No Code Artist")
                .artistBirthDate(LocalDate.of(1990, 1, 1))
                .artistFee(new BigDecimal("500.00"))
                .artistActive(true)
                .build();

        webTestClient.post()
                .uri(TheaterArtistResource.ARTISTS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(artist)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testDelete() {
        webTestClient.delete()
                .uri(TheaterArtistResource.ARTISTS + TheaterArtistResource.ARTIST_CODE, "TART01")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDelete_NotFound() {
        webTestClient.delete()
                .uri(TheaterArtistResource.ARTISTS + TheaterArtistResource.ARTIST_CODE, "NONEXISTENT")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchActive() {
        PatchActiveDto dto = new PatchActiveDto(false);

        webTestClient.patch()
                .uri(TheaterArtistResource.ARTISTS + TheaterArtistResource.ARTIST_CODE + TheaterArtistResource.ACTIVE, "TART01")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TheaterArtist.class)
                .value(updated -> {
                    assertThat(updated).isNotNull();
                    assertThat(updated.getArtistCode()).isEqualTo("TART01");
                    assertThat(updated.getArtistFullName()).isEqualTo("Alice Performer");
                    assertThat(updated.getArtistActive()).isFalse();
                });
    }

    @Test
    void testPatchActive_NotFound() {
        PatchActiveDto dto = new PatchActiveDto(true);

        webTestClient.patch()
                .uri(TheaterArtistResource.ARTISTS + TheaterArtistResource.ARTIST_CODE + TheaterArtistResource.ACTIVE, "NONEXISTENT")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus().isNotFound();
    }
}
