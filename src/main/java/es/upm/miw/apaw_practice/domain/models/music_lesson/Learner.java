package es.upm.miw.apaw_practice.domain.models.music_lesson;

import java.util.List;

public class Learner {

  private String identityDocument;

  private String name;

  private boolean isBeginner;

  private List<MusicalInstrument> musicalInstruments;

  public Learner() {
    //Empty for framework
  }

  public Learner(String identityDocument, String name, boolean isBeginner, List<MusicalInstrument> musicalInstruments) {
    this.identityDocument = identityDocument;
    this.name = name;
    this.isBeginner = isBeginner;
    this.musicalInstruments = musicalInstruments;
  }

  public String getIdentityDocument() {
    return this.identityDocument;
  }

  public void setIdentityDocument(String identityDocument) {
    this.identityDocument = identityDocument;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isBeginner() {
    return this.isBeginner;
  }

  public void setBeginner(boolean beginner) {
    this.isBeginner = beginner;
  }

  public List<MusicalInstrument> getMusicalInstruments() {
    return this.musicalInstruments;
  }

  public void setMusicalInstruments(List<MusicalInstrument> musicalInstruments) {
    this.musicalInstruments = musicalInstruments;
  }

  @Override
  public String toString() {
    return "Learner{" +
        "identityDocument='" + this.identityDocument + '\'' +
        ", name='" + this.name + '\'' +
        ", isBeginner=" + this.isBeginner +
        ", musicalInstruments=" + this.musicalInstruments +
        '}';
  }
}
