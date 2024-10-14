package es.upm.miw.apaw_practice.domain.services.music_lesson;

import es.upm.miw.apaw_practice.domain.persistence_ports.music_lesson.LearnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearnerService {

  private final LearnerPersistence learnerPersistence;

  @Autowired
  public LearnerService(LearnerPersistence learnerPersistence) {
    this.learnerPersistence = learnerPersistence;
  }

  public void delete(String identityDocument) {
    this.learnerPersistence.delete(identityDocument);
  }

}
