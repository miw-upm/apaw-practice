package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.music_lesson.Learner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class LearnerPersistenceMongodbIT {

  @Autowired
  private LearnerPersistenceMongodb learnerPersistenceMongodb;

  @Test
  void testReadByIdentityDocument() {
    Learner actualLearner = this.learnerPersistenceMongodb.readByIdentityDocument("N1254552");
    assertEquals("Maria Perez", actualLearner.getName());
    assertTrue(actualLearner.isBeginner());
    assertNotNull(actualLearner.getBranch());
    assertEquals("MAD-PRDO", actualLearner.getBranch().getCode());
    assertNotNull(actualLearner.getLessons());
    assertFalse(actualLearner.getLessons().isEmpty());
    assertEquals(2, actualLearner.getLessons().size());
    assertTrue(actualLearner.getLessons().stream()
        .allMatch(lesson -> lesson.getFee().compareTo(BigDecimal.valueOf(18.75)) == 0));
  }

  @Test
  void testFindFeeSumByInstrumentDifficultyLevel() {
    BigDecimal expectedFeeSum = BigDecimal.valueOf(18.75);
    BigDecimal actualFeeSumByAdvancedDifficulty = this.learnerPersistenceMongodb.findFeeSumByInstrumentDifficultyLevel("Beginner");
    assertEquals(expectedFeeSum, actualFeeSumByAdvancedDifficulty);
  }

}
