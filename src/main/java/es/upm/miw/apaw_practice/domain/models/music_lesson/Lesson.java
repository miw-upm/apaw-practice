package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Lesson {

  private LocalDate date;

  private Integer durationInHours;

  private BigDecimal fee;

  private List<MusicalInstrument> musicalInstruments;

  public Lesson() {
    //Empty for framework
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

  @Override
  public String toString() {
    return "Lesson{" +
        ", date=" + date +
        ", durationInHours=" + durationInHours +
        ", musicalInstruments=" + musicalInstruments +
        ", fee=" + fee +
        '}';
  }
}
