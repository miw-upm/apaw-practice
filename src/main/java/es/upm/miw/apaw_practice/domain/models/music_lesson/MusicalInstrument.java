package es.upm.miw.apaw_practice.domain.models.music_lesson;

public class MusicalInstrument {

  private String model;

  private String difficultyLevel;

  private String type;

  public MusicalInstrument() {
    //Empty for framework
  }

  public MusicalInstrument(String model, String difficultyLevel, String type) {
    this.model = model;
    this.difficultyLevel = difficultyLevel;
    this.type = type;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "MusicalInstrument{" +
        "model='" + model + '\'' +
        ", difficultyLevel='" + difficultyLevel + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
