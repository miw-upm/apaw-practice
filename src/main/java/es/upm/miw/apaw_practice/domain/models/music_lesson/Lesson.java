package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.time.LocalDate;

public class Lesson {

  private Integer id;

  private LocalDate date;

  private Tutor tutor;

  private Learner learner;

  private Integer durationInHours;

  private MusicalInstrument musicalInstrument;

  public Lesson() {
    //Empty for framework
  }

  public Lesson(Integer id, LocalDate date, Tutor tutor, Learner learner, Integer durationInHours, MusicalInstrument musicalInstrument) {
    this.id = id;
    this.date = date;
    this.tutor = tutor;
    this.learner = learner;
    this.durationInHours = durationInHours;
    this.musicalInstrument = musicalInstrument;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Tutor getTutor() {
    return this.tutor;
  }

  public void setTutor(Tutor tutor) {
    this.tutor = tutor;
  }

  public Learner getLearner() {
    return this.learner;
  }

  public void setLearner(Learner learner) {
    this.learner = learner;
  }

  public Integer getDurationInHours() {
    return durationInHours;
  }

  public void setDurationInHours(Integer durationInHours) {
    this.durationInHours = durationInHours;
  }

  public MusicalInstrument getMusicalInstrument() {
    return this.musicalInstrument;
  }

  public void setMusicalInstrument(MusicalInstrument musicalInstrument) {
    this.musicalInstrument = musicalInstrument;
  }

  @Override
  public String toString() {
    return "Lesson{" +
        "id=" + this.id +
        ", date=" + this.date +
        ", tutor=" + this.tutor +
        ", learner=" + this.learner +
        ", durationInHours=" + this.durationInHours +
        ", musicalInstrument=" + this.musicalInstrument +
        '}';
  }
}
