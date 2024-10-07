package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Lesson {

  private String id;

  private LocalDate date;

  private Integer durationInHours;

  private BigDecimal fee;

  private List<MusicalInstrument> musicalInstrument;

  public Lesson() {
    //Empty for framework
  }

  public Lesson(String id, LocalDate date, Integer durationInHours, BigDecimal fee, List<MusicalInstrument> musicalInstrument) {
    this.id = id;
    this.date = date;
    this.durationInHours = durationInHours;
    this.fee = fee;
    this.musicalInstrument = musicalInstrument;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public List<MusicalInstrument> getMusicalInstrument() {
    return musicalInstrument;
  }

  public void setMusicalInstrument(List<MusicalInstrument> musicalInstrument) {
    this.musicalInstrument = musicalInstrument;
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
        "id='" + id + '\'' +
        ", date=" + date +
        ", durationInHours=" + durationInHours +
        ", musicalInstrument=" + musicalInstrument +
        ", fee=" + fee +
        '}';
  }
}
