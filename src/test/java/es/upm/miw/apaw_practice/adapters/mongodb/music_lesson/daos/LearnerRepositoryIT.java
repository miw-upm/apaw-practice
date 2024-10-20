package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.BranchEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.LearnerEntity;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class LearnerRepositoryIT {

  @Autowired
  private LearnerRepository learnerRepository;

  @Autowired
  private BranchRepository branchRepository;

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

  @Test
  void testFindByBranchId(){
    String branchId = this.branchRepository.findByCode("MAD-PRDO")
        .map(BranchEntity::getId)
        .orElse(StringUtils.EMPTY);

    String[] expectedLearnerIdentityDocuments = {"N1254552", "C1234567"};
    Stream<LearnerEntity> actualLearner = this.learnerRepository.findByBranchId(branchId);

    List<LearnerEntity> actualLearnerList = actualLearner.toList();
    assertFalse(branchId.isEmpty());
    assertFalse(actualLearnerList.isEmpty());
    actualLearnerList.forEach(learnerEntity -> {
      assertTrue(Arrays.stream(expectedLearnerIdentityDocuments)
          .anyMatch(identityDocument -> learnerEntity.getIdentityDocument().equals(identityDocument)));
    });
  }

}
