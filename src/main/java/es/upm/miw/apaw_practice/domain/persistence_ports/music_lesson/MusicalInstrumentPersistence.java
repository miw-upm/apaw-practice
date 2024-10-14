package es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicalInstrumentPersistence {

  MusicalInstrument readByModel(String model);

  Stream<MusicalInstrument> readAll();

  MusicalInstrument create(MusicalInstrument musicalInstrument);

  boolean existsModel(String model);
}
