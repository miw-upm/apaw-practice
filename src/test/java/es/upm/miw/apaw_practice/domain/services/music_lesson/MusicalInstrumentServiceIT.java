package es.upm.miw.apaw_practice.domain.services.music_lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class MusicalInstrumentServiceIT {

  @Autowired
  private MusicalInstrumentService musicalInstrumentService;

  @Test
  void testCreate() {
    String model = "STW-PN-100";
    String difficultyLevel = "Advanced";
    String type = "Piano";
    MusicalInstrument musicalInstrumentInput = new MusicalInstrument(model, difficultyLevel, type);
    MusicalInstrument actualMusicalInstrument = this.musicalInstrumentService.create(musicalInstrumentInput);
    assertNotNull(actualMusicalInstrument);
    assertEquals(model, actualMusicalInstrument.getModel());
    assertEquals(difficultyLevel, actualMusicalInstrument.getDifficultyLevel());
    assertEquals(type, actualMusicalInstrument.getType());
  }

  @Test
  void testAssertModelNotExist() {
    assertThrows(ConflictException.class, () -> this.musicalInstrumentService.assertModelNotExist("STW-PN-998"));
  }

}
