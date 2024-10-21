package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TreeLessonLeaf implements TreeLesson {

  private final Lesson lesson;

  public TreeLessonLeaf(Lesson lesson) {
    this.lesson = lesson;
  }

  @Override
  public LocalDate getDate() {
    return this.lesson.getDate();
  }

  @Override
  public Integer getDurationInHours() {
    return this.lesson.getDurationInHours();
  }

  @Override
  public BigDecimal getFee() {
    return this.lesson.getFee();
  }

  @Override
  public List<MusicalInstrument> getMusicalInstruments() {
    return this.lesson.getMusicalInstruments();
  }

  @Override
  public boolean isComposite() {
    return false;
  }

  @Override
  public void add(TreeLesson lessonComponent) {
    throw new UnsupportedOperationException("Unsupported operation in leaf");
  }

  @Override
  public void remove(TreeLesson lessonComponent) {
    // Do nothing because it is a leaf
  }

  @Override
  public List<TreeLesson> getLeafLessons() {
    return List.of();
  }

  @Override
  public String toString() {
    return "LessonLeaf{" +
        "lesson=" + lesson +
        '}';
  }
}
