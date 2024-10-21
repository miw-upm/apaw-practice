package es.upm.miw.apaw_practice.adapters.rest.music_lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrumentLevelUpdating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class MusicalInstrumentResourceIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void testCreate() {
    MusicalInstrument musicalInstrument =
        new MusicalInstrument("RTF-SC-002", "Intermediate", "Guitar");
    this.webTestClient
        .post()
        .uri(MusicalInstrumentResource.MUSICAL_INSTRUMENTS)
        .body(BodyInserters.fromValue(musicalInstrument))
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(MusicalInstrument.class)
        .value(Assertions::assertNotNull)
        .value(musicalInstruments -> {
          var musicalInstrumentReturned = musicalInstruments.get(0);
          assertEquals(musicalInstrument.getModel(), musicalInstrumentReturned.getModel());
          assertEquals(musicalInstrument.getDifficultyLevel(), musicalInstrumentReturned.getDifficultyLevel());
          assertEquals(musicalInstrument.getType(), musicalInstrumentReturned.getType());
        });
  }

  @Test
  void testCreateConflict() {
    MusicalInstrument musicalInstrument =
        new MusicalInstrument("FND-SC-001", "Intermediate", "Electric Guitar");

    this.webTestClient
        .post()
        .uri(MusicalInstrumentResource.MUSICAL_INSTRUMENTS)
        .body(BodyInserters.fromValue(musicalInstrument))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.CONFLICT);
  }

  @Test
  void testUpdateDifficultyLevel() {
    String model = "LWG-SN-401";
    String newDifficultyLevel = "Advanced";
    MusicalInstrumentLevelUpdating musicalInstrumentLevelUpdating =
        new MusicalInstrumentLevelUpdating(model, newDifficultyLevel);

    this.webTestClient
        .patch()
        .uri(MusicalInstrumentResource.MUSICAL_INSTRUMENTS)
        .body(BodyInserters.fromValue(List.of(musicalInstrumentLevelUpdating)))
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  void testUpdateDifficultyLevel_notFound() {
    String model = "YMH-FL-224";
    String newDifficultyLevel = "Advanced";
    MusicalInstrumentLevelUpdating musicalInstrumentLevelUpdating =
        new MusicalInstrumentLevelUpdating(model, newDifficultyLevel);

    this.webTestClient
        .patch()
        .uri(MusicalInstrumentResource.MUSICAL_INSTRUMENTS)
        .body(BodyInserters.fromValue(List.of(musicalInstrumentLevelUpdating)))
        .exchange()
        .expectStatus().isNotFound();
  }
}
