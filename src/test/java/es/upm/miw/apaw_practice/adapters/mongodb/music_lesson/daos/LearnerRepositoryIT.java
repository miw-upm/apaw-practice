package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LearnerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class LearnerRepositoryIT {

  @Autowired
  private LearnerRepository learnerRepository;

  @Test
  void testFindByIdentityDocument() {
    Optional<LearnerEntity> actualLearnerEntity = this.learnerRepository.findByIdentityDocument("Z1254521");
    assertTrue(actualLearnerEntity.isPresent());
    LearnerEntity actualLearner = actualLearnerEntity.get();
    assertEquals("Juan Perez", actualLearner.getName());
    assertFalse(actualLearner.isBeginner());
    assertNotNull(actualLearner.getBranch());
    assertEquals("MAD-CTRH", actualLearner.getBranch().getCode());
    assertNotNull(actualLearner.getLessons());
    assertFalse(actualLearner.getLessons().isEmpty());
    assertEquals(2, actualLearner.getLessons().size());
  }

}
