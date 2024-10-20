package es.upm.miw.apaw_practice.adapters.rest.music_lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Learner;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Lesson;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class LearnerResourceIT {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void testDelete() {
    this.webTestClient
        .delete()
        .uri(LearnerResource.LEARNERS + LearnerResource.IDENTITY_DOCUMENT_ID, "O4758589")
        .exchange()
        .expectStatus().isOk();
  }

  @Test
  void testUpdateLessons() {
    LocalDate currentDate = LocalDate.now();
    Integer durationInHours = 2;
    BigDecimal fee = new BigDecimal("15.50");
    String musicalInstrumentModel = "FND-SC-001";
    MusicalInstrument musicalInstrument = new MusicalInstrument();
    musicalInstrument.setModel(musicalInstrumentModel);
    Lesson[] lessons = {new Lesson(currentDate, durationInHours, fee, List.of(musicalInstrument))};

    this.webTestClient
        .put()
        .uri(LearnerResource.LEARNERS + LearnerResource.IDENTITY_DOCUMENT_ID + LearnerResource.LESSONS, "C1234567")
        .body(BodyInserters.fromValue(lessons))
        .exchange()
        .expectStatus().isOk()
        .expectBody(Learner.class)
        .value(learner -> {
          assertFalse(learner.getLessons().isEmpty());
          assertEquals(durationInHours, learner.getLessons().get(0).getDurationInHours());
          assertEquals(currentDate, learner.getLessons().get(0).getDate());
          assertEquals(fee, learner.getLessons().get(0).getFee());
        });
  }

  void testGetFindFeeSumByInstrumentDifficultyLevel() {
    this.webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder.path(LearnerResource.LEARNERS + LearnerResource.SEARCH + LearnerResource.FEE_SUM_BY_DIFFICULTY_LEVEL)
                .queryParam("q", "difficultyLevel:Beginner")
                .build())
        .exchange()
        .expectStatus().isOk()
        .expectBody(BigDecimal.class)
        .value(feeSum -> assertEquals(BigDecimal.valueOf(18.75), feeSum));
  }

  @Test
  void testGetFindFeeSumByInstrumentDifficultyLevel_NotValidLevel() {
    this.webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder.path(LearnerResource.LEARNERS + LearnerResource.SEARCH + LearnerResource.FEE_SUM_BY_DIFFICULTY_LEVEL)
                .queryParam("q", "difficultyLevel:Extra")
                .build())
        .exchange()
        .expectStatus().isOk()
        .expectBody(BigDecimal.class)
        .value(feeSum -> assertEquals(BigDecimal.ZERO, feeSum));
  }

}
