package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities;

import java.util.Objects;
import java.util.UUID;

import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MusicalInstrumentEntity {

  @Id
  private String id;

  @Indexed(unique = true)
  private String model;

  private String difficultyLevel;

  private String type;

  public MusicalInstrumentEntity() {
    //empty from framework
  }

  public MusicalInstrumentEntity(String model, String difficultyLevel, String type) {
    this.id = UUID.randomUUID().toString();
    this.model = model;
    this.difficultyLevel = difficultyLevel;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public MusicalInstrument toMusicalInstrument() {
    MusicalInstrument musicalInstrument = new MusicalInstrument();
    BeanUtils.copyProperties(this, musicalInstrument);
    return musicalInstrument;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    MusicalInstrumentEntity that = (MusicalInstrumentEntity) object;
    return Objects.equals(model, that.model);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(model);
  }

  @Override
  public String toString() {
    return "MusicalInstrumentEntity{" +
        "id='" + id + '\'' +
        ", model='" + model + '\'' +
        ", difficultyLevel='" + difficultyLevel + '\'' +
        ", type='" + type + '\'' +
        '}';
  }

}
