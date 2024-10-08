package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import es.upm.miw.apaw_practice.domain.models.music_lesson.Lesson;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LessonEntity {

  @Id
  private String id;

  private LocalDate date;

  private Integer durationInHours;

  private BigDecimal fee;

  private List<MusicalInstrumentEntity> musicalInstruments;

  public LessonEntity() {
    //empty from framework
  }

  public LessonEntity(LocalDate date, Integer durationInHours, BigDecimal fee,
      List<MusicalInstrumentEntity> musicalInstruments) {
    this.id = UUID.randomUUID().toString();
    this.date = date;
    this.durationInHours = durationInHours;
    this.fee = fee;
    this.musicalInstruments = musicalInstruments;
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

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public List<MusicalInstrumentEntity> getMusicalInstruments() {
    return musicalInstruments;
  }

  public void setMusicalInstruments(
      List<MusicalInstrumentEntity> musicalInstruments) {
    this.musicalInstruments = musicalInstruments;
  }

  public Lesson toLesson() {
    Lesson lesson = new Lesson();
    BeanUtils.copyProperties(this, lesson);
    return lesson;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    LessonEntity that = (LessonEntity) object;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "LessonEntity{" +
        "id='" + id + '\'' +
        ", date=" + date +
        ", durationInHours=" + durationInHours +
        ", fee=" + fee +
        ", musicalInstruments=" + musicalInstruments +
        '}';
  }

}
