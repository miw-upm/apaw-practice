package es.upm.miw.apaw_practice.domain.models.music_lesson;

public class MusicalInstrumentLevelUpdating {

  private String model;

  private String difficultyLevel;

  public MusicalInstrumentLevelUpdating() {
    //empty for framework
  }

  public MusicalInstrumentLevelUpdating(String model, String difficultyLevel) {
    this.model = model;
    this.difficultyLevel = difficultyLevel;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getDifficultyLevel() {
    return difficultyLevel;
  }

  public void setDifficultyLevel(String difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
  }

  @Override
  public String toString() {
    return "MusicalInstrumentLevelUpdating{" +
        "model='" + model + '\'' +
        ", difficultyLevel='" + difficultyLevel + '\'' +
        '}';
  }
}
