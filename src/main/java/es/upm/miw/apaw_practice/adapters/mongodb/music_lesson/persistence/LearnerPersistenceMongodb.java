package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import java.util.List;
import java.util.stream.Stream;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.LearnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.MusicalInstrumentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LearnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LessonEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.MusicalInstrumentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Learner;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Lesson;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.LearnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("learnerPersistence")
public class LearnerPersistenceMongodb implements LearnerPersistence {

  private final LearnerRepository learnerRepository;

  private final MusicalInstrumentRepository musicalInstrumentRepository;

  @Autowired
  public LearnerPersistenceMongodb(LearnerRepository learnerRepository, MusicalInstrumentRepository musicalInstrumentRepository) {
    this.learnerRepository = learnerRepository;
    this.musicalInstrumentRepository = musicalInstrumentRepository;
  }

  @Override
  public Learner readByIdentityDocument(String identityDocument) {
    return this.learnerRepository.findByIdentityDocument(identityDocument)
        .orElseThrow(() -> new NotFoundException(" Learner identityDocument: " + identityDocument))
        .toLearner();
  }

  @Override
  public Stream<Learner> readAll() {
    return this.learnerRepository.findAll()
        .stream()
        .map(LearnerEntity::toLearner);
  }

  @Override
  public void delete(String identityDocument) {
    this.learnerRepository.deleteByIdentityDocument(identityDocument);
  }

  @Override
  public Learner update(Learner learner) {
    LearnerEntity learnerEntity = this.learnerRepository
        .findByIdentityDocument(learner.getIdentityDocument())
        .orElseThrow(() -> new NotFoundException("Learner identityDocument " + learner.getIdentityDocument()));

    List<LessonEntity> lessonEntities = learner.getLessons()
        .stream()
        .map(this::buildLessonEntityByModel)
        .toList();

    learnerEntity.setLessons(lessonEntities);

    return this.learnerRepository.save(learnerEntity)
        .toLearner();
  }

  private LessonEntity buildLessonEntityByModel(Lesson lesson) {
    List<MusicalInstrumentEntity> musicalInstrumentEntities =
        lesson.getMusicalInstruments()
            .stream()
            .map(musicalInstrument -> this.musicalInstrumentRepository
                .findByModel(musicalInstrument.getModel())
                .orElseThrow(() -> new NotFoundException("Musical Instrument: " + musicalInstrument.getModel())))
            .toList();

    return new LessonEntity(lesson.getDate(), lesson.getDurationInHours(),
        lesson.getFee(), musicalInstrumentEntities);
  }

}
