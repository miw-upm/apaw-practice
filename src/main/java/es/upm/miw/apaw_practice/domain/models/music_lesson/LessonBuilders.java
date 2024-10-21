package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LessonBuilders {

  interface Date {

    DurationInHours date(LocalDate date);
  }

  interface DurationInHours {

    Fee durationInHours(Integer durationInHours);
  }

  interface Fee {

    MusicalInstruments fee(BigDecimal fee);
  }

  interface MusicalInstruments {

    Builder musicalInstrument(List<MusicalInstrument> musicalInstruments);
  }

  interface Builder {

    Lesson build();
  }
}
