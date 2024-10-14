package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.MusicalInstrumentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class MusicalInstrumentRepositoryIT {

  @Autowired
  private MusicalInstrumentRepository musicalInstrumentRepository;

  @Test
  void testFindByModel() {
    Optional<MusicalInstrumentEntity> actualMusicalInstrumentEntity = this.musicalInstrumentRepository.findByModel("STW-PN-998");
    assertTrue(actualMusicalInstrumentEntity.isPresent());
    MusicalInstrumentEntity actualMusicalInstrument = actualMusicalInstrumentEntity.get();
    assertEquals("Advanced", actualMusicalInstrument.getDifficultyLevel());
    assertEquals("Grand Piano", actualMusicalInstrument.getType());
  }

}
