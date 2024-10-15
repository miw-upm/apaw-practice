package es.upm.miw.apaw_practice.domain.services.music_lesson;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrumentLevelUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.MusicalInstrumentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicalInstrumentService {

  private final MusicalInstrumentPersistence musicalInstrumentPersistence;

  @Autowired
  public MusicalInstrumentService(MusicalInstrumentPersistence musicalInstrumentPersistence) {
    this.musicalInstrumentPersistence = musicalInstrumentPersistence;
  }

  public MusicalInstrument create(MusicalInstrument musicalInstrument) {
    this.assertModelNotExist(musicalInstrument.getModel());
    return musicalInstrumentPersistence.create(musicalInstrument);
  }

  public void assertModelNotExist(String model) {
    if (this.musicalInstrumentPersistence.existsModel(model)) {
      throw new ConflictException("Model code exist: " + model);
    }
  }

  public void updateDifficultyLevel(Stream<MusicalInstrumentLevelUpdating> levelUpdatingList) {
    levelUpdatingList
        .map(instrumentNewLevel -> {
          MusicalInstrument musicalInstrument = this.musicalInstrumentPersistence.readByModel(
              instrumentNewLevel.getModel());
          musicalInstrument.setDifficultyLevel(instrumentNewLevel.getDifficultyLevel());
          return musicalInstrument;
        })
        .forEach(musicalInstrument -> this.musicalInstrumentPersistence.update(
            musicalInstrument.getModel(), musicalInstrument));
  }
}
