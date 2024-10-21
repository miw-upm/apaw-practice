package es.upm.miw.apaw_practice.domain.models.music_lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LessonTest {

  @Test
  public void testBuilder() {
    LocalDate now = LocalDate.now();
    Integer durationInHours = 2;
    BigDecimal fee = BigDecimal.valueOf(18.15);
    List<MusicalInstrument> musicalInstruments = List.of(new MusicalInstrument("XX-DD-Q", "Beginner", "Piano"));

    Lesson expectedLesson = new Lesson(now, durationInHours, fee, musicalInstruments);

    Lesson actualLesson = Lesson.builder()
        .date(now)
        .durationInHours(durationInHours)
        .fee(fee)
        .musicalInstrument(musicalInstruments)
        .build();

    assertEquals(expectedLesson, actualLesson);
  }
}
