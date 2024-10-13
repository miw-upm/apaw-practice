package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import java.util.stream.Stream;

import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos.LearnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LearnerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Learner;
import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.LearnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("learnerPersistence")
public class LearnerPersistenceMongodb implements LearnerPersistence {

  private final LearnerRepository learnerRepository;

  @Autowired
  public LearnerPersistenceMongodb(LearnerRepository learnerRepository) {
    this.learnerRepository = learnerRepository;
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
}
