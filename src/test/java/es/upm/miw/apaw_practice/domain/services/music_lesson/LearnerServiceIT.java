package es.upm.miw.apaw_practice.domain.services.music_lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Lesson;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.LearnerPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class LearnerServiceIT {

  @Autowired
  private LearnerService learnerService;

  @Autowired
  private LearnerPersistence learnerPersistence;

  @Test
  void testDelete() {
    var learnerCountPrev = this.getLearnersCount();
    this.learnerService.delete("Y5879632");
    var learnerCountAfter = this.getLearnersCount();
    assertTrue(learnerCountPrev > learnerCountAfter);
  }

  @Test
  void testUpdateLesson(){
    LocalDate currentDate = LocalDate.now();
    Integer durationInHours = 2;
    BigDecimal fee = new BigDecimal("15.50");
    String musicalInstrumentModel = "FND-SC-001";
    MusicalInstrument musicalInstrument = new MusicalInstrument();
    musicalInstrument.setModel(musicalInstrumentModel);

    Lesson lesson = new Lesson(currentDate, durationInHours, fee, List.of(musicalInstrument));
    var updatedLearner = this.learnerService.updateLessons("B8858525", List.of(lesson));

    assertNotNull(updatedLearner);
    assertNotNull(updatedLearner.getLessons());
    assertFalse(updatedLearner.getLessons().isEmpty());

    updatedLearner.getLessons()
        .forEach(updatedLesson -> {
          assertEquals(durationInHours, updatedLesson.getDurationInHours());
          assertEquals(fee, updatedLesson.getFee());
          assertEquals(currentDate, updatedLesson.getDate());
          assertTrue(updatedLesson.getMusicalInstruments()
              .stream()
              .map(MusicalInstrument::getModel)
              .anyMatch(musicalInstrumentModel::equals));
        });
  }

  @Test
  void testUpdateLesson_notFoundLearner(){
    LocalDate currentDate = LocalDate.now();
    Integer durationInHours = 2;
    BigDecimal fee = new BigDecimal("15.50");
    String musicalInstrumentModel = "FND-SC-001";
    MusicalInstrument musicalInstrument = new MusicalInstrument();
    musicalInstrument.setModel(musicalInstrumentModel);

    Lesson lesson = new Lesson(currentDate, durationInHours, fee, List.of(musicalInstrument));
    assertThrows(NotFoundException.class, () ->this.learnerService.updateLessons("B8858525X", List.of(lesson)));

  }

  private Long getLearnersCount() {
    return this.learnerPersistence
        .readAll()
        .count();
  }

}
