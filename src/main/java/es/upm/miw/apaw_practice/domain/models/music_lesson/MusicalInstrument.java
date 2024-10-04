package es.upm.miw.apaw_practice.domain.models.music_lesson;

public class MusicalInstrument {

  private Integer id;

  private String name;

  private String difficultyLevel;

  public MusicalInstrument() {
    //Empty for framework
  }

  public MusicalInstrument(Integer id, String name, String difficultyLevel) {
    this.id = id;
    this.name = name;
    this.difficultyLevel = difficultyLevel;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDifficultyLevel() {
    return this.difficultyLevel;
  }

  public void setDifficultyLevel(String difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
  }

  @Override
  public String toString() {
    return "MusicalInstrument{" +
        "id=" + this.id +
        ", name='" + this.name + '\'' +
        ", difficultyLevel='" + this.difficultyLevel + '\'' +
        '}';
  }
}
