package es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music_lesson.entities.BranchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
class BranchRepositoryIT {

  @Autowired
  private BranchRepository branchRepository;

  @Test
  void testFindByCode() {
    Optional<BranchEntity> actualBranchEntity = this.branchRepository.findByCode("MAD-CTRH");
    assertTrue(actualBranchEntity.isPresent());
    BranchEntity actualBranch = actualBranchEntity.get();
    assertEquals("Serrano Street, 45", actualBranch.getAddress());
    assertEquals("913245678", actualBranch.getPhoneNumber());
  }

}
