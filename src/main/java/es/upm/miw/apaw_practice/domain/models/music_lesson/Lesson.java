package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Lesson {

  private LocalDate date;

  private Integer durationInHours;

  private BigDecimal fee;

  private List<MusicalInstrument> musicalInstruments;

  public Lesson() {
    //Empty for framework
  }

  public static LessonBuilders.Date builder() {
    return new Builder();
  }

  public Lesson(LocalDate date, Integer durationInHours, BigDecimal fee, List<MusicalInstrument> musicalInstruments) {
    this.date = date;
    this.durationInHours = durationInHours;
    this.fee = fee;
    this.musicalInstruments = musicalInstruments;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Integer getDurationInHours() {
    return durationInHours;
  }

  public void setDurationInHours(Integer durationInHours) {
    this.durationInHours = durationInHours;
  }

  public List<MusicalInstrument> getMusicalInstruments() {
    return musicalInstruments;
  }

  public void setMusicalInstruments(List<MusicalInstrument> musicalInstruments) {
    this.musicalInstruments = musicalInstruments;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public static class Builder implements LessonBuilders.Date, LessonBuilders.DurationInHours, LessonBuilders.Fee,
      LessonBuilders.MusicalInstruments, LessonBuilders.Builder {

    private final Lesson lesson;

    public Builder() {
      this.lesson = new Lesson();
    }

    @Override
    public LessonBuilders.DurationInHours date(LocalDate date) {
      this.lesson.date = date;
      return this;
    }

    @Override
    public LessonBuilders.Fee durationInHours(Integer durationInHours) {
      this.lesson.durationInHours = durationInHours;
      return this;
    }

    @Override
    public LessonBuilders.MusicalInstruments fee(BigDecimal fee) {
      this.lesson.fee = fee;
      return this;
    }

    @Override
    public LessonBuilders.Builder musicalInstrument(List<MusicalInstrument> musicalInstruments) {
      this.lesson.musicalInstruments = musicalInstruments;
      return this;
    }

    @Override
    public Lesson build() {
      return this.lesson;
    }
  }

  @Override
  public String toString() {
    return "Lesson{" +
        " date=" + date +
        ", durationInHours=" + durationInHours +
        ", musicalInstruments=" + musicalInstruments +
        ", fee=" + fee +
        '}';
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Lesson lesson = (Lesson) object;
    return Objects.equals(date, lesson.date) && Objects.equals(durationInHours, lesson.durationInHours)
        && Objects.equals(fee, lesson.fee) && Objects.equals(musicalInstruments, lesson.musicalInstruments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, durationInHours, fee, musicalInstruments);
  }
}
