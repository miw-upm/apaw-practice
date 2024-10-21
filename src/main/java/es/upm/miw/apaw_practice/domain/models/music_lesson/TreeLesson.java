package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TreeLesson {

  LocalDate getDate();

  Integer getDurationInHours();

  BigDecimal getFee();

  List<MusicalInstrument> getMusicalInstruments();

  boolean isComposite();

  void add(TreeLesson lessonComponent);

  void remove(TreeLesson lessonComponent);

  List<TreeLesson> getLeafLessons();

}
