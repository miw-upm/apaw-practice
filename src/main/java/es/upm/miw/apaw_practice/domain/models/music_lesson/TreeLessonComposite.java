package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreeLessonComposite implements TreeLesson {

  private final LocalDate date;

  private final Integer durationInHours;

  private final BigDecimal fee;

  private final List<MusicalInstrument> musicalInstruments;

  private final List<TreeLesson> treeLessons;

  public TreeLessonComposite(LocalDate date, Integer durationInHours, BigDecimal fee, List<MusicalInstrument> musicalInstruments) {
    this.date = date;
    this.durationInHours = durationInHours;
    this.fee = fee;
    this.musicalInstruments = musicalInstruments;
    this.treeLessons = new ArrayList<>();
  }

  @Override
  public LocalDate getDate() {
    return this.date;
  }

  @Override
  public Integer getDurationInHours() {
    return this.durationInHours;
  }

  @Override
  public BigDecimal getFee() {
    return this.fee;
  }

  @Override
  public List<MusicalInstrument> getMusicalInstruments() {
    return this.musicalInstruments;
  }

  @Override
  public boolean isComposite() {
    return true;
  }

  @Override
  public void add(TreeLesson treeLesson) {
    this.treeLessons.add(treeLesson);
  }

  @Override
  public void remove(TreeLesson treeLesson) {
    this.treeLessons.remove(treeLesson);
  }

  @Override
  public List<TreeLesson> getLeafLessons() {
    return this.treeLessons;
  }
}
