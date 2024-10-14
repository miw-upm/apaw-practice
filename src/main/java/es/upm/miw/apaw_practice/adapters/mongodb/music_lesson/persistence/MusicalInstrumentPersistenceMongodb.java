package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.MusicalInstrumentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.MusicalInstrumentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_lesson.MusicalInstrument;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.MusicalInstrumentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("musicalInstrumentPersistence")
public class MusicalInstrumentPersistenceMongodb implements MusicalInstrumentPersistence {

  private final MusicalInstrumentRepository musicalInstrumentRepository;

  @Autowired
  public MusicalInstrumentPersistenceMongodb(MusicalInstrumentRepository musicalInstrumentRepository) {
    this.musicalInstrumentRepository = musicalInstrumentRepository;
  }

  @Override
  public MusicalInstrument readByModel(String model) {
    return this.musicalInstrumentRepository.findByModel(model)
        .orElseThrow(() -> new NotFoundException(" MusicalInstrument model: " + model))
        .toMusicalInstrument();
  }

  @Override
  public Stream<MusicalInstrument> readAll() {
    return this.musicalInstrumentRepository.findAll()
        .stream()
        .map(MusicalInstrumentEntity::toMusicalInstrument);
  }

  @Override
  public MusicalInstrument create(MusicalInstrument musicalInstrument) {
    return this.musicalInstrumentRepository
        .save(new MusicalInstrumentEntity(musicalInstrument))
        .toMusicalInstrument();
  }

  @Override
  public boolean existsModel(String model) {
    return this.musicalInstrumentRepository
        .findByModel(model)
        .isPresent();
  }

  @Override
  public MusicalInstrument update(String model, MusicalInstrument musicalInstrument) {
    MusicalInstrumentEntity musicalInstrumentEntity = this.musicalInstrumentRepository
        .findByModel(musicalInstrument.getModel())
        .orElseThrow(() -> new NotFoundException("Musical Instrument : " + musicalInstrument.getModel()));

    musicalInstrumentEntity.fromMusicalInstrument(musicalInstrument);
    return this.musicalInstrumentRepository
        .save(musicalInstrumentEntity)
        .toMusicalInstrument();
  }
}
