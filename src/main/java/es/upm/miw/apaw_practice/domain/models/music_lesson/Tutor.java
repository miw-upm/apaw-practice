package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.math.BigDecimal;
import java.util.List;

public class Tutor {

  private String identityDocument;

  private String name;

  private BigDecimal hourlyRate;

  private List<MusicalInstrument> musicalInstruments;

  public Tutor() {
    //Empty for framework
  }

  public Tutor(String identityDocument, String name, BigDecimal hourlyRate, List<MusicalInstrument> musicalInstruments) {
    this.identityDocument = identityDocument;
    this.name = name;
    this.hourlyRate = hourlyRate;
    this.musicalInstruments = musicalInstruments;
  }

  public String getIdentityDocument() {
    return identityDocument;
  }

  public void setIdentityDocument(String identityDocument) {
    this.identityDocument = identityDocument;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getHourlyRate() {
    return this.hourlyRate;
  }

  public void setHourlyRate(BigDecimal hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  public List<MusicalInstrument> getMusicalInstruments() {
    return this.musicalInstruments;
  }

  public void setMusicalInstruments(List<MusicalInstrument> musicalInstruments) {
    this.musicalInstruments = musicalInstruments;
  }

  @Override
  public String toString() {
    return "Tutor{" +
        "identityDocument='" + this.identityDocument + '\'' +
        ", name='" + this.name + '\'' +
        ", hourlyRate=" + this.hourlyRate +
        ", musicalInstruments=" + this.musicalInstruments +
        '}';
  }
}
