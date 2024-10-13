package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class MusicalInstrumentPersistenceMongodbIT {

  @Autowired
  private MusicalInstrumentPersistenceMongodb musicalInstrumentPersistenceMongodb;

  @Test
  void testReadByModel() {
    MusicalInstrument actualMusicalInstrument = this.musicalInstrumentPersistenceMongodb.readByModel("GBS-LP-234");
    assertEquals("Intermediate", actualMusicalInstrument.getDifficultyLevel());
    assertEquals("Electric Guitar", actualMusicalInstrument.getType());
  }

}
